<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>This is UserPage</h1>
	<h1>Person List</h1>

	<br />
	<br />
	<div>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Option</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>
						<a href = "/Edit/${user.id}">Edit</a>
						<a href = "/Del/${user.id}">Del</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>