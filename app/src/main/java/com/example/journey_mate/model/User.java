package com.example.journey_mate.model;

public class User {

    private String _id, name,password,token;

    public User(String _id, String name, String password,String token) {
        this._id = _id;
        this.name = name;
        this.password = password;
        this.token = token;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
