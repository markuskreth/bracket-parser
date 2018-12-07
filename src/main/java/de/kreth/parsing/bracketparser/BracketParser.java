package de.kreth.parsing.bracketparser;

import de.kreth.parsing.bracketparser.inner.StructureBuilder;

public class BracketParser {

	public static void main(String[] args) {

	}

	public BracketStructure parse(CharSequence text) {
		StructureBuilder builder = BracketStructure.builder(text);
		return builder.build();
	}

}
