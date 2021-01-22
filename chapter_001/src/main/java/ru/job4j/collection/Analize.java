package ru.job4j.collection;

import java.util.*;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
//        Comparator<User> userComparator = new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.id - o2.id;
//            }
//        };
//        Collections.sort(previous, userComparator);
//        Collections.sort(current, userComparator);
//        Iterator<User> itrPrevious = previous.iterator();
//        Iterator<User> itrCurrent = current.iterator();
//        while (itrPrevious.hasNext() || itrCurrent.hasNext()) {
//            if (itrPrevious.hasNext() && !itrCurrent.hasNext()) {
//                itrPrevious.next();
//                result.setDeleted(result.getDeleted() + 1);
//            } else if (!itrPrevious.hasNext() && itrCurrent.hasNext()) {
//                itrCurrent.next();
//                result.setAdded(result.getAdded() + 1);
//            } else if (itrPrevious.hasNext() && itrCurrent.hasNext()) {
//                User prev = itrPrevious.next();
//                User curr = itrCurrent.next();
//                if (prev.id == curr.id && !prev.name.equals(curr.name)) {
//                    result.setChanged(result.getChanged() + 1);
//                } else if (prev.id < curr.id) {
//                    result.setDeleted(result.getDeleted() + 1);
//                    boolean isIn = true;
//                    while (itrPrevious.hasNext()) {
//                        prev = itrPrevious.next();
//                        if (prev.id < curr.id) {
//                            result.setDeleted(result.getDeleted() + 1);
//                        } else if (prev.id == curr.id) {
//                            if (!prev.name.equals(curr.name)) {
//                                result.setChanged(result.getChanged() + 1);
//                            }
//                            isIn = false;
//                            break;
//                        } else if (prev.id > curr.id) {
//                            result.setAdded(result.getAdded() + 1);
//                            result.setDeleted(result.getDeleted() + 1);  // ???
//                            isIn = false;
//                            break;
//                        }
//                    }
//                    if (isIn) {
//                        result.setAdded(result.getAdded() + 1);
//                    }
//                } else if (prev.id > curr.id) {
//                    result.setAdded(result.getAdded() + 1);
//                    boolean isIn = true;
//                    while (itrCurrent.hasNext()) {
//                        curr = itrCurrent.next();
//                        if (prev.id > curr.id) {
//                            result.setAdded(result.getAdded() + 1);
//                        } else if (prev.id == curr.id) {
//                            if (!curr.name.equals(prev.name)) {
//                                result.setChanged(result.getChanged() + 1);
//                            }
//                            isIn = false;
//                            break;
//                        } else if (prev.id < curr.id) {
//                            result.setDeleted(result.getDeleted() + 1);
//                            result.setAdded(result.getAdded() + 1);   // ???
//                            isIn = false;
//                            break;
//                        }
//                    }
//                    if (isIn) {
//                        result.setDeleted(result.getDeleted() + 1);
//                    }
//                }
//            }
//        }

        Map<Integer, User> mapPrev = new HashMap<>();
        previous.forEach(el -> mapPrev.put(el.id, el));
        Map<Integer, User> mapCurr = new HashMap<>();
        current.forEach(el -> mapCurr.put(el.id, el));
        Iterator<Map.Entry<Integer, User>> itrPrev = mapPrev.entrySet().iterator();
        while (itrPrev.hasNext()) {
            Map.Entry<Integer, User> pair = itrPrev.next();
            if (mapCurr.containsKey(pair.getKey())) {
                if (!pair.getValue().name.equals(mapCurr.get(pair.getKey()).name)) {
                    result.setChanged(result.getChanged() + 1);
                }
                mapCurr.remove(pair.getKey());
                itrPrev.remove();
                mapPrev.remove(pair.getKey());
            }
        }
        result.setAdded(result.getAdded() + mapCurr.size());
        result.setDeleted(result.getDeleted() + mapPrev.size());

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
