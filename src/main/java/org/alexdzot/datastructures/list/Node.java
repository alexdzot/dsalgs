package org.alexdzot.datastructures.list;

public class Node<E> {

    private E element;

    private Node<E> next;

    public Node(E element) {
        this.element = element;
    }

    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> node) {
        this.next = node;
    }
}
