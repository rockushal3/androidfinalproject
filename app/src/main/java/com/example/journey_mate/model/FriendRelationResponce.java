package com.example.journey_mate.model;

public class FriendRelationResponce {
    User user_id_1,user_id_2;
    String Status;

    public FriendRelationResponce(User user_id_1, User user_id_2, String status) {
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
        Status = status;
    }

    public User getUser_id_1() {
        return user_id_1;
    }

    public User getUser_id_2() {
        return user_id_2;
    }

    public String getStatus() {
        return Status;
    }
}
