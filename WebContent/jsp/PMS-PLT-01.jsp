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


</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section id="inner-headline">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="inner-heading">
						<h2 align="center">Plaything Register</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">
		<form:form id="contactform" method="post" role="form"
			modelAttribute="plaything" action="confirmPlaythingAdd"
			enctype="multipart/form-data">
			<div class="row" style="margin-left: 200px;">
				<!-- <img src="img/QRCode.jpg" class="rounded float-left" alt="QR Code" width="100px" height="100px" style="margin-left: 900px;"> -->
				<div class="row">
					<div class="span4 field form-group">
						Plaything ID:
						<form:input type="text" path="playthingId" readonly="true" />
						<div style="color: red;">
							<form:errors path="playthingId" />
						</div>
					</div>
					<div class="span4 field form-group">
						Created Date:*
						<form:input type="date" path="createdDate" />
						<div style="color: red;">
							<form:errors path="createdDate" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4 field form-group">
						Plaything Name:*
						<form:input type="text" path="playthingName" />
						<div style="color: red;">
							<form:errors path="playthingName" />
						</div>
					</div>
					<div class="span4 field form-group">
						Price:*
						<form:input type="text" path="price" class="rightAligned" />
						<div style="color: red;">
							<form:errors path="price" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4 field form-group">
						Plaything Categories:
						<form:select path="playthingCategoriesId">
							<form:options items="${playthingCategoriesList}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<div style="color: red;">
							<form:errors path="playthingCategoriesId" />
						</div>
					</div>
					<div class="span4 field form-group">
						Status:
						<form:select path="playthingStatusId">

							<form:options items="${statusList}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<div style="color: red;">
							<form:errors path="playthingStatusId" />
						</div>
					</div>
				</div>



				<div class="row">

					<div class="span4 field form-group">
						Service Count:
						<form:input type="text" path="serviceCount" />
						<div style="color: red;">
							<form:errors path="serviceCount" />
						</div>
					</div>
					<div class="span4 field form-group">
						Add Photo: <input type="file" name="file" />
						<div style="color: red;">
							<form:errors path="description" />
						</div>
					</div>

				</div>


				<div class="row">

					<div class="span4 field form-group">
						Description:
						<form:textarea path="description" rows="4" cols="50" />
						<div style="color: red;">
							<form:errors path="description" />
						</div>
					</div>

				</div>


				<div class="row">

					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Register" />
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