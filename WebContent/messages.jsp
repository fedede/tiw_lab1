<%-- Add JSTL support --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<link rel="stylesheet" href="css/messaging.css">

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

		<!-- end:header-top -->
     <div id="message-container" class="fh5co-section-gray">	
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
						<h3>Messages</h3>
						<p>These are the messages you received by now.</p>
				</div>
			</div>
			
			<div class="row row-bottom-padded-md">			
				<div class="col-md-8 col-md-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="pull-right">
								<div class="btn-group">
									<button type="button" class="btn btn-success btn-filter" data-target="leido">Leidos</button>
									<button type="button" class="btn btn-warning btn-filter" data-target="no-leido">No Leidos</button>
									<button type="button" class="btn btn-default btn-filter" data-target="all">Todos</button>
								</div>
							</div>
							<div class="table-container">
								<table class="table table-filter">
									<tbody>
										<c:forEach items="${requestScope.messages}" var="message">
											<c:choose>
												<c:when test='${false}'>
													<tr data-status="leido" class="leido">
												</c:when>
												<c:otherwise>
													<tr data-status="no-leido" class="no-leido">
												</c:otherwise>
											</c:choose>
												<td>
													<a href="javascript:;" class="star">
														<i class="glyphicon glyphicon-star"></i>
													</a>
												</td>
												<td>
													<div class="media">
														<h4 class="title">
															<c:out value='${message.getSender().getEmail()}'/>
														</h4>
													</div>
												</td>                                        
												<td>      
													<div class="media">
														<p class="summary"><c:out value="${message.getContent()}"/></p>
														<p class="meta"><c:out value='${message.getSendDate()}'/></p>                                                
													</div>
												</td>
											</tr>
										</c:forEach>                                        
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 

		
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

