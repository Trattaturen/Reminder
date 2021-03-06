<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Reminder Home Page</title>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="buttons-area">
				<a class="button" href="home">Home</a> 
				<a class="button active" href="add">Add</a> 
				<a class="button" href="display">Dashboard</a>
			</div>
			<div class = "registration-area">
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

			<c:if test="${not empty message}">
				<p class="${type}">${message}</p>
			</c:if>
			
			<h1>Fill form to create new Event</h1>
			
			<form action="add" method="POST">
				<table class="add-table">
					<tbody>
						<tr>
							<td>Title</td>
							<td><input class="add-input" type="text" name="title"></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><textarea class="add-input" name="description"></textarea></td>
						</tr>
						<tr>
							<td>Type</td>
							<td><select class="add-input" name="type">
									<option value="">Select Event type</option>
									<option value="togo">To go</option>
									<option value="tosee">To see</option>
									<option value="other">Other</option>
							</select></td>
						</tr>
						<tr>
							<td>Remind</td>
							<td><input class="add-input" type="checkbox" name="remind"></td>
						</tr>
						<tr>
							<td>Day</td>
							<td><input class="add-input" type="date" name="day">
						</tr>
						<tr>
							<td>Time</td>
							<td><input class="add-input" type="time" name="time"></td>
						</tr>
					</tbody>
				</table>
				<br> <input class="button" type="submit" value="Create">
			</form>
		</div>
	</div>
	<div class="footer"></div>




</body>
</html>