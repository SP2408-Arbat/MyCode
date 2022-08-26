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
 * Servlet implementation class ReadyToSell
 */
@WebServlet("/ReadyToSell")
public class ReadyToSell extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String id= request.getParameter("p_id");
		String r_id= request.getParameter("r_id");
		String b_email= request.getParameter("bid");
		
		try 
		{
			
			
			PreparedStatement ps1 = con.prepareStatement("UPDATE `product_details` SET status='ReadyToSell' where id='"+id+"'");
			int i=ps1.executeUpdate();
			if (i>0) 
			{
				PreparedStatement ps2 = con.prepareStatement("UPDATE `bidding_request` SET status='ReadyToSell' where r_id='"+r_id+"'");
				int i2=ps2.executeUpdate();
				System.out.println("Registration Done");
				response.sendRedirect("viewProducts.jsp?update=done");

			}
			else 
			{
				System.out.println("Login fail");
				response.sendRedirect("viewProducts.jsp?fail=done");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}
	}
}