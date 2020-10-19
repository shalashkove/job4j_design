package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T result = null;
        T var;
        boolean inHasElement = true;
        while (inHasElement) {
            try {
                var = in.pop();
                out.push(var);
            } catch (NoSuchElementException e) {
                inHasElement = false;
            }
        }
        result = out.pop();
        boolean outHasElement = true;
        while (outHasElement) {
            try {
                var = out.pop();
                in.push(var);
            } catch (NoSuchElementException e) {
                outHasElement = false;
            }
        }
        return result;
    }

    public void push(T value) {
        in.push(value);
    }
}
