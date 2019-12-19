package com.example.journey_mate.model;

public class Trip {
    String user_id,trip_name,description,date;

    public Trip(String user_id, String trip_name, String description, String date) {
        this.user_id = user_id;
        this.trip_name = trip_name;
        this.description = description;
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
