package ru.job4j.collection;

import java.util.*;

public class LinkedArray<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;
    private int modCount;


    public void add(T model) {
        addLast(model);
    }

    void addLast(T element) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<T>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        T result = getNode(index).item;
        return result;
    }

    private Node<T> getNode(int index) {
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> result = new LinkedArrayItr();
        return result;
    }

    private class LinkedArrayItr implements Iterator<T> {
        private LinkedArray.Node<T> lastReturned;
        private LinkedArray.Node<T> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        public LinkedArrayItr() {
            next = (size > 0) ? getNode(0) : null;
            nextIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result;
            lastReturned = next;
            next = next.next;
            nextIndex++;
            result = lastReturned.item;
            return result;
        }
    }

    private static class Node<T> {
        T item;
        LinkedArray.Node<T> next;
        LinkedArray.Node<T> prev;

        Node(LinkedArray.Node<T> prev, T element, LinkedArray.Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
