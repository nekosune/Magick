package org.cvpcs.bukkit.magickraft.runestruct;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuneNodeParser {
	private static final Pattern NODE_PATTERN = Pattern.compile("^\\((\\w+)(:.+)?\\)$");

	private HashMap<String, IRuneNodeDataParser> mNodeTypes;

	public RuneNodeParser() {
		mNodeTypes = new HashMap<String, IRuneNodeDataParser>();
	}

	public void addDataParser(IRuneNodeDataParser parser) {
		mNodeTypes.put(parser.getNodeTypeId(), parser);
	}

	public IRuneNode parseRuneNode(String txt) {
		// parse out our rune node
		Matcher matcher = NODE_PATTERN.matcher(txt);

		if(matcher.find()) {
			String id = matcher.group(1);
			String data = matcher.group(2);

			// format the data properly
			if(data != null && data.length() > 1) {
				data = data.substring(1);
			} else {
				data = "";
			}

			if(mNodeTypes.containsKey(id)) {
				return mNodeTypes.get(id).parseNodeData(this, data);
			}
		}

		return null;
	}
}
