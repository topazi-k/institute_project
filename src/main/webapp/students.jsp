<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Group</title>
</head>
<body>

	<h1>Group: ${group.groupName}</h1>
	<br>
	<h2>Students List:</h2>
	<br>
	<ol>
		<c:forEach items="${group.students}" var="student">
			<li><a href="students/student?id=${student.id}">${student.firstName}
					${student.lastName}</a></li>
		</c:forEach>
	</ol>

</body>
</html>
