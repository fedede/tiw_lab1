package com.gr8.bnb.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gr8.bnb.models.User;



@WebServlet(
		urlPatterns="/adminlogin",
		loadOnStartup=1,
		initParams={@WebInitParam(name="configuration", value="com.gr8.bnb.controllers")}
		)
public class AdminLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final String ADMIN_LOGIN = "/adminlogin.jsp";
	private static final String ADMIN_HOME  = "/adminhome.jsp";
	
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
	
		@Override
		public void init(ServletConfig config) throws ServletException {
			this.config = config;
		}
	       


		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			config.getServletContext().getRequestDispatcher(ADMIN_LOGIN).forward(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			String message = "";
			String page = ADMIN_LOGIN;
			HttpSession session = request.getSession();
			
			if (user.equals("admin") && password.equals("admin")){				
				page = ADMIN_HOME;
				session.setAttribute("admin_authenticated", true);
				session.setAttribute("usersList",User.getUsers());
				
				
			}else{		
				message = "Wrong username or password";
			}		
			request.setAttribute("AdminLoginErrorMessage", message);
			config.getServletContext().getRequestDispatcher(page).forward(request, response);
				
			
		}

}
