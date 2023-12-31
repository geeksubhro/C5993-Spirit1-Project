package com.railway.management.dataInput;

import com.railway.management.process.InputProcess;
import com.railway.management.process.UserProcess;


public class UserDataInput {

    public static void create() {
    	InputProcess inputProcess = new InputProcess();

        System.out.println("Enter Person Details:");
        String name = inputProcess.getString("Name:  ");

		String email = inputProcess.getString("Email:  ");

		int age = inputProcess.getInt("Age:  ");

		String address = inputProcess.getString("Address:  ");
		 
		Long phone = inputProcess.getLong("Phone:  ");

		String userId = inputProcess.getString("User ID:  ");

		String password = inputProcess.getString("Password:  ");


        // Call the details method with user-input values
        UserProcess.addUser(name, email, age, address, phone,  userId,
                password);
    }
}
