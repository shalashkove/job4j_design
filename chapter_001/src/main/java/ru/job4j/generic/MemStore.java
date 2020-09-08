package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int index = 0; index != mem.size(); index++) {
            if (id.equals(mem.get(index))) {
                mem.set(index, model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (T elem : mem) {
            if (id.equals(elem.getId())) {
                mem.remove(elem);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T elem: mem) {
            if (id.equals(elem.getId())) {
                result = elem;
                break;
            }
        }
        return result;
    }
}
