<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- captura de clave de partida -->
<div class="form-group form-md-line-input">
	<label class="col-md-4 control-label" for="formalityId">Codigo de Partida:</label>
	<div class="col-md-8">
			<form:input path="entry.code" id="entryCode" class="form-control"></form:input>
			<div class="form-control-focus"></div>
			<span class="help-block">
				<spring:message code="application.pages.tramite.add.selectFormality"/>
			</span>
	</div>
	<label class="col-md-4 control-label" for="formalityId">Nombre de Partida:</label>
	<div class="col-md-8">
			<form:input path="entry.description" id="entryDescription" class="form-control"></form:input>
			<div class="form-control-focus"></div>
			<span class="help-block">
				<spring:message code="application.pages.tramite.add.selectFormality"/>
			</span>
	</div>
</div>



<c:if test="${executeInnerJs == 1}">
	<script type="text/javascript">
		$(document).ready(function(){
			entriesCapture();
		});
	</script>
</c:if>