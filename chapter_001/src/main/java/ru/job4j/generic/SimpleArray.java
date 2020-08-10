package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private final T[] data;
    private int point;

    public SimpleArray(int size) {
        if (size <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.data = (T[]) new Object[size];
        this.point = 0;
    }

    public void add(T model) {
        if (!checkIndex(this.point)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.data[point++] = model;
    }

    public void set(int index, T model) {
        if (!checkIndex(index) || index >= this.point) {
            throw new NoSuchElementException();
        }
        this.data[index] = model;
    }

    public void remove(int index) {
        if (!checkIndex(index) || index >= this.point) {
            throw new NoSuchElementException();
        }
        if (index == point - 1) {
            this.data[index] = null;
            point--;
        } else {
            System.arraycopy(this.data, index + 1, this.data, index, this.point - index - 1);
            this.data[--point] = null;
        }
    }

    public T get(int index) {
        if (!checkIndex(index) || index >= this.point) {
            throw new NoSuchElementException();
        }
        return this.data[index];
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < this.data.length;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> result = new Iterator<T>() {
            private int pointer = -1;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (pointer + 1 < point) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[++pointer];
            }
        };
        return result;
    }
}
