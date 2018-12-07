package de.kreth.parsing.bracketparser.inner;

import de.kreth.parsing.bracketparser.BracketPair;

public enum DefaultBracketPairs {

	INSTANCE(new BracketPair[] { new BracketPair('(', ')'), new BracketPair('\'', '\''), new BracketPair('\"', '\"'),
			new BracketPair('[', ']'), new BracketPair('{', '}') });

	final BracketPair[] pairs;

	DefaultBracketPairs(BracketPair[] pairs) {
		this.pairs = pairs;
	}
}
