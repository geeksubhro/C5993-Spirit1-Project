package com.railway.management.exceptions;

public class InvalidEmailFormatException extends IllegalArgumentException {

 /**
	 * 
	 */
	private static final long serialVersionUID = 8791956242723140329L;

public InvalidEmailFormatException() {
     super("Invalid email format");
 }
}
