package com.gr8.bnb.handlers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.gr8.bnb.models.House;
import model.House;

/**
 * Servlet implementation class searchServlet
 */
public class ResultsHandler implements  RequestHandler {

	private final String RESULTS_PAGE = "/results.jsp";
	
	private final WebTarget houseWebTarget;
	
	public ResultsHandler(Client client){
		this.houseWebTarget = client.target("http://localhost:8082/");
	}


	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotSupportedException, SystemException {
		String errorMessage = null;

		/* Get parameters from modal. */
		String city =  request.getParameter("city").toUpperCase();
		String startDate =  request.getParameter("date-start");
		String endDate =  request.getParameter("date-end");
		String minPrice = request.getParameter("min-price");
		String maxPrice = request.getParameter("max-price");
		String type = request.getParameter("ac-type");
		String guestCount =  request.getParameter("guest-count");
		
		/* Parse parameters to its tipes. */
		//Float minPrice = (sMinPrice == null) ? null : Float.parseFloat(sMinPrice);
		//Float maxPrice = (sMaxPrice == null) ? null : Float.parseFloat(sMaxPrice);
		//Integer guestCount = (sGuestCount ==  null) ? null : Integer.parseInt(sGuestCount);
		Boolean shared = null;
		if (type != null) {
			shared = type.equals("shared") ? false : true; 
		}
		
		/* Set the houses path. */
		WebTarget housePath = houseWebTarget.path("houses");
		
		/* Add to the query the non null parameters. */
		if (city != null) {
			housePath.queryParam("city", city);
		}
		
		if (minPrice != null) {
			housePath.queryParam("minPrice", minPrice);
		}
		
		if (maxPrice != null) {
			housePath.queryParam("maxPrice", maxPrice);
		}
		
		if (guestCount != null) {
			housePath.queryParam("guestCount", guestCount);
		}
		
		if (shared != null) {
			housePath.queryParam("shared", shared);
		}
		
		if (startDate != null) {
			housePath.queryParam("startDate", startDate);
		}
		
		if (endDate != null) {
			housePath.queryParam("endDate", endDate);
		}
		
		/* Perform the request. */
		Builder builder = housePath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();
		
		House[] houses = null;
		if (res.getStatus() == HttpServletResponse.SC_OK) {
			houses = res.readEntity(House[].class);
			request.setAttribute("houses", houses);
		} else {
			errorMessage = "Couldn't read houses";
			request.setAttribute("errorMessage", errorMessage);
		}
		
		return RESULTS_PAGE;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String handlePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return null;
	}
}
