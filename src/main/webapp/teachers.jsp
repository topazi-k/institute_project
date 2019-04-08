<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teachers</title>
</head>
<body>

	<h1 align="center">All Teachers:</h1>

	<ol>
		<c:forEach items="${teachers}" var="teacher">
			<li><a href="./teacher?id=${teacher.id}">${teacher.firstName} ${teacher.lastName}</a></li>
		</c:forEach>
	</ol>

</body>
</html>
