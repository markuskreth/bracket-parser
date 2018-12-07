package de.kreth.parsing.bracketparser.inner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import de.kreth.parsing.bracketparser.BracketPair;
import de.kreth.parsing.bracketparser.BracketStructure;

public class StructureBuilder {

	public CharSequence text;
	List<GroupBuilder> groups;
	Deque<GroupBuilder> builders;

	public StructureBuilder(CharSequence text) {
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
				if (p.endMatch(ch) && !builders.isEmpty() && builders.peek().enclosingPair.equals(p)) {
					GroupBuilder groupBuilder = builders.pop();
					groupBuilder.setEndIndex(i);
					groups.add(groupBuilder);
					break;
				}
				if (p.startMatch(ch)) {
					builders.push(new GroupBuilder(p, i));
					break;
				}
			}
		}
	}

	public class GroupBuilder {

		private final BracketPair enclosingPair;
		public final int startIndex;
		private int endIndex;

		public GroupBuilder(BracketPair enclosingPair, int startIndex) {
			super();
			this.enclosingPair = enclosingPair;
			this.startIndex = startIndex;
		}

		public void setEndIndex(int endIndex) {
			this.endIndex = endIndex;
		}

		public int getEndIndex() {
			return endIndex;
		}
	}

	public Stream<GroupBuilder> groupStream() {
		return groups.stream();
	}
}
