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
import com.gr8.bnb.helpers.InputChecker;


@WebServlet(
		urlPatterns="/editprofile",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class EditProfileServlet extends HttpServlet {

	private static final String HOME_JSP     = "/index.jsp";

	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isEditProfilePage", (Boolean) true);
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name     = request.getParameter("editName");
		String surname  = request.getParameter("editSurname");
		String password = request.getParameter("editPassword");

		/* Check if valid parameters an get error */
		String errorMessage = InputChecker.checkUserParameters(name, surname, password);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		/* Check that a user is associated with this session */
		if (user != null) {
			user.setName(name);
			user.setSurname(surname);
			user.setPassword(password);
			
			session.setAttribute("user", user);

			/* Check if updated successfully on the database */
			if (!user.update()) {
				errorMessage = "Cannot update user on the database";
			}
		} else {
			errorMessage = "You are trying to access without login"; // This message should never be displayed
		}

		/* If error message send message to the view */
		if (errorMessage != null) {
			request.setAttribute("isEditProfilePage", (Boolean) true);
			request.setAttribute("editProfileErrorMessage", errorMessage);
		}

		/* Redirect to home page */
		config.getServletContext().getRequestDispatcher(HOME_JSP).forward(request, response);
	}
}
