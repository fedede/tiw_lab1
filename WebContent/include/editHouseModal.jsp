<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- House Publish Modal -->
<div class="modal fade" id="HouseEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
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
				<form id="editHouseForm" class="form-houseedit" action="editHouse" method="post">
					<input type="hidden" id="editHouseId" name="editHouseId" class="form-control" value="">
					<input type="text" id="editHouseName" name="editHouseName" class="form-control" placeholder="House name" required autofocus>
					<input type="text" id="editHouseFullDescription" name="editHouseFullDescription" class="form-control" placeholder="Full description" required>
					<input type="text" id="editHouseShortDescription" name="editHouseShortDescription" class="form-control" placeholder="Short description" required>              
					<input type="text" id="editHouseCity" name="editHouseCity" class="form-control" placeholder="City of the house" required>              
					<select id="editHouseType" name="editHouseType" form="editHouseForm" class="form-control">
						<option value="private">Private Room</option>
						<option value="shared">Shared Room</option>
					</select>
					<input type="number" id="editHouseGuestNumber" name="editHouseGuestNumber" class="form-control" placeholder="Number of guests" max="4" min="1" required>
					<input type="text" id="editHousePhoto" name="editHousePhoto" class="form-control" placeholder="Url to the photo of the house">
					<input type="number" id="editHousePrice" name="editHousePrice" class="form-control" placeholder="Price per night (EUR)" min="0" required>
					<div class="input-field">
						<label for="date-start">Available from:</label>
						<input type="text" class="form-control" id="editHouseDateStart" name="editHouseDateStart" placeholder="mm/dd/yyyy"/>
					</div>
					<div class="input-field">
						<label for="date-start">Available until:</label>
						<input type="text" class="form-control" id="editHouseDateEnd" name="editHouseDateEnd" placeholder="mm/dd/yyyy"/>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Publish</button>
				</form>
			
			</div>
		
		</div>
	</div>
</div>            
