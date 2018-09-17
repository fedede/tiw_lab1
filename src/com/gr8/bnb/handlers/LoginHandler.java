package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gr8.bnb.models.User;


public class LoginHandler implements RequestHandler {

	private static final String HOME_JSP  = "/index.jsp";
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isLoginPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");
		//String rememberMe = request.getParameter("remember-me");

		String errorMessage = null;

		HttpSession session = request.getSession();
		
		User user = User.find(email, password);
		if (user != null){
			/* set the user and mark the session as authenticate */
			session.setAttribute("user", user);
			session.setAttribute("authenticated", true);
		}else{				
			/* Open login form on load with error message */
			request.setAttribute("isLoginPage", (Boolean) true);
			errorMessage = "Wrong email or password";
		}

		if (errorMessage != null) {
			request.setAttribute("LoginErrorMessage", errorMessage);
		}
		
		return HOME_JSP;
	}
}
