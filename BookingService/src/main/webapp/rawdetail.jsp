<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/loads.do'/>">Loads</a> <i class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/loadDetail.do?id=${loadRaw.loadFile.id}'/>">Load
			Detail</a> <i class="icon-angle-right"></i></li>
	<li><a href="#">Colum Detail</a>
</ul>
<!-- end: Breadcrumb -->

<%-- <div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break"></span>
				${loadRaw.loadFile.loadFileName} - Line ${loadRaw.loadLineNumber +1}
			</h2>
			<div class="box-icon">
				<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a> <a href="#"
					class="btn-close"><i class="halflings-icon remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>Name</th>
						<th>Status</th>
						<th>Company</th>
						<th>Department</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${loadRaw.id}</td>
						<td></td>
						<td>${loadRaw.id}</td>
						<td>${loadRaw.id}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row--> --%>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break"></span>
				File ${loadRaw.loadFile.loadFileName} - Line
				${loadRaw.loadLineNumber +1}
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
			<!-- table table-bordered table-striped table-condensed table table-striped table-bordered bootstrap-datatable datatable-->
			<table id="myTable"
				class="table table-striped table-bordered table-condensed bootstrap-datatable datable">
				<thead>
					<tr>
						<th>File Column <i class="icon-sort"></i></th>
						<th>Column Num <i class="icon-sort"></i></th>
						<th>Data<i class="icon-sort"></i></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rawData" items="${loadRaw.loadRawDatas}">
						<tr>
							<td>${rawData.fileColum.columName}</td>
							<td>${rawData.fileColum.columNumber}</td>
							<td>${rawData.loadRawDataText}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<a href="<c:url value='/loadDetail.do?id=${loadRaw.loadFile.id}'/>"><span
	class="btn btn-important">Back</span></a>