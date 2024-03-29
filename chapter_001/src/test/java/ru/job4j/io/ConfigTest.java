package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() throws Exception {
        String path = "./data/pair_without_comment1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongKeyThenIAE() throws Exception {
        String path = "./data/pair_without_comment2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongValueThenIAE() throws Exception {
        String path = "./data/pair_without_comment3.properties";
        Config config = new Config(path);
        config.load();
    }
}
