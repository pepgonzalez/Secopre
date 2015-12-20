	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase"><spring:message code="application.pages.tramite.add.title"/></span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form  method="POST" modelAttribute="requestForm" id="requestForm" action="auth/tram/add" novalidate="novalidate">
							
								<div class="form-body">
									<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>
									
									
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="formalityId"><spring:message code="application.pages.tramite.add.select"/></label>
										<div class="col-md-4">
												<form:select path="formalityId" id="formalityId" class="form-control">
												   	<form:option value="-1" label="Seleccione..."/>
	    											<form:options items="${formalities}" />
												</form:select>
												<div class="form-control-focus"></div>
												<span class="help-block">
													<spring:message code="application.pages.tramite.add.selectFormality"/>
												</span>
										</div>
										
										<label class="col-md-2 control-label" for="districtId"><spring:message code="application.pages.tramite.add.distric"/></label>
										<div class="col-md-4">
												<form:select path="districtId" id="districtId" class="form-control">
												   	<form:option value="-1" label="Seleccione..."/>
	    											<form:options items="${districts}" />
												</form:select>
												<div class="form-control-focus"></div>
												<span class="help-block">
													<spring:message code="application.pages.tramite.add.selectFormality"/>
												</span>
										</div>
									</div>
									
									<!-- justificacion -->
									<div class="form-group form-md-line-input">
										<label class="col-md-12 control-label" for="justification" style="text-align:left;"><spring:message code="application.pages.tramite.add.justification"/></label>
										<div class="col-md-12">
											<form:textarea path="justification" id="justification" class="form-control" rows="2" />
											<div class="form-control-focus">
											</div>
											<span class="help-block">
												<spring:message code="application.pages.tramite.add.notEmpty"/>
											</span>
										</div>
									</div>
								
								</div>
								
								<div class="form-actions margin-top-10">
									<div class="row">
										<div class="col-md-offset-2 col-md-10">
											<button type="button" class="btn default" onclick="sendRequestJQ('auth/tram/list','dashboard','noAction()','GET');"><spring:message code="application.back"/></button>
											<button type="button" class="btn green" id="submitRequestForm"><spring:message code="application.pages.tramite.add.crear"/></button>
										</div>
									</div>
								</div>
								
							</form:form>	
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
