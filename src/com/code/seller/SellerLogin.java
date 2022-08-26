package com.code.seller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class SellerLogin
 */
@WebServlet("/SellerLogin")
public class SellerLogin extends HttpServlet {
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
	
		String email= request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		System.out.println("email "+email);
		System.out.println("Password "+password);

		
		try 
		{
			
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM `seller_details` where email='"+ email + "' AND password='" + password + "'");
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) 
			{
				
				session.setAttribute("email", email);
				session.setAttribute("fname", rs.getString("fname"));
				
				System.out.println("Login Done");
				response.sendRedirect("sellerHome.jsp?login=done");

			}
			else 
			{
				System.out.println("Login fail");
				response.sendRedirect("sellerLogin.jsp?login=fail");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}

	}
}