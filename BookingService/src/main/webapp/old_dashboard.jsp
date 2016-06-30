<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<body>
	<!-- start: Header -->
	<div class="navbar">
		<div class="navbar-inner">
			<jsp:include page="headerMenu.jsp" />
		</div>
	</div>
	<!-- end: Header -->
	<!-- start: Main Container -->
	<div class="container-fluid-full">
		<div class="row-fluid">
			<jsp:include page="leftMenu.jsp" />
				
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="Login">Home</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">Dashboard</a></li>
			</ul>

			<div class="row-fluid">
				
				<div class="span4 statbox red" onTablet="span6" onDesktop="span3">
					<div class="boxchart">5,6,7,8,2,4,5,8,7,8</div>
					<div class="number">200<i class="icon-arrow-up"></i></div>
					<div class="title">Errors in AIFMD</div>
					<div class="footer">
						<a href="ReportsList?r=aifm"> more</a>
					</div>	
				</div>
			
				<div class="span3 statbox red" onTablet="span6" onDesktop="span3">
					<div class="boxchart">5,6,7,8,9,4,5,2,7,8</div>
					<div class="number">123<i class="icon-arrow-up"></i></div>
					<div class="title">Errors in AIF</div>
					<div class="footer">
						<a href="ReportsList?r=aif"> more</a>
					</div>
				</div>
				<div class="span3 statbox orange" onTablet="span6" onDesktop="span3">
					<div class="boxchart">5,6,7,8,2,4,5,8,7,8</div>
					<div class="number">10<i class="icon-arrow-up"></i></div>
					<div class="title">Errors in Loads</div>
					<div class="footer">
						<a href="Loads"> more</a>
					</div>	
				</div>
			</div><!--/row-->
			
			<div class="row-fluid hideInIE8 circleStats">
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox pink">
						<div class="header">AIF - Q1</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="100" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox pink">
						<div class="header">AIF - Q2</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="83" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">83</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox pink">
						<div class="header">AIF - Q3</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="14" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">14</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox pink">
						<div class="header">AIF - Q4</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="5" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">5</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
		</div><!--/row-->
		
		<div class="row-fluid hideInIE8 circleStats">
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox yellow">
						<div class="header">AIF - H1</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="100" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox yellow">
						<div class="header">AIF - H2</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="83" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">83</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox green">
						<div class="header">AIF - Y1</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="14" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">14</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox red">
						<div class="header">AIF - X1</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="5" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">5</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
				
				<div class="span2 noMargin" onTablet="span4" onDesktop="span2">
                	<div class="circleStatsItemBox red">
						<div class="header">AIF - X2</div>
						<span class="percent">% completed</span>
                    	<div class="circleStat">
                    		<input type="text" value="5" class="whiteCircle" />
						</div>
						<div class="footer">
							<span class="count">
								<span class="number">5</span>
								<span class="unit"></span>
							</span>
							<span class="sep"> / </span>
							<span class="value">
								<span class="number">100</span>
								<span class="unit"></span>
							</span>	
						</div>
                	</div>
				</div>
		</div><!--/row-->

		</div><!--/.fluid-container-->
	
			<!-- end: Content -->
		</div><!--/#content.span10-->
	</div>
	<!--end: Main Container -->
		
	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>Settings</h3>
		</div>
		<div class="modal-body">
			<p>Here settings can be configured...</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a>
			<a href="#" class="btn btn-primary">Save changes</a>
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<footer>

		<p>
			<span style="text-align:left;float:left">&copy; 2015 - Risk Management Solutions (Madrid)</span>
		</p>

	</footer>
	
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
