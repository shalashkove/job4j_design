package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    int point;
    int next = -1;

    public EvenIt(final int[] numbers) {
        this.data = numbers;
        this.point = -1;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (next != -1) {
            result = true;
        } else {
            point++;
            while (point < data.length) {
                if (data[point] % 2 == 0) {
                    result = true;
                    next = point;
                    break;
                }
                point++;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        point = next;
        next = -1;
        return data[point];
    }
}
