package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] data;
    int point;

    public EvenIt(final int[] numbers) {
        this.data = numbers;
        this.point = 0;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        int marker = this.point;
        while (marker < data.length) {
            if (data[marker] % 2 == 0) {
                result = true;
                break;
            }
            marker++;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = null;
        while (point < data.length) {
            if (data[point] % 2 == 0) {
                result = data[point];
                point++;
                break;
            }
            point++;
        }
        return result;
    }
}
