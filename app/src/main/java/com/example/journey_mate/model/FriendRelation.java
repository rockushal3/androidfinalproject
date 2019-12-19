package com.example.journey_mate.model;

public class FriendRelation {
    String user_id_1,User_id_2,Status;

    public FriendRelation(String user_id_1, String user_id_2, String status) {
        this.user_id_1 = user_id_1;
        User_id_2 = user_id_2;
        Status = status;
    }

    public String getUser_id_1() {
        return user_id_1;
    }

    public String getUser_id_2() {
        return User_id_2;
    }

    public String getStatus() {
        return Status;
    }
}
