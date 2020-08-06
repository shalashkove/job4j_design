package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.it.EvenIt;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenZeroThenAIBExeption() {
        SimpleArray<String> simpleArray = new SimpleArray<>(0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenMinusThenAIBExeption() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(-1);
    }

    @Test
    public void whenAddThenAdded() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test");
        assertThat(simpleArray.get(0), is("Test"));
    }

    @Test
    public void whenSomeAddedThenRightAdded() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("Test1");
        simpleArray.add("Test2");
        assertThat(simpleArray.get(0), is("Test1"));
        assertThat(simpleArray.get(1), is("Test2"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenOverElementsThenAIBExeption() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("Test1");
        simpleArray.add("Test2");
        simpleArray.add("Test3");
    }

    @Test
    public void whenSetThenRightSetted() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("Test1");
        simpleArray.set(0, "Test2");
        assertThat(simpleArray.get(0), is("Test2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenOverElementsSetThenNSEExeption() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("Test1");
        simpleArray.add("Test2");
        simpleArray.set(2, "Test3");
    }

    @Test
    public void whenRemoveThenRightRemoved() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test1");
        simpleArray.add("Test2");
        simpleArray.add("Test3");
        simpleArray.remove(0);
        assertThat(simpleArray.get(0), is("Test2"));
        assertThat(simpleArray.get(1), is("Test3"));
        assertThat(simpleArray.get(2), is(nullValue()));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        SimpleArray<String> simpleArray = new SimpleArray<>(3);
        simpleArray.add("Test1");
        simpleArray.add("Test2");
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Test1"));
        assertThat(it.next(), is("Test2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("Test1");
        simpleArray.add("Test2");
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Test1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Test2"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }


}
