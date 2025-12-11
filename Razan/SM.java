package Razan;

import java.util.*;

public class SM {

    Scanner input = new Scanner(System.in);
    List<user> users = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();

    public void processFlow() {

        System.out.println("===== Customer Registration =====");

        System.out.print("\nEnter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your email: ");
        String email = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = input.nextLine();

        // Create customer
        customer c = new customer(1, name, email, password, phone, "Customer");

        System.out.println("\n===== Create Event =====");

        System.out.print("\nEnter event type (e.g., Wedding): ");
        String type = input.nextLine();

        System.out.print("Enter event date: ");
        String date = input.nextLine();

        System.out.print("Enter event location: ");
        String location = input.nextLine();

        // For now: simple event ID
        event e = new event(1, type, date, location);

        // Customer books the event
        Booking booking = c.bookEvent(e);
        bookings.add(booking);

        System.out.println("\n===== Booking Created =====");
        System.out.println("\n"+ booking);

        // PM receives
        PM pm = new PM();
        pm.reciveRequest(booking);

        // Assign SP
        SP sp = new SP();
        pm.forwardToSP(sp, booking);

        pm.showBoll(booking);

        System.out.println("\n===== Final Booking Details =====");
        System.out.println("\n"+ booking);
    }
}
