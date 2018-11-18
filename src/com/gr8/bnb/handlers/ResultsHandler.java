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

//import com.gr8.bnb.models.House;
import model.Home;

/**
 * Servlet implementation class searchServlet
 */
public class ResultsHandler implements  RequestHandler {

	private final String RESULTS_PAGE = "/results.jsp";
	private final int MAX_HOUSES_PER_PAGE = 10;
	private EntityManager em;
	private UserTransaction ut;
	
	public ResultsHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}


	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NotSupportedException, SystemException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYY");
		String page = request.getParameter("page");
		int pageNum = (page == null) ? 0 : Integer.parseInt(page);
		
		String city =  request.getParameter("city").toUpperCase();
		String sInitDate =  request.getParameter("date-start");
		String sEndDate =  request.getParameter("date-end");
		String sPrice = request.getParameter("price");
		String sType = request.getParameter("ac-type");
		String sNumAdults =  request.getParameter("adults");
		String sNumChildren = request.getParameter("children");
		
				
		Date endDate = null;
		Date initDate = null;
		try {
			initDate = format.parse(sInitDate);
			endDate = format.parse(sEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int minPrice = 0;
		int maxPrice = 0;
		if(sPrice.equals("P1")){
			 minPrice = 0;
			 maxPrice = 35;
		}else if(sPrice.equals("P2")){
			minPrice = 36;
			maxPrice = 69;
		}else if(sPrice.equals("P3")){
			minPrice = 70;
			maxPrice = 130;
		}else{
			minPrice = 131;
			maxPrice = Integer.MAX_VALUE;
		}
				
		/*
		int type = -1;
		int numAdults = -1;
		int numChildren = -1;
		
		*/
		byte []isPrivate = sType.equals("shared") ? new byte[]{0} : new byte[]{1}; 
		int guestNum = Integer.parseInt(sNumAdults);
		ut.begin();
		List<Home> homeList = em.createQuery("SELECT h from Home h "
				+ "WHERE h.city = :city "
				+ "AND h.guestNum >= :guestNum "
				+ "AND h.isPrivate = :isPrivate "
				+ "AND h.price BETWEEN :minPrice AND :maxPrice "
				+ "AND h.initDate <= :initDate "
				+ "AND h.endDate > :endDate "
				+ "ORDER BY h.id ",
				Home.class)
				.setParameter("city", city)
				.setParameter("guestNum", guestNum)
				.setParameter("isPrivate", isPrivate)
				.setParameter("minPrice", minPrice)
				.setParameter("maxPrice", maxPrice)
				.setParameter("initDate", initDate)
				.setParameter("endDate", endDate)
				.setFirstResult(pageNum * MAX_HOUSES_PER_PAGE)
				.setMaxResults(MAX_HOUSES_PER_PAGE)
				.getResultList();
				/*
				em.createQuery(
				
				, Home.class)
				.getResultList();
				*/
		/*
		try {
			House.create(
					"test house",
					"full description",
					"shortdesc",
					"madrid",
					true, 
					2, 
					40,
					(Date) format.parse("13-05-2018"),
					(Date) format.parse("13-07-2018"),
					"test@tmail.com", 
					"img.png");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//ArrayList houseList = (ArrayList) request.getAttribute("houses");
		//if (houseList == null){
		request.setAttribute("houses", homeList);
		//}

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
