<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- start: Breadcrumb -->
<ul class="breadcrumb">
	<li><i class="icon-home"></i> <a
		href="<c:url value='/welcome.do'/>">Home</a> <i
		class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/dataManager.do'/>">Data Manager</a><i
		class="icon-angle-right"></i>
	<li><a href="<c:url value='/companyDetail.do?id=${company.id}'/>">Company
			detail</a> <i class="icon-angle-right"></i></li>
	<li><a href="<c:url value='/companyReports.do?id=${company.id}'/>">Reports
			(${company.companyName})</a><i class="icon-angle-right"></i></li>
	<li><a href="#">${reportexecution.reportExecutionName }</a></li>
</ul>
<!-- end: Breadcrumb -->

<!-- Download Help -->
<!-- <div class="box-content">
	<p>
		<a href="<c:url value='/downloadAIFMHelp.do'/>"><i
			class="glyphicons-icon circle_question_mark" title="Download Help"></i></a>
	</p>
</div> -->

<!-- errors link -->
<div class="box-content">
	<c:choose>
		<c:when test="${reportexecution.hasErrors == true}">
			<a class="btn btn-small btn-danger"
				href="<c:url value="reportError.do?id=${reportexecution.id}" />">
				Errors </a>
		</c:when>
		<c:otherwise>
			<span class="label label-success">No errors</span>
		</c:otherwise>
	</c:choose>
</div>

<form:form method="POST" commandName="reportexecution">

	<c:forEach var="section" varStatus="status" items="${sections}">

		<div class="row-fluid">
			<div class="box span12">
				<div class="box-header" data-original-title>
					<h2>
						<i class="halflings-icon edit"></i> <span class="break"></span> <a
							href="#" class="btn-minimize">${section} Test</a>
					</h2>
					<div class="box-icon">
						<c:choose>
							<c:when
								test="${reportexecution.reportCatalog.reportLevel == 'COMPANY'}">
								<a href="<c:url value='/downloadAIFMDHelp.do?id=AIFM'/>"> <i
									class="halflings-icon question-sign" title="Download Help"></i></a>
								<a href="#" class="btn-minimize"><i
									class="halflings-icon chevron-down"></i></a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value='/downloadAIFMDHelp.do?id=AIF'/>"> <i
									class="halflings-icon question-sign" title="Download Help"></i></a>
								<a href="#" class="btn-minimize"><i
									class="halflings-icon chevron-down"></i></a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				
				<div class="box-content" style="display: none;overflow: hidden;">
					<fieldset>
						
						<c:set var="grupo" scope="session" value="o"/>
												
						<c:forEach var="reportData" varStatus="status" items="${reportexecution.reportDatas}">
															
							<c:if test="${reportData.reportField.reportFieldSection == section}">
								
								<c:if test="${not empty reportData.reportDataBlock}">
									<c:choose>
										<c:when	test="${grupo == 'o'}">
											<c:set var="grupo" value="${reportData.reportDataBlock}"/>
										</c:when>
										<c:when	test="${reportData.reportDataBlock == grupo}">
										</c:when>
										<c:otherwise>
											<hr width=90% align="center">
		       								<c:set var="grupo" value="${reportData.reportDataBlock}"/>
		    							</c:otherwise>
									</c:choose> 
								</c:if>
																
								<!-- Not editable Fields -->
								<c:choose>
									<c:when
										test="${reportData.reportField.reportFieldEditable == true}">
										<div class="control-group" style="float:left;margin:0 10px">
											<label class="control-label">
												${reportData.reportField.reportFieldNum}.
												${reportData.reportField.reportFieldName}</label>
											<div class="controls">
												<span class="input-xlarge uneditable-input">
													${reportData.reportDataText}</span>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${reportData.hasErrors == true}">
												<!-- Field with Error -->
												<div class="control-group error" style="float:left;margin:0 10px">
													<label class="control-label" for="inputError">
														${reportData.reportField.reportFieldNum}.
														${reportData.reportField.reportFieldName} 
														<c:if test="${reportData.reportDataBlock != null}"> 
														- [${reportData.reportDataBlock}] </c:if>
														<c:if test="${reportData.reportField.reportFieldMandatory == true}">
															<div class="mandatory-mark">(*)</div>
														</c:if>
													</label>
													<div class="controls">
														<!-- Search if is dropdown and set it -->
														<c:set var="contains" value="false" />
														<c:set var="dropdown" value="" />
														<c:forEach var="item" items="${fieldlist}">
															<c:if
																test="${item eq reportData.reportField.reportFieldMask}">
																<c:set var="contains" value="true" />
																<c:set var="dropdown" value="${item}" />
															</c:if>
														</c:forEach>
														<c:choose>
															<c:when test="${contains}">
																<!-- Dropdown -->
																<%-- <form:select
																	path="reportDatas[${status.index}].reportDataText"
																	items="${fn:split(fieldlistmap[dropdownfinal],',' ) }" /> --%>
																<form:select
																	path="reportDatas[${status.index}].reportDataText">
																	<form:option value="--SELECT--">--SELECT--</form:option>
																	<c:forEach
																		items="${fn:split(fieldlistmap[dropdown],',' ) }"
																		var="value">
																		<form:option value="${value}"></form:option>
																	</c:forEach>
																</form:select>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when
																		test="${reportData.reportField.reportFieldMask == 'DATE'}">
																		<!-- Date -->
																		<form:input
																			path="reportDatas[${status.index}].reportDataText"
																			cssClass="input-xlarge datepicker" maxlength="300" />
																	</c:when>
																	<c:otherwise>
																		<!-- Text -->
																		<form:input
																			path="reportDatas[${status.index}].reportDataText"
																			cssClass="input-xlarge" maxlength="300" />
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
														<span class="help-inline"> <c:forEach
																var="reportError" varStatus="status"
																items="${reportData.reportDataErrors}">
														${reportError.reportDataErrorText}
													</c:forEach>
														</span>
													</div>
												</div>
												<!-- /Field with Error -->
											</c:when>
											<c:otherwise>
												<!-- Normal Field -->
												<div class="control-group" style="float:left;margin:0 10px"">
				<label class="control-label" for="disabledInput"> <span
														title="${reportData.reportField.reportFieldName}">${reportData.reportField.reportFieldNum}.
															${reportData.reportField.reportFieldName}</span> <c:if
															test="${reportData.reportDataBlock != null}"> 
														- [${reportData.reportDataBlock}] </c:if> <!--  field mandatory -->
														<c:if
															test="${reportData.reportField.reportFieldMandatory == true}">
															<c:choose>
																<c:when test="${empty reportData.reportDataText}">
																	<div class="mandatory-red">(*)</div>
																</c:when>
																<c:otherwise>
																	<div class="mandatory-mark">(*)</div>
																</c:otherwise>
															</c:choose>
														</c:if> <!--  /field mandatory -->
													</label>
													<div class="controls">
														<!-- Search if is dropdown and set it -->
														<c:set var="contains" value="false" />
														<c:set var="dropdown" value="" />
														<c:forEach var="item" items="${fieldlist}">
															<c:if
																test="${item eq reportData.reportField.reportFieldMask}">
																<c:set var="contains" value="true" />
																<c:set var="dropdown" value="${item}" />
															</c:if>
														</c:forEach>
														<c:choose>
															<c:when test="${contains}">
																<!-- Dropdown -->
																<%-- <form:select
																	path="reportDatas[${status.index}].reportDataText"
																	items="${fn:split(fieldlistmap[dropdown],',' ) }"/> --%>
																<form:select
																	path="reportDatas[${status.index}].reportDataText">
																	<form:option value="--SELECT--">--SELECT--</form:option>
																	<c:forEach
																		items="${fn:split(fieldlistmap[dropdown],',' ) }"
																		var="value">
																		<form:option value="${value}"></form:option>
																	</c:forEach>
																</form:select>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when
																		test="${reportData.reportField.reportFieldMask == 'DATE'}">
																		<!-- Date -->
																		<form:input
																			path="reportDatas[${status.index}].reportDataText"
																			cssClass="input-xlarge datepicker" maxlength="300" />
																	</c:when>
																	<c:otherwise>
																		<!-- Text -->
																		<form:input
																			path="reportDatas[${status.index}].reportDataText"
																			cssClass="input-xlarge" maxlength="300" />
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
												<!-- /Normal Field -->
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<c:if test="${empty reportData.reportDataBlock}">
									<hr width=90% align="center" >
								</c:if>
							</c:if>
						</c:forEach>
					</fieldset>
				</div>
			</div>
			<!--/span-->
		</div>
	</c:forEach>

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

<a href="<c:url value='/companyReports.do?id=${company.id}'/>"><span
	class="btn btn-important">Back</span></a>

<!-- new part with blocks -->

<%-- <c:forEach var="reportsection" varStatus="status"
		items="${reportsections}">

		<div class="row-fluid">
			<div class="box span12">
				<div class="box-header" data-original-title>
					<h2>
						<i class="halflings-icon edit"></i> <span class="break"></span>
						${reportsection.sectionName}
					</h2>
					<div class="box-icon">
						<a href="#" class="btn-minimize"><i
							class="halflings-icon chevron-down"></i></a>
					</div>
				</div>
				<div class="box-content" style="display: none;">
					<fieldset>
						<c:forEach var="reportData" varStatus="status"
							items="${reportexecution.reportDatas}">
							<c:if
								test="${reportData.reportField.reportFieldSection == reportsection.sectionName }">
								<c:choose>
									<c:when test="${reportsection.hasBlock == true}">
										<!-- Block Section -->
										<c:forEach var="reportDataWithBlock" varStatus="statusBlock"
											items="${reportexecution.reportDatas}">

											<c:if
												test="${reportData.reportField.reportFieldSection == reportDataWithBlock.reportField.reportFieldSection 
											&& reportData.reportDataBlock eq reportDataWithBlock.reportDataBlock}">


												<div class="control-group">
													<label class="control-label" for="disabledInput">
														${reportDataWithBlock.reportField.reportFieldNum}.
														${reportDataWithBlock.reportField.reportFieldName} - 
														${reportDataWithBlock.reportDataText} </label>
													<div class="controls">
														<form:input
															path="reportData[${status.index}].reportDataText"
															cssClass="input-xlarge" /> 
													</div>
												</div>
											</c:if>
										</c:forEach>

										<!-- /Block Section -->


									</c:when>
									<c:otherwise>
										<!-- NO Block Section -->
										<c:choose>
											<c:when test="${reportData.hasErrors == true}">
												<!-- Field with Error -->
												<div class="control-group error">
													<label class="control-label" for="inputError">
														${reportData.reportField.reportFieldNum}.
														${reportData.reportField.reportFieldName } </label>
													<div class="controls">
														<form:input
															path="reportDatas[${status.index}].reportDataText"
															cssClass="input-xlarge" />
														<span class="help-inline"> <c:forEach
																var="reportError" varStatus="status"
																items="${reportData.reportDataErrors}">
														${reportError.reportDataErrorText}
													</c:forEach>
														</span>
													</div>
												</div>
												<!-- /Field with Error -->
											</c:when>
											<c:otherwise>
												<!-- Normal Field -->
												<div class="control-group">
													<label class="control-label" for="disabledInput">
														${reportData.reportField.reportFieldNum}.
														${reportData.reportField.reportFieldName } </label>
													<div class="controls">
														<form:input
															path="reportDatas[${status.index}].reportDataText"
															cssClass="input-xlarge" />
													</div>
												</div>
												<!-- /Normal Field -->
											</c:otherwise>
										</c:choose>
										<!-- /NO Block Section -->
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</fieldset>
				</div>
			</div>
			<!--/span-->
		</div>
	</c:forEach>
 --%>
