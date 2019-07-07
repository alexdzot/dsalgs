package org.alexdzot.datastructures.list.doublylinked;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    private DoublyLinkedList<String> sut;

    @BeforeEach
    void setUp() {
        sut = new DoublyLinkedList<>();
    }

    @Test
    void testFirstOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.first());
        assertEquals(DoublyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testFirst() {
        sut.addFirst("a");

        assertEquals("a", sut.first());
    }

    @Test
    void testAddFirstOnEmptyList() {
        sut.addFirst("A");

        assertFalse(sut.isEmpty());
        assertEquals(1, sut.getSize());
        assertEquals("A", sut.first());
    }

    @Test
    void testAddFirst() {
        sut.addFirst("a");

        assertEquals("a", sut.first());

        sut.addFirst("b");

        assertEquals("b", sut.first());
    }

    @Test
    void testAddLastOnEmptyList() {
        sut.addLast("z");

        assertFalse(sut.isEmpty());
        assertEquals("z", sut.last());
    }

    @Test
    void testAddLast() {
        sut.addFirst("a");

        sut.addLast("z");

        assertEquals("z", sut.last());
    }

    @Test
    void testLastOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.last());
        assertEquals(DoublyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testLast() {
        sut.addLast("z");

        assertEquals("z", sut.last());
    }

    @Test
    @DisplayName("first() and last() returns the same element when list has only element")
    void testLastAndFirstReturnsSameElement() {
        sut.addFirst("a");

        assertEquals("a", sut.first());
        assertEquals("a", sut.last());
        assertSame(sut.first(), sut.last());
    }

    @Test
    void testRemoveFirstOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.removeFirst());
        assertEquals(DoublyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testRemoveFirstWhenListHasOneElement() {
        sut.addFirst("a");

        String actual = sut.removeFirst();

        assertEquals("a", actual);
        assertTrue(sut.isEmpty());
    }

    @Test
    void testRemoveFirst() {
        sut.addFirst("a");
        sut.addLast("b");
        sut.addLast("c");

        String actual = sut.removeFirst();

        assertEquals("a", actual);
        assertEquals("b", sut.first());
    }

    @Test
    void testRemoveLastOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.removeLast());
        assertEquals(DoublyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testRemoveLastWhenListHasOneElement() {
        sut.addLast("a");

        String actual = sut.removeLast();

        assertEquals("a", actual);
        assertTrue(sut.isEmpty());
    }

    @Test
    void testRemoveLast() {
        sut.addLast("b");
        sut.addLast("c");

        String actual = sut.removeLast();

        assertEquals("c", actual);
        assertEquals("b", sut.last());
    }
}