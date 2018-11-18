package com.gr8.bnb.handlers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import model.User;
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

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SystemException, NotSupportedException {
		String name     = request.getParameter("editName");
		String surname  = request.getParameter("editSurname");
		String password = request.getParameter("editPassword");
		/* Check if valid parameters an get error */
		String errorMessage = InputChecker.checkUserParameters(name, surname, password);

		HttpSession session = request.getSession();
		User sesionUser = (User) session.getAttribute("user");
		User dbUser = User.findByEmail(ut, em, sesionUser.getEmail());
		
		if( dbUser != null ){
			boolean updated = User.updateByEmail(ut, em, dbUser.getEmail(), name, surname, password);
			
			if (updated) {
				sesionUser.setName(name);
				sesionUser.setSurname(surname);
				sesionUser.setPassword(password);

				session.setAttribute("user", sesionUser);
				session.setAttribute("authenticated", true);
			} else {
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