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
				<a class="registration-link" href="register">Sign up</a>
			</div>
		</div>
	</div>
	<div class="container">

		<div class="content clearfix">
			<h1>Fill form to Register</h1>
			<form action="register" method="POST">
				<table class="add-table">
					<tbody>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="mail"> 
								<c:if test="${not empty mail_error}">
									<p class="${type}">${mail_error}</p>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password">
								<c:if test="${not empty pass_error}">
									<p class="${type}">${pass_error}</p>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>Confirm password</td>
							<td><input type="password" name="confirmed-password">
								<c:if test="${not empty pass_mismatch}">
									<p class="${type}">${pass_mismatch}</p>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
				<br> <input class="button" type="submit" value="register">
			</form>
		</div>
	</div>
	<div class="footer"></div>




</body>
</html>