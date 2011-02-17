package org.cvpcs.bukkit.magickraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuneSetLoader {

    private static final Pattern[] FILE_FILTERS = new Pattern[] {
        Pattern.compile("\\.jar$"),
    };

    private static final Pattern INFO_PATTERN = Pattern.compile("^[ \\t]*([^ \\t=]+)[ \\t]*=[ \\t]*([^ \\t]+)[ \\t]*$");
    private static final String INFO_CLASSNAME = "className";

    private final Map<String, Class<?>> mClasses = new HashMap<String, Class<?>>();

    public RuneSet loadRuneSet(Magickraft plugin, File file)
            throws InvalidRuneSetException {
        RuneSet result = null;
        String runeSetClassName = null;

        if(!file.exists()) {
            throw new InvalidRuneSetException(new FileNotFoundException(String.format("%s does not exist", file.getPath())));
        }
        try {
            JarFile jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("runeset.info");

            if (entry == null) {
                throw new InvalidRuneSetException(new FileNotFoundException("Jar does not contain plugin.yml"));
            }

            InputStream stream = jar.getInputStream(entry);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            while((line = br.readLine()) != null) {
                // find our class name
                Matcher matcher = INFO_PATTERN.matcher(line);
                if(matcher.matches()) {
                    if(INFO_CLASSNAME.equals(matcher.group(1))) {
                        runeSetClassName = matcher.group(2);
                    }
                }
            }

            br.close();
            jar.close();
        } catch (IOException ex) {
            throw new InvalidRuneSetException(ex);
        }

        try {
            ClassLoader loader = new RuneSetClassLoader(this, new URL[]{file.toURI().toURL()}, getClass().getClassLoader());
            Class<?> jarClass = Class.forName(runeSetClassName, true, loader);
            Class<? extends RuneSet> runeSet = jarClass.asSubclass(RuneSet.class);
            Constructor<? extends RuneSet> constructor = runeSet.getConstructor(Magickraft.class);

            result = constructor.newInstance(plugin);
        } catch (Throwable ex) {
            throw new InvalidRuneSetException(ex);
        }

        return result;
    }

    public Pattern[] getRuneSetFileFilters() {
        return FILE_FILTERS;
    }

    public Class<?> getClassByName(final String name) {
        return mClasses.get(name);
    }

    public void setClass(final String name, final Class<?> clazz) {
        mClasses.put(name, clazz);
    }
}
