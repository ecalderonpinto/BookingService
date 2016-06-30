<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/admin/admin.do'/>">General Admin</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Report Catalog Detail</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="catalog">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">Report
						Catalog</span>
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>Report Name</th>
							<th>Level</th>
							<th>Version</th>
							<th>Description</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${reportcatalog.reportCatalogName}</td>
							<td>${reportcatalog.reportLevel}</td>
							<td>${reportcatalog.reportVersion}</td>
							<td>${reportcatalog.reportCatalogDesc}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>


<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Report
					Fields</span>
			</h2>
			<div class="box-icon">
				<a href="#"
					onclick="$('#myTable').tableExport({type:'excel',escape:'false'});">
					<img src="img/xls.png" width="21px">
				</a> <a href="#"
					onclick="$('#myTable').tableExport({type:'pdf',pdfFontSize:'7',escape:'false'});">
					<img src="img/pdf.png" width="21px">
				</a> <a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<div class="box-content">
			<table id="myTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Field Name <i class="icon-sort"></i></th>
						<th>Type<i class="icon-sort"></i></th>
						<th>Number<i class="icon-sort"></i></th>
						<!-- <th>Format</th> -->
						<!-- <th>Description</th> -->
						<!-- <th>Mask</th> -->
						<th>Section<i class="icon-sort"></i></th>
						<th>Detail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="reportfield" items="${reportfields}">
						<tr>
							<td>${reportfield.reportFieldName}</td>
							<td>${reportfield.reportFieldType}</td>
							<td>${reportfield.reportFieldNum}</td>
							<%-- <td>${reportfield.reportFieldFormat}</td> --%>
							<%-- <td>${reportfield.reportFieldDesc}</td> --%>
							<%-- <td>${reportfield.reportFieldMask}</td> --%>
							<td>${reportfield.reportFieldSection}</td>
							<td><a class="btn btn-small"
								href="<c:url value="/reportFieldDetail.do?id=${reportfield.id}" />">
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