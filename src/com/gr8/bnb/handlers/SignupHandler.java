package com.gr8.bnb.handlers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.gr8.bnb.models.User;
import model.User;
import com.gr8.bnb.helpers.InputChecker;


public class SignupHandler implements RequestHandler {

	private static final String HOME_JSP = "/index.jsp";
	private static final int HTTP_CREATED = 201;
	
	WebTarget userWebtarget;
	public SignupHandler(Client client){
		this.userWebtarget = client.target("http://localhost:8081/");
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException{
		request.setAttribute("isSignUpPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		/* Get new user parameters. */
		String email    = request.getParameter("inputEmail");
		String name     = request.getParameter("inputName");
		String surname  = request.getParameter("inputSurname");
		String password = request.getParameter("inputPassword");
		
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setSurname(surname);
		user.setPassword(password);
		
		/* Check if valid parameters an get error */
		String errorMessage = InputChecker.checkUserParameters(name, surname, email, password);

		/* Post user to the REST API. */
		WebTarget userPath = userWebtarget.path("user");
		Builder builder = userPath.request(MediaType.APPLICATION_JSON);
		
		Response res = builder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		
		/* Check if resource is created. */
		if (res.getStatus() == HTTP_CREATED ) {
			HttpSession session = request.getSession();
			User sessionUser = res.readEntity(User.class);
			
			session.setAttribute("user", sessionUser);
			session.setAttribute("authenticated", true);
		} else {
			errorMessage = "Could not create user";
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
