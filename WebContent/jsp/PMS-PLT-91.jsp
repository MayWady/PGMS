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
						<h2 align="center">Plaything Confrim</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">



		<form:form id="contactform" method="post" role="form"
			modelAttribute="plaything">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>
			<div class="row" style="margin-left: 200px;">
				<div class="row">
					<table width="100%" style="margin-left: 30px;">

						<tr>
							<td>Plaything ID:</td>
							<form:hidden path="playthingId" />
							<td style="color: #5cb85c">${plaything.playthingId}</td>
							<td>Created Date:*</td>
							<form:hidden path="createdDate" />
							<td style="color: #5cb85c">${plaything.createdDate}</td>
							<td rowspan="7"><img alt="Photo" src="${plaything.image}"
								width="200" height="200" /></td>
						</tr>
						<tr>
							<td width="25%">Plaything Name:*</td>
							<form:hidden path="playthingName" />
							<td style="color: #5cb85c" width="25%">${plaything.playthingName}</td>
							<td width="25%">Price:</td>
							<form:hidden path="price" />
							<td style="color: #5cb85c" width="25%">${plaything.price}</td>
						</tr>
						<tr>
							<td>Plaything Categories:*</td>
							<form:hidden path="playthingCategoriesId" />
							<td style="color: #5cb85c">${plaything.playthingCategoriesName}</td>
							<td>Status:</td>
							<form:hidden path="playthingStatusId" />
							<td style="color: #5cb85c">${plaything.playthingStatusName}</td>
						</tr>
						<tr>

							<td>Service Count:*</td>
							<form:hidden path="serviceCount" />
							<td style="color: #5cb85c">${plaything.serviceCount}</td>
							<td valign="top">Description:</td>
							<form:hidden path="description" />
							<td style="color: #5cb85c" colspan="3">${plaything.description}
							</td>
						</tr>

					</table>
				</div>
				<div class="row">

					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Confirm"
							formaction="addPlaything" /> <input type="submit"
							class="btn btn-color" value="Cancel"
							formaction="setupPlaythingAdd" />
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