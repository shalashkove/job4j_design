package ru.job4j.generic;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

//    Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода
//      данной структуры.

//    Объект должен принимать количество ячеек. Структура не должна быть динамической.
//            Примечание:
//    В методах, где используется индекс нужно делать валидацию. Индекс должен находиться в рамках добавленных элементов.
//      Например, у вас есть хранилище из 10 элементов. Вы добавили 3 элемента. Каким может быть индекс? [0, 2].
//          Для проверки индекса используйте метод Objects.checkIndex.
//

    private final T[] data;
    int point;

    public SimpleArray(int size) {
        if (size <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.data = (T[]) new Object[size];
        this.point = 0;
    }

    public void add(T model) {
        if (!checkIndex(this.point)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.data[point++] = model;
    }

    public void set(int index, T model) {
        if (!checkIndex(index)) {
            throw new NoSuchElementException();
        }
        this.data[index] = model;
    }

    public void remove(int index) {
        if (!checkIndex(index)) {
            throw new NoSuchElementException();
        }
        while (index < this.point - 1) {
            this.data[index] = this.data[index + 1];
            index++;
        }
        data[index] = null;
    }

    public T get(int index) {
        if (!checkIndex(index)) {
            throw new NoSuchElementException();
        }
        return this.data[index];
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < this.data.length;
    }

    @Override
    public Iterator<T> iterator() {
        //Iterator<T> result = Collections.emptyIterator();
        Iterator<T> result = new Iterator<T>() {
            private int pointer = -1;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if ((pointer + 1 < data.length) && (data[pointer + 1] != null)) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[++pointer];
            }
        };

        return result;
    }
}
