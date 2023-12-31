package com.railway.management.dataInput;

import com.railway.management.process.AdminProcess;
import com.railway.management.process.InputProcess;

public class AdminDataInput {

    public static void create() {
    	try{
    	System.out.println("Enter Person Details:");
            InputProcess inputProcess= new InputProcess();
            String name =  inputProcess.getString("Name:  ");

            String email = inputProcess.getString("Email:  ");

            int age = inputProcess.getInt("Age:  ");

            String address = inputProcess.getString("Address:  ");

            Long phone =inputProcess.getLong("Phone:  ");

            String userId = inputProcess.getString("User ID:  ");

            String password =inputProcess.getString("Password:  ");
            String privillage = null;

            int privil;
            do {
                privil = inputProcess.getInt("Enter Admin Privilege:\\n(1 for Full Access, 2 for Limited Access and 3 for Read Only) ");
                if (privil == 1) {
                    privillage = "Full Access";
                    break;
                } else if (privil == 2) {
                    privillage = "Limited Access";
                    break;
                } else if (privil == 3) {
                    privillage = "Read Only";
                    break;
                }
            } while (privil > 3 || privil < 1);

            // Call the details method with user-input values
            AdminProcess.addAdmin(name, email, age, address, phone, userId, password, privillage);
        }catch (Exception e){
        	e.printStackTrace();
        }
    	
    }
}
