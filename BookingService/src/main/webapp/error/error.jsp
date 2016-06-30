<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="error">
	<p>AN ERROR OCCUR IN LAST PAGE, PLEASE TRY OR START AGAIN.</p>

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

</div>