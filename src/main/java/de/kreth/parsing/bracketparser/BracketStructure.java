package de.kreth.parsing.bracketparser;

import java.util.Collections;
import java.util.List;

import de.kreth.parsing.bracketparser.StructureBuilder.Group;

public class BracketStructure {

	private final CharSequence text;
	private final List<Group> groups;

	BracketStructure(StructureBuilder builder) {
		this.text = builder.text;
		this.groups = Collections.unmodifiableList(builder.groups);
	}

	public CharSequence getText() {
		return text;
	}

	public int groupCount() {
		return groups.size();
	}

	public Group getGroup() {
		if (groups.isEmpty()) {
			return null;
		} else {
			return groups.get(0);
		}
	}

	static StructureBuilder builder(CharSequence text) {
		return new StructureBuilder(text);
	}

}
