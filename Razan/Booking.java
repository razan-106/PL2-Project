package Razan;

import java.util.Random;

public class Booking {
    private int bookingId;
    private customer customerId;
    private event eventId;
    private String status;
    private double totalPrice;

    public Booking(customer c, event event) { 
        this.bookingId = new Random().nextInt(10000); // رقم عشوائي بس ثابت 
        this.customerId = c;
        this.eventId = event;
        this.status = "Pending";
        this.totalPrice = new Random().nextInt(3000); // سعر عشوائي بس ثابت
    }

    public Booking(int bookingId, customer customer, event event, String status, double totalPrice) {
        this.bookingId = bookingId;
        this.customerId = customer;
        this.eventId = event;
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

    public event getEventId() {
        return eventId;
    }
    public void setEventId(event eventId) {
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
        return "Booking : {" + eventId.getType() + " , Date : " + eventId.getDate() + " , Location : " + eventId.getLocation() +  " , Status : " + status + " , Price =" + getTotalPrice() +  " }";
    }

}
