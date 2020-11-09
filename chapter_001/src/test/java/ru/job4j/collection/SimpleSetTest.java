package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSetTest {

    @Test
    public void whenAddThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        String rsl = set.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        Iterator<String> it = set.iterator();
        set.add("second");
        it.next();
    }

    @Test
    public void whenMultiAddThenRightResult() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("fourth");
        set.add("fifth");
        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        String rsl = it.next();
        assertThat(rsl, is("fifth"));
    }
}
