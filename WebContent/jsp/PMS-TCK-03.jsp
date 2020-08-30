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
						<h2 align="center">Ticket Detail</h2>
						<div style="color: blue;">${param.msg}</div>
						<div style="color: red;">${param.error}</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section id="content">



		<form:form id="contactform" method="post" role="form" name="listForm"
			modelAttribute="ticketDetail">


			<div class="row" style="margin-left: 200px;">
				<div class="row">
					<div class="span4 field form-group">
						Ticket ID:
						<form:input type="text" path="ticketId" readonly="true" />
						<div style="color: red;">
							<form:errors path="ticketId" />
						</div>
					</div>
					<div class="span4 field form-group">
						Ticket Price:*
						<form:input type="text" path="ticketPrice" class="rightAligned"
							readonly="true" />
						<div style="color: red;">
							<form:errors path="ticketPrice" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Ticket Type:*
						<form:select path="ticketType">
							<form:options items="${ticketTypeNameList}"
								itemValue="ticketTypeId" itemLabel="ticketTypeName" />
						</form:select>
						<div style="color: red;">
							<form:errors path="ticketType" />
						</div>
					</div>
					<div class="span4 field form-group">
						Quantity:*
						<form:input type="text" path="quantity" class="rightAligned"
							maxlength="2" readonly="true"/>
						<div style="color: red;">
							<form:errors path="quantity" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Customer Name:
						<form:input type="text" path="customerName" />
						<div style="color: red;">
							<form:errors path="customerName" />
						</div>
					</div>
					<div class="span4 field form-group">
						Topup Amount:*
						<form:input type="text" path="topupAmount" class="rightAligned"
							maxlength="8" readonly="true"/>
						<div style="color: red;">
							<form:errors path="topupAmount" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Member ID:
						<form:input type="text" path="memberId" />
						<div style="color: red;">
							<form:errors path="memberId" />
						</div>
					</div>
					<div class="span4 field form-group">
						Discount(%):*
						<form:input type="text" path="discount" class="rightAligned"
							readonly="true" />
						<div style="color: red;">
							<form:errors path="discount" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Date:*
						<form:input type="date" path="date" readonly="true" />
						<div style="color: red;">
							<form:errors path="date" />
						</div>
					</div>
					<div class="span4 field form-group">
						Total:*
						<form:input type="text" path="balance" class="rightAligned"
							readonly="true" maxlength="18" />
						<div style="color: red;">
							<form:errors path="balance" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Valid From:*
						<form:input type="date" path="validFrom" />
						<div style="color: red;">
							<form:errors path="validFrom" />
						</div>
					</div>
					<div class="span4 field form-group">
						Valid To:*
						<form:input type="date" path="validTo" />
						<div style="color: red;">
							<form:errors path="validTo" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="span4 field form-group">
						Status:
						<form:select path="ticketStatus">
							<form:options items="${statusList}" itemValue="id"
								itemLabel="name" />
						</form:select>
						<div style="color: red;">
							<form:errors path="ticketStatus" />
						</div>
					</div>
				</div>

				<div class="row">

					<p style="margin-left: 30px;">
						<c:if test="${SessionUser.role eq 1}">
							<input type="submit" class="btn btn-color" value="Update"
								formaction="confirmTicketUpdate" />
							<!-- <input type="submit" class="btn btn-color" value="Delete"
								onClick="javascript:checkDelete()" /> -->
						</c:if>
						<input type="submit" class="btn btn-color" value="Topup"
							formaction="setupTopupAmountAdd" /> <input type="submit"
							class="btn btn-color" value="Back" formaction="backTicketSearch" />

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

	<script type="text/javascript">
		function checkDelete() {

			var con = confirm("Are you sure to delete?");
			if (con) {
				document.listForm.action = "deleteTicket";
				document.action.submit;

			} else {
				document.listForm.action = "setupTicketUpdate";
				document.action.submit;
			}
		}
	</script>
</body>
</html>