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

//import com.gr8.bnb.models.User;
import model.User;
import com.gr8.bnb.helpers.InputChecker;


public class SignupHandler implements RequestHandler {

	private static final String HOME_JSP = "/index.jsp";
	
	
	private EntityManager em;
	private UserTransaction ut;
	
	public SignupHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException{
		request.setAttribute("isSignUpPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		
		String email    = request.getParameter("inputEmail");
		String name     = request.getParameter("inputName");
		String surname  = request.getParameter("inputSurname");
		String password = request.getParameter("inputPassword");

		HttpSession session = request.getSession();

		/* Check if valid parameters an get error */
		String errorMessage = InputChecker.checkUserParameters(name, surname, email, password);

		/* If no error message try to create the user */
		if (errorMessage == null) {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setSurname(surname);
			user.setPassword(password);
			user.setIsAdmin(new byte[1]);
			//.create(name, surname, email, password);
			ut.begin();
			TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
			tq.setParameter("email", email);
			List<User> res = tq.getResultList();
			if( res.isEmpty() ){
				session.setAttribute("user", user);
				session.setAttribute("authenticated", true);
				em.persist(user);
				try {
					ut.commit();
				} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
						| HeuristicRollbackException e) {
					// TODO Auto-generated catch block
					errorMessage = "Unknown error creating your account. Try again.";
					e.printStackTrace();
				}
			}else {				
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