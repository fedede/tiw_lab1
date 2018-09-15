package com.gr8.bnb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchServlet
 */
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("IN RESULTS GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IN RESULTS POST");
		String city = request.getParameter("city");
		String startDate = request.getParameter("date-start");
		String endDate = request.getParameter("date-end");
		String price = request.getParameter("price");
		String accommodationType = request.getParameter("ac-type");
		String adults = request.getParameter("adults");
		String children = request.getParameter("children");
		
		response.getWriter().append(city + " || " + startDate + " || " + endDate + " || " +  price  + " || " + accommodationType + " || " + adults + " || " + children);
	}

}
