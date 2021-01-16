package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenEmptyListsThenZeroResult() {
        List<Analize.User> previousList = new ArrayList<>();
        List<Analize.User> currentList = new ArrayList<>();
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(0));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(0));
    }

    @Test
    public void whenDifferentElementsThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(4, "Four"));
        previousList.add(new Analize.User(5, "Five"));
        previousList.add(new Analize.User(6, "Six"));
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(1, "One"));
        currentList.add(new Analize.User(2, "Two"));
        currentList.add(new Analize.User(3, "Three"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(3));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(3));
    }

    @Test
    public void whenDiffElementsThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(1, "One"));
        previousList.add(new Analize.User(2, "Two"));
        previousList.add(new Analize.User(3, "Three"));
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(4, "Four"));
        currentList.add(new Analize.User(5, "Five"));
        currentList.add(new Analize.User(6, "Six"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(3));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(3));
    }

    @Test
    public void whenNotCrossingElementsThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(1, "One"));
        previousList.add(new Analize.User(2, "Two"));
        previousList.add(new Analize.User(3, "Three"));
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(5, "Five"));
        currentList.add(new Analize.User(6, "Six"));
        currentList.add(new Analize.User(7, "Seven"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(3));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(3));
    }

    @Test
    public void when2Add2Delete1ChangeThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(1, "One"));
        previousList.add(new Analize.User(2, "Two"));
        previousList.add(new Analize.User(3, "Three"));
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(3, "OtherThree"));
        currentList.add(new Analize.User(5, "Five"));
        currentList.add(new Analize.User(6, "Six"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(2));
        assertThat(rsl.changed, is(1));
        assertThat(rsl.deleted, is(2));
    }

    @Test
    public void whenSameElementsThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(1, "One"));
        previousList.add(new Analize.User(2, "Two"));
        previousList.add(new Analize.User(3, "Three"));
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(3, "Three"));
        currentList.add(new Analize.User(2, "Two"));
        currentList.add(new Analize.User(1, "One"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(0));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(0));
    }

    @Test
    public void whenChangeNameThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(1, "One"));
        previousList.add(new Analize.User(2, "Two"));
        previousList.add(new Analize.User(3, "Three"));
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(3, "ThreeChange"));
        currentList.add(new Analize.User(2, "TwoChange"));
        currentList.add(new Analize.User(1, "One"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(0));
        assertThat(rsl.changed, is(2));
        assertThat(rsl.deleted, is(0));
    }

    @Test
    public void whenEmptyPreviousThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        List<Analize.User> currentList = new ArrayList<>();
        currentList.add(new Analize.User(4, "Four"));
        currentList.add(new Analize.User(5, "Five"));
        currentList.add(new Analize.User(6, "Six"));
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(3));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(0));
    }

    @Test
    public void whenEmptyCurrentThenRightResult() {
        List<Analize.User> previousList = new ArrayList<>();
        previousList.add(new Analize.User(1, "One"));
        previousList.add(new Analize.User(2, "Two"));
        previousList.add(new Analize.User(3, "Three"));
        List<Analize.User> currentList = new ArrayList<>();
        Analize analize = new Analize();
        Analize.Info rsl = analize.diff(previousList, currentList);
        assertThat(rsl.added, is(0));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(3));
    }
}
