<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Playground Management System</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Your page description here" />
<meta name="author" content="" />

<!-- css -->
<link href="resources/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/bootstrap-responsive.css" rel="stylesheet" />
<link href="resources/css/prettyPhoto.css" rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<!-- Theme skin -->
<link id="t-colors" href="resources/color/default.css" rel="stylesheet" />

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

<style>
#con {
	margin-top: 50px;
	width: 300px;
	height: 350px;
	border: 1px solid #5cb85c;
	border-radius: 30px;
	padding-top: 30px;
}
</style>
</head>
<body>
	<center>
		<div id="con">
			<h6>Playground Management System</h6>
			<br> <br>
			<form:form method="POST" action="login" modelAttribute="loginUser"
				id="contactform">


				<div style="color: red;">${errorMsg}</div>


				<div class="row" style="margin-left: 15px;">
					<div class="row">
						<div class="span3 field form-group">
							<form:input path="userId" placeholder="* User ID" />
							<div style="color: red;">
								<form:errors path="userId" />
							</div>
						</div>

					</div>

					<div class="row">
						<div class="span3 field form-group">
							<form:password path="password" placeholder="* Password" />
							<div style="color: red;">
								<form:errors path="password" />
							</div>
						</div>

					</div>


					<div class="row">

						<p style="margin-left: 220px;">
							<input type="submit" class="btn btn-color" value="Login" />

						</p>
					</div>
				</div>
			</form:form>
		</div>
	</center>
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.easing.1.3.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/modernizr.custom.js"></script>
	<script src="resources/js/toucheffects.js"></script>
	<script src="resources/js/google-code-prettify/prettify.js"></script>
	<script src="resources/js/jquery.prettyPhoto.js"></script>
	<script src="resources/js/portfolio/jquery.quicksand.js"></script>
	<script src="resources/js/portfolio/setting.js"></script>
	<script src="resources/js/animate.js"></script>

	<!-- Contact Form JavaScript File -->
	<script src="resources/contactform/contactform.js"></script>

	<!-- Template Custom JavaScript File -->
	<script src="resources/js/custom.js"></script>
</body>
</html>