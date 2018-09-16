package com.gr8.bnb.controllers;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gr8.bnb.models.House;

/**
 * Servlet implementation class searchServlet
 */
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("IN RESULTS GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
		// TODO Auto-generated method stub
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
		
		RequestDispatcher reqDis = request.getRequestDispatcher("/results.jsp");
		reqDis.forward(request, response);
	}

}
