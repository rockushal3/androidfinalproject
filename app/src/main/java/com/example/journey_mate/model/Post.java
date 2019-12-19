package com.example.journey_mate.model;

public class Post {
    String user_id,caption,image,date,time;

    public Post(String user_id, String caption, String image, String date, String time) {
        this.user_id = user_id;
        this.caption = caption;
        this.image = image;
        this.date = date;
        this.time = time;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCaption() {
        return caption;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
