package com.helwan.event_management_system.models;


public class Event {
    private int eventId;
    private String type;
    private String date;
    private String location;
    private int guestCount;
    private String details ;

    public Event(){}

    public Event(int eventId, String type, String date, String location,int guestCount,String details ) {
        this.eventId = eventId;
        this.type = type;
        this.date = date;
        this.location = location;
        this.guestCount =guestCount ;
        this.details=details;
    }

    public int guestCount() {
        return  guestCount;
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

    public String details() {
        return details;
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
