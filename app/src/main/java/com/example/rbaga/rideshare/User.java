package com.example.rbaga.rideshare;

public class User {

    private String userID;
    private String email;
    private String name;

    //Getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    //Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
