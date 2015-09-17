<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<div class="form-group form-md-line-input">
	<label class="col-md-2 control-label" for="movementName">Nombre del movimiento</label>
	<div class="col-md-10">
		<form:input path="movementName" type="text" id="movementName" class="form-control" readonly="${requestForm.authorizationForm}" />
		<div class="form-control-focus">
		</div>
		<span class="help-block">
			<spring:message code="application.pages.tramite.add.notEmpty"/>
		</span>
	</div>
</div>

<div class="form-group form-md-line-input">
	<label class="col-md-2 control-label" for="movementPrice">Precio del movimiento</label>
	<div class="col-md-10">
		<form:input path="movementPrice" type="text" id="movementPrice" class="form-control" readonly="${requestForm.authorizationForm}"/>
		<div class="form-control-focus">
		</div>
		<span class="help-block">
			<spring:message code="application.pages.tramite.add.notEmpty"/>
		</span>
	</div>
</div>