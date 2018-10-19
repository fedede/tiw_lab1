package com.gr8.bnb.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Iterator;

// Models
import com.gr8.bnb.models.House;
import com.gr8.bnb.models.Message;
import com.gr8.bnb.models.User;


@WebServlet(
		urlPatterns="/message",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class MessageServlet extends HttpServlet {
	private static final String MESSAGES_PAGE  = "/messages";
	private static final String LOGIN_PAGE = "/login";
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean authenticated = Boolean.TRUE == session.getAttribute("authenticated");
		
		String page = LOGIN_PAGE;
		if (authenticated) {
			page = MESSAGES_PAGE;
			
			User owner = User.find(request.getParameter("owner"));
			User receiver = User.find(request.getParameter("receiver"));
			String message = (String) request.getParameter("message");
			Date receivedDate = new Date();
			
			Message.create(owner,receiver, receivedDate, message);
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
