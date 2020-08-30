<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Playground Management System</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Your page description here" />
<meta name="author" content="" />

<!-- css -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/prettyPhoto.css" rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- Theme skin -->
<link id="t-colors" href="color/default.css" rel="stylesheet" />

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="ico/apple-touch-icon-144-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="ico/apple-touch-icon-114-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="ico/apple-touch-icon-72-precomposed.png" />
<link rel="apple-touch-icon-precomposed"
	href="ico/apple-touch-icon-57-precomposed.png" />
<link rel="shortcut icon" href="ico/favicon.png" />


</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section id="inner-headline">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="inner-heading">
						<h2 align="center">Plaything Categories Search</h2>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">
		<form id="contactform" action="" method="post" role="form">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>

			<div class="row" style="margin-left: 200px;">
				<div class="row">
					<div class="span4 field form-group">
						Plaything Categories ID:<input type="text" name="id" />
						<div class="validation"></div>
					</div>
					<div class="span4 field form-group">
						Plaything Categories Name:<input type="text" name="name" />
						<div class="validation"></div>
					</div>
				</div>



				<div class="row">
					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Search" />
					</p>
				</div>
			</div>
		</form>
		<table class="table table-striped"
			style="width: 70%; margin-left: 200px">
			<th>Plaything Categories ID</th>
			<th>Plaything Categories Name</th>
			<th>Action</th>
			<tr>
				<td>PC001</td>
				<td>Horror</td>
				<td><a href="PMS-PTC-03.jsp">Edit</a></td>
			</tr>
			<tr>
				<td>PC002</td>
				<td>3D</td>
				<td><a href="PMS-PTC-03.jsp">Edit</a></td>
			</tr>
			<tr>
				<td>PC003</td>
				<td>2D</td>
				<td><a href="PMS-PTC-03.jsp">Edit</a></td>
			</tr>
			<tr>
				<td>PC004</td>
				<td>Excited</td>
				<td><a href="PMS-PTC-03.jsp">Edit</a></td>
			</tr>
		</table>
	</section>
	<script src="js/jquery.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/modernizr.custom.js"></script>
	<script src="js/toucheffects.js"></script>
	<script src="js/google-code-prettify/prettify.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/portfolio/jquery.quicksand.js"></script>
	<script src="js/portfolio/setting.js"></script>
	<script src="js/animate.js"></script>

	<!-- Contact Form JavaScript File -->
	<script src="contactform/contactform.js"></script>

	<!-- Template Custom JavaScript File -->
	<script src="js/custom.js"></script>
</body>
</html>