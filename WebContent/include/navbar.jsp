<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header id="fh5co-header-section" class="sticky-banner">
	<div class="container">
		<div class="nav-header">
			<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
			<h1 id="fh5co-logo"><a href="index.jsp"><i class="icon-airplane"></i>TIWbnb</a></h1>
			<!-- START #fh5co-menu-wrap -->
			<nav id="fh5co-menu-wrap" role="navigation">
				<ul class="sf-menu" id="fh5co-primary-menu">
					<li class="active"><a href="index.jsp">Home</a></li>
					<li ><a href="houses">Apartments</a></li>
					<c:choose>
						<c:when test="${sessionScope.authenticated == true}">
							<li ><a href="messages">Messages</a></li>                              
							<li><a href="#" id="userId"><c:out value="${sessionScope.user.name}"/> Profile</a></li> 
							<li><a href="#" id="logout">Logout</a></li> 
						</c:when>
						<c:otherwise>
							<li><a href="#" id="SignUp">Sign Up</a></li> 
							<li><a href="#" id="Login">Log In</a></li> 
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</div>
	</div>
</header>
