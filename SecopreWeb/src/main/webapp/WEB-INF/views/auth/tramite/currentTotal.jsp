<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
	
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>Partida</th>
			<th>Enero</th>
			<th>Febrero</th>
			<th>Marzo</th>
			<th>Abril</th>
			<th>Mayo</th>
			<th>Junio</th>
			<th>Julio</th>
			<th>Agosto</th>
			<th>Septiembre</th>
			<th>Octubre</th>
			<th>Noviembre</th>
			<th>Diciembre</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${totals}" var="item">
			<tr class="odd gradeX">

				<td>${item.desc}</td>
				<td>${item.enero}</td>
				<td>${item.febrero}</td>
				<td>${item.marzo}</td>
				<td>${item.abril}</td>
				<td>${item.mayo}</td>
				<td>${item.junio}</td>
				<td>${item.julio}</td>
				<td>${item.agosto}</td>
				<td>${item.septiembre}</td>
				<td>${item.octubre}</td>
				<td>${item.noviembre}</td>
				<td>${item.diciembre}</td>
			</tr>							
		</c:forEach>
	</tbody>
</table>