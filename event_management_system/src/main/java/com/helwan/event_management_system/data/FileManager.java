/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helwan.event_management_system.data;
import com.helwan.event_management_system.models.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;


/**
 *
 * @author omara
 */
public class FileManager {

    // Hardcoded constant paths
    public static final String USER_FILE_PATH = "src/main/resources/data/users.txt";
    public static final String BOOKING_FILE_PATH = "src/main/resources/data/bookings.txt";
    public static final String EVENT_FILE_PATH = "src/main/resources/data/events.txt";

    private String userFilePath;
    private String bookingFilePath;

    public FileManager() {
        this.userFilePath = USER_FILE_PATH;
        this.bookingFilePath = BOOKING_FILE_PATH;
        createUserFileIfNotExist(userFilePath);
        createBookingFileIfNotExist(bookingFilePath);
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

        if (!user.exists()) {
            try {
                // Create parent directories if they don't exist
                user.getParentFile().mkdirs();
                
                user.createNewFile();
                System.out.println("The name of user File created:" + user.getName());
                System.out.println("The path of user File created:" + user.getPath());
                
                // Populate with default admin users
                try (PrintWriter writer = new PrintWriter(new FileWriter(user))) {
                    writer.println("Admin,admin@system.com,1,admin123,Admin");
                    writer.println("SuperAdmin,superadmin@system.com,2,admin123,Admin");
                    System.out.println("Initialized users.txt with default admin accounts");
                }

            } catch (IOException e) {
                System.out.println("Failed to create user file " + e.getMessage());
            }
        }
    }

    //Create method about booking file if not exist
    public static void createBookingFileIfNotExist(String bookingFilePath) {
        File booking = new File(bookingFilePath);
        if (!booking.exists()) {
            try {
                // Create parent directories if they don't exist
                booking.getParentFile().mkdirs();
                
                booking.createNewFile();
                System.out.println("The name of booking File created:" + booking.getName());
                System.out.println("The path of booking File created:" + booking.getPath());

            } catch (IOException e) {
                System.out.println("Failed to create booking file " + e.getMessage());
            }
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
    public ArrayList<Booking> readInfoFromBooking() {

        ArrayList<Booking> bookings = new ArrayList<>();
        File file = new File(this.bookingFilePath);

        if (!file.exists()) {
            System.out.println("Booking file doesn't exist.");
            return bookings;
        }

        // Load users and filter customers
        ArrayList<user> users = loadUsers();
        ArrayList<customer> customerList = new ArrayList<>();
        for (user u : users) {
            if (u instanceof customer) {
                customerList.add((customer) u);
            }
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

                if (foundCustomer == null) {
                    System.out.println("WARNING: Customer ID " + customerId + " not found in users file!");
                    continue;
                }

                // ------ create minimal Event object only with ID ------
                Event foundEvent = new Event(eventId, "", "", 0, "");

                bookings.add(new Booking(bookingId, foundCustomer, foundEvent, status, totalPrice));
            }

        } catch (Exception e) {
            System.out.println("Error reading booking file: " + e.getMessage());
            e.printStackTrace();
        }

        return bookings;
    }

    public ArrayList<Booking> loadBookings() {
        return readInfoFromBooking();
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
    
    // Overloaded method to save users using the default path
    public void savingUser(ArrayList<user> users) {
        savingUser(users, this.userFilePath);
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
    
    // Overloaded method to save bookings using the default path
    public void savingBooking(ArrayList<Booking> bookings) {
        savingBooking(bookings, this.bookingFilePath);
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

    /**
     * Load events from events.txt file
     */
    public ArrayList<Event> loadEvents() {
        ArrayList<Event> events = new ArrayList<>();
        
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(EVENT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] eventData = line.split(",");
                if (eventData.length < 6) continue;
                
                int eventId = Integer.parseInt(eventData[0]);
                String type = eventData[1];
                String date = eventData[2];
                String location = eventData[3];
                int guestCount = Integer.parseInt(eventData[4]);
                String details = eventData[5];
                
                events.add(new Event(eventId, type, location, guestCount, details));
            }
        } catch (Exception e) {
            System.out.println("Error reading events file: " + e.getMessage());
        }
        
        return events;
    }
}