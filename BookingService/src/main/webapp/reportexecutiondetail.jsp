<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a
		href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/dataManager.do'/>">Data Manager</a><i
		class="icon-angle-right"></i>
	<li><a href="<c:url value='/companyDetail.do?id=${company.id}'/>">Company
			detail ${company.companyName}</a> <i class="icon-angle-right"></i></li>
	<c:choose>
		<c:when test="${reportExecution.reportCatalog.reportLevel == 'FUND' }">
			<li><a
				href="<c:url value='/fundReports.do?id=${reportExecution.fund.id}'/>">Reports
					(${reportExecution.fund.fundName})</a> <i class="icon-angle-right"></i></li>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when
					test="${reportExecution.reportCatalog.reportLevel == 'DEPARTMENT' }">
					<li><a
						href="<c:url value='/departmentReports.do?id=${reportExecution.department.id}'/>">Reports
							(${reportExecution.department.departmentName})</a> <i
						class="icon-angle-right"></i></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="<c:url value='/companyReports.do?id=${reportExecution.company.id}'/>">Reports
							(${reportExecution.company.companyName})</a> <i
						class="icon-angle-right"></i></li>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<li><a href="#">New AIFM Report</a></li>
</ul>
<!-- end: Breadcrumb -->

${resultMessage}

<form:form method="POST" commandName="reportExecution">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">New
						AIFM Report</span>
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<table class="table table-striped table-bordered table-condensed">
					<tbody>
						<tr>
							<td>Report Name <b>(*)</b></td>
							<td><form:input path="reportExecutionName"
									cssClass="input-xlarge" required="required" maxlength="100" /></td>
							<td>Report Description</td>
							<td><form:input path="reportExecutionDesc"
									cssClass="input-xlarge" maxlength="300" /></td>
						</tr>
						<tr>
							<td>Period Type <b>(*)</b></td>
							<td><form:input path="reportPeriodType"
									cssClass="input-xlarge" required="required" maxlength="10" /></td>
							<td>Period Year <b>(*)</b></td>
							<td><form:input path="reportPeriodYear"
									cssClass="input-xlarge" required="required" maxlength="4" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->

	<!-- FormActions -->
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon edit"></i><span class="break"></span>Actions
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<fieldset>
					<div class="form-actions">
						<button type="submit" class="btn btn-danger">Save changes</button>
						<button class="btn">Cancel</button>
					</div>
				</fieldset>

			</div>
		</div>
		<!--/span-->
	</div>
	<!-- /FormActions -->

</form:form>

<!--back button-->
<c:choose>
	<c:when test="${reportExecution.reportCatalog.reportLevel == 'FUND' }">
		<a
			href="<c:url value='/fundReports.do?id=${reportExecution.fund.id}'/>"><span
			class="btn btn-important">Back</span></a>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when
				test="${reportExecution.reportCatalog.reportLevel == 'DEPARTMENT' }">
				<a
					href="<c:url value='/departmentReports.do?id=${reportExecution.department.id}'/>">
					<span class="btn btn-important">Back</span>
				</a>
			</c:when>
			<c:otherwise>
				<a
					href="<c:url value='/companyReports.do?id=${reportExecution.company.id}'/>"><span
					class="btn btn-important">Back</span></a>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>
