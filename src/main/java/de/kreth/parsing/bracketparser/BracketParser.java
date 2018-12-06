package de.kreth.parsing.bracketparser;

public class BracketParser {

	public static void main(String[] args) {

	}

	public BracketStructure parse(CharSequence text) {
		StructureBuilder builder = BracketStructure.builder(text);
		return builder.build();
	}

}
