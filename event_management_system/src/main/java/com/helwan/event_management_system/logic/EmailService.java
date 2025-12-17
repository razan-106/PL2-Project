/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.helwan.event_management_system.logic;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author omara
 */
public class EmailService {

    private final String customerEmail;

    // SMTP credentials (READ FROM ENV OR CONFIG)
    private static final String SMTP_EMAIL =
            "omaraymanelngm@gmail.com";

    private static final String SMTP_PASSWORD = "rode mfaw eqif irvq"; 

    public EmailService(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void sendBookingConfirmation(
            String customerName,
            String eventType,
            String eventDate,
            String location,
            int bookingId
    ) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SMTP_EMAIL, SMTP_PASSWORD);
                }
            });

        try {
            Message message = new MimeMessage(session);

            // Sender (real Outlook account, branded name)
            message.setFrom(new InternetAddress(
                    SMTP_EMAIL,
                    "Event Management System"
            ));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(customerEmail)
            );

            message.setSubject("Booking Confirmation");

            String body =
                    "Dear " + customerName + ",\n\n" +
                    "Your booking has been successfully created.\n\n" +
                    "Booking ID: " + bookingId + "\n" +
                    "Event Type: " + eventType + "\n" +
                    "Event Date: " + eventDate + "\n" +
                    "Location: " + location + "\n\n" +
                    "Thank you for using Event Management System.\n\n" +
                    "— Event Management System";

            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}