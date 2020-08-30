<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<header>

		<div class="container">


			<div class="row nomargin" style="margin-top: 10px;">
				<div class="span4">
					<div class="logo">
						<h1>PGMS</h1>
					</div>
				</div>
				<div class="span8">
					<div class="navbar navbar-static-top">
						<div class="navigation">
							<c:if test="${SessionUser.role eq 2}">
								<nav>
									<ul class="nav topnav">
										<li><a href="home">Home</a></li>

										<li class="dropdown"><a href="#">User <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupChangePassword">Change Password</a></li>
											</ul></li>
										<li class="dropdown"><a href="#">Ticket <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupTicketAdd">Ticket Register</a></li>
												<li><a href="setupTicketSearch">Ticket Search</a></li>
												<li><a href="setupTopupAmountSearch">Topup Search</a></li>

											</ul></li>
										<li class="dropdown"><a href="#">MemberShip<i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupMembershipAdd">Membership
														Register</a></li>
												<li><a href="setupMembershipSearch">Membership
														Search</a></li>

											</ul></li>
										<li class="dropdown"><a href="#">Plaything <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupPlaythingAdd">Plaything Register</a></li>
												<li><a href="setupPlaythingSearch">Plaything Search</a></li>
												<li><a href="setupPlaythingCategoriesAdd">Plaything
														Category Register</a></li>
											</ul></li>



										<li><a href="logout"><i class="fa fa-sign-out"
												style="font-size: 24px; color: white;"></i></a></li>
									</ul>
								</nav>
							</c:if>
							<c:if test="${SessionUser.role eq 1 }">
								<nav>
									<ul class="nav topnav">
										<li><a href="home">Home</a></li>

										<li class="dropdown"><a href="#">User <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupUserAdd">User Register</a></li>
												<li><a href="setupUserSearch">User Search</a></li>
												<li><a href="setupChangePassword">Change Password</a></li>
											</ul></li>
										<li class="dropdown"><a href="#">Ticket <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupTicketAdd">Ticket Register</a></li>
												<li><a href="setupTicketSearch">Ticket Search</a></li>
												<li><a href="setupTopupAmountSearch">Topup Search</a></li>
												<li><a href="setupTicketTypeAdd">Ticket Type
														Register</a></li>
												<li><a href="setupTicketTypeSearch">Ticket Type
														Search</a></li>
											</ul></li>
										<li class="dropdown"><a href="#">MemberShip<i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupMembershipAdd">Membership
														Register</a></li>
												<li><a href="setupMembershipSearch">Membership
														Search</a></li>
												<li><a href="setupMemberTypeAdd">Member Type
														Register</a></li>
												<li><a href="setupMemberTypeSearch">Member Type
														Search</a></li>
											</ul></li>
										<li class="dropdown"><a href="#">Plaything <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupPlaythingAdd">Plaything Register</a></li>
												<li><a href="setupPlaythingSearch">Plaything Search</a></li>
												<li><a href="setupPlaythingCategoriesAdd">Plaything
														Category Register</a></li>
											</ul></li>

										<li class="dropdown"><a href="#">Report <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupCustomerReportSearch">Customer
														Report Search</a></li>
												<li><a href="setupPlaythingReportSearch">Plaything
														Report Search</a></li>
											</ul></li>
										<li class="dropdown"><a href="#">Status <i
												class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="setupUserStatusAdd">User Status
														Register</a></li>
												<li><a href="setupTicketStatusAdd">Ticket Status
														Register</a></li>
												<li><a href="setupTicketTypeStatusAdd">Ticket Type
														Status Register</a></li>
												<li><a href="setupMembershipStatusAdd">Membership
														Status Register</a></li>
												<li><a href="setupPlaythingStatusAdd">Plaything
														Status Register</a></li>
												<li><a href="setupPlayStatusAdd">Play Status
														Register</a></li>
											</ul></li>

										<li><a href="logout"><i class="fa fa-sign-out"
												style="font-size: 24px; color: white;"></i></a></li>
									</ul>
								</nav>
							</c:if>
						</div>
						<!-- end navigation -->
					</div>
				</div>
			</div>
		</div>
	</header>
</body>
</html>