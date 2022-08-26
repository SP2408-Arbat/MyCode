package com.code.bidder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.code.admin.GlobalFunction;
import com.db.conn.ConnectionProvider;

/**
 * Servlet implementation class BiddingRequest
 */
@WebServlet("/BiddingRequest")
public class BiddingRequest extends HttpServlet {
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
	
		int bid_price= Integer.parseInt(request.getParameter("bid_price"));
		String p_id= request.getParameter("p_id");
		
		GlobalFunction gf=new GlobalFunction();
		HashMap<String, String> product_details=gf.getProductDetails(p_id);
		String p_o_id=product_details.get("upload_by");
		HttpSession session=request.getSession();
		String email=session.getAttribute("email").toString();
		int base_price=Integer.parseInt(product_details.get("base_price"));
		
		
		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date dateobj = new Date();
		String c_date_time=df1.format(dateobj);

		try 
		{
			if(base_price<bid_price)
			{
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO `bidding_request`(`p_id`, `request_by`, `p_o_id`, `biiding_amount`, `b_date_time`, `status`) VALUES ('"+p_id+"','"+email+"','"+p_o_id+"','"+bid_price+"','"+c_date_time+"','Bidding')");
				int i=ps1.executeUpdate();
				if (i>0) 
				{
					System.out.println("Registration Done");
					response.sendRedirect("productInDetails.jsp?id="+p_id+"&bid=done");
	
				}
				else 
				{
					System.out.println("Login fail");
					response.sendRedirect("productInDetails.jsp?id="+p_id+"&fail=bid");
				}
			}
			else
			{
				System.out.println("biiding Amount is Low");
				response.sendRedirect("productInDetails.jsp?id="+p_id+"&low=bid");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exc "+e);
		}
	}
}