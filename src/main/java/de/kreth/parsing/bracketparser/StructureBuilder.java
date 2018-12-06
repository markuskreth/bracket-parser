package de.kreth.parsing.bracketparser;

import java.util.ArrayList;
import java.util.List;

class StructureBuilder {

	CharSequence text;
	List<Group> groups;
	List<GroupBuilder> builders;

	StructureBuilder(CharSequence text) {
		this.text = text;
		groups = new ArrayList<>();
		builders = new ArrayList<>();
	}

	public BracketStructure build() {
		return new BracketStructure(this);
	}

	public class Group {

		private final BracketPair enclosingPair;
		private final int startIndex;
		private final int endIndex;

		public Group(BracketPair enclosingPair, int startIndex, int endIndex) {
			super();
			this.enclosingPair = enclosingPair;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

	}

	class GroupBuilder {

		private final BracketPair enclosingPair;
		private final int startIndex;
		private int endIndex;
		
		public GroupBuilder(BracketPair enclosingPair, int startIndex) {
			super();
			this.enclosingPair = enclosingPair;
			this.startIndex = startIndex;
		}
		
		public void setEndIndex(int endIndex) {
			this.endIndex = endIndex;
		}
	}
}
