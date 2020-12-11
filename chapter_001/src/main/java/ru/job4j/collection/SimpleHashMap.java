package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K, V>>  {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private int capacity;
    private float loadFactor;
    private Object[] table;
    private int modCount;

    public SimpleHashMap() {
        this.size = 0;
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.table = new Object[this.capacity];
        this.modCount = 0;
    }

    static class Node<K, V> {
        final K key;
        V value;
        SimpleHashMap.Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key)
                    && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }

    static int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h ^ (h >>> 16));
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (size >= capacity * loadFactor) {
            growTable();
        }
        result = insertNode(key, value);
        if (result) {
            size++;
            modCount++;
        }
        return result;
    }

    private boolean insertNode(K key, V value) {
        boolean result = false;
        int basket = findBasket(key);
        Node<K, V> linkNode = (Node<K, V>) table[basket];
        if (linkNode == null) {
            table[basket] = new Node<K, V>(key, value, null);
            result = true;
        } else {
            while (linkNode.getNext() != null) {
                if (Objects.equals(key, linkNode.getKey())) {
                    return result;
                }
                linkNode = linkNode.getNext();
            }
            Node<K, V> addedNode = new Node<K, V>(key, value, null);
            linkNode.next = addedNode;
            result = true;
        }
        return result;
    }


    private int findBasket(K key) {
        int result = SimpleHashMap.hash(key) & (capacity - 1);
        return result;
    }

    private void growTable() {
        int oldCapacity = capacity;
        Object[] oldTable = table;
        capacity = capacity * 2;
        table = new Object[this.capacity];
        size = 0;
        for (int index = 0; index != oldCapacity; index++) {
            Node<K, V> node = (Node<K, V>) oldTable[index];
            if (node != null && node.next == null) {
                insert(node.key, node.value);
            } else if (node != null && node.next != null) {
                insert(node.key, node.value);
                while (node.next != null) {
                    node = node.next;
                    insert(node.key, node.value);
                }
            }
        }
    }

    public V get(K key) {
        V result = null;
        int basket = findBasket(key);
        Node<K, V> varNode = (Node<K, V>) table[basket];
        if (varNode != null) {
            do {
                if (Objects.equals(key, varNode.getKey())) {
                    result = varNode.getValue();
                    break;
                }
                varNode = varNode.next;
            } while (varNode.next != null);
            if (Objects.equals(key, varNode.getKey())) {
                result = varNode.getValue();
            }
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        int basket = findBasket(key);
        Node<K, V> lastNode = null;
        Node<K, V> currentNode = (Node<K, V>) table[basket];
        if (currentNode != null) {
            if (currentNode.getNext() == null
                    && Objects.equals(key, currentNode.getKey())) {
                table[basket] = null;
                result = true;
                size--;
                modCount++;
            } else {
                while (currentNode.getNext() != null) {
                    if (Objects.equals(key, currentNode.getKey())) {
                        if (lastNode == null) {
                            table[basket] = currentNode.getNext();
                        } else {
                            lastNode.next = currentNode.getNext();
                        }
                        result = true;
                        size--;
                        modCount++;
                        break;
                    }
                    lastNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Node<K, V>> {
        boolean isFirstIteration = true;
        int cursor;
        int indexNode = -1;
        Node<K, V> currentNode = null; //(Node<K, V>) table[0];
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Node<K, V> next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            if (isFirstIteration) {
                while (indexNode != capacity) {
                    indexNode++;
                    currentNode = (Node<K, V>) table[indexNode];
                    if (currentNode != null) {
                        isFirstIteration = false;
                        break;
                    }
                }
            } else {
                if (currentNode.next != null) {
                    currentNode = currentNode.next;
                } else {
                    while (indexNode != capacity) {
                        indexNode++;
                        currentNode = (Node<K, V>) table[indexNode];
                        if (currentNode != null) {
                            break;
                        }
                    }
                }
            }
            cursor++;
            return currentNode;
        }
    }
}
