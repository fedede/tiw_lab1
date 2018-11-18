package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import com.gr8.bnb.helpers.MessageManager;

// Models
import model.User;


public class MessageHandler implements RequestHandler {
	private static final String MESSAGES_PAGE  = "/messages";

	private MessageManager messageManager;
	private EntityManager em;
	private UserTransaction ut;
	
	public MessageHandler(EntityManager em, UserTransaction ut, MessageManager messageManager){
		this.em = em;
		this.ut = ut;
		this.messageManager = messageManager;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}
	
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Get parameters */
		String message = (String) request.getParameter("message");
		String ownerEmail = request.getParameter("owner");
		String senderEmail = request.getParameter("receiver");

		User owner = User.findByEmail(ut, em, ownerEmail);
		User sender = User.findByEmail(ut, em, senderEmail);
		
		String errorMessage = null;
		if (owner == null) {
			errorMessage = "Invalid receiver email";
		} else if (sender == null) {
			errorMessage = "Invalid sender email";
		} else {
			messageManager.send(senderEmail, ownerEmail, message);
		}
		
		if (errorMessage == null) {
			request.setAttribute("errorMessage", errorMessage);
		}
		
		return MESSAGES_PAGE;
	}
}
