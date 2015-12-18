<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
		
<div class="tooltip-popup" style="display:block;">	
	<div class="qtip-titlebar">
      		<div id="qtip-{id}-title" class="qtip-title">Detalle de Clave Programatica</div>
  		</div>
   	<div id="qtip-{id}-content" class="qtip-content ui-widget-content" aria-atomic="true" style="background-color:white">

		<table class="popupContainer">
			<tbody>
				<c:if test="${existeKey == true}">
					<tr> 
						<td><a href="#">Clave:</a></td>
						<td><a href="#">${pk.fullKey}</a></td>
						<td><a href="#">Año:</a></td>
						<td><a href="#">${pk.year}</a></td>
					</tr>
					<tr> 
						<td><a href="#">Ramo:</a></td>
						<td><a href="#">${pk.ramo}</a></td>
						<td><a href="#">Unidad Responsable:</a></td>
						<td><a href="#">${pk.unitResponsable}</a></td>
					</tr>
					<tr>
						<td><a href="#">Grupo funcional:</a></td>
						<td><a href="#">${pk.functionalGroup}</a></td>
						<td><a href="#">Función:</a></td>
						<td><a href="#">${pk.function}</a></td>
					</tr>
					<tr>
						<td><a href="#">Sub-función:</a></td>
						<td><a href="#">${pk.subfunction}</a></td>
						<td><a href="#">Programa:</a></td>
						<td><a href="#">${pk.program}</a></td>
					</tr>
					<tr>
						<td><a href="#">Actividad Institucional:</a></td>
						<td><a href="#">${pk.activity}</a></td>
						<td><a href="#">Programa Presupuestario:</a></td>
						<td><a href="#">${pk.programBudget}</a></td>
					</tr>
					<tr>
						<td><a href="#">Tipo de Gasto:</a></td>
						<td><a href="#">${pk.expenseType}</a></td>
						<td><a href="#">Fuente de Financiamiento:</a></td>
						<td><a href="#">${pk.financingSource}</a></td>
					</tr>
					
				</c:if>
				
				<c:if test="${existeKey == false}">
					<tr>
						<td colspan="2"><a href="#">Seleccione una clave...</a></td>
					</tr>
				</c:if>
				
			</tbody>
		</table>
	</div>
</div>
