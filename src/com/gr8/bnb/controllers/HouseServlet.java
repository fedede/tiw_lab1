package com.gr8.bnb.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Iterator;

// Models
import com.gr8.bnb.models.House;


/**
 * Servlet implementation class HouseServlet
 */
public class HouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("houseId"));
		//response.getWriter().append("Served at: ").append(request.getContextPath()).append("|| House ID: " + id);
		for (Iterator<House> it = House.getHouses().iterator(); it.hasNext();){
			House house = it.next();
			System.out.println("ID: " + house.getId());
			if(house.getId() == id){
				request.setAttribute("foundHouse", house);
				System.out.println("FOUND: " + house.getId());
			}
		}
		RequestDispatcher reqDis = request.getRequestDispatcher("/house.jsp");
		reqDis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
