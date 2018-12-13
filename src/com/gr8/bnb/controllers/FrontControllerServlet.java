package com.gr8.bnb.controllers;


import java.io.IOException; 
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.gr8.bnb.handlers.RequestHandler;
import com.gr8.bnb.helpers.MessageManager;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**  
 * Servlet implementation class ControllerServlet
 */ 
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    
	private ArrayList<String> noAuthPost = new ArrayList<String>();
	private ArrayList<String> noAuthGet  = new ArrayList<String>();
	
	private Map<String, RequestHandler> handlerHash  = new HashMap<String, RequestHandler>();
	
	private final String HOME_JSP = "/index.jsp";
	
	private final String LOGIN_PAGE = "/login"; 
	private final String LOGOUT_PAGE = "/logout";
	private final String SIGNUP_PAGE = "/signup";
	private final String HOUSES_PAGE = "/house";
	private final String MESSAGES_PAGE = "/messages";
	private final String MESSAGE_PAGE = "/message";
	private final String RESULTS_PAGE = "/results";
	private final String EDITPROFILE_PAGE = "/editprofile";
	private final String PUBLISHHOUSE_PAGE = "/publishHouse";
	private final String USER_HOUSES_PAGE = "/userHouses";
	private final String EDIT_HOUSE_PAGE = "/editHouse";
	private final String DELETE_HOUSE_PAGE = "/deleteHouse";
	private final String BOOKING_ACCEPT_PAGE = "/booking";
	
	public void init() throws ServletException {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		
		MessageManager messageManager = new MessageManager(client);
		
		/* Create helper for sending and receiving messages */
		handlerHash.put(LOGIN_PAGE, new com.gr8.bnb.handlers.LoginHandler(client));
		handlerHash.put(LOGOUT_PAGE, new com.gr8.bnb.handlers.LogoutHandler());
		handlerHash.put(SIGNUP_PAGE, new com.gr8.bnb.handlers.SignupHandler(client));
		handlerHash.put(HOUSES_PAGE, new com.gr8.bnb.handlers.HouseHandler(client, messageManager));
		handlerHash.put(MESSAGES_PAGE, new com.gr8.bnb.handlers.MessagesHandler(messageManager));
		handlerHash.put(MESSAGE_PAGE, new com.gr8.bnb.handlers.MessageHandler(client, messageManager));
		handlerHash.put(RESULTS_PAGE, new com.gr8.bnb.handlers.ResultsHandler(client));
		handlerHash.put(EDITPROFILE_PAGE, new com.gr8.bnb.handlers.EditProfileHandler(client));
		handlerHash.put(PUBLISHHOUSE_PAGE, new com.gr8.bnb.handlers.HousePublishHandler(client));
		handlerHash.put(USER_HOUSES_PAGE, new com.gr8.bnb.handlers.UserHousesHandler(client));
		handlerHash.put(EDIT_HOUSE_PAGE, new com.gr8.bnb.handlers.EditHouseHandler(client));
		handlerHash.put(DELETE_HOUSE_PAGE, new com.gr8.bnb.handlers.DeleteHouseHandler(client));
		handlerHash.put(BOOKING_ACCEPT_PAGE, new com.gr8.bnb.handlers.BookingHandler(client, messageManager));
		
		noAuthGet.add(LOGIN_PAGE);
		noAuthGet.add(SIGNUP_PAGE);
		noAuthGet.add(HOUSES_PAGE);
		noAuthGet.add(RESULTS_PAGE);

		noAuthPost.add(LOGIN_PAGE);
		noAuthPost.add(SIGNUP_PAGE);
		
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String viewName = "";
		try {
			viewName = getView(request, response);
		} catch (NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getServletContext().getRequestDispatcher(viewName).forward(request, response); 
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String viewName = "";
		try {
			viewName = getView(request, response);
		} catch (NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getServletContext().getRequestDispatcher(viewName).forward(request, response); 
	}
	
	private String getView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotSupportedException, SystemException {
		String viewName;
		
		String path = request.getServletPath();
		RequestHandler handler = handlerHash.get(path);

		HttpSession session = request.getSession();
		boolean authenticated = Boolean.TRUE == session.getAttribute("authenticated");
		
		if (handler == null) {
			viewName = "/sorryNotFound.jsp";
		} else if (!authenticated && !noAuthGet.contains(path)){
			viewName = "/forbidden.jsp";
		} else if (request.getMethod().equalsIgnoreCase("POST")){
			viewName = handler.handlePost(request, response);
		} else {
			viewName = handler.handleGet(request, response);
		}
		
		if (viewName == null) {
			viewName = HOME_JSP;
		}
		
		return viewName;
	}
}
