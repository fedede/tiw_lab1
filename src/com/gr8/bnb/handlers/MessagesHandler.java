package com.gr8.bnb.handlers;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gr8.bnb.helpers.MessageManager;

import model.Message;
// Models
import model.User;

public class MessagesHandler implements RequestHandler {
	private static final String MESSAGES_JSP  = "/messages.jsp";

	private MessageManager messageManager;
	
	public MessagesHandler(MessageManager messageManager){
		this.messageManager = messageManager;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		
		User receiver = (User) httpSession.getAttribute("user");

		Message[] messages = messageManager.receive(receiver);
		for(Message message: messages){
			message.setContent(message.getContent().replace("<IP>", request.getLocalAddr()));
		}
		request.setAttribute("messages", messages);
		
		return MESSAGES_JSP;
	}
	
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return handleGet(request, response);
	}
}