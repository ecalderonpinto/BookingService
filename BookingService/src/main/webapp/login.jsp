<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<html>
<head>

<!-- start: Meta -->
<meta charset="utf-8">
<title>Reporting System</title>
<!-- end: Meta -->

<!-- start: CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/style-responsive.css" rel="stylesheet" type="text/css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'> 
<!-- end: CSS -->


<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="img/favicon.ico">
<!-- end: Favicon -->

<style type="text/css">
body {
	background: url(img/bg-login.jpg) !important;
}
</style>

<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>

</head>

<body>

	<div class="container-fluid-full">
		<div class="row-fluid">

			<div class="row-fluid">
				<div class="login-box">
					<div style="text-align: center; margin-bottom: 50px;">
						<h1>Reporting Tool v1.0</h1>
					</div>

					<!--
					<div class="icons">
						<a href="index.html"><i class="halflings-icon home"></i></a>
						<a href="#"><i class="halflings-icon cog"></i></a>
					</div>
					-->

					<%-- <c:if test="${alerts}">
						<div class="box-content alerts">
							<c:choose>
								<c:when test="${alert.error}">
									<div class="alert alert-error">
										<button type="button" class="close" data-dismiss="alert">×</button>
										<strong>Error:</strong>
										<c:out value="${alert.message}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="alert alert-success">
										<button type="button" class="close" data-dismiss="alert">×</button>
										<strong>Success:</strong>
										<c:out value="${alert.message}" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if> --%>

					<h2>Login to your account</h2>
					<%-- <form:form method="POST" commandName="login"> --%>
					<form action="j_spring_security_check" method="post" id="loginForm">
						<fieldset>
							<div class="input-prepend" title="Username">
								<span class="add-on"><i class="halflings-icon user"></i></span>
								<%-- <form:input path="user" cssClass="input-large span10"
									required="required" maxlength="40" /> --%>
								<input style="height: 30px !important;" class="text" type="text" name="j_username" class="input-large"
									id="j_username" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password">
								<span class="add-on"><i class="halflings-icon lock"></i></span>
								<%-- 	<form:password path="password" cssClass="input-large span10"
									required="required" maxlength="128" /> --%>
								<input style="height: 30px !important;" class="text" type="password" name="j_password"
									id="j_password" />
							</div>
							<div class="clearfix"></div>
							<!--
							<label class="remember" for="remember"><input type="checkbox" id="remember" />Remember me</label>
							-->
							<input type='checkbox'
								name='_spring_security_remember_me' /> Remember me on this
							computer.
							<div class="button-login">
								<!-- <button type="submit" class="btn btn-primary">Login</button> -->
								<button type="submit">login</button>
								<%-- <a class="btn btn-primary" href="<c:url value="/welcome.do" />">Login</a> --%>
							</div>
							 <div class="clearfix"></div>
						</fieldset>
					</form>
					<%-- </form:form> --%>

					<h2>Forgot Password?</h2>
					<p>
						No problem, <a href="#">click here</a> to get a new password.
					</p>

				</div>
				<!--/span-->
			</div>
			<!--/row-->


		</div>
		<!--/.fluid-container-->

	</div>
	<!--/fluid-row-->

	<!-- start: JavaScript-->

	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-migrate-1.0.0.min.js"></script>
	<script src="js/jquery-ui-1.10.0.custom.min.js"></script>
	<script src="js/jquery.ui.touch-punch.js"></script>
	<script src="js/modernizr.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src='js/fullcalendar.min.js'></script>
	<script src='js/jquery.dataTables.min.js'></script>
	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>
	<script src="js/jquery.chosen.min.js"></script>
	<script src="js/jquery.uniform.min.js"></script>
	<script src="js/jquery.cleditor.min.js"></script>
	<script src="js/jquery.noty.js"></script>
	<script src="js/jquery.elfinder.min.js"></script>
	<script src="js/jquery.raty.min.js"></script>
	<script src="js/jquery.iphone.toggle.js"></script>
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<script src="js/jquery.gritter.min.js"></script>
	<script src="js/jquery.imagesloaded.js"></script>
	<script src="js/jquery.masonry.min.js"></script>
	<script src="js/jquery.knob.modified.js"></script>
	<script src="js/jquery.sparkline.min.js"></script>
	<script src="js/counter.js"></script>
	<script src="js/retina.js"></script>
	<script src="js/custom.js"></script>
	<!-- end: JavaScript-->

</body>
</html>