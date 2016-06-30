<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/dataManager.do'/>">Data Manager</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Company detail ${company.companyName}</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="company">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">Company:  ${contCompany} reports</span>
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
							<th>Code</th>
							<th>Country</th>
							<th>Description</th>
							<th>Reports</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${company.companyName}</td>
							<td>${company.companyCode}</td>
							<td>${company.companyCountry}</td>
							<td>${company.companyDesc}</td>
							<td><c:if test="${company.companyCode != 'AML'}">
									<a class="btn btn-small btn-primary"
										href="<c:url value="/companyReports.do?id=${company.id}" />">
										AIFM Reports: ${contCompany}</a>
								</c:if></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->

	<c:if test="${fn:length(company.departments) gt 0}">
		<div class="row-fluid sortable">
			<div class="box span12">
				<div class="box-header">
					<h2>
						<i class="halflings-icon align-justify"></i> <span class="break">Departments:  ${contDepartment} reports</span>
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
								<th>Code<i class="icon-sort"></i></th>
								<th>Country<i class="icon-sort"></i></th>
								<th>Description<i class="icon-sort"></i></th>
								<th>Reports</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="department" varStatus="status"
								items="${company.departments}">
								<tr>
									<td>${department.departmentName}</td>
									<td>${department.departmentCode}</td>
									<td>${department.departmentCountry}</td>
									<td>${department.departmentDesc}</td>
									<td><c:if test="${department.departmentCode == 'AML'}">
											<a class="btn btn-small btn-primary"
												href="<c:url value="/departmentReports.do?id=${department.id}" />">
												DMO Reports: ${fn:length(department.reportExecutions)}</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- end departments -->
	</c:if>

	<c:if test="${fn:length(company.funds) gt 0}">
		<div class="row-fluid sortable">
			<div class="box span12">
				<div class="box-header">
					<h2>
						<i class="halflings-icon align-justify"></i> <span class="break">Funds:  ${contFund} reports</span>
					</h2>
					<div class="box-icon">
						<a href="#"
							onclick="$('#myTable2').tableExport({type:'excel',escape:'false'});">
							<img src="img/xls.png" width="21px">
						</a> <a href="#"
							onclick="$('#myTable2').tableExport({type:'pdf',pdfFontSize:'7',escape:'false'});">
							<img src="img/pdf.png" width="21px">
						</a> <a href="#" class="btn-minimize"><i
							class="halflings-icon chevron-up"></i></a>
					</div>
				</div>
				<div class="box-content">
					<table id="myTable2"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>Name<i class="icon-sort"></i></th>
								<th>Isin<i class="icon-sort"></i></th>
								<th>Code<i class="icon-sort"></i></th>
								<th>Description<i class="icon-sort"></i></th>
								<th>Class<i class="icon-sort"></i></th>
								<th>Reports</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fund" varStatus="status" items="${company.funds}">
								<tr>
									<td>${fund.fundName}</td>
									<td>${fund.fundIsin}</td>
									<td>${fund.fundCode}</td>
									<td>${fund.fundDesc}</td>
									<td>${fund.fundClass}</td>
									<td><a class="btn btn-small btn-primary"
										href="<c:url value="/fundReports.do?id=${fund.id}" />"> AIF
											Reports: ${fn:length(fund.reportExecutions)}</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- end funds -->
	</c:if>

</form:form>

<a href="<c:url value='/dataManager.do'/>"><span class="btn btn-important">Back</span></a>