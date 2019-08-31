package com.example.avocado1;

import java.util.List;

public class User {
    private String id;
    private String Email;
    private  String Password;
    private  String UserName;
    private List Prefrences;

    public User() {

    }

    public User(String id, String email, String password, String userName) {
        this.id = id;
        Email = email;
        Password = password;
        UserName = userName;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

}



