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
		BracketStructure result = parser.parse("a(inner1)b(inner2)");
		assertEquals(2, result.groupCount());
		assertEquals("(inner1)", result.getGroup().getOuter());
		assertEquals("(inner1)", result.getGroup(0).getOuter());
		assertEquals("(inner2)", result.getGroup(1).getOuter());

	}

}
