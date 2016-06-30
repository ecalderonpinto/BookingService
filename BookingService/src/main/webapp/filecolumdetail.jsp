<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/fileConfig.do'/>">File Config</a> <i class="icon-angle-right"></i>
	</li>
	<li><a href="<c:url value="/fileConfigDetail.do?id=${fileconfig.id}"/> ">File Config Detail</a> <i class="icon-angle-right"></i>
	</li>
	<li><a href="#">File Colum Detail</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="fileconfig">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">File Config</span>
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
							<th>Department name</th>
							<th>Name</th>
							<th>Type</th>
							<th>Separator</th>
							<th>Format line</th>
							<th>Cron</th>
							<th>Path</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${fileconfig.department.departmentName}</td>
							<td>${fileconfig.fileConfigName}</td>
							<td>${fileconfig.fileType}</td>
							<td>${fileconfig.fileSeparator}</td>
							<td>${fileconfig.fileFormatLine}</td>
							<td>${fileconfig.fileCron}</td>
							<td>${fileconfig.filePath}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>

<form:form method="POST" commandName="fileconfig">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">File Colum</span>
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
							<th>Name</th>
							<th>Type</th>
							<th>Format</th>
							<th>Number</th>
							<th>Block</th>
							<th>Description</th>
							<th>Field name</th>
							<th>Field number</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${filecolum.columName}</td>
							<td>${filecolum.columType}</td>
							<td>${filecolum.columFormat}</td>
							<td>${filecolum.columNumber}</td>
							<td>${filecolum.columBlock}</td>
							<td>${filecolum.columDesc}</td>
							<td>${filecolum.reportField.reportFieldName}</td>
							<td>${filecolum.reportField.reportFieldNum}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form:form>

<a href="<c:url value='/fileConfig.do'/>"><span
	class="btn btn-important">Back</span></a>