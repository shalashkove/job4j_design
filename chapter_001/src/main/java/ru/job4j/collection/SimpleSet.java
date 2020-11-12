package ru.job4j.collection;

import java.util.*;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> simpleArray;

    public SimpleSet() {
        this.simpleArray = new SimpleArray<T>();
    }

    public boolean add(T model) {
        boolean result;
        if (checkDublicate(model)) {
            result = false;
        } else {
            simpleArray.add(model);
            result = true;
        }
        return result;
    }

    private boolean checkDublicate(T model) {
        boolean result = false;
        Iterator<T> itr = simpleArray.iterator();
        while (itr.hasNext()) {
            if (Objects.equals(itr.next(), model)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
