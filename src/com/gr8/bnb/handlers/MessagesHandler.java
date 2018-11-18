package com.gr8.bnb.handlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import com.gr8.bnb.helpers.MessageManager;

// Models
import model.User;

public class MessagesHandler implements RequestHandler {
	private static final String MESSAGES_JSP  = "/messages.jsp";

	private MessageManager messageManager;
	private EntityManager em;
	private UserTransaction ut;
	
	public MessagesHandler(EntityManager em, UserTransaction ut, MessageManager messageManager){
		this.em = em;
		this.ut = ut;
		this.messageManager = messageManager;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		
		User receiver = (User) httpSession.getAttribute("user");

		String receiverEmail = receiver.getEmail();
		
		ArrayList<TextMessage> messages = messageManager.receive(receiverEmail);
		
		request.setAttribute("messages", messages);
		return MESSAGES_JSP;
	}
	
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return handleGet(request, response);
	}
}