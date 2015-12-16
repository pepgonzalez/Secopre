	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Listado de Reportes</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-12">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="application.tools"/><i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li>
													<a href="javascript:;"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="reportList">
							<thead>
							<tr>
								<th>ID</th>
								<th>Nombre Reporte</th>
								<th>Origen del reporte</th>
								<th>Tipo Archivo</th>
								<th>Acciones</th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${reportList}" var="reportItem">
									<tr class="odd gradeX">
	
										<td>${reportItem.reportId}</td>
										<td>${reportItem.description}</td>
										<td>${reportItem.reportSourceDescription}
										<td>${reportItem.reportType}</td>
										<td>	
											<c:if test="${reportItem.hasReportParameters == false}">
												<a href="#" onclick="openResourceNative('report/download/${reportItem.reportId}','dashboard','()','GET');">
													<span class="label label-sm label-success"> Ver Reporte </span>
												</a>
											</c:if>
											
											<c:if test="${reportItem.hasReportParameters == true}">
												<a href="#" onclick="sendRequestJQ('auth/report/params/${reportItem.reportId}','dashboard','initReportParamCapture()','GET');">
													<span class="label label-sm label-success"> Ver Reporte </span>
												</a>
											</c:if>
											
										</td>
									</tr>							
								</c:forEach>
							</tbody>
							</table>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
