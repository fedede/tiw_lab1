package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//import com.gr8.bnb.models.User;
import model.User;
//import com.gr8.bnb.helpers.InputChecker;
import model.House;

public class DeleteHouseHandler implements RequestHandler {

	private static final String HOME_JSP = "/index.jsp";
	
	WebTarget houseWebTarget;
	public DeleteHouseHandler(Client client){
		this.houseWebTarget = client.target("http://localhost:8082/");
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException{
		request.setAttribute("isHousePubPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		/* Get parameters. */
		String houseId = request.getParameter("houseId");
		

		/* Check house exists. */
		WebTarget housePath = houseWebTarget.path("house").path(houseId);
		Builder builder = housePath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();
		
		if (res.getStatus() != HttpServletResponse.SC_OK) {
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		
		/* Check house is owned by current user. */
		User user = (User) request.getSession().getAttribute("user");
		House house = res.readEntity(House.class);
		
		if (house == null || user == null || house.getOwner().getId() != user.getId()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

		/* Delete the house. */
		WebTarget deleteHousePath = houseWebTarget.path("house").path(houseId);
		Builder deleter = deleteHousePath.request(MediaType.APPLICATION_JSON);
		Response res2 = deleter.delete();
		
		if (res2.getStatus() != HttpServletResponse.SC_OK) {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
		
		/* Redirect to home page */
		return HOME_JSP;
	}
}