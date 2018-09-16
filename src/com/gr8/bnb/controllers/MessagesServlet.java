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
		urlPatterns="/messages",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class MessageServlet extends HttpServlet {
	private static final String LOGIN_PAGE  = "/login";
	private static final String MESSAGES_JSP  = "/messages.jsp";
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean authenticated = Boolean.TRUE == session.getAttribute("authenticated");
		
		String page = LOGIN_PAGE;
		if (authenticated) {
			page = MESSAGES_JSP;
			
			User receiver = (User) session.getAttribute("user");
			ArrayList<Message> messages = Message.findAll(receiver);
			
			request.setAttribute("messages", messages);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
