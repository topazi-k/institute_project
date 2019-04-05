<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>
</head>

<body>
	<h1 align="center">All Students:</h1>
	<ol>
		<c:forEach items="${students}" var="student">
			<li><a href="./student?id=${student.id}">${student.firstName} ${student.lastName}</a></li>
		</c:forEach>
	</ol>
	
</body>
</html>