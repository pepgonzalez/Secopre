	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Captura de Parámetros - ${reportName}</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- formulario -->	
							<div>
								<form:form cssClass="form-horizontal" method="POST" modelAttribute="reportParametersForm" id="reportParametersForm" 
								           action="auth/report/download/paramReport" novalidate="novalidate">
							
									<div class="form-body">
										<!-- Se incluyen los DIV de alertamiento en formularios -->
										<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>
										
									<form:hidden path="reportId" />
										
									<c:forEach items="${reportParameters}" var="reportParam">
									
										<c:if test="${reportParam.parameterType == 'input'}">
										
											<div class="form-group form-md-line-input">
												<label class="col-md-3 control-label" for="form_control_1">
													${reportParam.label}
													<c:if test="${reportParam.required}">
														<span class="required">*</span>
													</c:if>
												</label>
												<div class="col-md-9">
													<div class="input-icon">
														<form:input path="${reportParam.parameterPath}" class="form-control" 
														data-required="${reportParam.required}" data-parametername="${reportParam.label}"
														data-parameterType="input"/>
														<div class="form-control-focus"></div>
													</div>
												</div>
											</div>
											
										</c:if>
										
										<c:if test="${reportParam.parameterType == 'date'}">
										
											<div class="form-group form-md">
												<label class="col-md-3 control-label" for="form_control_1">
													${reportParam.label}
													<c:if test="${reportParam.required}">
													<span class="required">*</span>
													</c:if>
												</label>
												<div class="col-md-9">
                                                        <div data-date-format="dd/mm/yyyy" class="input-group date date-picker">
														<div class="input-group-addon">
													        <span class="fa fa-calendar"></span>
													    </div>
														<input name="${reportParam.parameterPath}"  
														id="${reportParam.parameterPath}"  type="text" value="${reportParam.parameterPath}" 
														class="form-control" data-required="${reportParam.required}" data-parametername="${reportParam.label}"
														data-parameterType="date">
														<div class="form-control-focus">
														</div>
														</div>
														<span id="dueDateStr-error" class="help-block help-block-error">
															Capture la fecha
														</span>														
												</div>
											</div>	
										</c:if>
										
										<c:if test="${reportParam.parameterType == 'select'}">
											<div class="form-group form-md-line-input">
												<label class="col-md-3 control-label" for="form_control_1">
													${reportParam.label}
													<c:if test="${reportParam.required}">
														<span class="required">*</span>
													</c:if>
												</label>
												<div class="col-md-9">
													<form:select path="${reportParam.parameterPath}" class="form-control input-small" 
																 data-required="${reportParam.required}" data-parametername="${reportParam.label}" data-parameterType="date">
														<form:option value="-1" label="Seleccione..."/>
						    							<form:options items="${reportParam.parameterOptions}" />
													</form:select>
												</div>
											</div>
										</c:if>
										
									</c:forEach>
										  				
									</div>
								
									<!-- acciones -->
									<div class="form-actions margin-top-10">
										<div class="row">
											<div class="col-md-offset-2 col-md-10">
												<button type="button" class="btn default" onclick="sendRequestJQ('auth/report/list','dashboard','initReports()','GET');"><spring:message code="application.back"/></button>
												<button type="button" class="btn default" id="downloadReport">Ver</button>
											</div>
										</div>
									</div>
								
								</form:form>	
							</div>
										
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
