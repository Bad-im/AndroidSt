package com.example.myapplication4;

public class User {
    private String username;
    private String password;
    private String uniqueInfo;

    public User(String username, String password, String uniqueInfo) {
        this.username = username;
        this.password = password;
        this.uniqueInfo = uniqueInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUniqueInfo() {
        return uniqueInfo;
    }
}
