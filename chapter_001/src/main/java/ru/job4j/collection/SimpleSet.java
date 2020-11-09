package ru.job4j.collection;

import java.util.*;

public class SimpleSet<T> implements Iterable<T> {

    private final static int DEFAULT_CAPACITY = 3;
    private int modCount;
    private int size;
    private int capacity;
    private Object[] container;

    public SimpleSet() {
        this.modCount = 0;
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public boolean add(T model) {
        boolean result;
        if (checkDublicate(model)) {
            result = false;
        } else {
            modCount++;
            if (size == capacity) {
                grow();
            }
            container[size++] = model;
            result = true;
        }
        return result;
    }

    private void grow() {
        capacity = capacity * 2;
        container = Arrays.copyOf(container, capacity);
    }

    private boolean checkDublicate(T model) {
        boolean result = false;
        for (int i = 0; i != size; i++) {
            if (container[i].equals(model)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleSet.Itr();
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
