package com.code.seller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class PaymentProcess
 */
@WebServlet("/PaymentProcess")
public class PaymentProcess extends HttpServlet {
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
	
		String id= request.getParameter("p_id");
		String r_id= request.getParameter("r_id");
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		System.out.println("P Id "+id);
		System.out.println("R ID "+r_id);
		System.out.println("Email "+email);
		
		try 
		{
			
			
			PreparedStatement ps1 = con.prepareStatement("UPDATE `product_details` SET status='PaymentDone' where id='"+id+"'");
			int i=ps1.executeUpdate();
			if (i>0) 
			{
				PreparedStatement ps2 = con.prepareStatement("UPDATE `bidding_request` SET status='PaymentDone' where r_id='"+r_id+"'");
				int i2=ps2.executeUpdate();
				System.out.println("payment Done");
				response.sendRedirect("bidderViewBuyProducts.jsp?buy=done");

			}
			else 
			{
				System.out.println("payment Fail fail");
				response.sendRedirect("bidderViewBuyProducts.jsp?fail=buy");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}
	}
}