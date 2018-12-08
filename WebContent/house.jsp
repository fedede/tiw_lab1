<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<page import=com.gr8.bb.models.House> 
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>TIWbnb</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
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
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">

	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>
	
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

		<div id="fh5co-tours" class="fh5co-section-gray">
			<div class="container">
		
				<div class="row">
					<div class="col-md-12 animate-box">
						<h2 class="heading-title">${foundHouse.name}</h2>
					</div>
					<div class="col-md-6 animate-box">
                        <span class="description">
						<p>${foundHouse.fullDesc}</p> 
                        </span>
                        <table class="table">
                            <tbody>
                                <tr>                                
                                    <th scope="row">Host:</th>
                                    <td><span class="host">${requestScope.foundHouse.getUser().getName()}</span></td>
                                </tr>
                                
                                <tr>                                
                                        <th scope="row">Price:</th>
                                    <td><span class="price">${foundHouse.price}EUR</span></td>
                                </tr>
                                <tr>
                                        <th scope="row">Number of beds:</th>
                                        <td><span class="beds">${foundHouse.guestNum}</span></td>
                                </tr>
                                <tr>
                                        <th scope="row">Type of apartment:</th>
                                        <td><span class="type">Full Apartment</span></td>
                                </tr>                                
                            </tbody>
                        </table>
                        <div class="col-xxs-12 col-xs-6 mt">
                        	<c:choose> 
								<c:when test="${sessionScope.authenticated == true}">
                            		<input id="book" type="book" class="btn btn-primary btn-block" value="Book">
                        		</c:when>
                        		<c:otherwise>
                        			<input id="book" type="book" class="btn btn-primary btn-block disabled" value="Book">
                        		</c:otherwise>
                        	</c:choose>
                        </div>
                        <div class="col-xxs-12 col-xs-6 mt">
                            <c:choose> 
								<c:when test="${sessionScope.authenticated == true}">
                            		<input id="contact" type="contact" class="btn btn-primary btn-block" value="Contact">
                        		</c:when>
                        		<c:otherwise>
                        			<input id="contact" type="contact" class="btn btn-primary btn-block disabled" value="Contact">
                        		</c:otherwise>
                        	</c:choose>
                            
                        </div>
                                                                        
                    </div>
					<div class="col-md-6 animate-box">
						<img class="img-responsive" src="${requestScope.foundHouse.getImg()}" alt="travel">
					</div>
				</div>
			</div>
		</div>
		
		   
		
        <c:choose> 
			<c:when test="${sessionScope.authenticated == true}">
			    <%@ include file="include/sendMessageModal.jsp" %>
		        <%@ include file="include/bookModal.jsp" %>      
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

