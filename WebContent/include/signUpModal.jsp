<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- SignUp Modal -->
<div class="modal fade" id="SignUpModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="h3 mb-3 font-weight-normal">Enter your data</h1>
				<c:if test="${requestScope.signUpErrorMessage != null}">
					<span style="color: red;"> <c:out value="${requestScope.signUpErrorMessage}" /></span>
				</c:if>
			</div>
			<div class="modal-body">
				<form class="form-registro" action="signup" method="post">
					<input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
					<input type="text" id="inputName" name="inputName" class="form-control" placeholder="Name" required>
					<input type="text" id="inputSurname" name="inputSurname" class="form-control" placeholder="Surname" required>              
					<input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Sign up</button>
				</form>
			
			</div>
			
			<div class="modal-footer">
				<p class="text-center">Do you have an account?<a href="#" id="goSignUpLogin">Log In</a></p>
			</div>
		
		</div>
	</div>
</div>          
