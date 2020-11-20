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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return ((31 + name.hashCode() * 31) * 31 + children * 31) * 31 + birthday.hashCode() * 31;
    }

    public static void main(String[] args) {
        User user1 = new User("name1", 1, new GregorianCalendar(1975, Calendar.JANUARY, 1));
        User user2 = new User("name1", 1, new GregorianCalendar(1975, Calendar.JANUARY, 1));
        Object object1 = new Object();
        Object object2 = new Object();
        System.out.println("user1 = " + user1 + "   Object1 = " + object1);
        System.out.println("user2 = " + user2 + "   Object2 = " + object2);
//        Map<User, Object> mapa = Map.of(
//                user1, object1,
//                user2, object2);
        Map<User, Object> mapa = new HashMap<>();
        mapa.put(user1, object1);
        mapa.put(user2, object2);
        System.out.println(mapa);
    }
}
