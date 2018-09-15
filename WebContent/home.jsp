<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
<style>
	.error { color:red;}
</style>
</head>
<body>
	<h1>HOME</h1>
	
    <c:if test="${!requestScope.message.equals(\"\")}">
       <span class="error"><c:out value="${requestScope.message}" /></span>
    </c:if>
</body>
</html>
