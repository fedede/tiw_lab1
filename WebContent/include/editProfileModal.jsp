<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Edit Profile Modal -->
<div class="modal fade" id="EditProfileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="h3 mb-3 font-weight-normal">Edit your data</h1>
				<c:if test="${requestScope.editProfileErrorMessage != null}">
					<span style="color: red;"> <c:out value="${requestScope.editProfileErrorMessage}" /></span>
				</c:if>
			</div>
			<div class="modal-body">
				<form class="form-registro" action="editprofile" method="post">
					<input type="text" id="editName" name="editName" class="form-control" value="${sessionScope.user.getName()}" placeholder="Name" required>
					<input type="text" id="editSurname" name="editSurname" class="form-control" value="${sessionScope.user.getSurname()}" placeholder="Surname" required>              
					<input type="password" id="editPassword" name="editPassword" class="form-control" placeholder="Password" required>
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Sign up</button>
				</form>
			
			</div>
			
			<div class="modal-footer">
				<p class="text-center">Do you have an account?<a href="#" id="goSignUpLogin">Log In</a></p>
			</div>
		
		</div>
	</div>
</div>