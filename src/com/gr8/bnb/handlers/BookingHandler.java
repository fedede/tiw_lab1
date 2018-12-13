package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gr8.bnb.helpers.MessageManager;

import model.Message;
import model.Transaction;
import model.User;

public class BookingHandler implements RequestHandler {

	private static final String MESSAGES_JSP  = "/messages.jsp";
	private static final int HTTP_CREATED = 201;
	
	WebTarget transactionWebTarget;
	MessageManager messageManager;
	
	public BookingHandler(Client client, MessageManager messageManager){
		this.transactionWebTarget = client.target("http://localhost:8083/");
		this.messageManager = messageManager;

	}
	
	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		boolean accept = Boolean.parseBoolean(request.getParameter("accept"));
		String transactionId = request.getParameter("transaction");
		
		WebTarget transactionPath = null;
		
		if(accept == true){
			transactionPath = transactionWebTarget.path("transaction/"+ transactionId + "/accept");
			
		}else{
			transactionPath = transactionWebTarget.path("transaction/"+ transactionId + "/reject");
		}
		
		
		Builder builder = transactionPath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();
		if (res.getStatus() != HTTP_CREATED ) {
			Transaction tr = res.readEntity(Transaction.class);
			HttpSession httpSession = request.getSession();
			User owner = (User) httpSession.getAttribute("user");
			String message = "Your request for booking the house " 
							+ tr.getHouse().getName() + " in " 
							+ tr.getHouse().getCity() + " has been ";
			if(accept){
				message += "accepted!";
			}
			else{
				message += "rejected :(";
			}
			messageManager.send(owner, tr.getInvoiced(), message);
			
			Message[] messages = messageManager.receive(owner);
			request.setAttribute("messages", messages);
		}
		
		return MESSAGES_JSP;
	}


	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handlePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotSupportedException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}
}
