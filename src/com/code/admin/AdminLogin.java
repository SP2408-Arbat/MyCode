package com.code.admin;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.commons.codec.digest.DigestUtils;

import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
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
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username= request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		System.out.println("username "+username);
		System.out.println("Password "+password);
		String newPassword = DigestUtils.md5Hex(password);

		
		try 
		{
			
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM `admin_details` where username='"+ username + "' AND password='" + newPassword + "'");
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) 
			{
				
				session.setAttribute("username", username);
				
				System.out.println("Login Done");
				out.write("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'>"); 
				out.write("swal('Good job!', 'You clicked the button!', 'success');");
				out.write("</script>");
				response.sendRedirect("adminHome.jsp?login=done");

			}
			else 
			{
				System.out.println("Login fail");
				response.sendRedirect("adminLogin.jsp?login=fail");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}

	}

}
