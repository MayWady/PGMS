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
<link rel="resources/apple-touch-icon-precomposed" sizes="144x144"
	href="resources/ico/apple-touch-icon-144-precomposed.png" />
<link rel="resources/apple-touch-icon-precomposed" sizes="114x114"
	href="resources/ico/apple-touch-icon-114-precomposed.png" />
<link rel="resources/apple-touch-icon-precomposed" sizes="72x72"
	href="resources/ico/apple-touch-icon-72-precomposed.png" />
<link rel="resources/apple-touch-icon-precomposed"
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
						<h2 align="center">Plaything Report</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">



		<form:form id="contactform" method="post" role="form" modelAttribute="playthingReportSearch">

			<div id="sendmessage">Your message has been sent. Thank you!</div>
			<div id="errormessage"></div>

			<div class="row" style="margin-left: 200px;">

				<div class="row">
					<div class="span4 field form-group">
						Plaything Category:
						<form:select path="playthingCategory">
							<form:option value="0" Label="Select One"></form:option>
							<form:options items="${playthingCategoriesList}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<div class="validation"></div>
					</div>
					<div class="span4 field form-group">
						Plaything:
						<form:select path="plaything">
							<form:option value="0" Label="Select One"></form:option>
							<form:options items="${playthingList}" itemValue="playthingId"
								itemLabel="playthingName" />
						</form:select>

						<div class="validation"></div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						From Date:<form:input type="text" path="validFrom" />
						<div class="validation"></div>
					</div>
					<div class="span4 field form-group">
						To Date:<form:input type="text" path="validTo" />
						<div class="validation"></div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Running Count:<form:input type="text" path="runningCount" />
						<div class="validation"></div>
					</div>
					<div class="span4 field form-group">
						Play Count:<form:input type="text" path="playCount" />
						<div class="validation"></div>
					</div>
				</div>
				<div class="row">
					<p style="margin-left: 30px;">
						<input type="submit" class="btn btn-color" value="Search" formaction="playthingReportSearch"/>
					</p>
				</div>
			</div>
		</form:form>
		<table class="table table-striped"
			style="width: 80%; margin-left: 200px">
			<th>Date</th>
			<th>Plaything Category</th>
			<th>Plaything</th>
			<th>Service Count</th>
			<th>Maintenance Count</th>
			<th>Running Count</th>
			<th>Total Running Count</th>
			<th>Play Count</th>
			<th>Status</th>

			<c:forEach var="item" items="${playthingReportSearchList}">
				<tr>
					<td>${item.date}</td>
					<td>${item.playthingCategory}</td>
					<td>${item.plaything}</td>
					<td>${item.serviceCount}</td>
					<td>${item.maintenanceCount}</td>
					<td>${item.runningCount}</td>
					<td>${item.maintenanceCount}</td>
					<td>${item.totalRunningCount}</td>
					<td>${item.playCount}</td>
					<td>${item.playthingStatus}</td>

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