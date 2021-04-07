package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void when2PeriodsThen2Records() {
        Analizy analizy = new Analizy();
        String source = "./data/server1.log";
        String target = "./data/unavailable1.csv";
        analizy.unavailable(source, target);
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(line -> result.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rsl = List.of("10:57:01;10:59:01",
                                    "11:01:02;11:02:02");
        assertThat(result, is(rsl));
    }

    @Test
    public void whenEptyFileThenNoRecords() {
        Analizy analizy = new Analizy();
        String source = "./data/server2.log";
        String target = "./data/unavailable2.csv";
        analizy.unavailable(source, target);
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(line -> result.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rsl = List.of();
        assertThat(result, is(rsl));
    }

    @Test
    public void whenUnavailableOnlyThen1Record() {
        Analizy analizy = new Analizy();
        String source = "./data/server3.log";
        String target = "./data/unavailable3.csv";
        analizy.unavailable(source, target);
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(line -> result.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rsl = List.of("10:56:01;11:02:02");
        assertThat(result, is(rsl));
    }

    @Test
    public void whenNoUnavailableThenNoRecords() {
        Analizy analizy = new Analizy();
        String source = "./data/server4.log";
        String target = "./data/unavailable4.csv";
        analizy.unavailable(source, target);
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(line -> result.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rsl = List.of();
        assertThat(result, is(rsl));
    }

    @Test
    public void whenLastUnavailableThen1Record() {
        Analizy analizy = new Analizy();
        String source = "./data/server5.log";
        String target = "./data/unavailable5.csv";
        analizy.unavailable(source, target);
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(line -> result.add(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rsl = List.of("11:02:02;11:02:02");
        assertThat(result, is(rsl));
    }
}
