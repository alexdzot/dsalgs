package org.alexdzot.datastructures.list;

import java.util.NoSuchElementException;

public class CircularlyLinkedList<E> {

    public static final String EMPTY_LIST_MSG = "list is empty";

    private Node<E> tail;

    private int size;

    /**
     * Moves the first element of the list to the end
     *
     * @return <code>true</code> if list order was changed
     */
    public boolean rotate() {
        if (size == 0 || size == 1) {
            return false;
        }
        tail = tail.getNext();
        return true;
    }

    public void addLast(E element) {
        Node<E> newTail = new Node<>(element);
        if (tail == null) {
            tail = newTail;
        }
        newTail.setNext(tail.getNext());
        tail.setNext(newTail);
        tail = newTail;
        size++;
    }

    public void addLastAlternative(E element) {
        addFirst(element);
        tail = tail.getNext();
    }

    public void addFirst(E element) {
        Node<E> newHead = new Node<>(element);
        if (isEmpty()) {
            tail = newHead;
        }
        newHead.setNext(tail.getNext());
        tail.setNext(newHead);
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MSG);
        }
        E removed = tail.getNext().getElement();
        if (size == 1) {
            tail = null;
        } else {
            tail.setNext(tail.getNext().getNext());
        }
        size--;
        return removed;
    }

    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException(EMPTY_LIST_MSG);
        }
        return tail.getElement();
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException(EMPTY_LIST_MSG);
        }
        if (size == 1) {
            return tail.getElement();
        }
        return tail.getNext().getElement();
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public int size() {
        return size;
    }
}
