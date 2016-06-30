<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- start: Meta -->
<meta charset="utf-8">
<!-- end: Meta -->

<title><tiles:insertAttribute name="headerTitle" /></title>

<!-- start: CSS -->
<link id="bootstrap-style"
	href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-responsive.min.css'/>"
	rel="stylesheet">
<link id="base-style" href="<c:url value='/css/style.css'/>"
	rel="stylesheet">
<link id="base-style-responsive"
	href="<c:url value='/css/style-responsive.css'/>" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'>
<!-- end: CSS -->

<style>
/* filter table specific styling */
td.alt {
	background-color: #ffc;
	background-color: rgba(255, 255, 0, 0.2);
}
</style>

<!-- start: JavaScript-->
<script src="<c:url value='/js/jquery-1.9.1.min.js'/>"></script>
<script src="<c:url value='/js/jquery-migrate-1.0.0.min.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.10.0.custom.min.js'/>"></script>
<script src="<c:url value='/js/jquery.ui.touch-punch.js'/>"></script>
<script src="<c:url value='/js/modernizr.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/jquery.cookie.js'/>"></script>
<script src="<c:url value='/js/fullcalendar.min.js'/>"></script>
<script src="<c:url value='/js/jquery.dataTables.min.js'/>"></script>
<script src="<c:url value='/js/excanvas.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.pie.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.stack.js'/>"></script>
<script src="<c:url value='/js/jquery.flot.resize.min.js'/>"></script>
<script src="<c:url value='/js/jquery.chosen.min.js'/>"></script>
<script src="<c:url value='/js/jquery.uniform.min.js'/>"></script>
<script src="<c:url value='/js/jquery.cleditor.min.js'/>"></script>
<script src="<c:url value='/js/jquery.noty.js'/>"></script>
<script src="<c:url value='/js/jquery.elfinder.min.js'/>"></script>
<script src="<c:url value='/js/jquery.filtertable.js'/>"></script>
<script src="<c:url value='/js/jquery.filtertable.min.js'/>"></script>
<script src="<c:url value='/js/jquery.raty.min.js'/>"></script>
<script src="<c:url value='/js/jquery.iphone.toggle.js'/>"></script>
<script src="<c:url value='/js/jquery.uploadify-3.1.min.js'/>"></script>
<script src="<c:url value='/js/jquery.gritter.min.js'/>"></script>
<script src="<c:url value='/js/jquery.imagesloaded.js'/>"></script>
<script src="<c:url value='/js/jquery.masonry.min.js'/>"></script>
<script src="<c:url value='/js/jquery.knob.modified.js'/>"></script>
<script src="<c:url value='/js/jquery.sparkline.min.js'/>"></script>
<script src="<c:url value='/js/jquery.tablesorter.js'/>"></script>
<script src="<c:url value='/js/counter.js'/>"></script>
<script src="<c:url value='/js/retina.js'/>"></script>
<script src="<c:url value='/js/custom.js'/>"></script>
<script src="<c:url value='/js/tableExport.js'/>"></script>
<script src="<c:url value='/js/jquery.base64.js'/>"></script>
<script src="<c:url value='/js/sprintf.js'/>"></script>
<script src="<c:url value='/js/jspdf.js'/>"></script>
<script src="<c:url value='/js/base64.js'/>"></script>

<script type="text/javascript">
	function loading() {
		$('#loading').show();
	}
	$(document).ready(function() {
		$('.datepicker').each(function(i) {
			var temp = $('.datepicker')[i].value;
			$(this).datepicker('option', 'dateFormat', 'yy-mm-dd');
			$(this).datepicker('option', 'firstDay', 1);
			if (temp) {
				$(this).datepicker('setDate', new Date(temp));
			} else {
				$(this).datepicker('setDate', '');
			}
			$(this).datepicker('option', 'showOn', 'both');
			$(this).datepicker('option', 'buttonImageOnly', true);
			$(this).datepicker('option', 'buttonImage', 'img/calendar.png');
		});
	});

	$(document).ready(function() {
		$("#myTable").tablesorter();
	});
	$(document).ready(function() {
		$("#myTable2").tablesorter();
	});

	$(document).ready(function() {
		$('#myTable').filterTable({
			filterExpression : 'filterTableFindAll'
		});
	});
</script>
<!-- end: JavaScript -->

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>" />
<!-- end: Favicon -->

</head>

<body>
	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"> <%-- <img src="<c:url value='/img/logo.png'/>" width="5%"> --%>
				</span>
				</a> <a class="brand"><span>ReportingTool v1.0</span></a>

				<!-- start: Header Menu -->
				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<!-- start: User Dropdown -->
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> ${user.username} <span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title"><span>Account
										Settings</span></li>
								<!--
								<li><a href="#"><i class="halflings-icon user"></i> Profile</a></li>
								-->
								<li><a href="<c:url value='/login.do'/>"><i
										class="halflings-icon off"></i> Logout</a></li>
							</ul></li>
						<!-- end: User Dropdown -->
					</ul>
				</div>
				<!-- end: Header Menu -->
			</div>
		</div>
	</div>
	<!-- end: Header -->
	<!-- start: Main Container -->
	<div class="container-fluid-full">
		<div class="row-fluid">
			<!-- start: Main Menu -->
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<!-- <li><a href="<c:url value='/welcome.do'/>"><i class="icon-bar-chart"></i><span
								class="hidden-tablet"> Dashboard</span></a></li> -->
						<li><a href="<c:url value='/dataManager.do'/>"><i
								class="icon-pencil"></i><span class="hidden-tablet"> Data
									Manager</span></a></li>
						<li><a class="dropmenu" href="#"><i
								class="icon-warning-sign"></i><span class="hidden-tablet">
									Errors</span></a>
							<ul>
								<li><a class="submenu"
									href="<c:url value='/loadError.do'/> "><i
										class="icon-warning-sign"></i><span class="hidden-tablet">
											Load Errors</span> </a></li>
								<li><a class="submenu"
									href="<c:url value='/reportError.do?id='/>"><i
										class="icon-warning-sign"></i><span class="hidden-tablet">
											Report Errors</span></a></li>
								<!-- <li><a class="submenu" href="reportDataError.do"><i
										class="icon-warning-sign"></i><span class="hidden-tablet">
											Report Data Errors</span></a></li> -->
							</ul></li>
						<li><a href="<c:url value='/loads.do'/>"><i
								class="icon-file-alt"></i><span class="hidden-tablet">
									Loads</span></a></li>
						<li><a href="<c:url value='/user.do'/>"><i
								class="icon-user"></i><span class="hidden-tablet"> User</span></a></li>
						<li><a class="dropmenu" href="#"><i class="icon-wrench"></i><span
								class="hidden-tablet"> Admin</span></a>
							<ul>
								<li><a class="submenu"
									href="<c:url value='/admin/admin.do'/>"><i
										class="icon-cogs"></i><span class="hidden-tablet">
											General Admin</span></a></li>
								<li><a class="submenu"
									href="<c:url value='/reportCatalog.do'/>"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											Report Catalog</span></a></li>
								<li><a class="submenu"
									href="<c:url value='/admin/userManager.do'/>"><i
										class="icon-user"></i><span class="hidden-tablet"> User
											Manager</span></a></li>
								<li><a class="submenu"
									href="<c:url value='/fileConfig.do'/>"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											File Config</span></a></li>
								<li><a class="submenu"
									href="<c:url value='/fileColumList.do'/>"><i
										class="icon-file-alt"></i><span class="hidden-tablet">
											File Colum List</span></a></li>
								<li><a class="submenu"
									href="<c:url value='/reportFieldList.do'/>"><i
										class="icon-list"></i><span class="hidden-tablet">
											Report Field List</span></a></li>
							</ul></li>
						<!-- <li><a href="ReportsList?r=aifm"><i class="icon-list-alt"></i><span class="hidden-tablet"> AIFM Reports</span></a></li>
						<li><a href="ReportsList?r=aif"><i class="icon-list-alt"></i><span class="hidden-tablet"> AIF Reports</span></a></li> -->
					</ul>
				</div>
			</div>
			<!-- end: Main Menu -->

			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<!-- start: Content -->
			<div id="content" class="span10">

				<tiles:insertAttribute name="mainContent" />

			</div>
			<!-- end: Content -->
		</div>
	</div>
	<!--end: Main Container -->

	<tiles:insertAttribute name="modalWindow" />

	<div id="loading"
		style="position: fixed; top: 50%; right: 50%; left: 50%; width: auto; margin: 0;">
		<img alt="progress" src="<c:url value='/img/progress.gif'/>"></img>
	</div>

	<div class="clearfix"></div>

	<footer>
		<p>
			<span style="text-align: left; float: left">&copy; 2015 - Risk
				Management Solutions (Madrid)</span>
		</p>
	</footer>

</body>
</html>
