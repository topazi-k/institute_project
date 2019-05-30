<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courses</title>
</head>
<body>

	<h1 align="center">All classrooms:</h1>

	<ol>
		<c:forEach items="${classrooms}" var="classroom">
			<li><a href="./classroom?id=${classroom.id}">${classroom.number}</a></li>
		</c:forEach>
	</ol>

</body>
</html>
