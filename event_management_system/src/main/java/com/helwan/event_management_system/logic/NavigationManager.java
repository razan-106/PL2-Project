/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helwan.event_management_system.logic;

import com.helwan.event_management_system.UI.*;
import com.helwan.event_management_system.UI.admin.AdminDashboard;
import com.helwan.event_management_system.UI.booking.EVentBookForm;
import com.helwan.event_management_system.UI.booking.MyBookingsScreen;

/**
 *
 * @author omara
 */
public class NavigationManager {

    public static void navigateAfterLogin() {

        String email = SessionManager.getCurrentUser().getEmail();
        String role = SessionManager.getCurrentUser().getRole();

        // Check if email contains @admin (case-insensitive) - if so, route to admin dashboard
        if (email.toLowerCase().contains("@admin")) {
            new AdminDashboard().setVisible(true);
            return;
        }

        switch (role) {

            case "Admin":
                new AdminDashboard().setVisible(true);
                break;

            case "Customer":
                new EVentBookForm().setVisible(true);
                break;

            case "PM":
               // new PMDashbStaffUIoard().setVisible(true);
                break;

            case "SP":
             //   new SPDashboard().setVisible(true);
                break;

            default:
                throw new IllegalStateException("Unknown role: " + role);
        }
    }
}