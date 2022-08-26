<%@page import="com.code.bidder.Bidding"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.code.admin.GlobalFunction"%>
<%-- <%@page import="jdk.nashorn.internal.objects.Global"%> --%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.db.conn.ConnectionProvider"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title><jsp:include page="projectTitle.jsp"></jsp:include></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap styles -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- Customize styles -->
<link href="style.css" rel="stylesheet" />
<!-- font awesome styles -->
<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">

<!-- Favicons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico">
</head>
<body>
	<!-- 
	Upper Header Section 
-->
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="topNav">
			<div class="container">
				<div class="alignR">
					<div class="pull-left socialNw">
						<!-- <a href="#"></span></a> -->
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--
Lower Header Section 
-->
	<div class="container">
		<div id="gototop"></div>
		<header id="header">
		<div class="row">
			<div class="span4">
				<h1>
					<a class="logo" href="index.jsp"><span><jsp:include
								page="projectTitle.jsp"></jsp:include></span> <img
						src="assets/img/logo-bootstrap-shoping-cart.png"
						alt="bootstrap sexy shop"> </a>
				</h1>
			</div>
			<div class="span4 alignR"></div>
		</div>
		</header>
		<!--
Navigation Bar Section 
-->
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a data-target=".nav-collapse" data-toggle="collapse"
						class="btn btn-navbar"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a>
					<div class="nav-collapse">
						<jsp:include page="Header/bidderHeader.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- 
Body Section 
-->

		<%

if(request.getParameter("bid")!=null)
{
	out.print("<script>alert('Bidding Done')</script>");
}
if(request.getParameter("fail")!=null)
{
	out.print("<script>alert('Bidding Fail')</script>");
}
if(request.getParameter("low")!=null)
{
	out.print("<script>alert('Bidding Amount is Low from Base Price')</script>");
}

	String email=session.getAttribute("email").toString();
	String id=request.getParameter("id");
	String sql="select * from product_details where id='"+id+"'";
	session.setAttribute("iid", id);
	Connection con = ConnectionProvider.getConnection();
	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	int count=0;
	
	GlobalFunction gf=new GlobalFunction();
	
	Bidding bd=new Bidding();
	
	while (rs.next()) 
	{
		count=count+1;
		HashMap<String,String> prodcut_details=gf.getProductDetails(id);
		String file_name = prodcut_details.get("file_name");
		int b_price_org=Integer.parseInt(rs.getString("base_price"));
		int b_price_half=b_price_org/2;
		int b_price_new=b_price_org+b_price_half;
		
		int max_bid_amount=bd.getCurrentProductBidding(id);	
		int your_last_bid=bd.getLastProductBidding(id,email);
		
%>

		<div class="row">
			<div class="span12">
				<div class="well well-small">
					<div class="row-fluid">
						<div class="span5">
							<div id="myCarousel" class="carousel slide cntr">
								<div class="carousel-inner">
									<div class="item active">
										<a href="#"> <img src="products/<%=file_name %>" alt=""
											style="width: 100%"></a>
									</div>
								</div>
							</div>
						</div>
						<div class="span7">
							<h3><%=rs.getString("category") %>&nbsp;&nbsp;(<%=rs.getString("p_name") %>)&nbsp;&nbsp;
							</h3>
							<hr class="soft" />
							<form action="BiddingRequest" method="POST"
								class="form-horizontal qtyFrm">
								<div class="control-group">
									<input type="hidden" name="p_id" value="<%=id%>"> <label
										class="control-label"><span>Base Price:<%=rs.getString("base_price") %>/-
									</span></label>
									<div class="controls">
										<label class="control-label"><span>Expected: <%=b_price_new %>/-
										</span></label>
									</div>
								</div>
								<h4>Bidding Time</h4>
								<div class="control-group">
									<label class="control-label"><span>Start:<%=rs.getString("bid_sdate_time") %></span></label>
									<div class="controls">
										<label class="control-label"><span>Ending <%=rs.getString("bid_sdate_time") %></span></label>
									</div>
								</div>

								<p><%=rs.getString("specification") %>
								<div class="control-group">
									<label class="control-label"><span>Current
											Bidding<br /><%=max_bid_amount %></span></label>
									<div class="controls">
										<label class="control-label"><span>Your Last
												Bidding<br /><%=your_last_bid %></span></label>
									</div>
								</div>

								<div class="control-group">
									<label class="control-label"><span>Biding
											Amount:<input type="number" name="bid_price"
											value="<%=rs.getString("base_price") %>"></label>
									<div class="controls"></div>
								</div>

								<p>
									<button type="submit" class="shopBtn">
										<span class=" icon-shopping-cart"></span> Submit
									</button>
							</form>
						</div>
					</div>

					<hr class="softn clr" />
					<%} %>

					<ul id="productDetail" class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">Bidding
								Details</a></li>

					</ul>
					<div id="myTabContent" class="tab-content tabWrapper">
						<div class="tab-pane fade active in" id="home">
							<h4>Bidding Information</h4>
							<jsp:include page="viewBiddingData.jsp"></jsp:include>

						</div>
					</div>
					</section>

					<!--
Footer
-->
					<footer class="footer">
					<div class="row-fluid"></div>
					</footer>
				</div>
				<!-- /container -->

				<div class="copyright">
					<div class="container">
						<span>Copyright &copy; 2013<br> bootstrap ecommerce
							shopping template
						</span>
					</div>
				</div>
				<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
				<!-- Placed at the end of the document so the pages load faster -->
				<script src="assets/js/jquery.js"></script>
				<script src="assets/js/bootstrap.min.js"></script>
				<script src="assets/js/jquery.easing-1.3.min.js"></script>
				<script src="assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
				<script src="assets/js/shop.js"></script>
</body>
</html>