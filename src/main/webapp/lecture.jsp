<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lecture:</title>
</head>
<body>

	<h1>Lecture :</h1>
	<br>
	<h2>Date: ${lecture.date} time: ${lecture.time}</h2>

	<br>
	<h2>
		Group:<a href="./group?id=${lecture.group.id}">
			${lecture.group.groupName}</a>
	</h2>

	<br>
	<h2>
		Course: <a href="./course?id=${lecture.course.id}">${lecture.course.name}</a>
	</h2>

	<br>
	<h2>
		Teacher: <a href="./teacher?id=${lecture.teacher.id}">${lecture.teacher.firstName}
			${lecture.teacher.lastName}</a>
	</h2>

	<br>
	<h2>
		Classroom: <a href="./classroom?id=${lecture.classroom.id}">${lecture.classroom.number}</a>
	</h2>
</body>
</html>
