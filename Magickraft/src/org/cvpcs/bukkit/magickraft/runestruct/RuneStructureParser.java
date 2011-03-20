package org.cvpcs.bukkit.magickraft.runestruct;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class RuneStructureParser {
	private RuneNodeParser mNodeParser;

	public RuneStructureParser() {
		mNodeParser = new RuneNodeParser();
		mNodeParser.addDataParser(RNAnything.getDataParser());
		mNodeParser.addDataParser(RNComplexAnd.getDataParser());
		mNodeParser.addDataParser(RNComplexNot.getDataParser());
		mNodeParser.addDataParser(RNComplexOr.getDataParser());
		mNodeParser.addDataParser(RNMaterial.getDataParser());
		mNodeParser.addDataParser(RNMaterialGroup.getDataParser());
		mNodeParser.addDataParser(RNTier.getDataParser());
	}

	public IRuneNode[][][] parseStructure(BufferedReader in) {
		String txt = "";

		String line;

		try {
			while((line = in.readLine()) != null) {
				txt += line.replaceAll("\\s", "") + "\n";
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		return parseStructure(txt);
	}

	private IRuneNode[][][] parseStructure(String txt) {
		ArrayList<IRuneNode[][]> slices = new ArrayList<IRuneNode[][]>();

		String slices_txt[] = txt.split("\n\n");

		for(String slice_txt : slices_txt) {
			if(slice_txt.length() > 0) {
				slices.add(parseSlice(slice_txt));
			}
		}

		return slices.toArray(new IRuneNode[][][]{ });
	}

	private IRuneNode[][] parseSlice(String txt) {
		ArrayList<IRuneNode[]> rows = new ArrayList<IRuneNode[]>();

		String rows_txt[] = txt.split("\n");

		for(String row_txt : rows_txt) {
			if(row_txt.length() > 0) {
				rows.add(parseRow(row_txt));
			}
		}

		return rows.toArray(new IRuneNode[][]{ });
	}

	private IRuneNode[] parseRow(String txt) {
		ArrayList<IRuneNode> nodes = new ArrayList<IRuneNode>();
		Stack<Character> s = new Stack<Character>();

		int start = 0;
		for(int i = 0; i < txt.length(); i++) {
			char c = txt.charAt(i);

			switch(c) {
			case '(':
				s.push(c);
				break;
			case ')':
				s.pop();

				if(s.size() == 0) {
					IRuneNode node = mNodeParser.parseRuneNode(txt.substring(start, i + 1));

					if(node != null) {
						nodes.add(node);
					}

					start = i + 1;
				}
				
				break;
			}
		}
		
		return nodes.toArray(new IRuneNode[]{ });
	}
}
