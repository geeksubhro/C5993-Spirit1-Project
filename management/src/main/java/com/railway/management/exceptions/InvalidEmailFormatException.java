package com.railway.management.exceptions;

//Create a new Java class for the custom exception, e.g., InvalidEmailFormatException.java
public class InvalidEmailFormatException extends IllegalArgumentException {

 /**
	 * 
	 */
	private static final long serialVersionUID = 8791956242723140329L;

public InvalidEmailFormatException() {
     super("Invalid email format");
 }
}
