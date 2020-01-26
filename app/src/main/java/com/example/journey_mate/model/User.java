package com.example.journey_mate.model;

public class User {
    String _id, name, address, phone, gender, dob, email, password, token,coverimage,image;

    public User(String _id, String name, String address, String phone, String gender, String dob, String email, String password,String token,String coverimage,String image) {
        this._id = _id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.token = token;
        this.coverimage=coverimage;
        this.image=image;
    }

    public User(String name, String address, String phone, String gender, String dob, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
    }

    public User(String name, String address, String phone, String gender, String dob, String email, String password, String token, String coverimage, String image) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.token = token;
        this.coverimage=coverimage;
        this.image=image;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getCoverimage() {
        return coverimage;
    }

    public String getImage() {
        return image;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
