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

import model.User;
import com.gr8.bnb.helpers.InputChecker;


public class EditProfileHandler implements RequestHandler {
	
	private static final String HOME_JSP  = "/index.jsp";
	
	WebTarget userWebTarget; 
	
	public EditProfileHandler(Client client){
		this.userWebTarget = client.target("http://localhost:8081");
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("isEditProfilePage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SystemException, NotSupportedException {
		/* Get the user params. */
		String name     = request.getParameter("editName");
		String surname  = request.getParameter("editSurname");
		String password = request.getParameter("editPassword");
		String errorMessage = InputChecker.checkUserParameters(name, surname, password);

		/* Get the session user. */
		HttpSession session = request.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if (errorMessage == null) {
			/* Update the session user features. */
			sessionUser.setName(name);
			sessionUser.setSurname(surname);
			sessionUser.setPassword(password);

			/* Perform the update request on the rest api. */
			WebTarget webtargetPath = userWebTarget.path("user").path(sessionUser.getId().toString());
			Builder builder = webtargetPath.request(MediaType.APPLICATION_JSON);

			/* Check the response in order to see if performed successfully. */
			Response res = builder.put(Entity.entity(sessionUser, MediaType.APPLICATION_JSON));
			int status = res.getStatus();
			
			if (status != 200) {
				errorMessage = "Cannot update user";
			}
		}
		
		/* If there is no error update the user in the session. */
		if (errorMessage == null) {
			session.setAttribute("user", sessionUser);
			session.setAttribute("authenticated", true);
		} else {
			request.setAttribute("isEditProfilePage", (Boolean) true);
			request.setAttribute("editProfileErrorMessage", errorMessage);
		}

		/* Redirect to home page */
		return HOME_JSP;
	}
}