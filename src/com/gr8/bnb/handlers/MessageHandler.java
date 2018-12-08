package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gr8.bnb.helpers.MessageManager;

import model.User;

public class MessageHandler implements RequestHandler {
	private static final String MESSAGES_PAGE  = "/messages";
	
	MessageManager messageManager;
	WebTarget userWebtarget;
	
	public MessageHandler(Client client, MessageManager messageManager){
		this.userWebtarget = client.target("http://localhost:8081/");
		this.messageManager = messageManager;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}
	
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage = null;
		
		/* Get parameters */
		String message = (String) request.getParameter("message");
		String ownerId = request.getParameter("ownerId");
		User sender = (User) request.getSession().getAttribute("user");

		/* Get data from the user API. */
		WebTarget userPath = userWebtarget.path("users").path(ownerId);
		Builder builder = userPath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();

		/* If owner exists then get message data. */
		if (res.getStatus() == 200) {
			User owner = res.readEntity(User.class);
		
			boolean success = messageManager.send(sender, owner, message);
			if (!success) {
				errorMessage = "It was not possible to send message";
			}
		}
		
		if (errorMessage == null) {
			request.setAttribute("errorMessage", errorMessage);
		}
		
		return MESSAGES_PAGE;
	}
}
