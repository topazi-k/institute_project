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
	
	<h1 align="center">Find schedule:</h1>
	
	<h2>For student:</h2>

	<form action='<c:url value="/student_schedule" />' method="post">
		<fieldset>
			<legend> One day </legend>
			<input name="period" value="day"  hidden/>
			<p>Name:
			<select name="id">
				<c:forEach items="${students}" var="student">
					<option value="${student.id}">${student.firstName}
						${student.lastName}</option>
				</c:forEach>
			</select> <br>
			</p>
			<input type="date" size="10" name="date"required /> <small>Date</small> 
			</br> <input type="submit" value="Send" />
		</fieldset>
	</form>

	<form action='<c:url value="/student_schedule" />' method="post">
		<fieldset>
			<legend> Time period </legend>
			<input name="period" value="period"  hidden/>
			<p>
				Name: <select name="id">
					<c:forEach items="${students}" var="student">
						<option value="${student.id}">${student.firstName}
							${student.lastName}</option>
					</c:forEach>
				</select>
			</p>
			<input type="date" size="10" name="date_start"required /> <small>Start of period</small> 
			</br>
			<input type="date" size="10" name="date_end"required /> <small>End of period</small> 
			<input type="submit" value="Send" />
		</fieldset>
	</form>


</body>
</html>
