	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Listado de Trámites en proceso</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<button id="sample_editable_1_new" class="btn green" onclick="sendRequestJQ('auth/tram/add','dashboard','initTramitePage()','GET');">
												<spring:message code="application.add"/> <i class="fa fa-plus"></i>
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
											<input type="text" id="formalityDateSearch" placeholder="Buscar..." class="form-control input-medium">
										</div>
									</div>
								</div>
							</div>
						<div>	
					
							<table class="table table-striped table-bordered table-hover" id="formalityList">
							<thead>
							<tr>
								<th>Solicitud</th>
								<th>Justificación</th>
								<th>Distrito</th>
								<th>Trámite</th>
								<th>Importe</th>
								<th>Fecha Creación</th>
								<th>Siguiente Etapa</th>
								<th></th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${inboxList}" var="inboxItem">
									<tr class="odd gradeX">
	
										<td>${inboxItem.folio}</td>
										<td>${inboxItem.justification}</td>
										<td>${inboxItem.districtDescription}</td>
										<td>${inboxItem.formalityDescription}</td>
										
										<td>${inboxItem.totalAmount}</td>
										<td>${inboxItem.creationDateStr}</td>

										<td>
											<c:if test="${inboxItem.requestFinished == false}">
												<a href="#" onclick="sendRequestJQ('${inboxItem.nextStageURL}','dashboard','${inboxItem.nextStageJSFunction}','GET');">
													${inboxItem.nextDescription}
												</a>
											</c:if>
											<c:if test="${inboxItem.requestFinished == true}">
												${inboxItem.nextDescription}
											</c:if>
										</td>
										<td>
										
											<a href="javascript:;" class="btn btn-xs tooltip-control"><i class="fa fa-folder-open"></i></a>
											
											<!-- tooltip popup -->
											<div class="tooltip-popup">
											 	<div class="qtip-titlebar">
										        	<div id="qtip-{id}-title" class="qtip-title">Opciones</div>
										    	</div>
										    	
										    	<div id="qtip-{id}-content" class="qtip-content ui-widget-content" aria-atomic="true">
       
													<table class="popupContainer">
														<tbody>
															<c:if test="${inboxItem.requestFinished == true}">
																<tr>
																	<td>
																		<a href="#" onclick="showDataHistory('${inboxItem.requestId}')" >
																			Ver historia del Folio
																		</a>
																	</td>
																</tr>
															</c:if>
															<c:if test="${inboxItem.hasDocument}">
																<tr>
																	<td>
																		<a href="#" onclick="openResourceNative('wf/download/${inboxItem.requestId}','dashboard','()','GET');">
																			Ver documento anexo
																		</a>
																	</td>
																</tr>
															</c:if>
														</tbody>
													</table>
												</div>
											</div>
											
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
