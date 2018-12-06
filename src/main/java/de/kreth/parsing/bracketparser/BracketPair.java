package de.kreth.parsing.bracketparser;

public class BracketPair {

	private char opening;
	private char closing;
	
	public BracketPair(char opening, char cloding) {
		super();
		this.opening = opening;
		this.closing = cloding;
	}

	public boolean startMatch(char ch) {
		return ch == opening;
	}

	public boolean endMatch(char ch) {
		return ch == closing;
	}
	
	@Override
	public String toString() {
		return "Bracket " + opening + "..." + closing;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + closing;
		result = prime * result + opening;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BracketPair other = (BracketPair) obj;
		if (closing != other.closing)
			return false;
		if (opening != other.opening)
			return false;
		return true;
	}
	
}
