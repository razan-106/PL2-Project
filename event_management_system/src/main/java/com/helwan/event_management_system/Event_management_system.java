package com.helwan.event_management_system;

import com.helwan.event_management_system.UI.login.Login;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Event_Management_System {

    public static void main(String[] args) {

        // Set Nimbus Look & Feel once
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        // Start application with Login screen
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
