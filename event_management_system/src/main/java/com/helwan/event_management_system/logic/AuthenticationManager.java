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
    public boolean authenticate(String username, String password) {

        // 1) Load users from file
        ArrayList<user> users = fileManager.loadUsers();

        // 2) Check if any user matches
        for (user u : users) {
            if (u.getEmail().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
                return true;
            }
        }

        return false; // no match found
    }
}