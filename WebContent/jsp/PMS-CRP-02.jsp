
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
						<h2 align="center">Customer Report</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="content">
		<form:form id="contactform" method="post" role="form"
			action="setupCustomerReportSearch" modelAttribute="customerReportSearch">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>

			<div class="row" style="margin-left: 200px;">

				<div class="row">
					<div class="span4 field form-group">
						Customer Name:
						<form:input type="text" path="customerName" />
						<div class="validation"></div>
					</div>
					<div class="span4 field form-group">
						Ticket ID:
						<form:input type="text" path="ticketId" />
						<div class="validation"></div>
					</div>
				</div>
				<div class="row">
					<div class="span4 field form-group">
						Valid From:<input type="date" path="validFrom" />
						<div class="validation"></div>
					</div>
					<div class="span4 field form-group">
						Valid To:<input type="date" path="validTo" />
						<div class="validation"></div>
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
			style="width: 80%; margin-left: 200px;">
			<tr>
				<th>Customer name</th>
				<th>Ticket ID</th>
				<th>Valid From : </th>
				<th>Valid To : </th>
				<th>Plaything</th>
				<th>Time</th>
				<th>Balance</th>
			</tr>

			<c:forEach var="item" items="${customerReportSearchList}">
				<c:url value="setupCustomerReportUpdate" var="detail">
					<c:param name="id" value="${item.customerName }" />
					<c:param name="name" value="${item.ticketId}" />
					<c:param name="price" value="${item.validFrom }" />
					<c:param name="validTo" value="${item.validTo }" />
					<c:param name="validFrom" value="${item.plaything }" />
					<c:param name="status" value="${item.time}" />
					<c:param name="status" value="${item.balance}" />
				</c:url>
				<tr>
					<td>${item.ticketTypeId}</td>
					<td>${item.ticketTypeName}</td>
					<td>${item.price}</td>
					<td><a href="${detail}">Detail</a></td>

				</tr>
			</c:forEach>
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