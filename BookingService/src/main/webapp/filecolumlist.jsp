<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">File Colum List</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">File
					Colum List</span>
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
						<th>Name<i class="icon-sort"></i></th>
						<th>Number<i class="icon-sort"></i></th>
						<th>Type<i class="icon-sort"></i></th>
						<th>Origin<i class="icon-sort"></i></th>
						<th>Destination<i class="icon-sort"></i></th>
						<!-- <th>Detail</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="filecolumlist" items="${filecolumlist}">
						<tr>
							<td>${filecolumlist.fileColum.columName}</td>
							<td>${filecolumlist.fileColum.columNumber}</td>
							<td>${filecolumlist.fileColumListType}</td>
							<td>${filecolumlist.fileColumListOrig}</td>
							<td>${filecolumlist.fileColumListDest}</td>
							<%-- <td>
								<a class="btn btn-small" href="<c:url value="fileConfigDetail.do?id=${fileconfig.id}" />" >
									detail
								</a>
							</td> --%>
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