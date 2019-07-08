package org.alexdzot.datastructures.stack;

public class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 16;

    private E[] arr;

    private int size;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be negative or zero");
        }
        arr = (E[]) new Object[capacity];
    }

    @Override
    public void push(E element) {
        if (size == arr.length) {
            throw new IllegalStateException("Stack is full. Cannot push");
        }
        arr[size++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop");
        }
        E removed = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot top");
        }
        return arr[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return arr.length;
    }
}
