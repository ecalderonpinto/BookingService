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
	<li><i class="icon-home"></i> <a href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/user.do'/>">User</a><i class="icon-angle-right"></i></li>
	<li><a href="#">User Change Password</a></li>
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

<form:form method="POST" commandName="userchangepass" name="userForm">
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon align-justify"></i> <span class="break">User:
						${userchangepass.user.username}</span>
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
							<td><label class="control-label">Old password<b>(*)</b></label></td>
							<td><form:input type="password" path="oldPass"
									cssClass="input-xlarge" maxlength="255" id="oldpass"
									required="required" /></td>
						</tr>
						<tr>
							<td><label class="control-label">New password<b>(*)</b></label></td>
							<td><form:input type="password" path="newPass"
									cssClass="input-xlarge" maxlength="255" id="password1"
									required="required" /></td>
						</tr>
						<tr>
							<td><label class="control-label">New password repeat<b>(*)</b></label></td>
							<td><form:input type="password" path="newPass2"
									cssClass="input-xlarge" maxlength="255" id="password2"
									required="required" /></td>
						</tr>
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
						<button type="submit"
							onclick="CheckPassword(document.getElementById('password1').value ,document.getElementById('password2').value)"
							class="btn btn-danger">Save changes</button>
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