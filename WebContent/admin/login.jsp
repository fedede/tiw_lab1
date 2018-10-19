<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h1>Form</h1>
	<form method="post" action="adminlogin">
		<fieldset>
			<legend>Login</legend>
			<label>Name:</label> <input type="text" name="user"><br /> <label>Password:</label>
			<input type="password" name="password"><br />

		</fieldset>
		<input type="submit" value="Send">
	</form>
	<div>
	<c:if test="${requestScope.AdminLoginErrorMessage != null}">
		<p>
			<c:out value="${requestScope.AdminLoginErrorMessage}" />
		</p>
	</c:if>
	</div>
	
</body>
</html>