package com.helwan.event_management_system.logic;

import com.helwan.event_management_system.models.user;

public class SessionManager {

    private static user currentUser;

    private SessionManager() {
        // prevent instantiation
    }

    public static void setCurrentUser(user user) {
        currentUser = user;
    }

    public static user getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserId() {
        return currentUser != null ? currentUser.getId() : -1;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }
}
