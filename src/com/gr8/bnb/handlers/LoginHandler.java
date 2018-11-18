package com.gr8.bnb.handlers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
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


public class LoginHandler implements RequestHandler {

	private static final String HOME_JSP  = "/index.jsp";
	
	private EntityManager em;
	private UserTransaction ut;
	
	public LoginHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isLoginPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		
		String email = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");
		//String rememberMe = request.getParameter("remember-me");

		String errorMessage = null;

		HttpSession session = request.getSession();
		
		ut.begin();
		TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :pass", User.class);
		tq.setParameter("email", email);
		tq.setParameter("pass", password);
		List<User> res = tq.getResultList();
		
		if ( !res.isEmpty()){
			User user = res.get(0);
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
