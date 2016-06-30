<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function CheckPassword(pass1, pass2) {
		if (pass1 != null && pass1 != '' || pass2 != null && pass2 != '') {
			if (pass1 == pass2) {
				document.getElementById("userForm").submit();
				//return true;
			} else {
				alert("Passwords do not match.");
				//return false;
			}
		} else {
			alert("Please insert a password.");
		}
	}
	<!--
//-->
</script>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a>
		<i class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/admin/userManager.do'/>">User
			Manager</a><i class="icon-angle-right"></i></li>
	<li><a href="#">User Manager Detail</a></li>
</ul>
<!-- end: Breadcrumb -->

<form:form method="POST" commandName="userdetailmanager" id="userForm">
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
							<c:choose>
								<c:when test="${userdetailmanager.isNew}">
									<td>User Name <b>(*)</b></td>
									<td><form:input path="user.username"
											cssClass="input-xlarge" required="required" maxlength="255" /></td>
								</c:when>
								<c:otherwise>
									<td><label class="control-label">User Name</label></td>
									<td><div class="controls">
											<span class="input-xlarge uneditable-input">${userdetailmanager.user.username}</span>
										</div></td>
								</c:otherwise>
							</c:choose>
							<td>User Mail</td>
							<td><form:input path="user.userMail" cssClass="input-xlarge"
									maxlength="255" /></td>
						</tr>
						<tr>
							<td>First Name <b>(*)</b></td>
							<td><form:input path="user.firstName"
									cssClass="input-xlarge" required="required" maxlength="255" /></td>
							<td>Last Name <b>(*)</b></td>
							<td><form:input path="user.lastName" cssClass="input-xlarge"
									required="required" maxlength="255" /></td>
						</tr>
						<tr>
							<td>User Enabled <b>(*)</b></td>
							<td><form:select path="user.enabled" required="required">
									<%-- <form:options items="${userEnabledDrop}" /> --%>
									<c:forEach var="enabled" items="${userEnabledDrop}">
										<c:set var="varSelected" value="" />
										<c:if test="${enabled == userdetailmanager.user.enabled}">
											<c:set var="varSelected" value="selected" />
										</c:if>
										<form:option value="${enabled}" selected="${varSelected}">${enabled}</form:option>
									</c:forEach>
								</form:select></td>
							<td>Company</td>
							<td><form:select path="selectCompany">
									<%-- <form:options items="${userCompany}" /> --%>
									<form:option value="--SELECT--">--SELECT--</form:option>
									<c:forEach var="company" items="${userCompany}">
										<c:set var="varSelected" value="" />
										<c:if
											test="${company.key == userdetailmanager.user.company.id}">
											<c:set var="varSelected" value="selected" />
										</c:if>
										<form:option value="${company.key}" selected="${varSelected}">${company.value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
						<%-- <tr>
							<td>User Role <b>(*)</b></td>
							<td colspan="3"><form:select path="selectUserRol"
									required="required">
									<form:options items="${userRolDrop}" />
								</form:select></td>
						</tr> --%>

						<c:if test="${userdetailmanager.isNew}">
							<tr>
								<td>User Password <b>(*)</b></td>
								<td colspan="3"><form:input type="password"
										path="user.password" cssClass="input-xlarge"
										required="required" maxlength="255" id="password1" /></td>
							</tr>
							<tr>
								<td>Confirm Password <b>(*)</b></td>
								<td colspan="3"><input type="password"
										required="required" maxlength="255" id="password2" /></td>
							</tr>
						</c:if>

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
						<c:choose>
							<c:when test="${userdetailmanager.isNew}">
								<button class="btn btn-danger"
									onclick="CheckPassword(document.getElementById('password1').value ,document.getElementById('password2').value)">Create
									user</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-danger">Save user</button>
							</c:otherwise>
						</c:choose>
					</div>
				</fieldset>
			</div>
		</div>
		<!--/span-->
	</div>
	<!-- /FormActions -->

</form:form>

<a href="<c:url value='/admin/userManager.do'/>"><span
	class="btn btn-important">Back</span></a>