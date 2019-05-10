package regex.util;

import java.util.EmptyStackException;

/**
 * A linked list -based stack
 */
public class Stack<T> {
    private Node<T> top = null;
    private int size = 0;

    /**
     * Adds a new item to the top of the stack
     */
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Returns and removes the top element of the stack.
     * If the stack is empty, throws and EmptyStackException.
     */
    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }

        T ret = top.item;
        top = top.next;
        size--;
        return ret;
    }

    /**
     * Returns, but does not remove the top element of the stack.
     * If the stack is empty, throws and EmptyStackException.
     */
    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        
        return top.item;
    }

    /**
     * Returns the size of the stack
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the stack is empty, false otherwise;
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Wrapper for a single element in the Stack. Has a pointer to the next element.
     */
    private class Node<T> {
        T item;
        Node<T> next;


        public Node(T item) {
            this.item = item;
            this.next = null;
        }
    }
}