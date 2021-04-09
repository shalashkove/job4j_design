package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void when2PeriodsThen2Records() throws IOException {
        Analizy analizy = new Analizy();
        String source = "./data/server1.log";
        File target = folder.newFile("target.txt");
        analizy.unavailable(source, target.getAbsolutePath());
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
    public void whenEptyFileThenNoRecords() throws IOException {
        Analizy analizy = new Analizy();
        String source = "./data/server2.log";
        File target = folder.newFile("target.txt");
        analizy.unavailable(source, target.getAbsolutePath());
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
    public void whenUnavailableOnlyThen1Record() throws IOException {
        Analizy analizy = new Analizy();
        String source = "./data/server3.log";
        File target = folder.newFile("target.txt");
        analizy.unavailable(source, target.getAbsolutePath());
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
    public void whenNoUnavailableThenNoRecords() throws IOException {
        Analizy analizy = new Analizy();
        String source = "./data/server4.log";
        File target = folder.newFile("target.txt");
        analizy.unavailable(source, target.getAbsolutePath());
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
    public void whenLastUnavailableThen1Record() throws IOException {
        Analizy analizy = new Analizy();
        String source = "./data/server5.log";
        File target = folder.newFile("target.txt");
        analizy.unavailable(source, target.getAbsolutePath());
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
