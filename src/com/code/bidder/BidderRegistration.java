package com.code.bidder;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class BidderRegistration
 */
@WebServlet("/BidderRegistration")
public class BidderRegistration extends HttpServlet {
	static Connection con;
	public void init(ServletConfig config) throws ServletException 
	{
		try 
		{
			con=ConnectionProvider.getConnection();
		} 
		catch (Exception e) 
		{
			System.out.println("Exception "+e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String name= request.getParameter("name");
		String mobile= request.getParameter("mobile");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		System.out.println("Email ID: "+email);
		System.out.println("Password :  "+password);
		
		try 
		{
			
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO `bidder_details`(`fname`, `email`, `mobile`, `password`) VALUES ('"+name+"','"+email+"','"+mobile+"','"+password+"')");
			int i=ps1.executeUpdate();
			if (i>0) 
			{
				System.out.println("Registration Done");
				response.sendRedirect("bidderLogin.jsp?reg=done");

			}
			else 
			{
				System.out.println("Login fail");
				response.sendRedirect("bidderRegistration.jsp?fail=done");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}
	}
}