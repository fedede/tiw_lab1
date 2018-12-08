<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Send message modal -->
<div class="modal fade" id="SendMessageModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h1 class="h3 mb-3 font-weight-normal">Write your message</h1>
			</div>
			<div class="modal-body">
				<form class="form-registro" action="message" method="post" id="message-form">
					<input type="hidden" id="ownerId" name="owner" class="form-control" value="${requestScope.foundHouse.getUser().getId()}">

					<textarea name="message">Enter your message here...</textarea>
					<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Send</button>
				</form>
					
			</div>
		</div>
	</div>
</div>             
