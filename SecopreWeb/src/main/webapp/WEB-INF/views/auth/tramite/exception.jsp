<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

<c:if test="${not empty existErrors && existErrors == 1}">
	<div class="col-md-12">
		<div class="portlet-body">							
			<c:forEach items="${errors}" var="error">
				<div class="alert alert-danger">${error}</div>						
			</c:forEach>
		</div>	
	</div>
</c:if>