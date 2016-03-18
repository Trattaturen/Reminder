<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<title>Reminder Home Page</title>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="buttons-area">
				<a class="button" href="home">Home</a> <a class="button" href="add">Add</a>
				<a class="button active" href="display">Dashboard</a>
			</div>
		</div>
	</div>
	<div class="container">

		<div class="content clearfix">
			<h1>Dashboard</h1>

			<form action="search" method="POST">
				<input class="searchline" type="text" name="value"> 
				<input class="button" type="submit" value="Search">
			</form>
			<br>
			<p><%=request.getAttribute("message")%></p>
			<%
				if (request.getAttribute("events")!=null) {
			%>
			<table class="add-table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Description</th>
						<th>Date</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<%=request.getAttribute("events")%>
				</tbody>
			</table>

			<%
				}
			%>
		</div>
	</div>
	<div class="footer"></div>




</body>
</html>