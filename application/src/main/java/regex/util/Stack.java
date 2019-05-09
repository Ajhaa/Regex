package regex.util;

import java.util.EmptyStackException;

public class Stack<T> {
    private Node<T> top = null;
    private int size = 0;


    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }

        T ret = top.item;
        top = top.next;
        size--;
        return ret;
    }

    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        
        return top.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    private class Node<T> {
        T item;
        Node<T> next;


        public Node(T item) {
            this.item = item;
            this.next = null;
        }
    }
}