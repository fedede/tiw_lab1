package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Models
import com.gr8.bnb.models.House;


/**
 * Servlet implementation class HouseServlet
 */
public class HouseHandler implements RequestHandler {

	private String HOUSES_PAGE = "/house.jsp";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("houseId"));

		House house = House.find(id);
		if (house != null) {
			request.setAttribute("foundHouse", house);
		}

		return HOUSES_PAGE;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}

}