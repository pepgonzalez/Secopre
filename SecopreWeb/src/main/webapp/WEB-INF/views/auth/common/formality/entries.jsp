<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- captura de clave de partida -->
<div class="form-group form-md-line-input">
	
	<label class="col-md-4 control-label" for="accountingType">Tipo de Partida:</label>
	<div class="col-md-8">
			<form:select path="entry.accountingType" id="accountingType" class="form-control input-small movementComponent pk" value="${mov.entry.accountingType}">
			<form:option value="-1" label="Seleccione..."/>
  				<form:options items="${accountingTypes}" />
		</form:select>
	</div>
	
	<label class="col-md-4 control-label" for="entryCode">Codigo de Partida:</label>
	<div class="col-md-8">
			<form:input path="entry.code" id="entryCode" class="form-control"></form:input>
			<div class="form-control-focus"></div>
			<span class="help-block">
				<spring:message code="application.pages.tramite.add.selectFormality"/>
			</span>
	</div>
	<label class="col-md-4 control-label" for="entryDescription">Nombre de Partida:</label>
	<div class="col-md-8">
			<form:input path="entry.description" id="entryDescription" class="form-control"></form:input>
			<div class="form-control-focus"></div>
			<span class="help-block">
				<spring:message code="application.pages.tramite.add.selectFormality"/>
			</span>
	</div>

	<label class="col-md-4 control-label" for="pk">Clave Programatica:</label>	
	<div class="col-md-8">
		<form:select path="entry.programmaticKey.id" id="pk" class="form-control input-small movementComponent pk" value="${mov.programaticKeyId}">
			<form:option value="-1" label="Seleccione..."/>
  				<form:options items="${programaticKeysFull}" />
		</form:select>		
	</div>
	
	<div id="conceptOptions">
	<label class="col-md-4 control-label" for="concept">Concepto:</label>	
	<div class="col-md-8">
		<form:select path="entry.concept.id" id="concept" class="form-control input-small movementComponent pk" value="${mov.entry.concept.id}">
			<form:option value="-1" label="Seleccione..."/>
  				<form:options items="${concepts}" />
		</form:select>		
	</div>
	</div>
	
</div>


<c:if test="${executeInnerJs == 1}">
	<script type="text/javascript">
		$(document).ready(function(){
			entriesCapture();
		});
	</script>
</c:if>