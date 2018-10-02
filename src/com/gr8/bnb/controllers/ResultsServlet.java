package com.gr8.bnb.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		request.setAttribute("city", request.getParameter("city"));
		request.setAttribute("startDate", request.getParameter("date-start"));
		request.setAttribute("endDate", request.getParameter("date-end"));
		request.setAttribute("price", request.getParameter("price"));
		request.setAttribute("accommodationType", request.getParameter("ac-type"));
		request.setAttribute("adults", request.getParameter("adults"));
		request.setAttribute("children", request.getParameter("children"));
		
		RequestDispatcher reqDis =
				request.getRequestDispatcher("/results.jsp");
				reqDis.forward(request, response);
	}

}
