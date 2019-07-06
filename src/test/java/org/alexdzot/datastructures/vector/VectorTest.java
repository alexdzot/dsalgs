package org.alexdzot.datastructures.vector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VectorTest {

    private Vector<Integer> sut;

    @BeforeEach
    private void before() {
        sut = new Vector<>(2);
    }

    @Test
    @DisplayName("empty list after creation")
    public void listIsEmptyAfterCreation() {
        assertTrue(sut.isEmpty(), "list should be empty after creation");
    }

    @Test
    @DisplayName("list capacity increases two times when full")
    public void listCapacityIsIncreasedByFactorTwoWhenListIsFullAndPushHappens() {
        sut.push(1);
        sut.push(2);
        sut.push(3);
        assertEquals(4, sut.capacity(), "list must be increased when is full capacity");
    }

    @Test
    @DisplayName("list shrinks in half when size is 1/4 of capacity")
    public void listShrinksInTwoTimesWhenOneQuarterFull() {
        sut = new Vector<>(4);
        IntStream.range(1, 5).forEach(v -> sut.push(v));
        assertEquals(4, sut.size(), "list should contain 4 elements");
        for (int i = 0; i < 3; i++) {
            sut.pop();
        }
        assertEquals(2, sut.capacity(), "list should have 2 capacity");
        assertEquals(1, sut.size(), "size should be 1");
    }

    @Test
    public void removeFromEndOfTheList() {
        sut.push(1);
        sut.push(2);

        sut.remove(sut.size() - 1);

        assertEquals(1, sut.size(), "size doesn't match");
    }

    @Test
    @DisplayName("add value at specified index")
    public void addInsertValueForGivenIndex() {
        sut.push(0);
        sut.push(1);
        sut.push(3);

        sut.add(2, 2);

        assertEquals(2, sut.get(2));
        assertEquals(3, sut.get(3));
        assertEquals(4, sut.size());
    }

    @Test
    @DisplayName("add value to the beginning when list is empty")
    public void testPrependOnEmptyList() {
        sut.prepend(1);

        assertEquals(1, sut.size());
        assertEquals(1, sut.get(0));
    }

    @Test
    @DisplayName("add value to the beginning when list has some elements")
    public void testPrependOnListWithSomeElements() {
        sut.push(1);
        sut.push(2);

        sut.prepend(0);

        assertEquals(3, sut.size());
        assertEquals(0, sut.get(0));
    }
}