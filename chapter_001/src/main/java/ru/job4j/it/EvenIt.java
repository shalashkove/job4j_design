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
        while (point < data.length) {
            if (data[point] % 2 == 0) {
                result = true;
                break;
            }
            point++;
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
