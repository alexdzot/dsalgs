package org.alexdzot.datastructures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList<String> sut;

    @BeforeEach
    void setup() {
        sut = new LinkedList<>();
    }

    @Test
    void pushFrontToEmptyList() {
        assertEquals(0, sut.size());
        sut.pushFront("A");
        assertEquals(1, sut.size());
        assertEquals("A", sut.front());
    }

    @Test
    void pushFrontToListWithElements() {
        sut.pushFront("C");
        sut.pushFront("B");
        sut.pushFront("A");

        assertEquals(3, sut.size());
        assertEquals("A", sut.front());
    }

    @Test
    void testFrontOnEmptyListThrowsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.front());
        assertEquals("List is empty", exception.getMessage());
    }

    @Test
    void testFrontOnListWithElements() {
        sut.pushFront("B");
        assertEquals("B", sut.front());
        sut.pushFront("A");
        assertEquals("A", sut.front());
    }

    @Test
    void testIsEmptyOnEmptyList() {
        assertTrue(sut.isEmpty());
    }

    @Test
    void testIsEmptyOnListWithElements() {
        sut.pushFront("A");
        assertFalse(sut.isEmpty());
    }

    @Test
    void testPopFrontOnEmptyListThrowsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.popFront());
        assertEquals("Cannot pop from an empty list", exception.getMessage());
    }

    @Test
    void testPopFrontOnListWithElements() {
        sut.pushFront("A");
        assertEquals(1, sut.size());
        String actual = sut.popFront();
        assertEquals("A", actual);
        assertTrue(sut.isEmpty());
    }

    @Test
    void testBackOnEmptyListThrowsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.back());
        assertEquals("List is empty", exception.getMessage());
    }

    @Test
    void testBackOnListWitbOneElement() {
        sut.pushFront("Front and Back");

        assertEquals("Front and Back", sut.back());
        assertSame(sut.front(), sut.back());
    }

    @Test
    void testBackOnListWithManyElements() {
        sut.pushFront("D");
        sut.pushFront("C");
        sut.pushFront("B");
        sut.pushFront("A");

        assertEquals("D", sut.back());
    }

    @Test
    void testPushBackOnEmptyList() {
        sut.pushBack("A");

        assertEquals(1, sut.size());
        assertEquals("A", sut.back());
        assertEquals("A", sut.front());
        assertSame(sut.front(), sut.back());
    }

    @Test
    void testPushBackOnListWithElements() {
        sut.pushFront("C");
        sut.pushFront("B");
        sut.pushFront("A");

        sut.pushBack("D");

        assertEquals(4, sut.size());
        assertEquals("D", sut.back());
    }

    @Test
    void testPopBackOnEmptyListThrowsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> sut.popBack());
        assertEquals("List is empty", exception.getMessage());
    }

    @Test
    void testPopBackOnListWithOneElement() {
        sut.pushBack("Head & Tail");

        String actual = sut.popBack();

        assertEquals("Head & Tail", actual);
        assertEquals(0, sut.size());
        assertTrue(sut.isEmpty());
    }

    @Test
    void testPopBackOnListWithManyElements() {
        sut.pushBack("A");
        sut.pushBack("B");
        sut.pushBack("C");

        String actual = sut.popBack();

        assertEquals("C", actual);
        assertEquals(2, sut.size());
        assertEquals("B", sut.back());
    }

    @Test
    void testSearchOnEmptyList() {
        Node<String> actual = sut.search("x");
        assertNull(actual);
    }

    @Test
    void testSearchOnListWithoutGivenElement() {
        sut = LinkedList.of("A", "B", "C");

        Node<String> actual = sut.search("X");

        assertNull(actual);
    }

    @Test
    void testSearchOnListWithGivenElementInMiddle() {
        sut = LinkedList.of("A", "B", "C");

        Node<String> actual = sut.search("B");

        assertNotNull(actual);
        assertEquals("B", actual.getElement());
    }

    @Test
    void testSearchOnListWithGivenElementInHead() {
        sut = LinkedList.of("A", "B", "C");

        Node<String> actual = sut.search("A");

        assertNotNull(actual);
        assertEquals("A", actual.getElement());
    }

    @Test
    void testSearchOnListWithGivenElementInTail() {
        sut = LinkedList.of("A", "B", "C");

        Node<String> actual = sut.search("C");

        assertNotNull(actual);
        assertEquals("C", actual.getElement());
    }

    @Test
    void testContainsOnEmptyList() {
        assertFalse(sut.contains("x"));
    }

    @Test
    void testContainsOnListWithoutGivenElement() {
        sut = LinkedList.of("a", "b", "c");

        assertFalse(sut.contains("x"));
    }

    @Test
    void testContainsOnListWithGivenElement() {
        sut = LinkedList.of("x", "Y", "z");

        assertTrue(sut.contains("Y"));
    }

    @ParameterizedTest
    @MethodSource("argsProvider")
    void testToString(LinkedList<String> list, String expected) {
        assertEquals(expected, list.toString());
    }

    static Stream<Arguments> argsProvider() {
        return Stream.of(
                Arguments.of(LinkedList.of(), "[]"),
                Arguments.of(LinkedList.of("A"), "[A]"),
                Arguments.of(LinkedList.of("A", "B", "C"), "[A, B, C]")
        );
    }
}

