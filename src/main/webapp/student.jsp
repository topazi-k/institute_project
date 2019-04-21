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

	<form action="./student">
		<fieldset>
			<legend>Update student ${student.firstName}
				${student.lastName}</legend>
			<input name="crud_type" value="update" hidden /> 
			<input name="id"value="${student.id}" hidden /> 
			<input type="text" size="12"name="first_name" required /><small>New first name</small><br>
			<input type="text" size="12" name="last_name" required /><small>New last name</small><br>
			<p>
				New birth day: <br> 
				<input type="text" size="2" name="day"required /> <small>Day</small> 
				<input type="text" size="2"name="month" required /> <small>Month</small> 
				<input type="text" size="4" name="year" required /> <small>Year</small>
			</p>
			<input type="submit" value="Update" />
		</fieldset>
	</form>

	<form action="./students">
		<fieldset>
			<legend>Delete student ${student.firstName}${student.lastName}</legend>
			<input name="crud_type" value="delete" hidden /> 
			<input name="id"value="${student.id}" hidden />
			<input type="submit" value="Delete" />
		</fieldset>
	</form>
	<h2><a href="./students">All students</a></h2>
</body>
</html>
