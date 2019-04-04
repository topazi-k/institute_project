<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student</title>
</head>
<body>

	<h1>Student:</h1>
	<br>
	<p>First name: ${requestScope["student"].firstName}</p>
	<br>
	<p>Last name: ${requestScope["student"].lastName}</p>
	<br>
	<p>Birth day: ${requestScope["student"].birthDay}</p>
	
</body>
</html>
