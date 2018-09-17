package com.gr8.bnb.controllers;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.HOLDING;

import com.gr8.bnb.handlers.RequestHandler;

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
	
	private final String HOME_JSP = "index.jsp";
	
	private final String LOGIN_PAGE = "/login";
	private final String LOGOUT_PAGE = "/logout";
	private final String SIGNUP_PAGE = "/signup";
	private final String HOUSES_PAGE = "/houses";
	private final String MESSAGES_PAGE = "/messages";
	private final String MESSAGE_PAGE = "/message";
	private final String RESULTS_PAGE = "/results";
	private final String EDITPROFILE_PAGE = "/editprofile";

	
	
	public void init() throws ServletException {
		handlerHash.put(LOGIN_PAGE, new com.gr8.bnb.handlers.LoginHandler());
		handlerHash.put(LOGOUT_PAGE, new com.gr8.bnb.handlers.LogoutHandler());
		handlerHash.put(SIGNUP_PAGE, new com.gr8.bnb.handlers.SignupHandler());
		handlerHash.put(HOUSES_PAGE, new com.gr8.bnb.handlers.HouseHandler());
		handlerHash.put(MESSAGES_PAGE, new com.gr8.bnb.handlers.MessagesServlet());
		handlerHash.put(MESSAGE_PAGE, new com.gr8.bnb.handlers.MessageHandler());
		handlerHash.put(RESULTS_PAGE, new com.gr8.bnb.handlers.ResultsHandler());
		handlerHash.put(EDITPROFILE_PAGE, new com.gr8.bnb.handlers.EditProfileHandler());
		
		noAuthGet.add(LOGIN_PAGE);
		noAuthGet.add(SIGNUP_PAGE);
		noAuthGet.add(HOUSES_PAGE);
		noAuthGet.add(RESULTS_PAGE);
		
		noAuthPost.add(LOGIN_PAGE);
		noAuthPost.add(SIGNUP_PAGE);
		
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String viewName = getView(request, response);

		request.getServletContext().getRequestDispatcher(viewName).forward(request, response); 
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String viewName = getView(request, response);

		request.getServletContext().getRequestDispatcher(viewName).forward(request, response); 
	}
	
	private String getView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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