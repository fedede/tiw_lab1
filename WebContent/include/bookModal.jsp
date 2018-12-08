<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Book detail modal -->
<div class="modal fade" id="BookDetailsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="h3 mb-3 font-weight-normal">Give you info for renting the house</h1>
			</div>
			<div class="modal-body">
				<form class="form-registro" action="house" method="post" id="message-form">
					<input type="hidden" id="rentOwner" name="rentOwner" class="form-control" value="${requestScope.foundHouse.getUser().getEmail()}">
					<input type="hidden" id="rentReceiver" name="rentReceiver" class="form-control" value="${sessionScope.user.getEmail()}">
					<input type="hidden" id="rentHouse" name="rentHouse" class="form-control" value="${sessionScope.foundHouse.getId()}">
					<input type="text" id="creditCard" name="creditCard" class="form-control" placeholder="Credit card number (10 digits)" pattern="[0-9]{10}"required>
					<input type="text" id="cvc" name="cvc" class="form-control" placeholder="CV2 (3 digits)" pattern="[0-9]{3}" required>
					<div class="input-field">
						<label for="date-card">Card expiration date:</label>
						<input type="text" class="form-control" id="dateCard" name="dateCard" placeholder="mm/yy"/>
					</div>
					<div class="input-field">
						<label for="date-start">Check-in:</label>
						<input type="text" class="form-control" id="dateStart" name="dateStart" placeholder="mm/dd/yyyy"/>
					</div>
					<div class="input-field">
						<label for="date-end">Check-out:</label>
						<input type="text" class="form-control" id="dateEnd" name="dateEnd" placeholder="mm/dd/yyyy"/>
					</div>
					
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Rent</button>
				</form>
					
			</div>
		</div>
	</div>
</div>            
