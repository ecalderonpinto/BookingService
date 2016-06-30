<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a>
		<i class="icon-angle-right"></i></li>
	<li><a href="<c:url value='admin.do'/>">General Admin</a></li>
</ul>
<!-- end: Breadcrumb -->

<!-- message success / fail from load file -->
<c:if test="${alerts}">
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
</c:if>
<!-- / message success / fail from load file -->

<div class="row-fluid sortable">
	<div class="box blue span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon hand-top"></i><span class="break"></span>Quick
				Actions
			</h2>
		</div>
		<div class="box-content">
			<a class="quick-button span2" onclick="loading();"
				href="<c:url value='/admin/generateDB.do'/>"> <i
				class="icon-cogs"></i> <br>Generate Schema
			</a> <a class="quick-button span2"
				href="<c:url value='/admin/installAIFMDData.do'/> "> <i
				class="icon-cogs"></i> <br>Install AIFMD
			</a> <a class="quick-button span2"
				href="<c:url value='/admin/installDMOData.do'/> "> <i
				class="icon-cogs"></i> <br>Install DMO
			</a> <a class="quick-button span2"
				href="<c:url value='/admin/cleanData.do'/>"> <i
				class="icon-cogs"></i> <br>Clean Datas
			</a>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--/span-->

</div>
<!--/row-->

<a href="<c:url value='/welcome.do'/>"><span class="btn btn-important">Back</span></a>
