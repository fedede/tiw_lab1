package com.gr8.bnb.handlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

// Models
import model.Message;
import model.User;


public class MessagesServlet implements RequestHandler {
	private static final String MESSAGES_JSP  = "/messages.jsp";
	
	private EntityManager em;
	private UserTransaction ut;
	
	public MessagesServlet(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User receiver = (User) session.getAttribute("user");
		ArrayList<Message> messages = null; //Messa
			
		request.setAttribute("messages", messages);
		return MESSAGES_JSP;
	}
	
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}
}
