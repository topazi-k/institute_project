<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Students</title>
</head>

<body>
	<h1 align="center">All Students:</h1>
	<ol>
		<c:forEach items="${students}" var="student">
			<li><a href='<c:url value="/student?id=${student.id}"/>'>${student.firstName} ${student.lastName}</a></li>
		</c:forEach>
	</ol>
	
	<form action='<c:url value="/students" />' method="post">
		<fieldset>
			<legend>Create new student</legend>
			
			
			<input type="text" size="12"name="first_name" required /><small>First name</small><br>
			<input type="text" size="12" name="last_name" required /><small>Last name</small><br>
			<p>
				Birth day: <br> 
				<input type="text" size="2" name="day"required /> <small>Day</small> 
				<input type="text" size="2"name="month" required /> <small>Month</small> 
				<input type="text" size="4" name="year" required /> <small>Year</small>
			</p>
			<input type="submit" value="Create"/>
		</fieldset>
	</form>
	
	<form action='<c:url value="/student/delete" />' method="post">
		<fieldset>
			<legend>Delete student </legend>
			<p>Name:
			<select name="id">
				<c:forEach items="${students}" var="student">
					<option value="${student.id}">${student.firstName}
						${student.lastName} ${student.birthDay}</option>
				</c:forEach>
			</select> <br>
			</p>
			<input type="submit" value="Delete" />
		</fieldset>
	</form>
	
	<p><a href='<c:url value="/groups"/>'>All groups</a></p>
	
</body>
</html>
