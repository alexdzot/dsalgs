package org.alexdzot.datastructures.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    private Stack<String> sut;

    @BeforeEach
    void setUp() {
        sut = new ArrayStack<>(1);
    }

    @Test
    void cannotCreateStackWithCapacityEqualTo0() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sut = new ArrayStack<>(0));
        assertEquals("Capacity cannot be negative or zero", exception.getMessage());
    }

    @Test
    void cannotCreateStackWithNegativeCapacity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sut = new ArrayStack<>(-1));
        assertEquals("Capacity cannot be negative or zero", exception.getMessage());
    }

    @Test
    void stackHasDefaultCapacityWhenCreatedViaNonArgConstructor() {
        ArrayStack sut = new ArrayStack<>();

        assertEquals(16, sut.capacity());
    }

    @Test
    void stackHasACapacityPassedInConstructor() {
        ArrayStack sut = new ArrayStack<>(32);

        assertEquals(32, sut.capacity());
    }

    @Test
    void stackIsEmptyAfterCreation() {
        assertTrue(sut.isEmpty());
    }

    @Test
    void stackHasSize0AfterCreation() {
        assertEquals(0, sut.size());
    }

    @Test
    void topFromEmptyStackThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> sut.top());
        assertEquals("Stack is empty. Cannot top", exception.getMessage());
    }

    @Test
    void popFromEmptyStackThrowsException() {
        Exception exception = assertThrows(IllegalStateException.class, () -> sut.pop());
        assertEquals("Stack is empty. Cannot pop", exception.getMessage());
    }

    @Test
    void pushThrowsExceptionWhenStackIsFull() {
        sut.push("a");

        Exception exception = assertThrows(IllegalStateException.class, () -> sut.push("b"));
        assertEquals("Stack is full. Cannot push", exception.getMessage());
    }

    @Test
    void testPopReturnsLastPushedElement() {
        sut.push("a");

        assertEquals("a", sut.pop());
    }

    @Test
    void testTopReturnsLastPushedElement() {
        sut.push("a");

        assertEquals("a", sut.top());
    }

    @Test
    void isEmptyReturnsFalseWhenStackHasElements() {
        sut.push("a");

        assertFalse(sut.isEmpty());
    }

    @Test
    void sizeIncreasesAfterPush() {
        assertEquals(0, sut.size());

        sut.push("a");

        assertEquals(1, sut.size());
    }
}