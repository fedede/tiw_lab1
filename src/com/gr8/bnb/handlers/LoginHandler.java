package com.gr8.bnb.handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.User;


public class LoginHandler implements RequestHandler {

	private static final String HOME_JSP  = "/index.jsp";
	
	WebTarget userWebTarget;
	
	public LoginHandler(Client client){
		this.userWebTarget = client.target("http://localhost:8081");
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isLoginPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		String errorMessage = null;
		
		/* Get the login and password email. */
		String email = request.getParameter("loginEmail");
		String password = request.getParameter("loginPassword");

		/* Perform the get request. */
		WebTarget userPath = userWebTarget.path("users")
				.queryParam("email", email)
				.queryParam("password", password);
		
		Builder builder = userPath.request(MediaType.APPLICATION_JSON);
		
		Response res = builder.get();
		
		HttpSession session = request.getSession();
		if (res.getStatus() == HttpServletResponse.SC_OK) {
			User user = res.readEntity(User.class);
		
			/* set the user and mark the session as authenticate */
			session.setAttribute("user", user);
			session.setAttribute("authenticated", true);
		} else {
			errorMessage = "Invalid email or password";
		}


		/* If error message then set in the request. */
		if (errorMessage != null) {
			request.setAttribute("LoginErrorMessage", errorMessage);
		}
		
		return HOME_JSP;
	}
}
