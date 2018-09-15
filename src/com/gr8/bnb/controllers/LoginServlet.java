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

import com.gr8.bnb.models.User;


@WebServlet(
		urlPatterns="/login",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final String LOGIN_JSP = "/login.jsp";
	private static final String HOME_JSP  = "/home.jsp";
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		config.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = null;
		String page = LOGIN_JSP;
		HttpSession sesion = request.getSession();
		
		User user = User.find(email, password);
		if (user != null){
			page = HOME_JSP;
			sesion.setAttribute("user", user);
			sesion.setAttribute("authenticated", true);
				
		}else{				
			message = "Wrong email or password";
		}
		request.setAttribute("message", message);
		config.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}
