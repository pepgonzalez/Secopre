<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- grid de movimientos de disminucion -->
<div class="portlet box green" id="substractComponent">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>Reducción Automática
		</div>
		<div class="actions">
		</div>
	</div>
	
	<div class="col-md-12">
		<div class="alert alert-warning">** Los montos son relativos a la fecha de creación del trámite. Al autorizar el movimiento los montos se rectifican en función de los movimientos que se hayan autorizado previo a éste movimiento.</div>						
	</div>
	
	<div class="portlet-body" style="max-height:350px!Important;overflow:scroll;">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Clave Programatica</th>
						<th>Partida</th>
						<th>Monto Mensual</th>
						<th>Monto Total</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:choose>
					    <c:when test="${empty requestForm.downMovements}">
					       <tr id="noMovs">
					       		<td colspan="6">No hay Registros Capturados</td>
					       <tr>
					    </c:when>
					    <c:otherwise>
					        <c:forEach items="${requestForm.downMovements}" var="mov" varStatus="i">
								<tr data-name="rowContainer" id="row${i.index}" data-rowNumber="${i.index}">
									
									<td data-name="deleteAction" class="buttonColumn">
  										<a href="javascript:;" class="btn default btn-xs red movementComponent" id="rmvIdx${i.index}"><i class="fa fa-times"></i></a>
  										 <a href="javascript:;" class="btn btn-xs green lastButton movementComponent editButton" id="editIdx${i.index}"><i class="fa fa-edit"></i></a>
  										<c:if test="${mov.isSaved == false}">
											<a href="javascript:;" class="btn grey-cascade btn-xs default movementComponent" id="infoIdx${i.index}"><i class="fa fa-info-circle"></i></a>
										</c:if>
									</td>
									
									<td data-name="programaticKey">
										<form:select path="downMovements[${i.index}].programaticKeyId" class="form-control pk movementComponent" value="${mov.programaticKeyId}">
											<form:option value="-1" label="Seleccione..."/>
						    				<form:options items="${programaticKeys}" />
										</form:select>
									</td>
									<td data-name="entry">
										<form:select path="downMovements[${i.index}].entryId" class="form-control entry input-large movementComponent" value="${mov.entryId}">
											<form:option value="-1" label="Seleccione..."/>
											<form:options items="${entries}" />
										</form:select>
									</td>
									<td data-name="monthAmount">
										<span>$</span><form:input path="downMovements[${i.index}].monthAmount" class="form-control input-small numbersOnly monthAmount movementComponent" style="display:inline-block!Important;"/>
									</td>
									
									<td data-name="totalAmount">
										<span>$</span><form:input path="downMovements[${i.index}].totalAmount" class="form-control input-small numbersOnly movementComponent" style="display:inline-block!Important;"/>
									</td>
					
									<form:hidden path="downMovements[${i.index}].initialMonthId" class="form-control" data-name="initialMonthId"/>
									<form:hidden path="downMovements[${i.index}].finalMonthId" class="form-control" data-name="finalMonthId"/>
									<form:hidden path="downMovements[${i.index}].removedElement" class="form-control" data-name="removedElement"/>
									<form:hidden path="downMovements[${i.index}].movementTypeId" class="form-control" data-name="movementTypeId"/>
									<form:hidden path="downMovements[${i.index}].requestDetailId" class="form-control" data-name="requestDetailId"/>
									<form:hidden path="downMovements[${i.index}].isSaved" class="form-control" data-name="isSaved"/>
									
								</tr>
							</c:forEach>
					    </c:otherwise>
					</c:choose> 
				</tbody>
			</table>
		</div>
	</div>
</div>

<div id="currentTotals"></div>

<!-- row template para agregar registros -->
<template id="movementRowTemplate">
	<tr data-name="rowContainer">
	
		<td data-name="deleteAction" class="buttonColumn">
  			<a href="javascript:;" class="btn default btn-xs red movementComponent" id="rowDeleteButton"><i class="fa fa-times"></i></a>
  			<a href="javascript:;" class="btn grey-cascade btn-xs default movementComponent" id="rowInfoButton"><i class="fa fa-info-circle"></i></a>
		</td>
		
		<td data-name="programaticKey">
			<form:select path="upMovements" class="form-control pk movementComponent">
				<form:option value="-1" label="Seleccione..."/>
	  			<form:options items="${programaticKeys}" />
			</form:select>
		</td>
		<td data-name="entry">
			<form:select path="upMovements" class="form-control entry input-large movementComponent">
				<form:option value="-1" label="Seleccione..."/>
				<form:options items="${entries}" />
			</form:select>
		</td>
		
		<td data-name="monthAmount">
			<span>$</span><form:input path="upMovements" class="form-control input-small numbersOnly monthAmount movementComponent" style="display:inline-block!Important;"/>
		</td>
		
		<td data-name="totalAmount">
			<span>$</span><form:input path="upMovements" class="form-control input-small numbersOnly movementComponent" style="display:inline-block!Important;" readonly="true"/>
		</td>
		<form:hidden path="upMovements" class="form-control" data-name="initialMonthId"/>
		<form:hidden path="upMovements" class="form-control" data-name="finalMonthId"/>
		<form:hidden path="upMovements" class="form-control" data-name="removedElement"/>
		<form:hidden path="upMovements" class="form-control" data-name="movementTypeId"/>
		<form:hidden path="upMovements" class="form-control" data-name="requestDetailId"/>
	</tr>
</template>

<c:if test="${executeInnerJs == 1}">
	<script type="text/javascript">
		$(document).ready(function(){
			expenseCapture();
		});
	</script>
</c:if>