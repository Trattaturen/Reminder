<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Reminder Home Page</title>
</head>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="buttons-area">
				<a class="button" href="home">Home</a> <a class="button" href="add">Add</a>
				<a class="button" href="display">Dashboard</a>
			</div>
			<div class="registration-area">
				<a class="registration-link" href="register">Sign up | </a>
				<a class="registration-link" href="login">Log In</a>
			</div>
		</div>
	</div>
	<div class="container">

		<div class="content clearfix">
			<c:if test="${not empty message}">
				<p class="${type}">${message}</p>
			</c:if>
			<h1>Input your E-mail and password to Log In</h1>
			<form action="login" method="POST">
				<table class="add-table">
					<tbody>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="mail"></td>
							<c:if test="${not empty mail_error}">
								<td class="error" style="border: 0;">${mail_error}</td>
							</c:if>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password"> <c:if
									test="${not empty pass_error}">
									<td class="error" style="border: 0;">${pass_error}</td>
								</c:if></td>
						</tr>
					</tbody>
				</table>
				<br> <input class="button" type="submit" value="login">
			</form>
		</div>
	</div>
	<div class="footer"></div>




</body>
</html>