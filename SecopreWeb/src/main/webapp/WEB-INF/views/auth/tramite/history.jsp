<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
		
<div class="row" style="display: inline;">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
				<table class="table table-striped table-bordered table-hover" id="formalityList">
				<thead>
				<tr>
					<th>Consecutivo</th>
					<th>Etapa Inicial</th>
					<th>Siguiente Etapa</th>
					<th>Estatus</th>
					<th>Fecha Creación</th>
					<th>Usuario</th>
					<th>Comentarios</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestForm.requestHistory}" var="item">
						<tr class="odd gradeX">

							<td>${item.consecutive}</td>
							<td>${item.initialStage}</td>
							<td>${item.finalStage}</td>
							<td>${item.status}</td>
							<td>${item.creationDate}</td>
							<td>${item.username}</td>
							<td>${item.comments}</td>
						</tr>							
					</c:forEach>
				</tbody>
				</table>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
