package ru.job4j.collection;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("name1", 1, new GregorianCalendar(1975, Calendar.JANUARY, 1));
        User user2 = new User("name1", 1, new GregorianCalendar(1975, Calendar.JANUARY, 1));
        Map<User, Object> mapa = Map.of(
                user1, new Object(),
                user2, new Object());
//        Map<User, Object> mapa = new HashMap<>();
//        mapa.put(user1, new Object());
//        mapa.put(user2, new Object());
        System.out.println(mapa);
    }
}
