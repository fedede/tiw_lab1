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

	private static final String SIGNUP_JSP   = "/signup.jsp";
	private static final String HOME_JSP     = "/home.jsp";

	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		config.getServletContext().getRequestDispatcher(SIGNUP_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name     = request.getParameter("name");
		String surname  = request.getParameter("surname");
		String email    = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		String message = checkUserParameters(name, surname, email, password, repassword);
		String page = SIGNUP_JSP;
		HttpSession sesion = request.getSession();

		if (message == null) {
			User user = User.create(name, surname, email, password);
			if (user != null){				
				page = HOME_JSP;
				sesion.setAttribute("user", user);
				sesion.setAttribute("authenticated", true);
			} else {				
				message = "This email account is already registered";
			}
		}
		request.setAttribute("message", message);
		config.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
	
	/**
	 * Check if the input user parameters are valid in order to sign up a new user.
	 *
	 * @param name Name of the user we want to register, error if blank.
	 * @param surname Surname of the user we want to register, error if blank.
	 * @param email Email of the user we want to register, error if blank or not email formatted.
	 * @param password Password of the user we want to register, error if does not match repassword or less than 8 characters
	 * @param repassword Repeated password, error if does not match password
	 * @return Error message if error, null otherwise.
	 */
	private String checkUserParameters(String name, String surname, String email, String password, String repassword) {
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
		} else if (!password.equals(repassword)) {
			message = "Password does not match with the retype password";
		}
		return message;
	}
}