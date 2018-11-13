package com.gr8.bnb.handlers;
/*
 * RequestHandler.java
 *
 * Created on 13 de diciembre de 2005, 14:42
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author telmoz
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;


public interface RequestHandler {
	String handleGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException, NotSupportedException, SystemException;
	
	String handlePost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException, NotSupportedException, SystemException;
}
