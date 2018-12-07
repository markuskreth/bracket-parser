package de.kreth.parsing.bracketparser.inner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import de.kreth.parsing.bracketparser.BracketPair;
import de.kreth.parsing.bracketparser.BracketStructure;
import de.kreth.parsing.bracketparser.BracketStructure.Group;

class DefaultBracketTests {

	static Stream<Arguments> bracketProvider() {
		return Stream.of(DefaultBracketPairs.INSTANCE.pairs).map(br -> Arguments.of(br));
	}

	@ParameterizedTest
	@MethodSource("bracketProvider")
	void testDefaultSimple(BracketPair pair) {
		String inner = "inner";
		String whole = pair.enclose(inner);
		BracketStructure result = BracketStructure.parse("x" + whole + "y");
		assertNotNull(result);

		assertEquals(1, result.groupCount());
		Group first = result.getGroup();
		assertNotNull(first);
		assertEquals(inner, first.getInner());
		assertEquals(whole, first.getOuter());
	}

	@ParameterizedTest
	@MethodSource("bracketProvider")
	void testDefaultEdges(BracketPair pair) {

		String inner = "inner";
		String whole = pair.enclose(inner);
		BracketStructure result = BracketStructure.parse("x" + whole);

		Group first = result.getGroup();
		assertEquals(inner, first.getInner());
		assertEquals(whole, first.getOuter());

		result = BracketStructure.parse(whole + "y");

		first = result.getGroup();
		assertEquals(inner, first.getInner());
		assertEquals(whole, first.getOuter());
	}

}
