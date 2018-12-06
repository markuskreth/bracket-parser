package de.kreth.parsing.bracketparser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class StructureBuilder {

	CharSequence text;
	List<Group> groups;
	Deque<GroupBuilder> builders;

	StructureBuilder(CharSequence text) {
		this.text = text;
		groups = new ArrayList<>();
		builders = new ArrayDeque<>();
	}

	public BracketStructure build() {
		if (text != null) {
			parse(this);
		}
		return new BracketStructure(this);
	}

	private void parse(StructureBuilder builder) {
		for (int i = 0; i < builder.text.length(); i++) {
			char ch = builder.text.charAt(i);
			for (BracketPair p : DefaultBracketPairs.INSTANCE.pairs) {
				if (p.startMatch(ch)) {
					builders.push(new GroupBuilder(p, i));
					break;
				}
				if (p.endMatch(ch) && builders.peek().enclosingPair.equals(p)) {
					GroupBuilder groupBuilder = builders.pop();
					groups.add(groupBuilder.setEndIndex(i));
				}
			}
		}
	}

	public class Group {

		private final int startIndex;
		private final int endIndex;

		private Group(int startIndex, int endIndex) {
			super();
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		public CharSequence getInner() {
			return text.subSequence(startIndex + 1, endIndex - 1);
		}

		public CharSequence getOuter() {
			return text.subSequence(startIndex, endIndex);
		}
	}

	class GroupBuilder {

		private final BracketPair enclosingPair;
		private final int startIndex;

		public GroupBuilder(BracketPair enclosingPair, int startIndex) {
			super();
			this.enclosingPair = enclosingPair;
			this.startIndex = startIndex;
		}

		public Group setEndIndex(int endIndex) {
			return new Group(startIndex, endIndex);
		}
	}
}
