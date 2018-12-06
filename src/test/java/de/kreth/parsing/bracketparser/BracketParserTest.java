package de.kreth.parsing.bracketparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BracketParserTest {

	private BracketParser parser;

	@BeforeEach
	public void initParser() {
		parser = new BracketParser();
	}

	@Test
	void testInvalids() {
		BracketStructure result = parser.parse("");
		assertNotNull(result);
		assertEquals(0, result.groupCount());
		assertEquals("", result.getText());
		assertNull(result.getGroup());

		result = parser.parse(null);
		assertNotNull(result);
		assertEquals(0, result.groupCount());
		assertNull(result.getText());
		assertNull(result.getGroup());
	}

	@Test
	void testMultipleBrackets() {
		BracketStructure result = parser.parse("a(inner1)b(inner2)c");
		assertEquals(2, result.groupCount());
		assertEquals("(inner1)", result.getGroup().getOuter());
		assertEquals("(inner1)", result.getGroup(0).getOuter());
		assertEquals("(inner2)", result.getGroup(1).getOuter());

		result = parser.parse("a(b(inner)c)d");
		assertEquals(2, result.groupCount());
		assertEquals("(b(inner)c)", result.getGroup(0).getOuter());
		assertEquals("(inner)", result.getGroup(1).getOuter());

	}

	@Test
	void testMultipleMixedBrackets() {
		BracketStructure result = parser.parse("ab(c\"de(f)g\"[h])i");
		assertEquals(4, result.groupCount());
		assertEquals("(c\"de(f)g\"[h])", result.getGroup(0).getOuter());
		assertEquals("\"de(f)g\"", result.getGroup(1).getOuter());
		assertEquals("(f)", result.getGroup(2).getOuter());
		assertEquals("[h]", result.getGroup(3).getOuter());
	}
}
