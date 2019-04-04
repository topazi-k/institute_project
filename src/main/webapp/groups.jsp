<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Groups</title>
</head>
<body>

	<h1 align="center">All Groups:</h1>

	<ol>
		<c:forEach items="${groups}" var="group">
			<li><a href="groups/group?id=${group.id}">${group.groupName}</a></li>
		</c:forEach>
	</ol>

</body>
</html>
