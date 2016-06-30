<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/user.do'/>">User</a><i class="icon-angle-right"></i></li>
	<li><a href="#">User Detail</a></li>
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

<form:form method="POST" commandName="userdetail" name="MyFrom">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">User</span>
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
							<td><label class="control-label">User Name</label></td>
							<td><div class="controls">
									<span class="input-xlarge uneditable-input">${userdetail.user.username}</span>
								</div></td>
							<td>User Mail</td>
							<td><form:input path="user.userMail" cssClass="input-xlarge"
									maxlength="255" name="mail" /></td>
						</tr>
						<tr>
							<td><label class="control-label">First Name<b>(*)</b></label></td>
							<td><form:input path="user.firstName"
									cssClass="input-xlarge" maxlength="255" name="firstName"
									required="required" /></td>
							<td><label class="control-label">Last Name<b>(*)</b></label></td>
							<td><form:input path="user.lastName" cssClass="input-xlarge"
									maxlength="255" name="lastName" required="required" /></td>
						</tr>
						<tr>
							<td><label class="control-label">User Enabled</label></td>
							<td><div class="controls">
									<span class="input-xlarge uneditable-input">${userdetail.user.enabled}</span>
								</div></td>

							<td><label class="control-label">Company</label></td>
							<td><div class="controls">
									<span class="input-xlarge uneditable-input">${userdetail.user.company.companyName}</span>
								</div></td>
						</tr>
						<%-- <tr>
							<td><label class="control-label">User Role <b>(*)</b></label></td>
							<td colspan="3"><div class="controls">
									<span class="input-xlarge uneditable-input">${userdetailmanager.selectUserRol}</span>
								</div>
							</td>
						</tr> --%>
						<form:hidden path="userId" />

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
						<!-- <button class="btn">Cancel</button> -->
					</div>
				</fieldset>

			</div>
		</div>
		<!--/span-->
	</div>
	<!-- /FormActions -->

</form:form>

<a href="<c:url value='/user.do'/>"><span class="btn btn-important">Back</span></a>