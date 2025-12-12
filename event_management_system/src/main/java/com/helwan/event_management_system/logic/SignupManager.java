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
 * @author omara
 */
public class SignupManager {

    private FileManager fileManager;

    public SignupManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    // Signup Method
    public String signupUser(String name, String email, String password) {

        // 1) Check empty fields
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return "Please fill all fields.";
        }

        // 2) Load existing users
        ArrayList<user> users = fileManager.loadUsers();

        // 3) Check if email already exists
        for (user u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return "Email already exists!";
            }
        }

        // 4) Generate new ID
        int newId = fileManager.generateUserId(users);

        // 5) Create new user
        user newUser = new user(newId, name, email, password, "Customer");

        // 6) Add to list
        users.add(newUser);

        // 7) Save file
        fileManager.savingUser(users, fileManager.getUserFilePath());

        return "Signup Successful!";
    }
}
