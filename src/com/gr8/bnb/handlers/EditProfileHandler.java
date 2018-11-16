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
		ut.begin();
		TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		tq.setParameter("email", sesionUser.getEmail());
		List<User> res = tq.getResultList();
		User dbUser = res.get(0);
		
		if( dbUser != null ){
			Query uq = em.createQuery("UPDATE User u SET u.name = :name, u.surname = :sname, u.password = :pass WHERE u.email = :email");
			uq.setParameter("name", name);
			uq.setParameter("sname", surname);
			uq.setParameter("pass", password);
			uq.setParameter("email", dbUser.getEmail());
			
			uq.executeUpdate();
			try {
				ut.commit();
				sesionUser.setName(name);
				sesionUser.setSurname(surname);
				sesionUser.setPassword(password);
			} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
					| HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				errorMessage = e.getMessage();
				e.printStackTrace();
			}

			session.setAttribute("user", sesionUser);
			session.setAttribute("authenticated", true);
		
		}
		
		/* Check that a user is associated with this session */
		/*
		if (sesionUser != null) {
			sesionUser.setName(name);
			sesionUser.setSurname(surname);
			sesionUser.setPassword(password);
			
			session.setAttribute("user", sesionUser);

			/* Check if updated successfully on the database */
			/*
			if (!user.update()) {
				errorMessage = "Cannot update user on the database";
			}
			
		}
			 */
		
		/* If error message send message to the view */
		if (errorMessage != null) {
			request.setAttribute("isEditProfilePage", (Boolean) true);
			request.setAttribute("editProfileErrorMessage", errorMessage);
		}

		/* Redirect to home page */
		return HOME_JSP;
	}
}
