<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">File Config</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">File
					Configs</span>
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
						<th>Department name <i class="icon-sort"></i></th>
						<th>Name<i class="icon-sort"></i></th>
						<th>Type<i class="icon-sort"></i></th>
						<th>Separator<i class="icon-sort"></i></th>
						<th>Format line <i class="icon-sort"></i></th>
						<th>Cron<i class="icon-sort"></i></th>
						<th>Path<i class="icon-sort"></i></th>
						<th>Detail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="fileconfig" items="${fileconfigs}">
						<tr>
							<td>${fileconfig.department.departmentName}</td>
							<td>${fileconfig.fileConfigName}</td>
							<td>${fileconfig.fileType}</td>
							<td>${fileconfig.fileSeparator}</td>
							<td>${fileconfig.fileFormatLine}</td>
							<td>${fileconfig.fileCron}</td>
							<td>${fileconfig.filePath}</td>
							<td><a class="btn btn-small"
								href="<c:url value="/fileConfigDetail.do?id=${fileconfig.id}" />">
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

<a href="<c:url value='/welcome.do'/>"><span class="btn btn-important">Back</span></a>