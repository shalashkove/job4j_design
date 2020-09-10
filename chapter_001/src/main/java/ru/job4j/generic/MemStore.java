package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }


    public int findIndex(String id) {
        int result = -1;
        for (int index = 0; index != mem.size(); index++) {
            if (id.equals(mem.get(index))) {
                result = index;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index != -1) {
            mem.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = this.findIndex(id);
        if (index != -1) {
            mem.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = this.findIndex(id);
        if (index != -1) {
            result = mem.get(index);
        }
        return result;
    }
}
