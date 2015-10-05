<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="portlet box green" id="addComponent">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>Ampliación Líquida
		</div>
		<div class="actions">
			<a href="javascript:;" class="btn green btn-sm" id="addMov"><i class="fa fa-plus"></i>Agregar Movimiento </a>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Llave Programatica</th>
						<th>Partida</th>
						<th>Rango meses</th>
						<th></th>
						<th>Monto Mensual</th>
					</tr>
				</thead>
				<tbody>
				
					<c:choose>
					    <c:when test="${empty requestForm.upMovements}">
					       <tr id="noMovs">
					       		<td colspan="6">No hay Movimientos Capturados</td>
					       <tr>
					    </c:when>
					    <c:otherwise>
					        <c:forEach items="${requestForm.upMovements}" var="mov" varStatus="i">
								<tr data-name="rowContainer" id="row${i.index}">
									<td data-name="deleteAction">
										<a href="javascript:;" class="btn default btn-xs red" id="rmvIdx${i.index}">
											<i class="fa fa-times"></i>
										</a>
									</td>
									<td>
										<form:select path="upMovements[${i.index}].programaticKeyId" class="form-control input-small">
											<form:option value="-1" label="Seleccione..."/>
						    				<form:options items="${programaticKeys}" />
										</form:select>
									</td>
									<td>
										<form:select path="upMovements[${i.index}].entryId" class="form-control input-medium">
											<form:option value="-1" label="Seleccione..."/>
						    				<form:options items="${entries}" />
										</form:select>
									</td>
									<td>
										<div class="input-small" style="padding-top:8px;">
											<div id="sliderControl${i.index}"></div>
										</div>
									</td>
									<td>
										<div class="input-xsmall" style="padding-top:2px;">
											<span id="upMovements${i.index}.lower-offset"></span>-<span id="upMovements${i.index}.upper-offset"></span>
										</div>
									<td>
										<form:input path="upMovements[${i.index}].monthAmount" class="form-control input-small"/>
									</td>
					
									<form:hidden path="upMovements[${i.index}].initialMonthId" class="form-control"/>
									<form:hidden path="upMovements[${i.index}].finalMonthId" class="form-control"/>
									<form:hidden path="upMovements[${i.index}].removedElement" class="form-control"/>
								</tr>
							</c:forEach>
					    </c:otherwise>
					</c:choose> 
				</tbody>
			</table>
		</div>
		<div>
			<div class="text-rigth">
				<div class="btn">Total:</div>
				<a href="#myModal1" role="button" class="btn green" data-toggle="modal" id="upMovementsTotal">0.00</a>
			</div>
		</div>
	</div>
</div>

<div class="portlet box green" id="substractComponent">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>Reducción Líquida
		</div>
		<div class="actions">
			<a href="javascript:;" class="btn green btn-sm"><i class="fa fa-plus"></i>Agregar Movimiento </a>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Llave Programatica</th>
						<th>Partida</th>
						<th>Rango meses</th>
						<th></th>
						<th>Monto Mensual</th>
					</tr>
				</thead>
				<tbody>
					<%--  
					<c:if test="${not empty request.downMovements}">
						<c:forEach items="${request.downMovements}" var="mov" varStatus="i">
							<tr>
								<td>
									<a href="javascript:;" class="btn default btn-xs red"><i class="fa fa-times"></i></a>
								</td>
								<td>
									<form:select path="downMovements[i.index].programaticKeyId" class="form-control input-small">
										<form:option value="-1" label="Seleccione..."/>
					    				<form:options items="${programaticKeys}" />
									</form:select>
								</td>
								<td>
									<form:select path="downMovements[i.index].entryId" class="form-control input-medium">
										<form:option value="-1" label="Seleccione..."/>
					    				<form:options items="${entries}" />
									</form:select>
								</td>
								<td>
									<div class="input-small" style="padding-top:8px;">
										<div id="sliderControl"></div>
									</div>
								</td>
								<td>
									<div class="input-xsmall" style="padding-top:2px;">
										<span id="lower-offset"></span>-<span id="upper-offset"></span>
									</div>
								<td>
									<form:input path="downMovements[i.index].monthAmount" class="form-control input-small"/>
								</td>
				
								<form:hidden path="downMovements[i.index].initialMonthId" class="form-control"/>
								<form:hidden path="downMovements[i.index].finalMonthId" class="form-control"/>
							</tr>	
						</c:forEach>
					</c:if>
					--%>
				</tbody>
			</table>
		</div>
		<div>
			<div class="text-rigth">
				<div class="btn">Total:</div><a href="#myModal1" role="button" class="btn green" data-toggle="modal">$ 0.00</a>
			</div>
		</div>
	</div>
</div>

<template id="movementRowTemplate">
	<tr data-name="rowContainer">
		<td data-name="action">
			<a href="javascript:;" class="btn default btn-xs red">
				<i class="fa fa-times"></i>
			</a>
		</td>
		<td data-name="programaticKey">
			<form:select path="upMovements" class="form-control input-small">
				<form:option value="-1" label="Seleccione..."/>
	  				<form:options items="${programaticKeys}" />
			</form:select>
		</td>
		<td data-name="entry">
			<form:select path="upMovements" class="form-control input-medium">
				<form:option value="-1" label="Seleccione..."/>
	  				<form:options items="${entries}" />
			</form:select>
		</td>
		<td data-name="sliderControl">
			<div class="input-small" style="padding-top:8px;">
				<div id="sliderControl"></div>
			</div>
		</td>
		<td>
			<div class="input-xsmall" style="padding-top:2px;">
				<span data-name="lower-offset"></span>-<span data-name="upper-offset"></span>
			</div>
		<td data-name="monthAmount">
			<form:input path="upMovements" class="form-control input-small"/>
		</td>
		<form:hidden path="upMovements" class="form-control" data-name="initialMonthId"/>
		<form:hidden path="upMovements" class="form-control" data-name="finalMonthId"/>
		<form:hidden path="upMovements" class="form-control" data-name="removedElement"/>
	</tr>
</template>