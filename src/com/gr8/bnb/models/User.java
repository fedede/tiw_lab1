package com.gr8.bnb.models;

import java.util.ArrayList;

/**
 * It defines a user in the web page as it is expected to be stored in
 * 	the database.
 */
public class User {

	/* DATABASE EMULATION FIELDS */
	private static ArrayList<User> users = new ArrayList<User>();

	/* DATABASE ROW FIELDS */
	private String name;
	private String surname;
	private String email;
	private String password;

	
	/**
	 * @param name: First name of the database user.
	 * @param surname: Last name of the database user.
	 * @param email: Email of the database user.
	 * @param password: Password of the database user.
	 */
	public User(String name, String surname, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	/* GETTERS AND SETTERS */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* OBJECT FUNCTIONS */

	/**
	 * Check if a user exists on the database and insert it if not.
	 * @return: true if the user was inserted, false otherwise.
	 */
	public boolean save() {
		if (find(this.email) == null)	{
			users.add(this);
			return true;
		}
		return false;
	}

	/**
	 * Check if a user exists on the database and update it.
	 * @return: true if the user was updated, false otherwise.
	 */
	public boolean update() {
		User prevUser = find(this.email);
		if (prevUser == null) {
			return false;
		}
		users.remove(prevUser); // remove previous user
		users.add(this);        // add updated user
		return true;
	}

	/**
	 * Check if a user exists on the database and delete it if so.
	 * @return: true if the user was inserted, false otherwise.
	 */
	public boolean delete() {
		return users.remove(this);
	}

	/* DATABASE EMULATION FUCTIONS */

	/**
	 * Create a new user and insert it in the database.
	 *
	 * @param name: First name of the database user.
	 * @param surname: Last name of the database user.
	 * @param email: Email of the database user.
	 * @param password: Password of the database user.
	 *
	 * @return: the created user if inserted in the database successfully,
	 */
	public static User create(String name, String surname, String email,
String password) {
		User newUser = new User(name, surname, email, password);
		if (newUser.save()) {
			return newUser;
		}
		return null;
	}

	/**
	 * Find a user in the database by the email
	 *
	 * @param email: Email of the database user we want to find.
	 *
	 * @return: the user if found or null otherwise.
	 */
	public static User find(String email) {
		for (User u : users) {
			if (u.email.equals(email)) {
				return u;
			}	
		}
		return null;
	}

	/**
	 * Find a user in the database and verifies that the password 
	 * is correct.
	 * 
	 * @param email: Email of the database user we want to find.
	 * @param password: Password for the requested user.
	 *
	 * @return: the user if found a user with the given email and password.
	 */
	public static User find(String email, String password){
		for (User u : users) {
			if (u.email.equals(email) && u.password.equals(password)) {
				return u;
			}
		}
		return null;
	}
}
