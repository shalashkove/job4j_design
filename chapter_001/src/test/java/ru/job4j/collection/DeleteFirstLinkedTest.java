package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DeleteFirstLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenFindLastThenLast() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        Iterator<Integer> it = linked.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is(3));
        linked.deleteLast();
        it = linked.iterator();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenDelLastThenRightResult() {
        ForwardLinked<String> linked = new ForwardLinked<>();
        linked.add("Test1");
        linked.add("Test2");
        linked.add("Test3");
        String result = linked.deleteLast();
        assertThat(result, is("Test3"));
    }

    @Test
    public void whenSingleAddThenRightLastDel() {
        ForwardLinked<String> linked = new ForwardLinked<>();
        linked.add("Test1");
        String result = linked.deleteLast();
        assertThat(result, is("Test1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastThenNSEException() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteLast();
        linked.deleteLast();
    }
}
