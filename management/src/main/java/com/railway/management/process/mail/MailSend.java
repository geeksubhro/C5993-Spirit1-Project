package com.railway.management.process.mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Random;

public class MailSend {

    // Helper method to send an email with a random 4-digit OTP to the specified recipient
    public static int sendEmail(String recipientEmail, Session session) {
        // Ensure that recipientEmail is not null or empty
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            System.out.println("Invalid recipient email. Cannot send email.");
            return 0;
        }

        // Generate a random 4-digit OTP
        int otp = generateRandomOTP();

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender's email address
            message.setFrom(new InternetAddress("Subhrojeet.mail@gmail.com"));

            // Set the recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and content with the generated OTP
            message.setSubject("Your OTP");
            message.setText("Here is your OTP: " + otp);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");
            return otp;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
		return otp;
    }

    // Helper method to generate a random 4-digit OTP
    private static int generateRandomOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Random number between 1000 and 9999
        return (otp);
    }

    // Helper method to create and return the JavaMail Session
    public static Session createMailSession() {
        final String senderEmail = "Subhrojeet.mail@gmail.com";
        final String senderPassword = "fafawbtfuzrhqcwg"; // If using App Password

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    public static void main(String[] args) {
        // Example usage:
        Session session = createMailSession();

        // Replace "recipient@example.com" with the actual recipient's email address
        sendEmail("subhrojeet1232@gmail.com", session);
    }
}
