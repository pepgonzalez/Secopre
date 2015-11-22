<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
		
<div class="row" style="display: inline;">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
				<table class="table table-striped table-bordered table-hover" id="formalityList">
				<thead>
				<tr>
					<th>Fecha de inicio de captura</th>
					<th>Fecha final de captura</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${dueDates}" var="item">
						<tr class="odd gradeX">
							<td>${item.dueDateStr}</td>
							<td>${item.maxBlockDateStr}</td>
						</tr>							
					</c:forEach>
				</tbody>
				</table>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
