package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentPresent = findBy(parent);
        if (parentPresent.isPresent()
                && findBy(child).isEmpty()) {
            Node<E> parentNode = parentPresent.get();
            parentNode.children.add(new Node<E>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> pred = x -> x.value.equals(value);
        Optional<Node<E>> result = findByPredicate(pred);
        return result;
    }

    public boolean isBinary() {
        Predicate<Node<E>> pred = x -> x.children.size() > 2;
        Optional<Node<E>> result = findByPredicate(pred);
        return result.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return result;
    }
}
