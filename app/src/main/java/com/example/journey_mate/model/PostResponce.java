package com.example.journey_mate.model;

import java.util.Date;

public class PostResponce {
    Date date;
    String _id,image,caption;
    User user_id;

    public PostResponce(Date date, String _id, String image, String caption, User user_id) {
        this.date = date;
        this._id = _id;
        this.image = image;
        this.caption = caption;
        this.user_id = user_id;
    }

    public String get_id() {
        return _id;
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
