<%@page import="com.db.conn.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

								<table class="table table-striped">
								<tbody>
										<tr class="techSpecRow">
											<td class="techSpecTD1">Sr.No</td>
											<td class="techSpecTD2">Product ID</td>
											<td class="techSpecTD2">Product owner</td>
											<td class="techSpecTD2">Request BY</td>
											<td class="techSpecTD2">Bidding Amount</td>
											<td class="techSpecTD2">B Date and Time</td>
										</tr>
									</tbody>
								<%
								String iid=session.getAttribute("iid").toString();
								response.setIntHeader("Refresh", 2);
								Connection con=ConnectionProvider.getConnection();
								PreparedStatement ps1 = con.prepareStatement("SELECT * FROM `bidding_request` where p_id='"+iid+"' ORDER BY r_id DESC");
								ResultSet rs1 = ps1.executeQuery();
								int i=0;
								while (rs1.next()) 
								{
									
									i=i+1;
								
								%>
										<tr class="techSpecRow">
											<td class="techSpecTD1"><%=i %></td>
											<td class="techSpecTD2"><%=rs1.getString("p_id") %></td>
											<td class="techSpecTD2"><%=rs1.getString("request_by") %></td>
											<td class="techSpecTD2"><%=rs1.getString("p_o_id") %></td>
											<td class="techSpecTD2"><%=rs1.getString("biiding_amount") %></td>
											<td class="techSpecTD2"><%=rs1.getString("b_date_time") %></td>
										</tr>
								<%} %>	
								</table>
</body>
</html>