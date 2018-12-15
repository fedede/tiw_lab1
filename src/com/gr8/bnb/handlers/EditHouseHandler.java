package com.gr8.bnb.handlers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gr8.bnb.helpers.InputChecker;

//import com.gr8.bnb.models.User;
import model.User;
//import com.gr8.bnb.helpers.InputChecker;
import model.House;

public class EditHouseHandler implements RequestHandler {

	private static final String HOME_JSP = "/index.jsp";
	
	WebTarget houseWebTarget;
	public EditHouseHandler(Client client){
		this.houseWebTarget = client.target("http://localhost:8082/");
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException{
		return null;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		String errorMessage = null;
		
		/* Get parameters. */
		String houseId = request.getParameter("editHouseId");
		String houseName = request.getParameter("editHouseName");
		String fullDescription = request.getParameter("editHouseFullDescription");
		String shortDescription = request.getParameter("editHouseShortDescription");
		String type = request.getParameter("editHouseType");
		String sPrice = request.getParameter("editHousePrice");
		String sMaxGuests  = request.getParameter("editHouseGuestNumber");
		String photoURL = request.getParameter("editHousePhoto");
		String city = request.getParameter("editHouseCity");
		String sStartDate = request.getParameter("editHouseDateStart");
		String sEndDate = request.getParameter("editHouseDateEnd");
		
		HttpSession session = request.getSession();

		/* Check if valid parameters an get error */
		errorMessage = InputChecker.checkHouseParameters(houseName, fullDescription, 
				shortDescription, type, sMaxGuests, photoURL, city, sPrice, sStartDate, sEndDate);
		
		int guestNum = 0;
		try{
			guestNum = Integer.parseInt(sMaxGuests);
		}catch(Exception e){
			guestNum = 0;
		}

		if(guestNum < 0 || guestNum > 4){
			errorMessage = "Error in guest number2";
		}
		
		float price = -1;
		try{
			price = Float.parseFloat(sPrice);
		}catch(Exception e){
			price = -1;
		}
		
		if(price < 0){
			errorMessage = "Error in price2" + sPrice + "|| " + price;
		}

		Date startDate;
		Date endDate;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try{
			startDate = (Date) format.parse(sStartDate);
			endDate = (Date) format.parse(sEndDate);
		
		}catch(java.text.ParseException e){
			errorMessage = "Error with dates";
			throw new ServletException("Date: "+ e.getMessage());
		}
		
		/* If no error message try to create the user */
		if (errorMessage == null) {
			User owner = (User) session.getAttribute("user");
			House house = new House();
			house.setStartDate(startDate);
			house.setEndDate(endDate);
			house.setName(houseName);
			house.setFullDescription(fullDescription);
			house.setShortDescription(shortDescription);
			house.setCity(city.toUpperCase());
			house.setImageUrl(photoURL);
			house.setMaxGuests(guestNum);
			house.setShared(type.equals("shared"));
			house.setPrice(price);
			house.setOwner(owner);
			
			/* Perform post request to API. */
			WebTarget housePath = houseWebTarget.path("house").path(houseId);
			Builder builder = housePath.request(MediaType.APPLICATION_JSON);
			
			Response res = builder.put(Entity.entity(house, MediaType.APPLICATION_JSON));
			
			/* Check if resource is created. */
			if (res.getStatus() != HttpServletResponse.SC_OK ) {
				errorMessage = "Problem publishing house";
			}

		}
		
		/* If error message send message to the view */
		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
		}
		/* Redirect to home page */
		return HOME_JSP;
	}
}
