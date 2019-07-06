package org.alexdzot.datastructures.vector;

import java.util.Arrays;
import java.util.Objects;

public class Vector<E> {

    private static final int DEFAULT_CAPACITY = 16;

    private E[] arr;
    private int size;

    public Vector() {
        this(DEFAULT_CAPACITY);
    }

    public Vector(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
        arr = (E[]) new Object[capacity];
        size = 0;
    }

    public void push(E val) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        arr[size++] = val;
    }

    public E pop() {
        if (size == 0) {
            throw new IllegalStateException("List has no elements");
        }
        E result = arr[size - 1];
        arr[size - 1] = null;
        size--;
        if (size == arr.length / 4) {
            resize(arr.length / 2);
        }
        return result;
    }

    public E get(int i) {
        checkIndex(i);
        return arr[i];
    }

    public void set(int i, E val) {
        checkIndex(i);
        arr[i] = val;
    }

    public void prepend(E val) {
        if (isEmpty()) {
            push(val);
        } else {
            add(0, val);
        }
    }

    public void add(int i, E val) {
        checkIndex(i);
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        for (int j = size - 1; j >= i ; j--) {
            arr[j+1] = arr[j];
        }
        arr[i] = val;
        size++;

    }

    public E remove(int i) {
        checkIndex(i);
        E removed = arr[i];
        arr[i] = null;
        for (int j = i; j < size - 2; j++) {
            arr[j] = arr[j + 1];
        }
        size--;
        return removed;
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(i);
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(E val) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }


    private void resize(int capacity) {
        E[] newArr = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector<?> vector = (Vector<?>) o;
        return size == vector.size &&
                Arrays.equals(arr, vector.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }
}
