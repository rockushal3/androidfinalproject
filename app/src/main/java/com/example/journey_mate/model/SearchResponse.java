package com.example.journey_mate.model;

public class SearchResponse {
    User user_id;
    String _id,trip_name,date;

    public SearchResponse(User user_id, String _id, String trip_name, String date) {
        this.user_id = user_id;
        this._id = _id;
        this.trip_name = trip_name;
        this.date = date;
    }

    public User getUser_id() {
        return user_id;
    }

    public String get_id() {
        return _id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public String getDate() {
        return date;
    }
}
