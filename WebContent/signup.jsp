<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup Page</title>
<style>
	.error { color:red;}
</style>
</head>
<body>
	<h1>Form</h1>
	<form action="signup" method="post">
		<fieldset>
			<legend>Sign up</legend>
			<label for="name">Name:</label> 
			<input type="text" name="name" id="name"><br/> 
			<label for="surname">Surname:</label> 
			<input type="text" name="surname" id="surname"><br/>
			<label for="email">Email:</label> 
			<input type="text" name="email" id="email"><br/> 
			<label for="password">Password:</label> 
			<input type="password" name="password" id="password"><br/>
			<label for="repassword">Retype your password:</label> 
			<input type="password" name="repassword" id="repassword"><br/>

		</fieldset>
		<input type="submit" value="Send">
	</form>
	
    <c:if test="${requestScope.message != null}">
       <span class="error"><c:out value="${requestScope.message}" /></span>
    </c:if>
</body>
</html>
