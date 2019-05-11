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

	<h1 align="center">All courses:</h1>

	<ol>
		<c:forEach items="${courses}" var="course">
			<li><a href="./course?id=${course.id}">${course.name}</a></li>
		</c:forEach>
	</ol>

</body>
</html>
