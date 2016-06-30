<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/user.do'/>">User</a></li>
</ul>
<!-- end: Breadcrumb -->

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

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i> <span class="break">Users</span>
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
						<th>User Name<i class="icon-sort"></i></th>
						<th>First Name<i class="icon-sort"></i></th>
						<th>Last Name<i class="icon-sort"></i></th>
						<!-- <th>Role<i class="icon-sort"></i></th> -->
						<th>Mail<i class="icon-sort"></i></th>
						<th>Company<i class="icon-sort"></i></th>
						<th>Last login <i class="icon-sort"></i></th>
						<th>Enabled<i class="icon-sort"></i></th>
						<th>Detail</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${user.username}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<%-- <td>${user.userRol.rolName}</td> --%>
						<td>${user.userMail}</td>
						<td>${user.company.companyName}</td>
						<td>${user.lastLoginTms}</td>
						<td>${user.enabled}</td>
						<td><a class="btn btn-small"
							href="<c:url value="/userDetail.do" />"> Edit </a></td>
						<td><a class="btn btn-small"
							href="<c:url value="/userChangePass.do" />"> Change password </a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->


<a href="<c:url value='/welcome.do'/>"><span class="btn btn-important">Back</span></a>