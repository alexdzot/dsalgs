package org.alexdzot.datastructures.list;

import java.util.NoSuchElementException;

public class LinkedList<E> {

    private Node<E> head;

    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void pushFront(E val) {
        head = new Node<>(val, head);
        size++;

    }

    public E popFront() {
        if (head == null) {
            throw new NoSuchElementException("Cannot pop from an empty list");
        }
        E popped = head.getElement();
        head = head.getNext();
        size--;
        return popped;
    }

    public void pushBack(E val) {
        if (head == null) {
            head = new Node<>(val);
            size++;
            return;
        }

        Node<E> tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        tail.setNext(new Node<>(val));
        size++;
    }

    public E popBack() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        if (tail == head) {
            head = null;
            size--;
            return tail.getElement();
        }

        Node<E> newTail = head;
        while (newTail.getNext() != tail) {
            newTail = newTail.getNext();
        }
        newTail.setNext(null);

        size--;
        return tail.getElement();
    }

    public E front() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.getElement();
    }

    public E back() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> back = head;
        while (back.getNext() != null) {
            back = back.getNext();
        }
        return back.getElement();
    }


    public Node<E> search(E val) {
        Node<E> curr = head;
        for (int i = 0; i < size; i++, curr = curr.getNext()) {
            if (val.equals(curr.getElement())) {
                break;
            }
        }
        return curr;
    }

    public boolean contains(E val) {
        return search(val) != null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (head != null) {
            Node<E> current = head;
            builder.append(current.getElement());
            while (current.getNext() != null) {
                builder.append(", ");
                current = current.getNext();
                builder.append(current.getElement());
            }
        }
        builder.append("]");

        return builder.toString();
    }

    public static <E> LinkedList<E> of(E... keys) {
        LinkedList<E> result = new LinkedList<>();
        for (E e : keys) {
            result.pushBack(e);
        }
        return result;
    }
}
