<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Reminder Home Page</title>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="buttons-area">
				<a class="button" href="home">Home</a> <a class="button" href="add">Add</a>
				<a class="button active" href="display">Dashboard</a>
			</div>
			<div class="registration-area">
				<c:if test="${empty user}">
					<a class="registration-link" href="register">Sign up | </a>
					<a class="registration-link" href="login">Log In</a>
				</c:if>
				<c:if test="${not empty user}">
					<span class="registration-link">${user}</span>
					<a class="registration-link" href="LogoutServlet"> | Log Out</a>
				</c:if>
			</div>
		</div>
	</div>
	<div class="container">

		<div class="content clearfix">
			<h1>Dashboard</h1>

			<form action="search" method="POST">
				<input class="searchline" type="text" name="value"> <input
					class="button" type="submit" value="Search">
			</form>
			<br>
			<c:choose>
				<c:when test="${empty removeMessage}">
					<p class="${type}">${message}</p>
				</c:when>

				<c:otherwise>
					<p class="${removeType}">${removeMessage}</p>
				</c:otherwise>
			</c:choose>

			<c:if test="${not empty events}">
				<table class="add-table">
					<thead>

						<tr>
							<th>N</th>
							<th>Title</th>
							<th>Description</th>
							<th>Date</th>
							<th>Time</th>
							<th>Type</th>
							<th>Remind</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" scope="page" />
						<c:forEach var="event" items="${events}">
							<tr>
								<td>${count}</td>
								<td>${event.title}</td>
								<td>${event.description}</td>
								<td>${event.day}</td>
								<td>${event.time}</td>
								<td>${event.type}</td>
								<td>${event.remind}</td>
								<td>
									<!-- Stole from Yuriy :) -->
									<form action="remove" method="POST">
										<input type="hidden" name="id" value="${event.id}"> <input
											type="submit" value="Delete">
									</form>
								</td>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page" />
						</c:forEach>
					</tbody>
				</table>

			</c:if>
		</div>
	</div>
	<div class="footer"></div>




</body>
</html>