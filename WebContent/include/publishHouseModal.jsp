<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- House Publish Modal -->
<div class="modal fade" id="HousePublishModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="h3 mb-3 font-weight-normal">Enter the data of the house you want to post.</h1>
				<c:if test="${requestScope.housePubErrorMessage != null}">
					<span style="color: red;"> <c:out value="${requestScope.signUpErrorMessage}" /></span>
				</c:if>
			</div>
			<div class="modal-body">
				<form id="houseform" class="form-housepub" action="publishHouse" method="post">
					<input type="text" id="inputHousename" name="inputHousename" class="form-control" placeholder="House name" required autofocus>
					<input type="text" id="inputFulldescription" name="inputFulldescription" class="form-control" placeholder="Full description" required>
					<input type="text" id="inputShortdescription" name="inputShortdescription" class="form-control" placeholder="Short description" required>              
					<input type="text" id="inputHousecity" name="inputHousecity" class="form-control" placeholder="City of the house" required>              
					<select id="inputHousetype" name="inputHousetype" form="houseform" class="form-control">
						<option value="full">Full House</option>
						<option value="private">Private Room</option>
						<option value="shared">Shared Room</option>
					</select>
					<input type="number" id="inputGuestnumber" name="inputGuestnumber" class="form-control" placeholder="Number of guests" max="4" min="1" required>
					<input type="text" id="inputHousephoto" name="inputHousephoto" class="form-control" placeholder="Url to the photo of the house">
					<input type="number" id="inputHouseprice" name="inputHouseprice" class="form-control" placeholder="Price per night (EUR)" min="0" required>
					<div class="input-field">
						<label for="date-start">Available from:</label>
						<input type="text" class="form-control" id="inputHousedatestart" name="inputHousedatestart" placeholder="mm/dd/yyyy"/>
					</div>
					<div class="input-field">
						<label for="date-start">Available until:</label>
						<input type="text" class="form-control" id="inputHousedateend" name="inputHousedateend" placeholder="mm/dd/yyyy"/>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Publish</button>
				</form>
			
			</div>
			
			<div class="modal-footer">
				<p class="text-center">Do you have an account?<a href="#" id="goSignUpLogin">Log In</a></p>
			</div>
		
		</div>
	</div>
</div>            
