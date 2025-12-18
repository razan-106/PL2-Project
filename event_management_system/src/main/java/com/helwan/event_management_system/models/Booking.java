package com.helwan.event_management_system.models;

import java.util.Random;

public class Booking {
    private int bookingId;
    private customer customerId;
    private Event eventId;
    private String status;
    private double totalPrice;

    public Booking(customer c, Event event) { 
        this.bookingId = new Random().nextInt(10000); // رقم عشوائي
        this.customerId = c;
        this.eventId = event;
        this.status = "New";
        this.totalPrice = 500; // هيتحسب بعدين حسب نوع الإيفنت
}


    public Booking(int bookingId, customer customer, Event newEvent, String status, double totalPrice) {
        this.bookingId = bookingId;
        this.customerId = customer;
        this.eventId = newEvent;
        this.status = status;
        this.totalPrice = totalPrice;
    }


    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public customer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(customer customerId) {
        this.customerId = customerId;
    }

    public Event getEventId() {
        return eventId;
    }
    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        if (eventId == null) {

            return "Booking{Invalid Booking: Event is null}";
     }
        return "Booking{" + eventId.getType() + ", Status=" + status + ", Price=" + getTotalPrice() + "}";
    }

}
