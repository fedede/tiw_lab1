package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gr8.bnb.models.User;
import com.gr8.bnb.helpers.InputChecker;


public class SignupHandler implements RequestHandler {

	private static final String HOME_JSP     = "/index.jsp";

	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isSignUpPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email    = request.getParameter("inputEmail");
		String name     = request.getParameter("inputName");
		String surname  = request.getParameter("inputSurname");
		String password = request.getParameter("inputPassword");

		HttpSession session = request.getSession();

		/* Check if valid parameters an get error */
		String errorMessage = InputChecker.checkUserParameters(name, surname, email, password);

		/* If no error message try to create the user */
		if (errorMessage == null) {
			User user = User.create(name, surname, email, password);
			if (user != null){				
				session.setAttribute("user", user);
				session.setAttribute("authenticated", true);
			} else {				
				errorMessage = "This email account is already registered";
			}
		}

		/* If error message send message to the view */
		if (errorMessage != null) {
			request.setAttribute("isSignUpPage", (Boolean) true);
			request.setAttribute("signUpErrorMessage", errorMessage);
		}

		/* Redirect to home page */
		return HOME_JSP;
	}
}
