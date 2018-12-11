package com.gr8.bnb.handlers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gr8.bnb.helpers.MessageManager;

// Models
import model.House;
import model.TransactionRequest;


/**
 * Servlet implementation class HouseServlet
 */
public class HouseHandler implements RequestHandler {

	private String HOUSES_PAGE = "/house.jsp";
	private static final int HTTP_CREATED = 201;
	
	MessageManager messageManager;
	
	WebTarget userWebtarget;
	WebTarget transactionWebTarget;
	WebTarget houseWebTarget;
	
	public HouseHandler(Client client, MessageManager messageManager){
		this.transactionWebTarget = client.target("http://localhost:8083/");
		this.houseWebTarget = client.target("http://localhost:8082/");
		this.userWebtarget = client.target("http://localhost:8081/");
		this.messageManager = messageManager;

	}
	
	
	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		Long id = Long.parseLong(request.getParameter("houseId"));
		WebTarget housePath = houseWebTarget.path("house").path(id.toString());
		Builder builder = housePath.request(MediaType.APPLICATION_JSON);
		Response res = builder.get();
		
		if (res.getStatus() == HttpServletResponse.SC_OK) {
			House house = res.readEntity(House.class);
			/* set the user and mark the session as authenticate */
			request.setAttribute("foundHouse", house);
		} else {
			return "/error.jsp";
		}

		return HOUSES_PAGE;
	}

	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		String sCard = request.getParameter("creditCard");
		String sCV2 = request.getParameter("cvc");
		String sCheckIn = request.getParameter("dateStart");
		String sCheckOut = request.getParameter("dateEnd");
		String sRentHouseId = request.getParameter("rentHouse");
		String sOwnerId = request.getParameter("rentOwner");
		String sReceiverId = request.getParameter("rentReceiver");
		String sCardDate = request.getParameter("dateCard");
		
		String errorMessage = null;
		TransactionRequest tr = new TransactionRequest();
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		try{
			tr.setStartDate((Date) format.parse(sCheckIn));
			tr.setEndDate((Date) format.parse(sCheckOut));
			tr.setCardDate((Date) new SimpleDateFormat("MM/yy").parse(sCardDate));
		
		}catch(java.text.ParseException e){
				throw new ServletException("Error with dates");
		}
		
		tr.setCardNum(sCard);
		tr.setCV2(Integer.parseInt(sCV2));
		tr.setHouse(Long.parseLong(sRentHouseId));
		tr.setInvoiced(Long.parseLong(sReceiverId));
		
		
		WebTarget transactionPath = transactionWebTarget.path("transaction/new");
		Builder builder = transactionPath.request(MediaType.APPLICATION_JSON);
		
		Response res = builder.post(Entity.entity(tr, MediaType.APPLICATION_JSON));
		
		/* Check if resource is created. */
		if (res.getStatus() != HTTP_CREATED ) {
			errorMessage = "Could not book house";
		}else{
			User sender = (User) request.getSession().getAttribute("user");
			
			/* Get data from the user API. */
			WebTarget userPath = userWebtarget.path("users").path(sOwnerId);
			Builder builder2 = userPath.request(MediaType.APPLICATION_JSON);
			Response res2 = builder2.get();
			
			/* If owner exists then get message data. */
			if (res2.getStatus() == 200) {
				User owner = res.readEntity(User.class);
				String message = "Hi " + owner.getName() + "!"
								+ sender.getName() + " " + sender.getSurname() + 
								" wants to rent your house [HOUSENAME] from " +
								sCheckIn + " to " + sCheckOut + 
								"Do you want to <div>ACCEPT</div>?";
				boolean success = messageManager.send(sender, owner, message);
				if (!success) {
					errorMessage = "It was not possible to send message";
				}
			}
		}

		if (errorMessage != null) {
			request.setAttribute("bookErrorMessage", errorMessage);
		}
		
		return HOUSES_PAGE+"?houseId="+sRentHouseId;
		
	}

}