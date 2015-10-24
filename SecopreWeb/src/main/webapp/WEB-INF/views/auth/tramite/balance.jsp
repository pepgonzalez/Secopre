<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
		
<div class="row" style="display: inline;">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
				<table class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th>Distrito</th>
					<th>Partida</th>
					<th>Mes</th>
					<th>Monto Anual Distrito</th>
					<th>Monto Mensual Asignado</th>
					<th>Monto Actual</th>
					<th>Monto Comprometido</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${entryDistrictBalance}" var="item">
						<tr class="odd gradeX">

							<td>DTO-${item.district.number}</td>
							<td>${item.entry.description}</td>
							<td>${item.monthString}</td>
							<td>${item.annualAmount}</td>
							<td>${item.budgetAmount}</td>
							<td>${item.budgetAmountAssign}</td>
							<td>${item.committedAmount}</td>
						</tr>							
					</c:forEach>
				</tbody>
				</table>
		<!-- END SAMPLE FORM PORTLET-->
	</div>
</div>
