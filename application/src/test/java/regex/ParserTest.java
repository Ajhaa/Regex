package regex;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import regex.nfa.State;

public class ParserTest {

    @Test
    public void singleChar() {
        State s = parse("a");
        assertTrue(s.check("a", 0));
        assertFalse(s.check("b", 0));
    }

    @Test
    public void multipleCharacters() {
        State s = parse("abc");
        assertTrue(s.check("abc", 0));
        assertFalse(s.check("bfdasd", 0));
    }

    @Test
    public void unionWithTwo() {
        State s = parse("abc|dge");
        assertTrue(s.check("abc", 0));
        assertTrue(s.check("dge", 0));
        assertFalse(s.check("asdf", 0));
    }

    @Test
    public void unionWithMultiple() {
        State s = parse("ab|cde|efffg|homae");
        assertTrue(s.check("ab", 0));
        assertTrue(s.check("cde", 0));
        assertTrue(s.check("efffg", 0));
        assertTrue(s.check("homae", 0));
        assertFalse(s.check("asdgrgjnn", 0));
    }

    @Test
    public void simpleKleeneStar() {
        State s = parse("s*");
        assertTrue(s.check("", 0));
        assertTrue(s.check("s", 0));
        assertTrue(s.check("sss", 0));
        assertFalse(s.check("ssddfsa", 0));
    }

    @Test
    public void unionWithKleeneStar() {
        State s = parse("a*b*|d*c");
        assertTrue(s.check("", 0));
        assertTrue(s.check("aaa", 0));
        assertTrue(s.check("bbb", 0));
        assertTrue(s.check("aabbb", 0));
        assertTrue(s.check("dddc", 0));
        assertTrue(s.check("c", 0));
        assertTrue(s.check("dddc", 0));
        assertFalse(s.check("aaaddd", 0));
        assertFalse(s.check("ccccbb", 0));
    }

    @Test
    public void groupingWorksWithConcat() {
        State s = parse("a(bc)");
        assertTrue(s.check("abc", 0));
    }

    @Test
    public void groupingWithKleeneStar() {
        State s = parse("a(bc)*");
        assertTrue(s.check("a", 0));
        assertTrue(s.check("abc", 0));
        assertTrue(s.check("abcbc", 0));
        assertFalse(s.check("abbcc", 0));
    }

    @Test
    public void groupingWithUnion() {
        State s = parse("a(b|c)");
        assertTrue(s.check("ab", 0));
        assertTrue(s.check("ac", 0));
        assertFalse(s.check("a", 0));
        assertFalse(s.check("abc", 0));
    }

    @Test
    public void kleeneInGroupWithUnion() {
        State s = parse("a(b*|c)");
        assertTrue(s.check("ab", 0));
        assertTrue(s.check("a", 0));
        assertTrue(s.check("ac", 0));
        assertTrue(s.check("abbbbb", 0));
        assertFalse(s.check("c", 0));
        assertFalse(s.check("", 0));
        assertFalse(s.check("bbb", 0));
    }

    @Test
    public void nestedGroupingsWithUnionAndKleene() {
        State s = parse("a((x|y)*|b)");
        assertTrue(s.check("a", 0));
        assertTrue(s.check("ab", 0));
        assertTrue(s.check("ax", 0));
        assertTrue(s.check("ay", 0));
        assertTrue(s.check("axxx", 0));
        assertTrue(s.check("ayyy", 0));
        assertTrue(s.check("axyxxyxyx", 0));
        assertFalse(s.check("abb", 0));
        assertFalse(s.check("", 0));
        assertFalse(s.check("axb", 0));
        assertFalse(s.check("b", 0));

    }

    private State parse(String s) {
        return Parser.parse(s);
    }
}