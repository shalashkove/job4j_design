package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        //String path = "./data/pair_without_comment.properties";
        String path = "c:\\projects\\job4j_design\\chapter_001\\src\\test\\java\\ru\\job4j\\io\\data\\pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }
}
