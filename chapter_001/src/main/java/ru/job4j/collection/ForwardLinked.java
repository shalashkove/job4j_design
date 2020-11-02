//package ru.job4j.collection;
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//@SuppressWarnings("checkstyle:WhitespaceAround")
//public class ForwardLinked<T> implements Iterable<T> {
//    private Node<T> head;
//
//    public void add(T value) {
//        Node<T> node = new Node<T>(value, null);
//        if (head == null) {
//            head = node;
//            return;
//        }
//        Node<T> tail = head;
//        while (tail.next != null) {
//            tail = tail.next;
//        }
//        tail.next = node;
//    }
//
//    public void addFirst(T value) {
//        Node<T> node = new Node<T>(value, null);
//        if (head == null) {
//            head = node;
//            return;
//        }
//        Node<T> buff = head;
//        head = node;
//        head.next = buff;
//    }
//
//    public T deleteFirst() {
//        T result = null;
//        if (head == null) {
//            throw new NoSuchElementException();
//        }
//        result = head.value;
//        Node<T> buff = head;
//        head = buff.next;
//        buff.next = null;
//        return result;
//    }
//
//    public T deleteLast() {
//        T result = null;
//        if (head == null) {
//            throw new NoSuchElementException();
//        }
//        if (head != null && head.next == null) {
//            result = head.value;
//            head = null;
//        } else {
//            Node<T> lastReturn = head;
//            Node<T> cursor = head;
//            while (cursor.next != null) {
//                lastReturn = cursor;
//                cursor = cursor.next;
//            }
//            result = cursor.value;
//            lastReturn.next = null;
//        }
//        return result;
//    }
//
//    public boolean isEmpty() {
//        return head == null;
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return new Iterator<T>() {
//            Node<T> node = head;
//
//            @Override
//            public boolean hasNext() {
//                return node != null;
//            }
//
//            @Override
//            public T next() {
//                if (!hasNext()) {
//                    throw new NoSuchElementException();
//                }
//                T value = node.value;
//                node = node.next;
//                return value;
//            }
//        };
//    }
//
//    private static class Node<T> {
//        T value;
//        Node<T> next;
//
//        public Node(T value, Node<T> next) {
//            this.value = value;
//            this.next = next;
//        }
//    }
//}

package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void revert() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode = head.next;
        while (nextNode != null) {
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
            nextNode = currentNode.next;
        }
        currentNode.next = previousNode;
        head = currentNode;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> buff = head;
        head = node;
        head.next = buff;
    }

    public T deleteFirst() {
        T result = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        result = head.value;
        Node<T> buff = head;
        head = buff.next;
        buff.next = null;
        return result;
    }

    public T deleteLast() {
        T result = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head != null && head.next == null) {
            result = head.value;
            head = null;
        } else {
            Node<T> lastReturn = head;
            Node<T> cursor = head;
            while (cursor.next != null) {
                lastReturn = cursor;
                cursor = cursor.next;
            }
            result = cursor.value;
            lastReturn.next = null;
        }
        return result;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}