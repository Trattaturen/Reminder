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
				<a class="button active" href="home">Home</a> 
				<a class="button" href="add">Add</a> 
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

			<h1>Welcome to Reminder project HomePage</h1>
			<h3>Here are some features available at the moment:</h3>
			<ul class="info">
				<li>To ADD new Event:
					<div class="image-add"></div>
				</li>
				<li>To Display/Search/Remove Events:

					<div class="image-display"></div>
				</li>

			</ul>



		</div>
	</div>
	<div class="footer"></div>




</body>
</html>