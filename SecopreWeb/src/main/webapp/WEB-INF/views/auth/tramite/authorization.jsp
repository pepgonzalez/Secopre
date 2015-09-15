	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Autorizacion de tramite</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form cssClass="form-horizontal" method="POST" modelAttribute="requestForm" id="requestForm" action="auth/tramite/authorization" novalidate="novalidate">
							
								<div class="form-body">
									<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>
									
									<!-- campo de nombre de usuario -->
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="firstName"><spring:message code="application.pages.tramite.add.firstName"/></label>
										<div class="col-md-10">
											<form:input path="firstName" type="text" id="firstName" class="form-control" readonly="${requestForm.authorizationForm}"/>
											<div class="form-control-focus">
											</div>
											<span class="help-block">
												<spring:message code="application.pages.tramite.add.notEmpty"/>
											</span>
										</div>
									</div>
									
									<!-- campo de apellido paterno -->
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="parentLastName"><spring:message code="application.pages.tramite.add.parentLastName"/></label>
										<div class="col-md-10">
											<form:input path="parentLastName" type="text" id="parentLastName" class="form-control" readonly="${requestForm.authorizationForm}"/>
											<div class="form-control-focus">
											</div>
											<span class="help-block">
												<spring:message code="application.pages.tramite.add.notEmpty"/>
											</span>
										</div>
									</div>
									
									<!-- campo de apellido materno -->
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="motherLastName"><spring:message code="application.pages.tramite.add.parentLastName"/></label>
										<div class="col-md-10">
											<form:input path="motherLastName" type="text" id="motherLastName" class="form-control" readonly="${requestForm.authorizationForm}"/>
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
											<button type="button" class="btn default"><spring:message code="application.back"/></button>
											<button type="button" class="btn default">Cancelar Solicitur</button>
											<button type="button" class="btn green">Autorizar</button>
											<button type="button" class="btn green">Terminar Folio</button>
										</div>
									</div>
								</div>
								
							</form:form>	
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
