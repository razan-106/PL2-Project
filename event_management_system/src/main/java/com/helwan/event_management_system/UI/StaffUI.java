package com.helwan.event_management_system.UI;

import java.awt.*;
import javax.swing.*;


public class StaffUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Staff Board");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel pmpanel = new JPanel();
        JPanel sppanel = new JPanel();

        tabbedPane.addTab("Project Manager", pmpanel);
        tabbedPane.addTab("Servise Provider", sppanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        JPanel staffPanel = new JPanel();
        staffPanel.setLayout(new BorderLayout());

        tabbedPane.addTab("Staff", staffPanel);

        String [] staffcolumns = {"ID", "Name", "Phone", "Role"};
        Object[][] staffData = {};
        JTable staffTable = new JTable(staffData, staffcolumns);
        JScrollPane staffScroll = new JScrollPane(staffTable) ;
        staffPanel.add(staffScroll) ;

        String[] pmColumns = {"Booking ID", "Customer", "Event Type", "Status"} ; 
        Object[][] pmData = {{"1", "Rahma", "Wedding", "New"},
                             {"2", "Ahmed", "Birthday", "New"}} ;  
        
        JTable pmTable = new JTable(pmData, pmColumns);
        JScrollPane pmScroll = new JScrollPane(pmTable);
        pmpanel.setLayout(new BorderLayout());
        pmpanel.add(pmScroll, BorderLayout.CENTER);

        JButton assignBtn = new JButton("Assign to Service Provider");
        JPanel pmButtons = new JPanel();
        pmButtons.add(assignBtn);
        pmpanel.add(pmButtons,BorderLayout.SOUTH);

        String[] spColumns={"Task ID", "Event", "Status"};
        Object[][] spData = {
                            {"1", "Wedding", "Processing"},
                            {"2", "Birthday", "Processing"}};
        
        JTable spTable = new JTable(spData, spColumns);
        JScrollPane spScroll = new JScrollPane(spTable);
        sppanel.setLayout(new BorderLayout());
        sppanel.add(spScroll, BorderLayout.CENTER);

        JPanel spControls = new JPanel();
        spControls.setLayout(new FlowLayout());

        JLabel dateLabel = new JLabel("Delivery Date:");
        JTextField dateField = new JTextField(10);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(10);

        JButton submitPriceBtn = new JButton("Submit Price");
        JButton confirmDateBtn = new JButton("Confirm Date");

        spControls.add(priceLabel);
        spControls.add(priceField);
        spControls.add(dateLabel);
        spControls.add(dateField);
        spControls.add(submitPriceBtn);
        spControls.add(confirmDateBtn);

        sppanel.add(spControls, BorderLayout.SOUTH);
    }
}