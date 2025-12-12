

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helwan.event_management_system.data;
import com.helwan.event_management_system.models.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


/**
 *
 * @author omara
 */
public class FileManager {

    private String userFilePath;
    private String bookingFilePath;

    public FileManager() {

    }

    public FileManager(String userFilePath, String bookingFilePath) {
        this.userFilePath = userFilePath;
        this.bookingFilePath = bookingFilePath;
        createUserFileIfNotExist(userFilePath);
        createBookingFileIfNotExist(bookingFilePath);
    }
    //Setters & Getters
    public String getUserFilePath() {
        return userFilePath;
    }

    public void setUserFilePath(String userFilePath) {
        this.userFilePath = userFilePath;
    }

    public String getBookingFilePath() {
        return bookingFilePath;
    }

    public void setBookingFilePath(String bookingFilePath) {
        this.bookingFilePath = bookingFilePath;
    }
    
    //Create method about user file if not exist
    public static void createUserFileIfNotExist(String userFilePath) {
        File user = new File(userFilePath);

        if (!user.exists())
            try {
                user.createNewFile();
                System.out.println("The name of user File created:" + user.getName());
                System.out.println("The path of user File created:" + user.getPath());

            } catch (IOException e) {
                System.out.println("Failed to create user file " + e.getMessage());
            }
    }

    //Create method about booking file if not exist
    public static void createBookingFileIfNotExist(String bookingFilePath) {
        File booking = new File(bookingFilePath);
        if (!booking.exists())
            try {
                booking.createNewFile();
                System.out.println("The name of booking File created:" + booking.getName());
                System.out.println("The path of booking File created:" + booking.getPath());

            } catch (IOException e) {
                System.out.println("Failed to create booking file " + e.getMessage());
            }
    }

    //Method to read information from user file.
    public ArrayList<user> readInfoFromUser(String userFile) {
        ArrayList<user> users = new ArrayList<>();
        File file = new File(userFile);

        if (!file.exists()) {
            System.out.println("File doesn't exist.");
            return users;
        }

        try (Scanner input = new Scanner(file)) {

            while (input.hasNextLine()) {
                String line = input.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] userData = line.split("\\s*,\\s*");

                if (userData.length != 5) {
                    System.out.println("Sorry,this line is invalid: " + line);
                    continue;
                }

                String name = userData[0].trim();
                String email = userData[1].trim();
                int id = Integer.parseInt(userData[2].trim());
                String password = userData[3].trim();
                String role = userData[4].trim();

                switch (role.toLowerCase()) {
                    case "customer":
                        users.add(new customer(id, name, email, password, "", "Customer"));
                        break;
                    case "pm":
                        users.add(new PM(id, name, email, password, "", "PM", ""));
                        break;
                    case "sp":
                        users.add(new SP(id, name, email, password, "", "SP"));
                        break;
                    case "admin":
                        users.add(new admin(id, name, email, password, "Admin"));
                        break;
                    default:
                        users.add(new user(id, name, email, password, role));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error while reading from user file: " + e.getMessage());
        }
        return users;
    }

    public ArrayList<user> loadUsers() {
        return readInfoFromUser(this.userFilePath);
    }

    //Method to read information from booking file.
    public ArrayList<Booking> readInfoFromBooking(String bookFile,
            ArrayList<customer> customerList,
            ArrayList<event> eventList) {

        ArrayList<Booking> bookings = new ArrayList<>();
        File file = new File(bookFile);

        if (!file.exists()) {
            System.out.println("Booking file doesn't exist.");
            return bookings;
        }

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {

                String line = input.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] bookingData = line.split("\\s*,\\s*");
                if (bookingData.length != 5) {
                    System.out.println("Invalid booking line: " + line);
                    continue;
                }

                int bookingId = Integer.parseInt(bookingData[0]);
                int customerId = Integer.parseInt(bookingData[1]);
                int eventId = Integer.parseInt(bookingData[2]);
                String status = bookingData[3];
                double totalPrice = Double.parseDouble(bookingData[4]);

                // ------ search for customer ID  ------
                customer foundCustomer = null;
                for (customer c : customerList) {
                    if (c.getId() == customerId) {
                        foundCustomer = c;
                        break;
                    }
                }

                // ------  search for Event ID   ------
                event foundEvent = null;
                for (event e : eventList) {
                    if (e.getEventId() == eventId) {
                        foundEvent = e;
                        break;
                    }
                }

                if (foundCustomer == null || foundEvent == null) {
                    System.out.println("Warning: Invalid customer/event ID in line: " + line);
                    continue;
                }

                bookings.add(new Booking(bookingId, foundCustomer, foundEvent, status, totalPrice));
            }

        } catch (Exception e) {
            System.out.println("Error reading booking file: " + e.getMessage());
        }

        return bookings;
    }

    public ArrayList<Booking> loadBookings(ArrayList<customer> customerList, ArrayList<event> eventList) {
        return readInfoFromBooking(this.bookingFilePath, customerList, eventList);
    }

    //SavingUser Method
    public void savingUser(ArrayList<user> users, String userFilePath) {
        try (PrintWriter userOutput = new PrintWriter(new FileWriter(userFilePath))) {

            for (user u : users) {
                userOutput.println(
                        u.getName() + "," +
                        u.getEmail() + "," +
                        u.getId() + "," +
                        u.getPassword() + "," +
                        u.getRole());
            }

            System.out.println("User saved Successfully");

        } catch (IOException e) {
            System.out.println("Error happens while saving userInfo " + e.getMessage());
        }
    }

    //SavingBooking Method
    public void savingBooking(ArrayList<Booking> bookings, String bookingFilePath) {
        try (PrintWriter bookingOutput = new PrintWriter(new FileWriter(bookingFilePath))) {

            for (Booking b : bookings) {
                bookingOutput.println(
                        b.getBookingId() + "," +
                        b.getCustomerId().getId() + "," +
                        b.getEventId().getEventId() + "," +
                        b.getStatus() + "," +
                        b.getTotalPrice());
            }

            System.out.println("Booking saved Successfully");

        } catch (IOException e) {
            System.out.println("Error happens while saving bookingInfo " + e.getMessage());
        }
    }

    //Method for generating ID for user 
    public int generateUserId(ArrayList<user> users) {

        int maxUserId = 0;

        for (user u : users) {
            if (u.getId() > maxUserId) {
                maxUserId = u.getId();
            }
        }

        return maxUserId + 1;
    }

    //Method for generating ID for Booking 
    public int generateBookingId(ArrayList<Booking> bookings) {

        int maxBookingId = 0;

        for (Booking b : bookings) {
            if (b.getBookingId() > maxBookingId) {
                maxBookingId = b.getBookingId();
            }
        }

        return maxBookingId + 1;
    }
}
