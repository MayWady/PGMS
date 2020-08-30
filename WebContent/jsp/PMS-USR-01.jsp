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
	href="resources/ico/apple-touch-icon-144-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/ico/apple-touch-icon-114-precomposed.png" />
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/ico/apple-touch-icon-72-precomposed.png" />
<link rel="apple-touch-icon-precomposed"
	href="resources/ico/apple-touch-icon-57-precomposed.png" />
<link rel="shortcut icon" href="resources/ico/favicon.png" />


</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section id="inner-headline">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="inner-heading">
						<h2 align="center">User Register</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">

		<form:form id="contactform" method="post" role="form"
			modelAttribute="user">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>



			<div class="row" style="margin-left: 200px;">

				<div class="row">

					<div class="span4 field form-group">
						User ID:*
						<form:input type="text" path="userId" readonly="true"/>

						<div style="color: red;">
							<form:errors path="userId" />
						</div>
					</div>

					<div class="span4 field form-group">
						User Name:*
						<form:input type="text" path="userName" />
						<div style="color: red;">
							<form:errors path="userName" />
						</div>
					</div>
				</div>

				<div class="row">

					<div class="span4 field form-group">
						NRC No:*
						<form:input type="text" path="nrc" />

						<div style="color: red;">
							<form:errors path="nrc" />
						</div>
					</div>

					<div class="span4 field form-group">
						Role*:
						<form:select path="role">
							<form:option value="0" Label="Select One"></form:option>
							<form:option value="1" Label="Admin"></form:option>
							<form:option value="2" Label="Staff"></form:option>
						</form:select>
						<div style="color: red;">
							<form:errors path="role" />
						</div>
					</div>
				</div>


				<div class="row">
					<div class="span4 field form-group">
						Password:*
						<form:input type="password" name="password" id="password"
							onchange='check_pass();' path="password" />

						<div style="color: red;">
							<form:errors path="password" />
						</div>
					</div>

					<div class="span4 field form-group">
						Confirm Password:
						<form:input type="password" name="confirm_password"
							id="confirm_password" onchange='check_pass();'
							path="confirmPassword" />
						<div style="color: red;">
							<form:errors path="password" />
						</div>
					</div>
				</div>

				<div class="row">

					<div class="span4 field form-group">
						User Status:*
						<form:select path="userStatus">
							<form:options items="${statusList}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<div style="color: red;">
							<form:errors path="userStatus" />
						</div>
					</div>

					<div class="span4 field form-group">
						Email:*
						<form:input type="text" path="email" />
						<div style="color: red;">
							<form:errors path="email" />
						</div>
					</div>
				</div>

				<div class="row">

					<div class="span4 field form-group">
						Phone No:*
						<form:input type="text" path="phone" class="rightAligned" />

						<div style="color: red;">
							<form:errors path="phone" />
						</div>
					</div>

					<div class="span4 field form-group">
						Address:*
						<form:input type="text" path="address" />
						<div style="color: red;">
							<form:errors path="address" />
						</div>
					</div>
				</div>


				<div class="row">

					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Register"
							formaction="confirmUserAdd" id="submit" />
					</p>
				</div>


			</div>

		</form:form>


	</section>



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