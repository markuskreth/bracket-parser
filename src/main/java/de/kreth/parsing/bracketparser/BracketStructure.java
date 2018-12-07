package de.kreth.parsing.bracketparser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.kreth.parsing.bracketparser.inner.StructureBuilder;

/**
 * Structure of CharSequence with Brackets.
 * <p>
 * Structures are ordered by position of the opening bracket.
 * <p>
 * Example:
 * <p>
 * Text: ab(c"de(f)g"[h])i
 * <table border="1">
 * <tr>
 * <th>index</th>
 * <th>outer content</th>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>(c"de(f)g"[h])</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>"de(f)g"</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>(f)</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>[h]</td>
 * </tr>
 * </table>
 * 
 * @author markus
 *
 */
public class BracketStructure {

	private final CharSequence text;
	private final List<Group> groups;

	public static BracketStructure parse(CharSequence text) {
		StructureBuilder builder = BracketStructure.builder(text);
		builder.parse();
		return new BracketStructure(builder);
	}

	BracketStructure(StructureBuilder builder) {
		this.text = builder.text;
		List<Group> tmpGroups = builder.groupStream().map(gb -> new Group(gb.startIndex, gb.getEndIndex()))
				.collect(Collectors.toList());
		tmpGroups.sort((a, b) -> Integer.compare(a.startIndex, b.startIndex));
		this.groups = Collections.unmodifiableList(tmpGroups);
	}

	/**
	 * Get Source.
	 * 
	 * @return The complete origin text
	 */
	public CharSequence getText() {
		return text;
	}

	/**
	 * Count of found top level {@link Group}s.
	 * 
	 * @return groupcount.
	 */
	public int groupCount() {
		return groups.size();
	}

	/**
	 * Get the first {@link Group}.<br />
	 * This is an conveniance alias for {@link #getGroup(int)} with parameter 0.
	 * 
	 * @return first group
	 */
	public Group getGroup() {
		if (groups.isEmpty()) {
			return null;
		} else {
			return groups.get(0);
		}
	}

	/**
	 * Get group at index.
	 * 
	 * @param index index of top level group
	 * @return group at index or null if index < {@link #groupCount()}
	 */
	public Group getGroup(int index) {
		if (index < groups.size()) {
			return groups.get(index);
		} else {
			return null;
		}
	}

	static StructureBuilder builder(CharSequence text) {
		return new StructureBuilder(text);
	}

	/**
	 * Part of the text, separated by brackets
	 * 
	 * @author markus
	 *
	 */
	public class Group {

		private final int startIndex;
		private final int endIndex;

		private Group(int startIndex, int endIndex) {
			super();
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		/**
		 * 
		 * @return the encapsulated part without the brackets.
		 */
		public CharSequence getInner() {
			return text.subSequence(startIndex + 1, endIndex);
		}

		/**
		 * 
		 * @return the encapsulated part including the sourrounding brackets.
		 */
		public CharSequence getOuter() {
			return text.subSequence(startIndex, endIndex + 1);
		}

		BracketStructure parent() {
			return BracketStructure.this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + endIndex;
			result = prime * result + ((text == null) ? 0 : text.hashCode());
			result = prime * result + startIndex;
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
			Group other = (Group) obj;
			if (endIndex != other.endIndex) {
				return false;
			} else if (!BracketStructure.this.equals(other.parent()))
				return false;
			if (startIndex != other.startIndex)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return getOuter().toString();
		}
	}

}
