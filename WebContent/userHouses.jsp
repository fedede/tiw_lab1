<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<page import=com.gr8.bb.models.House> 
<!DOCTYPE html> <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]--> <!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>TIWbnb</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FREEHTML5.CO
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css">
<!-- Magnific Popup -->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- Date Picker -->
<link rel="stylesheet" href="css/bootstrap-datepicker.min.css">
<!-- CS Select -->
<link rel="stylesheet" href="css/cs-select.css">
<link rel="stylesheet" href="css/cs-skin-border.css">

<link rel="stylesheet" href="css/style.css">


<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<%@ include file="include/navbar.jsp" %>

			<!-- end:header-top -->

			<c:choose>
			<c:when test="${houses == null || fn:length(house) le 0 }">
			<div id="fh5co-tours" class="fh5co-section-gray">
				<div class="container">
					<div class="row">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h3>Your have not pusblished any house yet.</h3>
						</div>
					</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div id="fh5co-tours" class="fh5co-section-gray">
				<div class="container">
					<div class="row">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h3>Your houses: </h3>
						</div>
					</div>

					<div class="row row-bottom-padded-md">
						<c:forEach items="${houses}" var="house">
							<div class="col-md-4 col-sm-6 fh5co-tours animate-box"
								data-animate-effect="fadeIn">
								<div href="#">
									<img src=${house.getImg()}
										alt="Free HTML5 Website Template by FreeHTML5.co"
										class="img-responsive">
									<div class="desc">
										<span></span>
										<h3>${house.getName()}</h3>
										<span>${house.getShortDescription()}</span> <span class="price">${house.getPrice()}EUR</span>
										<a class="btn btn-primary btn-outline" href="house?houseId=${house.getId()}">View <i
											class="icon-arrow-right22"></i></a>
										<a class="btn btn-primary btn-outline" href="house?houseId=${house.getId()}">Edit <i
											class="icon-arrow-right22"></i></a>
										<a class="btn btn-primary btn-outline" href="house?houseId=${house.getId()}">Delete <i
											class="icon-arrow-right22"></i></a>
									</div>
								</div>
							</div>
						</c:forEach>
						
					</div>

				</div>
			</div>
			</c:otherwise>
			</c:choose>

		<c:choose> 
			<c:when test="${sessionScope.authenticated == true}">
				<%@ include file="include/editProfileModal.jsp" %>
				<%@ include file="include/publishHouseModal.jsp" %>
			</c:when>
			<c:otherwise>
				<%@ include file="include/loginModal.jsp" %>
				<%@ include file="include/signUpModal.jsp" %>  
			</c:otherwise>
		</c:choose>
            
        <%@ include file="include/footer.jsp" %>


	</div>
	<!-- END fh5co-page -->

	</div>
	<!-- END fh5co-wrapper -->
    <%@ include file="include/scripts.jsp" %>
                

</body>
</html>