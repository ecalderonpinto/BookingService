<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/admin/admin.do'/>">General Admin</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Report Catalog</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Report
					Catalogs</span>
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table id="myTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Report Name<i class="icon-sort"></i></th>
						<th>Level<i class="icon-sort"></i></th>
						<th>Version<i class="icon-sort"></i></th>
						<th>Description<i class="icon-sort"></i></th>
						<th>Details</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reportcatalog" items="${reportcatalogs}">
						<tr>
							<td>${reportcatalog.reportCatalogName}</td>
							<td>${reportcatalog.reportLevel}</td>
							<td>${reportcatalog.reportVersion}</td>
							<td>${reportcatalog.reportCatalogDesc}</td>
							<td><a class="btn btn-small"
								href="<c:url value="/reportCatalogDetail.do?id=${reportcatalog.id}"/>">
									detail </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->


<a href="<c:url value='/admin/admin.do'/>"><span class="btn btn-important">Back</span></a>