package com.code.bidder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.db.conn.ConnectionProvider;

public class Bidding 
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
				
			}
		} 
		catch (Exception e) {
			System.out.println("Exc "+e);
		}
		return product_details;
		
	}

	
	
	
	public int getCurrentProductBidding(String p_id) 
	{
		int result=0;
		try 
		{
			PreparedStatement ps=con.prepareStatement("SELECT MAX(biiding_amount) FROM `bidding_request` where p_id='"+p_id+"'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exc "+e);
		}
		return result;
	}
	
	public int getBiddingAmount(String p_id) 
	{
		int result=0;
		try 
		{
			PreparedStatement ps=con.prepareStatement("SELECT * FROM `bidding_request` where p_id='"+p_id+"' AND status='PaymentDone'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("biiding_amount");
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exc "+e);
		}
		return result;
	}
	

	
	public String getRequestID(String p_id,String email) 
	{
		String result="";
		try 
		{
			PreparedStatement ps=con.prepareStatement("SELECT * FROM `bidding_request` where p_id='"+p_id+"' AND request_by='"+email+"' AND status='ReadyToSell'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=rs.getString("r_id");
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exc "+e);
		}
		return result;
	}
	
	public int getProductFinalPrice(String p_id) 
	{
		int result=0;
		try 
		{
			PreparedStatement ps=con.prepareStatement("SELECT * FROM `bidding_request` where p_id='"+p_id+"' AND status!='Bidding'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=rs.getInt("biiding_amount");
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exc "+e);
		}
		return result;
	}
	
	public int getLastProductBidding(String p_id,String email) 
	{
		int result=0;
		try 
		{
			PreparedStatement ps=con.prepareStatement("SELECT MAX(biiding_amount) FROM `bidding_request` where p_id='"+p_id+"' AND request_by='"+email+"'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=rs.getInt(1);
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exc "+e);
		}
		return result;
	}
	
	public String getLastProductBiddingStatus(String p_id,String email) 
	{
		String result="";
		try 
		{
			PreparedStatement ps=con.prepareStatement("SELECT * FROM `bidding_request` where p_id='"+p_id+"' AND request_by='"+email+"'");
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				result=rs.getString("status");
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exc "+e);
		}
		return result;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
