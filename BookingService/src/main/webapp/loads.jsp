<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a
		href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="#">Loads</a></li>
</ul>
<!-- end: Breadcrumb -->

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>Manual
				Load
			</h2>
			<div class="box-icon">
				<!-- <a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a> -->
				<a href="#" class="btn-minimize"><i
					class="halflings-icon chevron-up"></i></a>
			</div>
		</div>
		<!-- message success / fail from load file -->
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
		<!-- / message success / fail from load file -->
		<div class="box-content">
			<form:form enctype="multipart/form-data" method="post"
				commandName="loadFile">
				<div class="control-group">
					<label class="control-label" for="inputFile">Load a file:</label>
					<div class="controls">
						<form:input path="inputFile" cssClass="input-file uniform_on"
							type="file" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="selectFileConfigs"> File
						Config: </label>
					<div class="controls">
						<form:select path="selectFileConfigs" items="${selectFileConfigs}"></form:select>
					</div>
				</div>
				<input type="submit" class="btn btn-primary" value="Load">
			</form:form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->

<c:if test="${dmoExists==true}">

	<!-- Actions Box  to load DMO -->
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header">
				<h2>
					<i class="halflings-icon filter"></i><span class="break"></span>Actions
				</h2>
				<div class="box-icon">
					<a href="#" class="btn-minimize"><i
						class="halflings-icon chevron-up"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form:form method="post" commandName="generateDMO">
					<div class="control-group">
						<label class="control-label" for="period">Period:</label>
						<div class="controls">
							<form:select path="period">
								<form:option value="1">1</form:option>
								<form:option value="2">2</form:option>
								<form:option value="3">3</form:option>
								<form:option value="4">4</form:option>
								<form:option value="5">5</form:option>
								<form:option value="6">6</form:option>
								<form:option value="7">7</form:option>
								<form:option value="8">8</form:option>
								<form:option value="9">9</form:option>
								<form:option value="10">10</form:option>
								<form:option value="11">11</form:option>
								<form:option value="12">12</form:option>
							</form:select>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="amount">Amount:</label>
						<div class="controls">
							<form:input path="amount" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="monthLess">Months less:</label>
						<div class="controls">
							<form:select path="monthLess">
								<form:option value="0">0</form:option>
								<form:option value="1">1</form:option>
								<form:option value="2">2</form:option>
								<form:option value="3">3</form:option>
								<form:option value="4">4</form:option>
								<form:option value="5">5</form:option>
								<form:option value="6">6</form:option>
								<form:option value="7">7</form:option>
								<form:option value="8">8</form:option>
								<form:option value="9">9</form:option>
								<form:option value="10">10</form:option>
								<form:option value="11">11</form:option>
								<form:option value="12">12</form:option>
							</form:select>
						</div>
					</div>
					
					<input type="submit" class="btn btn-primary" value="Generate DMO files">
				</form:form>
<%-- 				<a href="<c:url value='/dmoLoad.do'/>"><span --%>
<!-- 					class="btn btn-important btn-danger">Generate DMO files</span></a> -->
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->

</c:if>

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header">
			<h2>
				<i class="halflings-icon align-justify"></i><span class="break"></span>Loads
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
				class="table table-striped table-bordered table-condensed datatable">
				<thead>
					<tr>
						<!-- <th></th> -->
						<th>Name<i class="icon-sort"></i></th>
						<th>Config<i class="icon-sort"></i></th>
						<th>Date<i class="icon-sort"></i></th>
						<th>Registers<i class="icon-sort"></i></th>
						<th>Status<i class="icon-sort"></i></th>
						<th>Detail</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="load" items="${loads}">
						<tr>
							<%-- <td><a href="<c:url value='/loadDetail.do?id=${load.id}'/>"> <i
									class="icon-eye-open"></i>
							</a></td> --%>
							<td>${load.loadFileName}</td>
							<td>${load.fileConfig.fileConfigName}</td>
							<td>${load.loadFileDate}</td>
							<td>${fn:length(load.loadRaws)}</td>
							<td><c:choose>
									<c:when test="${fn:length(load.loadErrors) gt 0}">
										<a href="<c:url value="/viewErrors.do?id=${load.id}" />">
											<span class="label label-important">With Errors </span>
										</a>
									</c:when>
									<c:otherwise>
										<span class="label label-success">Loaded</span>
									</c:otherwise>
								</c:choose></td>
							<td><a class="btn btn-small"
								href="<c:url value='/loadDetail.do?id=${load.id}'/>">Detail</a></td>
							<td><a href="<c:url value='/loadDelete.do?id=${load.id}'/>"><i
									class="halflings-icon trash"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<!--/span-->
</div>

<a href="<c:url value='/welcome.do'/>"><span
	class="btn btn-important">Back</span></a>