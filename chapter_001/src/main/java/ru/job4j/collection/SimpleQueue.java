package ru.job4j.collection;


public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T result = null;
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        result = out.pop();
        return result;
    }

    public void push(T value) {
        in.push(value);
    }
}
