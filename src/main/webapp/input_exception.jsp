<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Course</title>
</head>
<body>

	<h1>Incorrect input:</h1>
	<h2>${message}</h2>
	<br>
	<p><a href= "${urlTryAgain}">${urlTryAgain }</a></p>
	<p><a href="./">Home</a></p>
	

</body>
</html>
