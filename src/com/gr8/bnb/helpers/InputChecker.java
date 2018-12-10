package com.gr8.bnb.helpers;

public class InputChecker {

	public static final String EMAIL_PATTERN = "^[\\w.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	
	/**
	 * Check if the input house parameters are valid in order to publish a new house.
	 *
	 * @param houseName Name of the user  house we want to register, error if blank.
	 * @param fullDescription Full house description, error if blank.
	 * @param shortDescription Short house description, error if blank.
	 * @param type House type (shared or private), error if blank.
	 * @param sMaxGuests Maximum number of guests as string, error if blank.
	 * @param photoURL URL to the house photo, error if blank.
	 * @param city City in which the house is located, error if blank.
	 * @param sPrice House price, error if blank.
	 * @param sStartDate Date in which the city starts being available in format MM/dd/yyyy, error if blank.
	 * @param sEndDate Date in which the city ends being available in format MM/dd/yyyy, error if blank.
	 * @return Error message if error, null otherwise.
	 */
	public static String checkHouseParameters(String houseName, String fullDescription, String shortDescription,
			String type, String sGuestNum, String photoURL, String city, String sPrice,
			String sStartDate, String sEndDate) {
		String errorMessage  = null;
		if(houseName == null || houseName.isEmpty()){
			errorMessage = "Error in house name";
		}else if(fullDescription == null ||fullDescription.isEmpty()){
			errorMessage = "Error in full description";
		}else if(shortDescription == null || shortDescription.isEmpty()){
			errorMessage = "Error in short description";
		}else if (type == null || type.isEmpty()){
			errorMessage = "Error in type";
		}else if(sGuestNum == null || sGuestNum.isEmpty()){
			errorMessage = "Error in guest number2";
		}else if(photoURL == null || photoURL.isEmpty()){
			errorMessage = "Error in photo url";
		}else if(city == null || city.isEmpty()){
			errorMessage = "Error in city";
		}else if(sPrice == null || sPrice.isEmpty()){
			errorMessage = "Error in price1";
		}else if(sStartDate == null || sStartDate.isEmpty()){
			errorMessage = "Error in start date";
		}else if(sEndDate == null || sEndDate.isEmpty()){
			errorMessage = "Error in end date";
		}
		return errorMessage;
	}
	
	/**
	 * Check if the input user parameters are valid in order to sign up a new user.
	 *
	 * @param name Name of the user we want to register, error if blank.
	 * @param surname Surname of the user we want to register, error if blank.
	 * @param email Email of the user we want to register, error if blank or not email formatted.
	 * @param password Password of the user we want to register, error less than 8 characters
	 * @return Error message if error, null otherwise.
	 */
	public static String checkUserParameters(String name, String surname, String email, String password) {

		/* Check if the input name, surname and password have the right format */
		String message = checkUserParameters(name, surname, password); // null if correct format

		/* Check if the input email have the right format */
		message = (message == null) ? checkUserEmail(email) : message;

		return message;
	}

	/**
	 * Check if the input user parameters are valid in order to sign up a new user.
	 *
	 * @param name Name of the user we want to register, error if blank.
	 * @param surname Surname of the user we want to register, error if blank.
	 * @param password Password of the user we want to register, error less than 8 characters
	 * @return Error message if error, null otherwise.
	 */
	public static String checkUserParameters(String name, String surname,  String password) {
		String message = null;

		/* If error forward message if not check next parameter */
		message = (message == null) ? checkUserName(name) : message;
		message = (message == null) ? checkUserSurame(surname) : message;
		message = (message == null) ? checkUserPassword(password) : message;

		return message;
	}

	/**
	 * Check if the input user name is valid in order to sign up a new user.
	 *
	 * @param name Name of the user we want to register, error if blank.
	 * @return Error message if error, null otherwise.
	 */
	public static String checkUserName(String name) {
		String message = null;

		if (name == null || name.isEmpty()) {
			message = "Name cannot be blank.";
		}
		
		return message;
	}

	/**
	 * Check if the input user surname is valid in order to sign up a new user.
	 *
	 * @param surname Surname of the user we want to register, error if blank.
	 * @return Error message if error, null otherwise.
	 */
	public static String checkUserSurame(String surname) {
		String message = null;

		if (surname == null || surname.isEmpty()) {
			message = "Surname cannot be blank.";
		}

		return message;
	}

	/**
	 * Check if the input user password is valid in order to sign up a new user.
	 *
	 * @param password Password of the user we want to register, error less than 8 characters
	 * @return Error message if error, null otherwise.
	 */
	public static String checkUserPassword(String password) {
		String message = null;

		if (password == null || password.length() < 8) {
			message = "Password shall contain at least 8 characters.";
		} 

		return message;
	}

	/**
	 * Check if the input user email is valid in order to sign up a new user.
	 *
	 * @param email Email of the user we want to register, error if blank or not email formatted.
	 * @return Error message if error, null otherwise.
	 */
	public static String checkUserEmail(String email) {
		String message = null;

		if (email == null || email.isEmpty()) {
			message = "Email cannot be blank.";
		} else if (!email.matches(EMAIL_PATTERN)) {
			message = "Email address is not valid. It must have the format: ***@***.**";
		} 

		return message;
	}
}
