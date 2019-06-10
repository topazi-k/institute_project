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
	<p>First name: ${student.firstName}</p>
	<br>
	<p>Last name: ${student.lastName}</p>
	<br>
	<p>Birth day: ${student.birthDay}</p>

	<form action='<c:url value="/student" />' method="post">
		<fieldset>
			<legend>Update student ${student.firstName}
				${student.lastName}</legend> 
			<input name="id"value="${student.id}" hidden /> 
			<input type="text" size="12"name="first_name" required /><small>New first name</small><br>
			<input type="text" size="12" name="last_name" required /><small>New last name</small><br>
			<input type="date" size="10" name="birthday"required /> <small>Birthday</small> 
			<input type="submit" value="Update" />
		</fieldset>
	</form>

	<form action='<c:url value="/student/delete"/>' method="post">
		<fieldset>
			<legend>Delete student ${student.firstName}${student.lastName}</legend>
			<input name="id"value="${student.id}" hidden />
			<input type="submit" value="Delete" />
		</fieldset>
	</form>

	<h2><a href='<c:url value="/students"/>'>All students</a></h2>
</body>
</html>
