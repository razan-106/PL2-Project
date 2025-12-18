/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helwan.event_management_system.logic;

import com.helwan.event_management_system.UI.*;
import com.helwan.event_management_system.UI.admin.AdminDashboard;
import com.helwan.event_management_system.UI.booking.EVentBookForm;
import com.helwan.event_management_system.UI.ProjectManager;
import com.helwan.event_management_system.UI.ServiceProvider;

/**
 *
 * @author omara
 */
public class NavigationManager {

    public static void navigateAfterLogin() {

        String role = SessionManager.getCurrentUser().getRole();

        // Route based on role only
        switch (role.toLowerCase()) {

            case "admin":
                new AdminDashboard().setVisible(true);
                break;

            case "customer":
                new EVentBookForm().setVisible(true);
                break;

            case "pm":
                new ProjectManager().setVisible(true);
                break;

            case "sp":
                new ServiceProvider().setVisible(true);
                break;

            default:
                throw new IllegalStateException("Unknown role: " + role);
        }
    }
}