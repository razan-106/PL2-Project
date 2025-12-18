package com.helwan.event_management_system.models;

public class Event {
    private int eventId;
    private String type;
    private String location;
    private int guestCount;
    private String details;
    
    // ضفنا ده عشان الـ SP يعرف يستخدمه
    private String date; 

    public Event() {}

    // الـ Constructor ده هو اللي صفحة الحجز بتنادي عليه
    // لاحظ إننا مش بناخد date في القوسين، بس بنحطله قيمة ابتدائية جوه
    public Event(int eventId, String type, String location, int guestCount, String details) {
        this.eventId = eventId;
        this.type = type;
        this.location = location;
        this.guestCount = guestCount;
        this.details = details;
        this.date = "Pending"; // <--- قيمة افتراضية لحد ما الـ SP يحددها
    }

    // --- Getters & Setters (عشان الإيرور يروح) ---
    
    public String getDate() { // الـ SP محتاج دي عشان يقرا التاريخ
        return date;
    }

    public void setDate(String date) { // الـ SP محتاج دي عشان يحدد التاريخ
        this.date = date;
    }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getGuestCount() { return guestCount; }
    public void setGuestCount(int guestCount) { this.guestCount = guestCount; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}  