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

	<form action="./student_schedule" method="post">
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
			
			<p>
				Date: <br> 
				<input type="text" size="2" name="day" /> <small>Day</small>
				<input type="text" size="2" name="month" /> <small>Month</small> 
				<input type="text" size="4" name="year" /> <small>Year</small>
			</p>
			
			</br> <input type="submit" value="Send" />
		</fieldset>
	</form>

	<form action="./student_schedule" method="post">
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
			<p>
				Start of period: <br> 
				<input type="text" size="2" name="day" /><small>Day</small>
				<input type="text" size="2" name="month" /> <small>Month</small>
				<input type="text" size="4" name="year" /> <small>Year</small>
			</p>
			</br>
			<p>
				End of period: <br> 
				<input type="text" size="2" name="last_day" /><small>Day</small> 
				<input type="text" size="2" name="last_month" /> <small>Month</small>
				<input type="text" size="4" name="last_year" /> <small>Year</small>
			</p>
			<input type="submit" value="Send" />
		</fieldset>
	</form>


</body>
</html>
