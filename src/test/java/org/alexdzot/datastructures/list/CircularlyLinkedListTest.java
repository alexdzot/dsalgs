package org.alexdzot.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularlyLinkedListTest {

    private CircularlyLinkedList<Integer> sut;

    @BeforeEach
    void setUp() {
        sut = new CircularlyLinkedList<>();
    }

    @Test
    void testRotateWhenListIsEmpty() {
        assertFalse(sut.rotate());
    }

    @Test
    void testRotateWhenListWithOneElement() {
        sut.addLast(1);

        assertFalse(sut.rotate());
    }

    @Test
    void testRotate() {
        sut.addLast(1);
        sut.addLast(2);
        sut.addLast(3);

        sut.rotate();

        assertEquals(2, sut.getFirst());
        assertEquals(1, sut.getLast());

        sut.rotate();

        assertEquals(2, sut.getLast());
        assertEquals(3, sut.getFirst());

        sut.rotate();

        assertEquals(3, sut.getLast());
        assertEquals(1, sut.getFirst());
    }

    @Test
    void testAddLast() {
        sut.addLast(1);

        assertFalse(sut.isEmpty());
        assertEquals(1, sut.size());
        assertEquals(1, sut.getLast());
    }

    @Test
    void testGetLastOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.getLast());
        assertEquals(CircularlyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testGetLast() {
        sut.addLast(1);

        assertEquals(1, sut.getLast());
    }

    @Test
    void testGetFirstOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.getFirst());
        assertEquals(CircularlyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testGetFirstOnListWithOneElement() {
        sut.addLast(1);

        assertEquals(1, sut.getLast());
        assertEquals(1, sut.getFirst());
        assertSame(sut.getLast(), sut.getFirst());
    }

    @Test
    void testGetFirst() {
        sut.addLast(1);
        sut.addLast(2);
        sut.addLast(3);

        assertEquals(1, sut.getFirst());
    }

    @Test
    void testAddFirstOnEmptyList() {
        sut.addFirst(10);

        assertEquals(10, sut.getFirst());
        assertEquals(10, sut.getLast());
    }

    @Test
    void testAddFirst() {
        sut.addLast(10);
        sut.addLast(100);

        sut.addFirst(1);

        assertEquals(1, sut.getFirst());
    }


    @Test
    void testAddLastAlternative() {
        sut.addFirst(10);
        sut.addFirst(1);

        sut.addLastAlternative(100);

        assertEquals(100, sut.getLast());
    }

    @Test
    void testRemoveFirstOnEmptyList() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.removeFirst());
        assertEquals(CircularlyLinkedList.EMPTY_LIST_MSG, exception.getMessage());
    }

    @Test
    void testRemoveFirstOnListWithOneElement() {
        sut.addFirst(1);

        Integer actual = sut.removeFirst();

        assertEquals(1, actual);
        assertTrue(sut.isEmpty());
    }

    @Test
    void testRemoveFirst() {
        sut.addLast(1);
        sut.addLast(2);
        sut.addFirst(0);

        Integer actual = sut.removeFirst();

        assertEquals(0, actual);
        assertEquals(2, sut.size());
        assertEquals(1, sut.getFirst());
    }
}