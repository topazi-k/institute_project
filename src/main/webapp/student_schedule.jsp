<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student schedule</title>
</head>
<body>

	<h1 align="center">Student schedule:</h1>

	<ol>
		<c:forEach items="${schedule}" var="lecture">
			<li><a href="./lecture?id=${lecture.id}">
					Date-${lecture.date} course-${lecture.course.name}
					group-${lecture.group.groupName}</a></li>
		</c:forEach>
	</ol>

</body>
</html>
