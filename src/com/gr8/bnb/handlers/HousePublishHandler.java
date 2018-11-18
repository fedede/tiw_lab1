package com.gr8.bnb.handlers;

import java.io.IOException;
import java.text.ParseException;
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

//import com.gr8.bnb.models.User;
import model.User;
//import com.gr8.bnb.helpers.InputChecker;
import model.Home;
import model.Transaction;

public class HousePublishHandler implements RequestHandler {

	private static final String HOME_JSP = "/index.jsp";
	
	
	private EntityManager em;
	private UserTransaction ut;
	
	public HousePublishHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
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
		String initDate = request.getParameter("inputHousedatestart");
		String endDate = request.getParameter("inputHousedateend");
		
		
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
		}else if(initDate == null || initDate.isEmpty()){
			errorMessage = "Error in start date";
		}else if(endDate == null || endDate.isEmpty()){
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
		
		/* If no error message try to create the user */
		if (errorMessage == null) {
			
			Home home = new Home();
			home.setName(houseName);
			home.setCity(city.toUpperCase());
			home.setFullDesc(fullDesc);
			home.setShortDesc(shortDesc);
			home.setGuestNum(guestNum);
			//home.setTransactions(new ArrayList<Transaction>());
			if(type.equals("shared")){
				home.setIsPrivate(new byte[]{0});
			}else{
				home.setIsPrivate(new byte[]{1});
			}
			home.setImg(photo);
			home.setPrice(price);
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//			boolean x = true;
//			if(x){
//				try {
//					throw new ServletException("InitDate: " + format.parse(initDate).toString()
//							+ "\nEndDate: " + format.parse(endDate).toString());
//				} catch (ParseException e) {
//					// TODO Auto-generated catch blockssssss
//					e.printStackTrace();
//				}
//				
//			}
			try{
				home.setInitDate((Date) format.parse(initDate));
				home.setEndDate((Date) format.parse(endDate));
			
			}catch(java.text.ParseException e){
				errorMessage = "Error with dates";
				throw new ServletException("Date: "+ e.getMessage());
			}

			User sessionUser = (User) session.getAttribute("user");
			home.setUser(sessionUser);
			ut.begin();
			em.persist(home);
			try {
				ut.commit();
			} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException| HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				errorMessage = "Unknown error creating your account. Try again.";
				e.printStackTrace();
				throw new ServletException("Commit: " + e.getMessage());
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
