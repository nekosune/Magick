package org.cvpcs.bukkit.magickraft.runestruct;

public interface IRuneNodeDataParser {

    String getNodeTypeId();

    IRuneNode parseNodeData(RuneNodeParser parser, String txt);

}
