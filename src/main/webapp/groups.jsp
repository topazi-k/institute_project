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
			<li><a href="./group?id=${group.id}">${group.groupName}</a></li>
		</c:forEach>
	</ol>

	<form action="./groups">
		<fieldset>
			<legend>Create new group</legend>
			<input name="crud_type" value="create" hidden /> 
			
			<input type="text" size="12"name="name" required /><small>Group name</small><br>
			<input type="text" size="12" name="number" required /><small>Group number(integer value)<br>
			
			<input type="submit" value="Create"/>
		</fieldset>
	</form>
	
	<form action="./groups">
		<fieldset>
			<legend>Delete group </legend>
			<input name="crud_type" value="delete" hidden /> 
			<p>Groups:
			<select name="id">
				<c:forEach items="${groups}" var="group">
					<option value="${group.id}">name:${group.groupName} number:${group.groupNumber} </option>
				</c:forEach>
			</select> <br>
			</p>
			<input type="submit" value="Delete" />
		</fieldset>
	</form>
	
	<p><a href="./faculties"></a></p>
	
</body>
</html>
