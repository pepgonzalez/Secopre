<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
	
<div class="row">
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat blue">
            <div class="visual">
                <i class="fa fa-comments"></i>
            </div>
            <div class="details">
                <div class="number">
                    <span data-counter="counterup" data-value='<c:out value="${balance.annualAmount}"/>'><c:out value="${balance.annualAmount}"/></span> $
                </div>
                <div class="desc">Presupuesto Anual</div>
            </div>
            <a class="more" href="javascript:;">
                <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat red">
            <div class="visual">
                <i class="fa fa-bar-chart-o"></i>
            </div>
            <div class="details">
                <div class="number">
                    <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsing}"/>"><c:out value="${balance.budgetAsing}"/></span> $
                	<div class="desc">Presupuesto Real</div>
                </div>
            </div>
            <a class="more" href="javascript:;">
                <i class="m-icon-swapright m-icon-white"></i>
            </a>

        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat green">
            <div class="visual">
                <i class="fa fa-shopping-cart"></i>
            </div>
            <div class="details">
                <div class="number">
                    <span data-counter="counterup" data-value="<c:out value="${balance.budgetCommit}"/>"><c:out value="${balance.budgetCommit}"/></span> $
                </div>
                <div class="desc">Total Comprometido</div>
            </div>
            <a class="more" href="javascript:;">
                <i class="m-icon-swapright m-icon-white"></i>
            </a>

        </div>
    </div>
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
        <div class="dashboard-stat purple">
            <div class="visual">
                <i class="fa fa-globe"></i>
            </div>
            <div class="details">
                <div class="number"> 
                    <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsing - balance.budgetCommit}"/>"><c:out value="${balance.budgetAsing - balance.budgetCommit}"/></span> $
                	<div class="desc"> Disponible</div>
                </div>
            </div>
            <a class="more" href="javascript:;">
                <i class="m-icon-swapright m-icon-white"></i>
            </a>
        </div>
    </div>
</div>
		
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
