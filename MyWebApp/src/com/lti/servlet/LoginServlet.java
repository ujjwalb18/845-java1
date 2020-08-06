package com.lti.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lti.service.LoginService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.lti")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String upass=request.getParameter("pwd");
		
		
		DatabaseLoginService ls2=new DatabaseLoginService();
		boolean isvalid=ls2.authenticate(uname, upass);
		if(isvalid)
		{
			String remem=request.getParameter("reme");
			if(remem!=null && remem.equals("Yes"))
			{
				Cookie c1=new Cookie("uname", uname);
				c1.setMaxAge(60*60);
				Cookie c2= new Cookie("upass",upass);
				c2.setMaxAge(60*60);
				response.addCookie(c1);
				response.addCookie(c2);
			}
			response.sendRedirect("welcome.html");
		}
		else
			response.sendRedirect("login.html");
		
	}

}
