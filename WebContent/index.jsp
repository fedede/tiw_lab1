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

		<header id="fh5co-header-section" class="sticky-banner">
			<div class="container">
				<div class="nav-header">
					<a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle dark"><i></i></a>
					<h1 id="fh5co-logo"><a href="/index.jsp"><i class="icon-airplane"></i>TIWbnb</a></h1>
					<!-- START #fh5co-menu-wrap -->
					<nav id="fh5co-menu-wrap" role="navigation">
						<ul class="sf-menu" id="fh5co-primary-menu">
							<li class="active"><a href="index.jsp">Home</a></li>
							<li ><a href="/viajes.jsp">Apartments</a></li>
							<c:choose>
								<c:when test="${sessionScope.authenticated == true}">
									<li ><a href="/messages">Messages</a></li>                              
									<li><a href="#" id="userId"><c:out value="${sessionScope.user.name}"/> Profile</a></li> 
									<li><a href="#" id="logout">Logout</a></li> 
								</c:when>
								<c:otherwise>
									<li><a href="#" id="SignUp">Sign Up</a></li> 
									<li><a href="#" id="Login">Log In</a></li> 
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div>
			</div>
		</header>

		<!-- end:header-top -->
	
		<div class="fh5co-hero">
			<div class="fh5co-overlay"></div>
			<div class="fh5co-cover" data-stellar-background-ratio="0.5" style="background-image: url(images/cover_bg_5.jpg);">
				<div class="desc">
					<div class="container">
						<div class="row">
							<div class="col-sm-5 col-md-5">
								<!-- <a href="index.html" id="main-logo">Travel</a> -->
								<div class="tabulation animate-box">

								  <!-- Nav tabs -->
								   <ul class="nav nav-tabs" role="tablist">
								      <li role="presentation" class="active">
								    	   <a href="#accommodations" aria-controls="Accommodations" role="tab" data-toggle="tab">Accommodations</a>
								      </li>
								   </ul>

								   <!-- Tab panes -->
									<div class="tab-content">
										<div role="tabpanel" class="tab-pane active" id="hotels">
									 		<div class="row">
									 			<form action ="results" method = "POST">
													<div class="col-xxs-12 col-xs-12 mt">
														<div class="input-field">
															<label for="from">City:</label>
															<input type="text" class="form-control" id="city" name="city" placeholder="Madrid, SPAIN"/>
														</div>
													</div>
													<div class="col-xxs-12 col-xs-6 mt alternate">
														<div class="input-field">
															<label for="date-start">Check-in:</label>
															<input type="text" class="form-control" id="date-start" name="date-start" placeholder="mm/dd/yyyy"/>
														</div>
													</div>
													<div class="col-xxs-12 col-xs-6 mt alternate">
														<div class="input-field">
															<label for="date-end">Check-out:</label>
															<input type="text" class="form-control" id="date-end" name="date-end" placeholder="mm/dd/yyyy"/>
														</div>
													</div>
	                                            	
													<div class="col-sm-12 mt">
														<section>
															<label for="class">Price</label>
															
															<select class="cs-select cs-skin-border" name="price">
																<option value="" disabled selected>Select Price</option>
																<option value="P1">Under 35EUR</option>            
																<option value="P2">36EUR - 69EUR</option>
																<option value="P3">70EUR - 130EUR¬</option>
																<option value="P4">131EUR or more</option>
															</select>
														</section>
													</div>
	                                            	
	        										<div class="col-sm-12 mt">
														<section>
															<label for="class">Type of accommodation</label>
															<select class="cs-select cs-skin-border" name="ac-type">
																<option value="" disabled selected>Select House Type</option>
																<option value="full">Full house</option>
																<option value="private">Private room</option>
																<option value="shared">Shared room</option>
															</select>
														</section>
													</div>
	                                            	
													<div class="col-xxs-12 col-xs-6 mt">
														<section>
															<label for="class">Adults:</label>
															<select class="cs-select cs-skin-border" id="adults" name="adults">
																<option value="" disabled selected>0</option>
																<option value="1">1</option>
																<option value="2">2</option>
																<option value="3">3</option>
																<option value="4">4</option>
															</select>
														</section>
													</div>
													<div class="col-xxs-12 col-xs-6 mt">
														<section>
															<label for="class">Children:</label>
															<select class="cs-select cs-skin-border" name="children">
																<option value="" disabled selected>0</option>
																<option value="1">1</option>
																<option value="2">2</option>
																<option value="3">3</option>
																<option value="4">4</option>
															</select>
														</section>
													</div>
													<div class="col-xs-12">
														<input type="submit" class="btn btn-primary btn-block" value="Find">
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="desc2 animate-box">
								<div class="col-sm-7 col-sm-push-1 col-md-7 col-md-push-1">
									<h3>TIWbnb</h3>
									<h2>Plan your stay</h2>
									<h3>Choose among thousand of available apartments</h3>
									<p>Starting from <span class="price">35EUR</span> one night</p>
									<!-- <p><a class="btn btn-primary btn-lg" href="#">Get Started</a></p> -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		            

		<c:choose> 
			<c:when test="${sessionScope.authenticated == true}">
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
									<input type="text" id="editName" name="editName" class="form-control" value="${sessionScope.user.name}" placeholder="Name" required>
									<input type="text" id="editSurname" name="editSurname" class="form-control" value="${sessionScope.user.surname}" placeholder="Surname" required>              
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
			</c:when>
			<c:otherwise>
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
				            
				            
				<!-- SignUp Modal -->
				<div class="modal fade" id="SignUpModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
						
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h1 class="h3 mb-3 font-weight-normal">Enter your data</h1>
								<c:if test="${requestScope.signUpErrorMessage != null}">
									<span style="color: red;"> <c:out value="${requestScope.signUpErrorMessage}" /></span>
								</c:if>
							</div>
							<div class="modal-body">
								<form class="form-registro" action="signup" method="post">
									<input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
									<input type="text" id="inputName" name="inputName" class="form-control" placeholder="Name" required>
									<input type="text" id="inputSurname" name="inputSurname" class="form-control" placeholder="Surname" required>              
									<input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
									<button class="btn btn-lg btn-primary btn-block" type="submit" id="Registrate">Sign up</button>
								</form>
							
							</div>
							
							<div class="modal-footer">
								<p class="text-center">Do you have an account?<a href="#" id="goSignUpLogin">Log In</a></p>
							</div>
						
						</div>
					</div>
				</div>            
			</c:otherwise>
		</c:choose>
            
		<footer>
			<div id="footer">
				<div class="container">
					<div class="row row-bottom-padded-md">
						<div class="col-md-2 col-sm-2 col-xs-12 fh5co-footer-link">
							<h3>About TIWbnb</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla porttitor enim et libero pharetra, Nam ipsum augue, eleifend ut dui eu, egestas malesuada velit. </p>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-12 fh5co-footer-link">
							<h3>Lorem ipsum </h3>
							<ul>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
							</ul>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-12 fh5co-footer-link">
							<h3>Lorem ipsum </h3>
							<ul>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
							</ul>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-12 fh5co-footer-link">
							<h3>Lorem ipsum </h3>
							<ul>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
							</ul>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-12 fh5co-footer-link">
							<h3>Lorem ipsum </h3>
							<ul>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
							</ul>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-12 fh5co-footer-link">
							<h3>Lorem ipsum </h3>
							<ul>
								<li><a href="#">Xxxxx xxxx</a></li>
								<li><a href="#">Xxxxx xxxx</a></li>
							</ul>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-md-offset-3 text-center">
							<p class="fh5co-social-icons">
								<a href="#"><i class="icon-twitter2"></i></a>
								<a href="#"><i class="icon-facebook2"></i></a>
								<a href="#"><i class="icon-instagram"></i></a>
								<a href="#"><i class="icon-dribbble2"></i></a>
								<a href="#"><i class="icon-youtube"></i></a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</footer>

	

	</div>
	<!-- END fh5co-page -->

	</div>
	<!-- END fh5co-wrapper -->

                
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

				$(document).on('click', '#logout', function (e) {
					$.ajax({
						type: "POST",
						url: "${pageContext.request.contextPath}/logout",
						data: {},
						success: function (data) {
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
        
	</body>
</html>

