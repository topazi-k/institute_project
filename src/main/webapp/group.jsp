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
	<h2>number:${group.groupNumber}</h2>
	<br>
	<h2>Students List:</h2>
	<br>
	<ol>
		<c:forEach items="${group.students}" var="student">
			<li><a href="./student?id=${student.id}">${student.firstName}
					${student.lastName}</a></li>
		</c:forEach>
	</ol>
	
	<form action='<c:url value="/group/add_student"/>' method="post">
		<fieldset>
			<legend>Add student</legend>
			<input name="id" value="${group.id }" hidden/>
			<p>Name:
			<select name="student_id">
				<c:forEach items="${free_students}" var="student">
					<option value="${student.id}">${student.firstName}
						${student.lastName} ${student.birthDay}</option>
				</c:forEach>
			</select> <br>
			</p>
			<input type="submit" value="Add"/>
		</fieldset>
	</form>
	
	<form action='<c:url value="/group/remove_student"/>' method="post">
		<fieldset>
			<legend>Remove student</legend>
			<input name="id" value="${group.id }" hidden/>
			<p>Name:
			<select name="student_id">
				<c:forEach items="${group.students}" var="student">
					<option value="${student.id}">${student.firstName}
						${student.lastName} ${student.birthDay}</option>
				</c:forEach>
			</select> <br>
			</p>
			<input type="submit" value="Remove"/>
		</fieldset>
	</form>
	
	<form action='<c:url value="/group"/>' method="post">
		<fieldset>
			<legend>Update information about group</legend>
			<input name="id" value="${group.id }" hidden/>
			<input type="text" size="7" name="number" required/><small>New number</small><br>
			<input type="text" size="12" name="name" required/><small>New name</small><br><br>
			<input type="submit" value="Update"/>
		</fieldset>
	</form>
	<p><a href='<c:url value="/groups"/>'>Groups List</a></p>
</body>
</html>
