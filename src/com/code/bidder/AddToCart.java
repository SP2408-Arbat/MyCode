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
import javax.servlet.http.HttpSession;

import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
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
	
		String p_id = request.getParameter("id");
		
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		
		String req_type = request.getParameter("req_type");
		if(req_type.equals("A"))
		{
			req_type="Add To Cart";
		}
		else
		{
			req_type="Wish List Product";
		}
		System.out.println("Req Type "+req_type);
		try 
		{
			
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO `cart_whish_list_products`(`p_id`, `request_by`, `status`) VALUES ('"+p_id+"','"+email+"','"+req_type+"')");
			int i=ps1.executeUpdate();
			if (i>0) 
			{
				System.out.println("Add Product Done");
				response.sendRedirect("bidderViewProducts.jsp?add=done");

			}
			else 
			{
				System.out.println("add Operation fail");
				response.sendRedirect("bidderViewProducts.jsp?fail=add");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}
	}
}