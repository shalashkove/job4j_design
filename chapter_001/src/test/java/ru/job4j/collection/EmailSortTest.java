package ru.job4j.collection;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EmailSortTest {

    @Test
    public void when2UsersThenRightResult() {
        List<EmailUser> lst = List.of(
                new EmailUser("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                new EmailUser("user2", List.of("foo@gmail.com", "ups@pisem.net")),
                new EmailUser("user3", List.of("xyz@pisem.net", "vasya@pupkin.com")),
                new EmailUser("user4", List.of("ups@pisem.net", "aaa@bbb.ru")),
                new EmailUser("user5", List.of("xyz@pisem.net"))
        );
        EmailSort emailSort = new EmailSort();
        Map<String, Set<String>> rsl = emailSort.mergeUser(lst);
        assertThat(rsl.size(), is(2));
    }

    @Test
    public void when3UsersThenRightResult() {
        List<EmailUser> lst = List.of(
                new EmailUser("user1", List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")),
                new EmailUser("user2", List.of("foo@gmail.com", "ups@pisem.net")),
                new EmailUser("user6", List.of("fgh@gmail.com")),
                new EmailUser("user7", List.of("fgh@gmail.com")),
                new EmailUser("user3", List.of("xyz@pisem.net", "vasya@pupkin.com")),
                new EmailUser("user4", List.of("ups@pisem.net", "aaa@bbb.ru")),
                new EmailUser("user8", List.of("fgh@gmail.com", "lkt@bbb.ru")),
                new EmailUser("user5", List.of("xyz@pisem.net"))
        );
        EmailSort emailSort = new EmailSort();
        Map<String, Set<String>> rsl = emailSort.mergeUser(lst);
        assertThat(rsl.size(), is(3));
    }

    @Test
    public void whenEmptyThenZeroResult() {
        List<EmailUser> lst = List.of();
        EmailSort emailSort = new EmailSort();
        Map<String, Set<String>> rsl = emailSort.mergeUser(lst);
        assertThat(rsl.size(), is(0));
    }
}
