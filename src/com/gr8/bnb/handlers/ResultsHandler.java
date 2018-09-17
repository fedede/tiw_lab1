package com.gr8.bnb.handlers;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gr8.bnb.models.House;

/**
 * Servlet implementation class searchServlet
 */
public class ResultsHandler implements  RequestHandler {

	private final String RESULTS_PAGE = "/results.jsp";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String handlePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");

		request.setAttribute("city", request.getParameter("city"));
		request.setAttribute("startDate", request.getParameter("date-start"));
		request.setAttribute("endDate", request.getParameter("date-end"));
		request.setAttribute("price", request.getParameter("price"));
		request.setAttribute("accommodationType", request.getParameter("ac-type"));
		request.setAttribute("adults", request.getParameter("adults"));
		request.setAttribute("children", request.getParameter("children"));
		
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
		//ArrayList houseList = (ArrayList) request.getAttribute("houses");
		//if (houseList == null){
		request.setAttribute("houses", House.getHouses());
		//}

		return RESULTS_PAGE;
	}

}
