package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


// Models
import model.House;
import model.User;


/**
 * Servlet implementation class HouseServlet
 */
public class UserHousesHandler implements RequestHandler {

	private final String USER_HOUSES_PAGE = "/userHouses.jsp";
	
	private WebTarget housesWebTarget;
	
	public UserHousesHandler(Client client) {
		this.housesWebTarget = client.target("http://localhost:8082/");
	}

	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		String errorMessage = null;
		

		/* Get houses path */
		User sessionUser = (User) request.getSession().getAttribute("user");
		WebTarget housesPath = housesWebTarget.path("users")
				.path(sessionUser.getId().toString())
				.path("houses");
		
		Builder builder = housesPath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();
		
		/* If get success status then set the houses. */
		House[] houses = null;

		if (res.getStatus() == HttpServletResponse.SC_OK) {
			houses = res.readEntity(House[].class);
		} else {
			errorMessage = "Cannot retrieve user houses";
		}
		
		request.setAttribute("houses", houses);
		
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
		}

		return USER_HOUSES_PAGE;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		return null;
	}

}