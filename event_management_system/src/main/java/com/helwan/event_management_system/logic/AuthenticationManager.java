/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helwan.event_management_system.logic;

import com.helwan.event_management_system.data.FileManager;
import com.helwan.event_management_system.models.user;
import java.util.ArrayList;

/**
 *
 * @author omarayman
 */
public class AuthenticationManager {

    private FileManager fileManager;

    public AuthenticationManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    // Authenticate user by username/email and password
    public user authenticate(String username, String password) {

        // 1) Load users from file
        ArrayList<user> users = fileManager.loadUsers();
        
        System.out.println("Attempting to authenticate: " + username);
        System.out.println("Total users in system: " + users.size());

        for (user user : users) {
            System.out.println("Checking user: " + user.getEmail() + " / " + user.getPassword());
            // Make email comparison case-insensitive
            if (user.getEmail().equalsIgnoreCase(username)
                && user.getPassword().equals(password)) {
                System.out.println("Authentication SUCCESS!");
                return user; // SUCCESS
            }
        }
        System.out.println("Authentication FAILED!");
        return null; // FAIL
        }
}