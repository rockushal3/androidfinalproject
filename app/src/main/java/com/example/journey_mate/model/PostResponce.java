package com.example.journey_mate.model;

import java.util.Date;

public class PostResponce {
    Date date;
    String image,caption;
    User user_id;

    public PostResponce(Date date, String image, String caption, User user_id) {
        this.date = date;
        this.image = image;
        this.caption = caption;
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getCaption() {
        return caption;
    }

    public User getUser_id() {
        return user_id;
    }
}
