package org.alexdzot.datastructures.list.doublylinked;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    public static final String EMPTY_LIST_MSG = "list is empty";

    private Node<E> header;

    private Node<E> trailer;

    private int size;

    public DoublyLinkedList() {
        header = new Node<>(null);
        trailer = new Node<>(null);
        header.setNext(trailer);
        trailer.setPrev(header);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    public E first() {
        errorIfEmpty();
        return header.getNext().getElement();
    }

    private void errorIfEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST_MSG);
        }
    }

    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    public E last() {
       errorIfEmpty();
       return trailer.getPrev().getElement();
    }

    public E removeFirst() {
        errorIfEmpty();
        return remove(header.getNext());
    }

    public E removeLast() {
        errorIfEmpty();
        return remove(trailer.getPrev());
    }

    private void addBetween(E element, Node<E> successor, Node<E> predecessor) {
        Node<E> node = new Node<>(element, successor, predecessor);
        successor.setNext(node);
        predecessor.setPrev(node);
        size++;
    }

    private E remove(Node<E> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
        return node.getElement();
    }

    private static class Node<E> {

        private E element;

        private Node<E> next;

        private Node<E> prev;

        public Node(E element) {
            this.element = element;
        }

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
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

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

}
