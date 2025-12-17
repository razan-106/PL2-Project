package com.helwan.event_management_system.models;

import java.util.*;

// child class of user

public class customer extends user {
    private String phoneNumber;
    private List<Booking> bookings = new ArrayList<>();

    public customer() {
        super();
    }

    public customer(int id, String name, String email, String password,  String phoneNumber , String role) {
        super(id, name, email, password ,"Customer");
        this.phoneNumber = phoneNumber;
    }

    public Booking bookEvent(Event event){
        Booking newBooking = new Booking(this, event);
        bookings.add(newBooking);
        return newBooking;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
