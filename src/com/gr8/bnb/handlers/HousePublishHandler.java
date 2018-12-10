package com.gr8.bnb.handlers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.gr8.bnb.models.User;
import model.User;
//import com.gr8.bnb.helpers.InputChecker;
import model.House;
import model.Transaction;

public class HousePublishHandler implements RequestHandler {

	private static final String HOME_JSP = "/index.jsp";
	private static final int HTTP_CREATED = 201;
	
	WebTarget houseWebTarget;
	public HousePublishHandler(Client client){
		this.houseWebTarget = client.target("http://localhost:8082/");
	}
	
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException{
		request.setAttribute("isHousePubPage", (Boolean) true);
		return HOME_JSP;
	}

	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {

		
		String houseName  = request.getParameter("inputHousename");
		String fullDesc     = request.getParameter("inputFulldescription");
		String shortDesc  = request.getParameter("inputShortdescription");
		String type = request.getParameter("inputHousetype");
		String sPrice = request.getParameter("inputHouseprice");
		String sGuestNum  = request.getParameter("inputGuestnumber");
		String photo = request.getParameter("inputHousephoto");
		String city = request.getParameter("inputHousecity");
		String sInitDate = request.getParameter("inputHousedatestart");
		String sEndDate = request.getParameter("inputHousedateend");
		
		
		HttpSession session = request.getSession();

		/* Check if valid parameters an get error */
		
		String errorMessage = null;//InputChecker.checkUserParameters(name, surname, email, password);
		
		if(houseName == null || houseName.isEmpty()){
			errorMessage = "Error in house name";
		}else if(fullDesc == null ||fullDesc.isEmpty()){
			errorMessage = "Error in full description";
		}else if(shortDesc == null || shortDesc.isEmpty()){
			errorMessage = "Error in short description";
		}else if (type == null || type.isEmpty()){
			errorMessage = "Error in type";
		}else if(sGuestNum == null || sGuestNum.isEmpty()){
			errorMessage = "Error in guest number2";
		}else if(photo == null || photo.isEmpty()){
			errorMessage = "Error in photo url";
		}else if(city == null || city.isEmpty()){
			errorMessage = "Error in city";
		}else if(sPrice == null || sPrice.isEmpty()){
			errorMessage = "Error in price1";
		}else if(sInitDate == null || sInitDate.isEmpty()){
			errorMessage = "Error in start date";
		}else if(sEndDate == null || sEndDate.isEmpty()){
			errorMessage = "Error in end date";
		}
		
		int guestNum = 0;
		try{
			guestNum = Integer.parseInt(sGuestNum);
		}catch(Exception e){
			guestNum = 0;
		}

		if(guestNum < 0 || guestNum > 4){
			errorMessage = "Error in guest number2";
		}
		
		int price = -1;
		try{
			price = Integer.parseInt(sPrice);
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
			startDate = (Date) format.parse(sInitDate);
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
			house.setFullDescription(fullDesc);
			house.setShortDescription(shortDesc);
			house.setCity(city.toUpperCase());
			house.setImageUrl(photo);
			house.setMaxGuests(guestNum);
			house.setShared(type.equals("shared"));
			house.setPrice(price);
			house.setOwner(owner);
			WebTarget housePath = houseWebTarget.path("house");
			Builder builder = housePath.request(MediaType.APPLICATION_JSON);
			
			Response res = builder.post(Entity.entity(house, MediaType.APPLICATION_JSON));
			
			/* Check if resource is created. */
			if (res.getStatus() != HTTP_CREATED ) {
				errorMessage = "Problem publishing house";
			}
		}
		
		/* If error message send message to the view */
		if (errorMessage != null) {
			request.setAttribute("isHousePubPage", (Boolean) true);
			request.setAttribute("housePubErrorMessage", errorMessage);
		}

		/* Redirect to home page */
		return HOME_JSP;
	}
}
