package com.gr8.bnb.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gr8.bnb.models.User;


/**
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet(urlPatterns = "/adminhome", loadOnStartup = 1, initParams = {
		@WebInitParam(name = "configuration", value = "com.gr8.bnb.controllers") })
public class AdminHomeServlet extends HttpServlet {

	private static final String ADMIN_HOME = "/adminhome.jsp";

	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean loggedin = false;
		if (session.getAttribute("admin_authenticated") != null) {
			loggedin = (boolean) session.getAttribute("admin_authenticated");
		}

		if (!loggedin) {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<p> You need to log in to access this page </p>");
			
		}
		else{
			config.getServletContext().getRequestDispatcher(ADMIN_HOME).forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
