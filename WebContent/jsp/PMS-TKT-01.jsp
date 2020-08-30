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
						<h2 align="center">Ticket Type Register</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">

		<form:form id="contactform" method="post" role="form"
			modelAttribute="ticketType">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>

			<div class="row" style="margin-left: 200px;">
				<div class="row">

					<div class="span4 field form-group">
						Ticket Type ID:
						<form:input type="text" path="ticketTypeId" readonly="true" />

						<div style="color: red;">
							<form:errors path="ticketTypeId" />
						</div>
					</div>

					<div class="span4 field form-group">
						Ticket Type Name:*
						<form:input type="text" path="ticketTypeName" />
						<div style="color: red;">
							<form:errors path="ticketTypeName" />
						</div>
					</div>
				</div>
				<div class="row">

					<div class="span4 field form-group">
						Price:*
						<form:input type="text" path="price" class="rightAligned" maxlength="8"/>
						<div style="color: red;">
							<form:errors path="price" />
						</div>
					</div>

					<div class="span4 field form-group">
						Status:
						<form:select path="ticketTypeStatusId">

							<form:options items="${statusList}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<div style="color: red;">
							<form:errors path="ticketTypeStatusId" />
						</div>
					</div>
				</div>
				<div class="row">

					<div class="span4 field form-group">
						Valid From:
						<form:input type="date" path="validFrom" />
						<div style="color: red;">
							<form:errors path="validFrom" />
						</div>
					</div>

					<div class="span4 field form-group">
						Valid To:
						<form:input type="date" path="validTo" />
						<div style="color: red;">
							<form:errors path="validTo" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="span4 field form-group">
						Plaything:
						<form:select path="plaything" multiple="true">
							<form:options items="${playthingList}" itemValue="playthingId"
								itemLabel="playthingName" />
						</form:select>

					</div>
				</div>

				<div class="row">

					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Register"
							formaction="confirmTicketTypeAdd" />
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