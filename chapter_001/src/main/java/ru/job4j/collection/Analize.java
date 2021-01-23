package ru.job4j.collection;

import java.util.*;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        List<User> listPrev = new ArrayList<>(previous);
        Map<Integer, User> mapCurr = new HashMap<>();
        current.forEach(el -> mapCurr.put(el.id, el));
        Iterator<User> itrPrev = previous.iterator();
        while (itrPrev.hasNext()) {
            User el = itrPrev.next();
            if (mapCurr.containsKey(el.id)) {
                if (!el.name.equals(mapCurr.get(el.id).name)) {
                    result.setChanged(result.getChanged() + 1);
                }
                mapCurr.remove(el.id);
                itrPrev.remove();
                listPrev.remove(el);
            }
        }
        result.setAdded(result.getAdded() + mapCurr.size());
        result.setDeleted(result.getDeleted() + listPrev.size());
        return result;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        public int getAdded() {
            return added;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public int getChanged() {
            return changed;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}
