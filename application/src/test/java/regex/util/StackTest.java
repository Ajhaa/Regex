package regex.util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.EmptyStackException;

public class StackTest {
    Stack<Integer> s;

    @Before
    public void setup() {
        s = new Stack<>();
    }

    @Test
    public void newStackSizeReturnsZero() {
        assertEquals(0, s.size());
    }

    @Test
    public void addingAndPoppingAffectsSize() {
        s.push(11);
        s.push(12);
        assertEquals(2, s.size());
        s.pop();
        s.pop();
        s.push(131);
        assertEquals(1, s.size());
    }

    @Test(expected = EmptyStackException.class)
    public void popThrowsEmptyStack() {
        s.pop();
    }

    @Test(expected = EmptyStackException.class)
    public void peekThrowsEmptyStack() {
        s.peek();
    }

    @Test
    public void itemCanBeAddedAndPopped() {
        s.push(11);
        assertEquals(new Integer(11), s.pop());
    }

    @Test
    public void popRemovesItem() {
        s.push(11);
        s.pop();
        assertEquals(0, s.size());
    }

    @Test
    public void peekReturnsButDoesntRemove() {
        s.push(11);
        s.push(12);
        assertEquals(new Integer(12), s.peek());
        assertEquals(new Integer(12), s.peek());
    }

    @Test
    public void isEmptyReturnsCorrect() {
        assertEquals(true, s.isEmpty());
        s.push(11);
        assertEquals(false, s.isEmpty());
        s.pop();
        assertEquals(true, s.isEmpty());
    }

    @Test
    public void correctOrderWithPopAndPeek() {
        s.push(1);
        s.push(3);
        s.push(2);
        s.push(7);
        s.push(-3);
        assertEquals(new Integer(-3), s.pop());
        assertEquals(new Integer(7), s.pop());
        assertEquals(new Integer(2), s.peek());
        assertEquals(new Integer(2), s.pop());
        assertEquals(new Integer(3), s.pop());
        assertEquals(new Integer(1), s.peek());
        assertEquals(new Integer(1), s.pop());
    }
}
