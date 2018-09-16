package com.gr8.bnb.models;

import java.util.ArrayList;
import java.util.Date;

import com.gr8.bnb.models.User;

/**
 * It defines a user in the web page as it is expected to be stored in
 * 	the database.
 */
public class Message {

	/* DATABASE EMULATION FIELDS */
	private static ArrayList<Message> messages = new ArrayList<Message>();
	private static int next_id = 0;
	
	/* DATABASE ROW FIELDS */
	private int id;
	private User sender;
	private User receiver;
	private Date receivedDate;
	private String message;
	private boolean read;

	
	/**
	 * @param sender: The user who sent the message.
	 * @param receiver: The user who receives the message. 
	 * @param receivedDate: the date in which the message was received.
	 * @param message: The message content.
	 */
	public Message(User sender, User receiver, Date receivedDate, String message) {
		this.id = next_id++;   // Set the id and increase the counter
		this.sender = sender;
		this.receiver = receiver;
		this.receivedDate = receivedDate;
		this.message = message;
		this.read = false;
	}
	
	/* GETTERS AND SETTERS */
	public static ArrayList<Message> getMessages(){
		return messages;
	}
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}
	
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	/* OBJECT FUNCTIONS */

	/**
	 * Check if a user exists on the database and insert it if not.
	 * @return: true if the user was inserted, false otherwise.
	 */
	public boolean save() {
		if (find(this.id) == null)	{
			messages.add(this);
			return true;
		}
		return false;
	}

	/**
	 * Check if a user exists on the database and update it.
	 * @return: true if the user was updated, false otherwise.
	 */
	public boolean update() {
		Message prevMessage = find(this.id);
		if (prevMessage == null) {
			return false;
		}
		messages.remove(prevMessage); // remove previous user
		messages.add(this);           // add updated user
		return true;
	}

	/**
	 * Check if a user exists on the database and delete it if so.
	 * @return: true if the user was inserted, false otherwise.
	 */
	public boolean delete() {
		return messages.remove(this);
	}

	/* DATABASE EMULATION FUCTIONS */

	/**
	 * Create a new message and insert it in the database.
	 *
	 * @param sender: The user who sends the message.
	 * @param receiver: The user who receives the message.
	 * @param receivedDate: Date in which the message was received.
	 * @param message: The content of the message.
	 *
	 * @return: the created message if inserted in the database successfully,
	 */
	public static Message create(User sender, User receiver, Date receivedDate,String message) {
		Message newMessage = new Message(sender, receiver, receivedDate, message);
		if (newMessage.save()) {
			return newMessage;
		}
		return null;
	}

	/**
	 * Find a message in the database by the email
	 *
	 * @param id: Id of the database user we want to find.
	 *
	 * @return: the user if found or null otherwise.
	 */
	public static Message find(int id) {
		for (Message m : messages) {
			if (m.id == id) {
				return m;
			}	
		}
		return null;
	}

	/**
	 * Find all message in the database sent by 'sender' to 'receiver'.
	 * 
	 * @param sender: User who sent the message
	 * @param receiver: User who receives the message.
	 *
	 * @return: A set with the messages sent by 'sender' to 'receiver'.
	 */
	public static ArrayList<Message> findAll(User sender, User receiver){
		ArrayList<Message> messagesSent = new ArrayList<Message>();
		for (Message m : messages) {
			if (m.sender.equals(sender) && m.receiver.equals(receiver)) {
				messagesSent.add(m);
			}
		}
		return messagesSent;
	}
	
	/**
	 * Find all message in the database sent to 'receiver' that are 'read'.
	 * 
	 * @param receiver: User who sent the message
	 * @param read: Describes whether or not the message has been read.
	 *
	 * @return: A set with the messages sent by 'sender' to 'receiver'.
	 */
	public static ArrayList<Message> findAll(User receiver) {
		ArrayList<Message> messagesSent = new ArrayList<Message>();
		for (Message m : messages) {
			if (m.receiver.equals(receiver)) {
				messagesSent.add(m);
			}
		}
		return messagesSent;
	}
	
}
