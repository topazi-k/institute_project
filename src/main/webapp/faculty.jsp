<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Faculty</title>
</head>
<body>

	<h1>Faculty: ${faculty.name}</h1>
	
	<br>
	<h2>Groups:</h2>
	<ol>
		<c:forEach items="${faculty.groups}" var="group">
			<li><a href="./group?id=${group.id}">${group.groupName}</a></li>
		</c:forEach>
	</ol>
	
	<br>
	<h2>Teachers:</h2>
	<ol>
		<c:forEach items="${faculty.teachers}" var="teacher">
			<li><a href="./teacher?id=${teacher.id}">${teacher.firstName} ${teacher.lastName}</a></li>
		</c:forEach>
	</ol>
	
	<br>
	<h2>Courses</h2>
	<ol>
		<c:forEach items="${faculty.courses}" var="course">
			<li><a href="./course?id=${course.id}">${course.name}</a></li>
		</c:forEach>
	</ol>
	
</body>
</html>