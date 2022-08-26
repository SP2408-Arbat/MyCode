package com.code.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.db.conn.ConnectionProvider;

public class GlobalFunction 
{
	static Connection con=ConnectionProvider.getConnection();

	public HashMap<String, String> getProductDetails(String id) 
	{
		HashMap<String, String> product_details=new HashMap();
		
		try 
		{
			PreparedStatement ps=con.prepareStatement("select * from product_details where id='"+id+"'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				product_details.put("category",rs.getString("category"));
				product_details.put("p_name",rs.getString("p_name"));
				product_details.put("specification",rs.getString("specification"));
				product_details.put("file_name",rs.getString("file_name"));
				product_details.put("base_price",rs.getString("base_price"));
				product_details.put("bid_sdate_time",rs.getString("bid_sdate_time"));
				product_details.put("bid_ldate_time",rs.getString("bid_ldate_time"));
				product_details.put("upload_by",rs.getString("upload_by"));
				product_details.put("status",rs.getString("status"));
				
			}
		} 
		catch (Exception e) {
			System.out.println("Exc "+e);
		}
		return product_details;
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
