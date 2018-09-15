package com.gr8.bnb.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
		urlPatterns="/logout",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class LogoutServlet extends HttpServlet {

	private static final String HOME_JSP  = "/index.jsp";
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String errorMessage = null;
		
		Boolean authenticated = (Boolean) session.getAttribute("authenticated");
		if (authenticated){
			session.setAttribute("user", null);
			session.setAttribute("authenticated", false);
		} else {				
			errorMessage = "You are not authenticated you cannot log out";
		}

		if (errorMessage != null) {
			request.setAttribute("LogoutErrorMessage", errorMessage);
		}
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}
}
