package org.cvpcs.bukkit.magickraft;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RuneSetClassLoader extends URLClassLoader {

    private final RuneSetLoader mLoader;
    private final Map<String, Class<?>> mClasses = new HashMap<String, Class<?>>();

    public RuneSetClassLoader(final RuneSetLoader loader, final URL[] urls, final ClassLoader parent) {
        super(urls, parent);

        mLoader = loader;
    }

    @Override
    protected Class<?> findClass(String name)
            throws ClassNotFoundException {
        Class<?> result = mClasses.get(name);

        if(result == null) {
            result = mLoader.getClassByName(name);

            if(result == null) {
                result = super.findClass(name);

                if(result != null) {
                    mLoader.setClass(name, result);
                }
            }

            mClasses.put(name, result);
        }

        return result;
    }

    public Set<String> getClasses() {
        return mClasses.keySet();
    }
}
