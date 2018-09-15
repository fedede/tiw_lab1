<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="es.uc3m.tiw.dominios.Usuario"
    import="java.util.ArrayList"			
    import="java.util.List" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <!-- NOTE WE IMPORTED THE ARRAYLIST AND LIST  -->
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.error{color:red;}
</style>
</head>
<body>
<h1>Hello</h1>
<h2>Your name is: <c:out value="${sessionScope.usuario.getNombre()}"/></h2> <!-- Display the value of the attribute "nombre" of the object "usuario" stored in the session -->
<h2>Your password is: <c:out value="${sessionScope.usuario.getPassword() }"/></h2> <!-- Display the value of the attribute "password" of the object "usuario" stored in the session -->

<h3>Here you have the list of users</h3>
<!--  If you couldn't get the list of users (because yiou access this jsp straight away instead of from the servlet)
      show here an error message -->
<c:choose>
	<c:when test="${requestScope.usuarios == null}">
		<h3 style="color: red;">Error: You access here without authenticate.</h3>
	</c:when>
	<c:otherwise>
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Password</th>
			</tr>
			<c:forEach items="${requestScope.usuarios}" var="user">
				<tr>
					<td><c:out value="${user.getNombre()}" /></td>
					<td><c:out value="${user.getApellidos()}" /></td>
					<td><c:out value="${user.getPassword() }" /></td>
				</tr>
			</c:forEach>
		<!--  If there are users show them here, each in a different row of the table-->
  		</table>
	</c:otherwise>
</c:choose>


<p><a href="login.jsp">Back</a></p>
</body>
</html>