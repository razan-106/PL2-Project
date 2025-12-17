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
        Object [][] spdata ={};
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