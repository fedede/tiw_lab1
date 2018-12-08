<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="h3 mb-3 font-weight-normal">Log in to continue</h1>
				<c:if test="${requestScope.loginErrorMessage != null}">
					<span style="color: red;"> <c:out value="${requestScope.loginErrorMessage}" /></span>
				</c:if>
			</div>
			<div class="modal-body">
				<form class="form-signin" action="login" method="post">
					<input type="email" id="loginEmail" name="loginEmail" class="form-control" placeholder="Email Address" required autofocus>
					<input type="password" id="loginPassword" name="loginPassword" class="form-control" placeholder="Password" required>
					<div class="checkbox mb-3">
						<label>
							<input type="checkbox" value="remember-me" name="remember-me"> Remember me
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="LogIn">Log in</button>
				</form>
			
			</div>
			
			<div class="modal-footer">
				<p class="text-center">Don't you have account?<a href="index.html">Sign up</a></p>
				<p class="text-center"><a href="index.jsp">Back</a></p>
			</div>
		
		</div>
	</div>
</div>
