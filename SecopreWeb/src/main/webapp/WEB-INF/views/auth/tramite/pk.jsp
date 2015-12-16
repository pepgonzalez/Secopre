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
						<td><a href="#">Año:</a></td>
						<td><a href="#">${pk.year}</a></td>
					</tr>
					<tr>
						<td><a href="#">Código:</a></td>
						<td><a href="#">${pk.code}</a></td>
					</tr>
					<tr>
						<td><a href="#">Finalidad:</a></td>
						<td><a href="#">${pk.finality}</a></td>
					</tr>
					<tr>
						<td><a href="#">Sub-función:</a></td>
						<td><a href="#">${pk.subfunction}</a></td>
					</tr>
					<tr>
						<td><a href="#">Actividad:</a></td>
						<td><a href="#">${pk.activity}</a></td>
					</tr>
					<tr>
						<td><a href="#">Pres. Programa:</a></td>
						<td><a href="#">${pk.programBudget}</a></td>
					</tr>
					<tr>
						<td><a href="#">Responsable:</a></td>
						<td><a href="#">${pk.unitResponsable}</a></td>
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
