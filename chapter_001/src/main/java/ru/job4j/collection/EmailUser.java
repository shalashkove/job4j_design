package ru.job4j.collection;

import java.util.List;

public class EmailUser {
    private String username;
    private List<String> emails;

    public EmailUser(String username, List<String> emails) {
        this.username = username;
        this.emails = emails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void addEmail(String email) {
        if (this.emails != null && !emails.contains(email)) {
            emails.add(email);
        }
    }
}
