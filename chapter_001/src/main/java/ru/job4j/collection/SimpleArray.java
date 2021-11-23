package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private int modCount;
    private int size;
    private int capacity;
    private Object[] container;

    public SimpleArray() {
        this.modCount = 0;
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        if (size == capacity) {
            grow();
        }
        container[size++] = model;
    }

    private void grow() {
        capacity = capacity * 2;
        container = Arrays.copyOf(container, capacity);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            lastRet = cursor;
            cursor++;
            return (T) container[lastRet];
        }
    }
}
