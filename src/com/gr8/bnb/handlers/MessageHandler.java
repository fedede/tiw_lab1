package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import java.util.Date;

// Models
import com.gr8.bnb.models.Message;
import com.gr8.bnb.models.User;


public class MessageHandler implements RequestHandler {
	private static final String MESSAGES_PAGE  = "/messages";
	
	private EntityManager em;
	private UserTransaction ut;
	
	public MessageHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}
	
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User owner = User.find(request.getParameter("owner"));
		User receiver = User.find(request.getParameter("receiver"));
		String message = (String) request.getParameter("message");
		Date receivedDate = new Date();
			
		Message.create(owner,receiver, receivedDate, message);

		return MESSAGES_PAGE;
	}

}
