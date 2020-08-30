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
						<h2 align="center">Plaything Search</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">



		<form:form id="contactform" method="post" role="form"
			modelAttribute="plaything" action="playthingSearch">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>

			<div class="row" style="margin-left: 200px;">
				<div class="row">
					<div class="span4 field form-group">
						Plaything ID:*
						<form:input type="text" path="playthingId" />
						<div style="color: red;">
							<form:errors path="playthingStatusId" />
						</div>
					</div>
					<div class="span4 field form-group">
						Plaything Name:
						<form:input type="text" path="playthingName" />
						<div style="color: red;">
							<form:errors path="playthingName" />
						</div>
					</div>
				</div>
				 <div class="row">
					<div class="span4 field form-group">
						Created Date:
						<form:input type="text" path="createdDate" />
						<div style="color: red;">
							<form:errors path="createdDate" />
						</div>
					</div>
					<div class="span4 field form-group">
						Running Count:
						<form:input type="text" path="runningCount" />
						<div style="color: red;">
							<form:errors path="runningCount" />
						</div>
					</div>
				</div> 
				<div class="row">

					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Search" />
					</p>
				</div>
			</div>
		</form:form>
		<table class="table table-striped"
			style="width: 70%; margin-left: 200px">
			<tr>
				<th>Plaything ID</th>
				<th>Plaything Name</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
			<c:forEach var="item" items="${playthingList}">
				<c:url value="setupPlaythingUpdate" var="detail">
					<c:param name="playthingId" value="${item.playthingId}" />
					<c:param name="name" value="${item.playthingName}" />
					<c:param name="desc" value="${item.description }" />
					<c:param name="price" value="${item.price }" />
					<c:param name="createddate" value="${item.createdDate}" />
					<c:param name="playcount" value="${item.playCount }" />
					<c:param name="servicecount" value="${item.serviceCount}" />
					<c:param name="runningcount" value="${item.runningCount }" />
					<c:param name="mcount" value="${item.maintenanceCount }" />
					<c:param name="status" value="${item.playthingStatusId }" />
					<c:param name="categories" value="${item.playthingCategoriesId}" />
					<c:param name="image" value="${item.image}" />
				</c:url>
				<tr>
					<td>${item.playthingId}</td>
					<td>${item.playthingName}</td>
					<td>${item.price}</td>
					<td><a href="${detail}">Detail</a></td>

				</tr>
			</c:forEach>
		</table>
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