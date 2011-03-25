package com.nekokittygames.bukkit.runestruct;

import com.nekokittygames.bukkit.runestruct.IRuneNode;
import com.nekokittygames.bukkit.runestruct.RuneNodeParser;

public interface IRuneNodeDataParser {

    String getNodeTypeId();

    IRuneNode parseNodeData(RuneNodeParser parser, String txt);

}
