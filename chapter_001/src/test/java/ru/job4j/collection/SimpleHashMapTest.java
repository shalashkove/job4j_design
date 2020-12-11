package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMapTest {

    @Test
    public void whenAddThenAdded() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        map.insert(2, "test2");
        map.insert(3, "test3");
        map.insert(15, "test15");
        map.insert(16, "test16");
        map.insert(32, "test32");
        map.iterator();
        System.out.println(map.toString());
    }

    @Test
    public void whenAddThenGet() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        map.insert(2, "test2");
        map.insert(3, "test3");
        map.insert(15, "test15");
        map.insert(16, "test16");
        map.insert(32, "test32");
        String rsl = map.get(0);
        assertThat(rsl, is("test0"));
        rsl = map.get(2);
        assertThat(rsl, is("test2"));
        rsl = map.get(3);
        assertThat(rsl, is("test3"));
        rsl = map.get(15);
        assertThat(rsl, is("test15"));
        rsl = map.get(16);
        assertThat(rsl, is("test16"));
        rsl = map.get(32);
        assertThat(rsl, is("test32"));
    }

    @Test
    public void whenDeleteThenDeleted() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        map.insert(2, "test2");
        map.insert(3, "test3");
        map.insert(15, "test15");
        map.insert(16, "test16");
        map.insert(32, "test32");
        Boolean rsl = map.delete(0);
        assertThat(rsl, is(true));
        rsl = map.delete(0);
        assertThat(rsl, is(false));
        rsl = map.delete(16);
        assertThat(rsl, is(true));
    }

    @Test
    public void whenGrowCapacityThenGrowup() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        for (int i = 0; i != 12; i++) {
            map.insert(i, "test" + i);
        }
        map.insert(16, "test16");
        map.insert(32, "test32");
        map.insert(15, "test15");
        map.insert(31, "test31");
        map.insert(63, "test63");
        map.insert(14, "test14");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        for (int i = 0; i != 18; i++) {
            System.out.println(it.next());
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        map.insert(1, "test1");
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test
    public void whenIteratorThenIterate() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        map.insert(2, "test2");
        map.insert(3, "test3");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        it.next();
        it.next();
        String rsl = it.next().getValue();
        assertThat(rsl, is("test3"));
    }

    @Test
    public void when6AddThen6Iterate() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        map.insert(2, "test2");
        map.insert(3, "test3");
        map.insert(15, "test15");
        map.insert(16, "test16");
        map.insert(32, "test32");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        String rsl = it.next().getValue();
        assertThat(rsl, is("test0"));
        rsl = it.next().getValue();
        assertThat(rsl, is("test16"));
        rsl = it.next().getValue();
        assertThat(rsl, is("test32"));
        rsl = it.next().getValue();
        assertThat(rsl, is("test2"));
        rsl = it.next().getValue();
        assertThat(rsl, is("test3"));
        rsl = it.next().getValue();
        assertThat(rsl, is("test15"));
    }

    @Test
    public void whenAddDeleteThenFalse() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        map.delete(0);
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        boolean rsl = it.hasNext();
        assertThat(rsl, is(false));
    }

    @Test
    public void whenNotElementsThenFalse() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        boolean rsl = it.hasNext();
        assertThat(rsl, is(false));
    }

    @Test
    public void whenSomeElementsThenTrue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(0, "test0");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        boolean rsl = it.hasNext();
        assertThat(rsl, is(true));
    }
}
