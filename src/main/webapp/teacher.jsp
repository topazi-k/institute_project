<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher</title>
</head>
<body>

	<h1>Teacher:</h1>
	<br>
	<h2>First name: ${teacher.firstName}</h2>
	<br>
	<h2>Last name: ${teacher.lastName}</h2>
	<br>
	<h2>Course: <a href="./course?id=${teacher.course.id}">${teacher.course.name}</a></h2>
	

</body>
</html>
