package com.railway.management.controller;

import javax.mail.Session;
import com.railway.management.process.InputProcess;
import com.railway.management.process.MailProcess;
import com.railway.management.process.PersonProcess;
import com.railway.management.process.mail.MailSend;

public class ResetPassword {
	final static InputProcess inputProcess = new InputProcess();

	public static void menu() {
		Session session = MailSend.createMailSession();
		try {
			String username = inputProcess.getString("Enter username: ");
			String recipientEmail = isValidUsername(username);

			if (username != null) {
				System.out.println("Username is valid. Reset options:");
				System.out.println("1. Reset Password via Email");
				System.out.println("2. Reset Password via SMS");
				System.out.println("3. Go Back");
				String resetOption = inputProcess.getString("Enter Your Option:");
				switch (resetOption) {
				case "1":
					System.out.println("Resetting password via email...");
					int otp = MailSend.sendEmail(recipientEmail, session);
					int getOtp = inputProcess.getInt("Enter Your Otp:  ");
					if (otp == getOtp) {
						System.out.println("OTP Matched");
						String newpassword = inputProcess.getString("Enter Your New Password Here:");
						PersonProcess.resetPassword(username, newpassword);
					} else {
						System.out.println("OTP Didn't Matched");
					}
					return;
				case "2":
					System.out.println("You Choose SMS but We Didn't had the SMS budget \nSoo... Try Email I guess.");
					break;
				case "3":
					return;
				default:
					System.out.println("Invalid reset option");
				}
			} else {
				System.out.println("Invalid username. Password reset failed.");
				return;
			}
		} catch (java.util.NoSuchElementException e) {

		}
	}

	private static String isValidUsername(String username) {
		try {
			return MailProcess.getEmailByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
