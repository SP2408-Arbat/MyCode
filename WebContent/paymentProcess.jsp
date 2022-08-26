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
		<div class="row">
			<div class="span12">
				<div class="well np">
					<div id="myCarousel" class="carousel slide homCar">
						<div class="carousel-inner">
<div class="item">
								<img style="width: 100%"
									src="assets/img/a11.jpeg"
									alt="bootstrap ecommerce templates">
							</div>
							<div class="item active">
								<img style="width: 100%" src="assets/img/a33.jpg"
									alt="bootstrap ecommerce templates">
							</div>
							<a class="left carousel-control" href="#myCarousel"
							data-slide="prev">&lsaquo;</a> <a class="right carousel-control"
							href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div>
				</div>
				<%
				String id=request.getParameter("id");
				String amount=request.getParameter("amount");
				String r_id=request.getParameter("r_id");
				
				
				%>
				
				<div class="well well-small">
					<hr class="soften" />
					<div class="row-fluid" align="center">
						<div id="newProductCar" class="carousel slide">
							<div class="carousel-inner">
							
								<div class="item active" align="center">
									<h1>Welcome</h1>
									<hr/>
									<form action="PaymentProcess" method="POST">
										<table style="width: 50%">
											<tr>
												<th>Amount</th>
												<td><input type="hidden" name="p_id" value="<%=id%>"><h3><%=amount %></h3></td>
											</tr>
											<tr>
												<th>Card Number<input type="hidden" name="r_id" value="<%=r_id%>"></th>
												<td><input type="text" class="form-control" name="card_no" required></td>
											</tr>
											<tr>
												<th>Month and Year</th>
												<td><input type="month" class="form-control" name="month_year" required></td>
											</tr>
											<tr>
												<th>CVV Number</th>
												<td><input type="number" maxlength="3" class="form-control" name="cvv" required></td>
											</tr>
											<tr>
												<th></th>
												<td><button type="submit" class="btn btn-success">Pay Amount To Seller</button></td>
											</tr>
										</table>
										</form>
		
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /container -->

		<div class="copyright">
			<div class="container">
				<p class="pull-right"></p>
				<span>Copyright &copy; <br> bootstrap ecommerce shopping
					template
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