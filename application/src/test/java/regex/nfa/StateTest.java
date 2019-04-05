package regex.nfa;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class StateTest {

    private final char e = 'ʒ';
    private State f;

    @Before
    public void setup() {
        f = new State(e, e, null, null, true);
    }

    @Test
    public void acceptingStateWithEmptyStringAccepts() {
        assertTrue(f.check("", 0));
    }

    @Test
    public void acceptingStateRejectsIfCharactersLeft() {
        assertFalse(f.check("asd", 1));
    }

    @Test
    public void epsilonWithEmptyStringTransits() {
        State s = new State(e, e, f, null, false);
        assertTrue(s.check("", 0));
    }

    @Test
    public void correctTransitAccepts() {
        State s = new State('a', e, f, null, false);
        assertTrue(s.check("a", 0));
    }

    @Test
    public void invalidTransitRejects() {
        State s = new State('a', e, f, null, false);
        assertFalse(s.check("b", 0));
    }

    @Test
    public void branchingStatesAcceptsBoth() {
        State s = new State('a', 'b', f, f, false);
        assertTrue(s.check("a", 0));
        assertTrue(s.check("b", 0));
    }

    @Test
    public void branchWithEpislonAccepts() {
        State s = new State('a', e, f, f, false);
        assertTrue(s.check("a", 0));
        assertTrue(s.check("", 0));
    }

    // equivalent to regex a*b*
    @Test
    public void twoKleenes() {
        State a = new State('a', 'ʒ', null, null, false);
        State b = new State('ʒ', 'ʒ', a, null, false);

        a.setLeft(b);

        State d = new State('b', 'ʒ', null, f, false);
        State c = new State('ʒ', 'ʒ', d, null, false);

        a.setRight(c);
        d.setLeft(c);

        State s = new State('ʒ', 'ʒ', b, null, false);

        assertTrue(s.check("aaaaa", 0));
        assertTrue(s.check("aaabbb", 0));
        assertTrue(s.check("bbbb", 0));
        assertTrue(s.check("", 0));
        assertFalse(s.check("bbba", 0));
        assertFalse(s.check("ccc", 0));
    }

    // a*|ab*
    @Test
    public void unionAndKleenes() {
        State a = new State(e, e, null, null);
        State b = new State('a', e, a, f);
        a.setLeft(b);
        State d = new State(e, e, null, null);
        State c = new State('a', e, d, null);
        State r = new State('b', e, d, f);
        d.setLeft(r);

        State s = new State('ʒ', 'ʒ', a, c);

        assertTrue(s.check("aaaa", 0));
        assertTrue(s.check("abbbb", 0));
        assertFalse(s.check("bbb", 0));
        assertFalse(s.check("aabb", 0));
    }
}