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
						<h2 align="center">Member Type Search</h2>
						<div style="color: blue;">${msg}</div>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

<section id="content">

		
		
		<form:form id="contactform" action="memberTypeSearch" method="post"
			role="form" modelAttribute="membertype">

			<div class="row" style="margin-left: 200px;">
				<div class="row">

					<div class="span0 field form-group">Search:</div>

					<div class="span4 field form-group">
						<form:input type="text" path="memberTypename" />
						<div style="color: red;">
							<form:errors path="memberTypename" />
						</div>
					</div>
					<div class="span4 field form-group">
						<input type="submit" class="btn btn-color" value="Search" />
					</div>
				</div>
			</div>
		</form:form>
		

		
		<table class="table table-striped"
			style="width: 70%; margin-left: 200px">
			<tr>
				<th>Member Type  ID</th>
				<th>Member Type Name</th>
				<th>Action</th>
			</tr>
			<c:forEach var="item" items="${membertypeList}" varStatus="mycount">
				<c:url value="setupMemberTypeUpdate" var="detail">
					<c:param name="membertypeId" value="${item.memberTypeid }" />
					<c:param name="membertypeName" value="${item.memberTypename }" />
					<c:param name="description" value="${item.description }" />
					<c:param name="price" value="${item.price }" />
					<c:param name="perdiscount" value="${item.perdiscount }" />
				</c:url>
				<tr>
					<td>${mycount.count }</td>
					<td>${item.memberTypename}</td>
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