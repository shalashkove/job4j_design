package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User() {
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("name1", 1, new GregorianCalendar(1975, Calendar.JANUARY, 1));
        User user2 = new User("name1", 1, new GregorianCalendar(1975, Calendar.JANUARY, 1));
        Map<User, Object> mapa = Map.of(
                user1, new Object(),
                user2, new Object());
        System.out.println(mapa);
    }
}
