package com.example.journey_mate.model;

public class CheckList {
    String trip_id,checklistname,status;

    public CheckList(String trip_id, String checklistname, String status) {
        this.trip_id = trip_id;
        this.checklistname = checklistname;
        this.status = status;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public String getChecklistname() {
        return checklistname;
    }

    public String getStatus() {
        return status;
    }
}
