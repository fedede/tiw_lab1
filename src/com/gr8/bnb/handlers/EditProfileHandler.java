package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import com.gr8.bnb.models.User;
import com.gr8.bnb.helpers.InputChecker;


public class EditProfileHandler implements RequestHandler {
	
	private static final String HOME_JSP  = "/index.jsp";
	
	private EntityManager em;
	private UserTransaction ut;
	
	public EditProfileHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isEditProfilePage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		}

		/* If error message send message to the view */
		if (errorMessage != null) {
			request.setAttribute("isEditProfilePage", (Boolean) true);
			request.setAttribute("editProfileErrorMessage", errorMessage);
		}

		/* Redirect to home page */
		return HOME_JSP;
	}
}
