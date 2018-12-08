package com.gr8.bnb.handlers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;

import com.gr8.bnb.helpers.MessageManager;

// Models
import model.House;
import model.Transaction;
import model.User;


/**
 * Servlet implementation class HouseServlet
 */
public class HouseHandler implements RequestHandler {

	private String HOUSES_PAGE = "/house.jsp";
	
	private EntityManager em;
	private UserTransaction ut;
	private MessageManager messageManager;
	
	public HouseHandler(Client client) {

	}
	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		int id = Integer.parseInt(request.getParameter("houseId"));
		House home = null; //House.findById(ut, em, id);
		if (home != null) {
			request.setAttribute("foundHouse", home);
		}

		return HOUSES_PAGE;
	}

	/**
	 * @throws SystemException 
	 * @throws NotSupportedException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotSupportedException, SystemException {
		ut.begin();
		String sCard = request.getParameter("creditCard");
		String sCVC = request.getParameter("cvc");
		String sCheckIn = request.getParameter("dateStart");
		String sCheckOut = request.getParameter("dateEnd");
		String rentHouseId = request.getParameter("rentHouse");
		String ownerMail = request.getParameter("rentOwner");
		String receiverMail = request.getParameter("rentReceiver");
		String sCardDate = request.getParameter("dateCard");
		Transaction tr = new Transaction();
//		tr.setCardNum(Integer.parseInt(sCard));
//		tr.setCv2(Integer.parseInt(sCVC));
//		tr.setUser((User) request.getSession().getAttribute("user"));
//		tr.setStatus("D");
		House toRent = null;// em.createQuery("SELECT h from Home h WHERE h.id = :id", House.class)
			//.setParameter("id", 101).getSingleResult();
//		tr.setHome(toRent);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
/*		try{
//			tr.setStartDate((Date) format.parse(sCheckIn));
//			tr.setEndDate((Date) format.parse(sCheckOut));
//			tr.setCardDate((Date) new SimpleDateFormat("MM/yy").parse(sCardDate));
		
		}catch(java.text.ParseException e){
				throw new ServletException("Error with dates");
		}*/
		em.persist(tr);
		try {
			ut.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return HOUSES_PAGE+"?houseId="+rentHouseId;
		
	}

}