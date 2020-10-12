package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class LinkedArrayTest {

    @Test
    public void whenAddThenAdded() {
        LinkedArray<String> linkedArray = new LinkedArray<>();
        linkedArray.add("Test1");
        assertThat(linkedArray.get(0), is("Test1"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenZeroThenAIBExeption() {
        LinkedArray<String> linkedArray = new LinkedArray<>();
        linkedArray.get(0);
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        LinkedArray<String> linkedArray = new LinkedArray<>();
        linkedArray.add("Test1");
        linkedArray.add("Test2");
        Iterator<String> it = linkedArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Test1"));
        assertThat(it.next(), is("Test2"));
    }

    @Test
    public void thenNullElementsThenNotHasNext() {
        LinkedArray<String> linkedArray = new LinkedArray<>();
        Iterator<String> it = linkedArray.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void thenSomeElementsThenHasNext() {
        LinkedArray<String> linkedArray = new LinkedArray<>();
        linkedArray.add("Test1");
        linkedArray.add("Test2");
        Iterator<String> it = linkedArray.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }
}
