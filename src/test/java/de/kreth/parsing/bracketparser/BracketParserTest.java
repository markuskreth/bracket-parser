package de.kreth.parsing.bracketparser;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
	void testDefault() {
		BracketStructure result = parser.parse("x(inner)y");
		assertNotNull(result);

		assertEquals(1, result.groupCount());
	}

}
