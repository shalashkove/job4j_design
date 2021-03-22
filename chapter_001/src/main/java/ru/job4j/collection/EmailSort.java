package ru.job4j.collection;

import java.util.*;

public class EmailSort {

    public Map<String, Set<String>> mergeUser(List<EmailUser> source) {
        Map<String, String> map = new HashMap<>();
        for (EmailUser user : source) {
            for (String email : user.getEmails()) {
                if (map.containsKey(email)) {
                    String name = map.get(email);
                    for (String transferEmail : user.getEmails()) {
                        map.put(transferEmail, name);
                    }
                    break;
                } else {
                    map.put(email, user.getUsername());
                }
            }
        }
        Map<String, Set<String>> result = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (result.containsKey(entry.getValue())) {
                result.get(entry.getValue()).add(entry.getKey());
            } else {
                Set<String> firstEntry = new HashSet<>();
                firstEntry.add(entry.getKey());
                result.put(entry.getValue(), firstEntry);
            }
        }
        return result;
    }
}
