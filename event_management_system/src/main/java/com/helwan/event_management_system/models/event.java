package com.helwan.event_management_system.models;


public class event {
    private int eventId;
    private String type;
    private String date;
    private String location;

    public event(){}

    public event(int eventId, String type, String date, String location) {
        this.eventId = eventId;
        this.type = type;
        this.date = date;
        this.location = location;
    }

    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}
