	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Presupuesto Anual de Partidas por distrito</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-3">
										<div class="btn-group">
											<button id="sample_editable_1_new" class="btn green" onclick="sendRequestJQ('auth/report/params/17','dashboard','initReportParamCapture()','GET');">
												Obtener plantilla de Carga
											</button>
										</div>
									</div>
									<div class="col-md-3">
										<div class="btn-group">
											<button id="sample_editable_1_new" class="btn green" onclick="alert('Carga de archivos');">
												Cargar Presupuesto anual
											</button>
										</div>
									</div>
									<div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="application.tools"/><i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li>
													<a href="javascript:;"><spring:message code="application.print"/></a>
												</li>
												<li>
													<a href="javascript:;"><spring:message code="application.export.pdf"/></a>
												</li>
												<li>
													<a href="javascript:;"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="row" style="margin-top:10px;">
									<div class="col-md-12">
										<div class="btn-group">
											<input type="text" id="entrySearch" placeholder="Buscar por partida..." class="form-control input-medium">
										</div>
									</div>
								</div>
							</div>
						<div>	
					
							<table class="table table-striped table-bordered table-hover" id="entryDistrictList">
							<thead>
							<tr>
								<th>Distrito</th>
								<th>Clave Programatica</th>
								<th>Año</th>
								<th>Partida</th>
								<th>Mes</th>
								<th>Presupuesto Anual</th>
								<th>Presupuesto Mensual</th>
								<th>Monto Actual</th>
								<th>Monto Comprometido</th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${entries}" var="e">
									<tr class="odd gradeX">
	
										<td>${e.district.number}</td>
										<td>${e.programmaticKey.code}</td>
										<td>${e.programmaticKey.year}</td>
										<td>${e.entry.description}</td>
										<td>${e.monthString}</td>
										<td>${e.annualAmount}</td>
										<td>${e.budgetAmount}</td>
										<td>${e.budgetAmountAssign}</td>
										<td>${e.committedAmount}</td>
									</tr>							
								</c:forEach>
							</tbody>
							</table>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
