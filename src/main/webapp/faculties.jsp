<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Faculties</title>
</head>
<body>

	<h1 align="center">All faculties:</h1>

	<ol>
		<c:forEach items="${faculties}" var="faculty">
			<li><a href="./faculty?id=${faculty.id}">${faculty.name}</a></li>
		</c:forEach>
	</ol>

</body>
</html>