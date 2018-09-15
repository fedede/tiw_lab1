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
	private static final String HOME_JSP  = "/index.jsp";
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isLoginPage", (Boolean) true);
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");
		//String rememberMe = request.getParameter("remember-me");

		String errorMessage = null;

		HttpSession sesion = request.getSession();
		
		User user = User.find(email, password);
		if (user != null){
			/* set the user and mark the session as authenticate */
			sesion.setAttribute("user", user);
			sesion.setAttribute("authenticated", true);
		}else{				
			/* Open login form on load with error message */
			request.setAttribute("isLoginPage", (Boolean) true);
			errorMessage = "Wrong email or password";
		}

		request.setAttribute("LoginErrorMessage", errorMessage);
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}
}
