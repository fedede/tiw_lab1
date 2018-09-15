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
		urlPatterns="/signup",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class SignupServlet extends HttpServlet {

	private static final String HOME_JSP     = "/index.jsp";

	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isSignUpPage", (Boolean) true);
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email    = request.getParameter("inputEmail");
		String name     = request.getParameter("inputName");
		String surname  = request.getParameter("inputSurname");
		String password = request.getParameter("inputPassword");

		HttpSession sesion = request.getSession();

		/* Check if valid parameters an get error */
		String errorMessage = checkUserParameters(name, surname, email, password);

		/* If no error message try to create the user */
		if (errorMessage == null) {
			User user = User.create(name, surname, email, password);
			if (user != null){				
				sesion.setAttribute("user", user);
				sesion.setAttribute("authenticated", true);
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
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}
	
	/**
	 * Check if the input user parameters are valid in order to sign up a new user.
	 *
	 * @param name Name of the user we want to register, error if blank.
	 * @param surname Surname of the user we want to register, error if blank.
	 * @param email Email of the user we want to register, error if blank or not email formatted.
	 * @param password Password of the user we want to register, error less than 8 characters
	 * @return Error message if error, null otherwise.
	 */
	private String checkUserParameters(String name, String surname, String email, String password) {
		final String EMAIL_PATTERN = "^[\\w.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		String message = null;
		if (name == null || name.isEmpty()) {
			message = "Name cannot be blank.";
		} else if (surname == null || surname.isEmpty()) {
			message = "Surname cannot be blank.";
		} else if (email == null || email.isEmpty()) {
			message = "Email cannot be blank.";
		} else if (!email.matches(EMAIL_PATTERN)) {
			message = "Email address is not valid. It must have the format: ***@***.**";
		} else if (password == null || password.length() < 8) {
			message = "Password shall contain at least 8 characters.";
		} 
		return message;
	}
}
