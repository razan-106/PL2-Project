package com.helwan.event_management_system.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

public class ServiceProvider extends JFrame { 
    
    // Global Variable
    private JTable spTable;
    private JButton submitBtn, confirmBtn;
    private JTextField priceField, dateField;

    // Theme constants make it easy to change from one place
    private final Color Main_BackGround = new Color(30, 41, 59);
    private final Color Snd_BackGround = new Color(15, 23, 42);
    private final Color Text_setForeground = Color.white;
    private final Color Text_setForeground_pan = Color.lightGray;

    public ServiceProvider() {
        setTitle("Service Provider");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Main_BackGround);

        String [] spcolumns = {"Task ID", "Event", "Status","Price","Date","Location"};
        Object [][] spdata;
        try {
            com.helwan.event_management_system.data.FileManager fileManager = new com.helwan.event_management_system.data.FileManager(
                com.helwan.event_management_system.data.FileManager.getResourcePath("data/users.txt"),
                com.helwan.event_management_system.data.FileManager.getResourcePath("data/booking.txt")
            );
            java.util.ArrayList<com.helwan.event_management_system.models.user> users = fileManager.loadUsers();
            java.util.ArrayList<com.helwan.event_management_system.models.Event> events = fileManager.loadEvents();
            java.util.ArrayList<com.helwan.event_management_system.models.customer> customers = new java.util.ArrayList<>();
            for (com.helwan.event_management_system.models.user u : users) {
                if (u instanceof com.helwan.event_management_system.models.customer) customers.add((com.helwan.event_management_system.models.customer) u);
            }
            java.util.ArrayList<com.helwan.event_management_system.models.Booking> bookings = fileManager.loadBookings(customers, events);
            java.util.List<Object[]> processingRows = new java.util.ArrayList<>();
            for (com.helwan.event_management_system.models.Booking booking : bookings) {
                if ("Processing".equalsIgnoreCase(booking.getStatus())) {
                    processingRows.add(new Object[] {
                        booking.getBookingId(),
                        booking.getEventId().getType(),
                        booking.getStatus(),
                        booking.getTotalPrice(),
                        booking.getEventId().getDate(),
                        booking.getEventId().getLocation()
                    });
                }
            }
            spdata = processingRows.toArray(new Object[0][]);
        } catch (Exception e) {
            spdata = new Object[0][spcolumns.length];
            System.out.println("Error loading bookings for SP: " + e.getMessage());
        }
        spTable = createTable(spdata, spcolumns);

        JScrollPane spscroll = new JScrollPane(spTable);
        spscroll.getViewport().setBackground(Snd_BackGround);
        add(spscroll, BorderLayout.CENTER);

        JPanel spcontrols = new JPanel();
        spcontrols.setLayout(new FlowLayout());
        spcontrols.setBackground(Main_BackGround);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setForeground(Text_setForeground_pan);
        priceField = new JTextField(10);
        priceField.setBackground(Snd_BackGround);
        priceField.setForeground(Text_setForeground);
        priceField.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Text_setForeground_pan);
        dateField = new JTextField(10);
        dateField.setBackground(Snd_BackGround);
        dateField.setForeground(Text_setForeground);
        dateField.setFont(new Font("Arial", Font.BOLD, 14));

        submitBtn = new JButton("Submit Price");
        confirmBtn = new JButton("Confirm Date");

        spcontrols.add(priceLabel);
        spcontrols.add(priceField);
        spcontrols.add(submitBtn);
        spcontrols.add(dateLabel);
        spcontrols.add(dateField);
        spcontrols.add(confirmBtn);

        add(spcontrols, BorderLayout.SOUTH);

        // Add action for Submit Price
        submitBtn.addActionListener(e -> {
            int selectedRow = spTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a booking to set the price.");
                return;
            }
            String priceText = priceField.getText().trim();
            double price = 150.0;
            if (!priceText.isEmpty()) {
                try {
                    price = Double.parseDouble(priceText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
                    return;
                }
            }
            int bookingId = (int) spTable.getValueAt(selectedRow, 0);
            try {
                com.helwan.event_management_system.data.FileManager fileManager = new com.helwan.event_management_system.data.FileManager(
                    com.helwan.event_management_system.data.FileManager.getResourcePath("data/users.txt"),
                    com.helwan.event_management_system.data.FileManager.getResourcePath("data/booking.txt")
                );
                java.util.ArrayList<com.helwan.event_management_system.models.user> users = fileManager.loadUsers();
                java.util.ArrayList<com.helwan.event_management_system.models.Event> events = fileManager.loadEvents();
                java.util.ArrayList<com.helwan.event_management_system.models.customer> customers = new java.util.ArrayList<>();
                for (com.helwan.event_management_system.models.user u : users) {
                    if (u instanceof com.helwan.event_management_system.models.customer) customers.add((com.helwan.event_management_system.models.customer) u);
                }
                java.util.ArrayList<com.helwan.event_management_system.models.Booking> bookings = fileManager.loadBookings(customers, events);
                boolean found = false;
                for (com.helwan.event_management_system.models.Booking booking : bookings) {
                    if (booking.getBookingId() == bookingId && "Processing".equalsIgnoreCase(booking.getStatus())) {
                        booking.setTotalPrice(price);
                        booking.setStatus("Priced");
                        found = true;
                        break;
                    }
                }
                if (found) {
                    fileManager.savingBooking(bookings, fileManager.getBookingFilePath());
                    JOptionPane.showMessageDialog(this, "Price set and status updated to 'Priced'.");
                    // Refresh the table
                    this.dispose();
                    new ServiceProvider().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Could not find the selected booking or it is not in 'Processing' status.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating booking: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private JTable createTable(Object[][] data, String [] columns) {
        JTable table = new JTable(data, columns);
        table.setBackground(Snd_BackGround);
        table.setForeground(Text_setForeground);
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(Main_BackGround);
        header.setForeground(Text_setForeground);
        
        return table;
    }

    public static void main(String[] args) {
        ServiceProvider sp = new ServiceProvider();
        sp.setVisible(true);
    }
}