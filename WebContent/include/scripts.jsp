<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- jQuery -->

<script src="js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/sticky.js"></script>

<!-- Stellar -->
<script src="js/jquery.stellar.min.js"></script>
<!-- Superfish -->
<script src="js/hoverIntent.js"></script>
<script src="js/superfish.js"></script>
<!-- Magnific Popup -->
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/magnific-popup-options.js"></script>
<!-- Date Picker -->
<script src="js/bootstrap-datepicker.min.js"></script>
<!-- CS Select -->
<script src="js/classie.js"></script>
<script src="js/selectFx.js"></script>

<!-- Main JS -->
<script src="js/main.js"></script>


<script>
	<c:choose>

		<c:when test="${sessionScope.authenticated == true}">
			<c:if test="${requestScope.isEditPage}">
				$("#EditProfileModal").modal("show");
			</c:if>
			$(document).on('click', '#userId', function () {
				$("#EditProfileModal").modal("show");
			});
			$(document).on('click', '#housePub', function () {
				$("#HousePublishModal").modal("show");
			});

			$(document).on('click', '#logout', function (e) {
				var index = "${pageContext.request.contextPath}/"
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/logout",
					data: {},
					success: function (data) {
						window.location.href = index;
						location.reload()
					}
				});
			});
		</c:when>

		<c:otherwise>
			<c:if test="${requestScope.isLoginPage}">
				$("#loginModal").modal("show");
			</c:if>

			<c:if test="${requestScope.isSignUpPage}">
				$("#SignUpModal").modal("show");
			</c:if>
			$(document).on('click', '#Login', function () {
				$("#loginModal").modal("show");
			});

			$(document).on('click', '#SignUp', function () {
				$("#SignUpModal").modal("show");
			});

			$(document).on('click', '#goSignUpLogin', function () {
				$("#SignUpModal").modal("hide");
				$("#loginModal").modal("show");              
			});
		</c:otherwise>
	</c:choose>

</script>

<script>
	$(document).on('click', '#contact', function () {
		$("#SendMessageModal").modal("show");
	});
	$(document).on('click', '#book', function () {
		$("#BookDetailsModal").modal("show");
	});
		
</script>

<script type="text/javascript">
    $(document).ready(function () {

	$('.star').on('click', function () {
      $(this).toggleClass('star-checked');
    });


    $('.btn-filter').on('click', function () {
      var $target = $(this).data('target');
      if ($target != 'all') {
        $('.table tr').css('display', 'none');
        $('.table tr[data-status="' + $target + '"]').fadeIn('slow');
      } else {
        $('.table tr').css('display', 'none').fadeIn('slow');
      }
    });

 });
</script>