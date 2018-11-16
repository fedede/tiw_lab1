package com.gr8.bnb.handlers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

// Models
import model.Home;


/**
 * Servlet implementation class HouseServlet
 */
public class HouseHandler implements RequestHandler {

	private String HOUSES_PAGE = "/house.jsp";
	
	private EntityManager em;
	private UserTransaction ut;
	
	public HouseHandler(EntityManager em, UserTransaction ut){
		this.em = em;
		this.ut = ut;
	}
	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		ut.begin();
		int id = Integer.parseInt(request.getParameter("houseId"));
		TypedQuery<Home> tq = em.createQuery("SELECT h FROM Home h WHERE h.id = :id", Home.class);
		tq.setParameter("id", id);
		Home home = tq.getSingleResult();
		if (home != null) {
			request.setAttribute("foundHouse", home);
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