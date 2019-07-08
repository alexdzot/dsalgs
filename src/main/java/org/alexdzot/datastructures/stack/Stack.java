package org.alexdzot.datastructures.stack;

public interface Stack<E> {

    void push(E element);

    E pop();

    E top();

    boolean isEmpty();

    int size();
}
