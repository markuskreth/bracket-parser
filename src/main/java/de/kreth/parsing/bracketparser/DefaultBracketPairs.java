package de.kreth.parsing.bracketparser;

enum DefaultBracketPairs {
	
	INSTANCE(new BracketPair[] {
			new BracketPair('(', ')')
			, new BracketPair('\'', '\'')
			, new BracketPair('\"', '\"')
			, new BracketPair('[', ']')
			, new BracketPair('{', '}')   
			});

	final BracketPair[] pairs;
	
	DefaultBracketPairs(BracketPair[] pairs) {
		this.pairs = pairs;
	}
}
