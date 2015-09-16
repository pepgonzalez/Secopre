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
							<form:form cssClass="form-horizontal" method="POST" modelAttribute="requestForm" id="requestForm" action="auth/wf/authorization" novalidate="novalidate">
							
								<div class="form-body">
									<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>
									
									<form:hidden path="requestId" />
									<form:hidden path="stageConfigId" />
									<form:hidden path="nextStageValueCode" id="nextStageValueCode" />
									
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
									
									<jsp:include page="/WEB-INF/views/auth/common/formality/${authorization.formalityCode}.jsp" flush="true"/>
									
								
								</div>
								
								<div class="form-actions margin-top-10">
									<div class="row">
										<div class="col-md-offset-2 col-md-10">
											
											<button type="button" class="btn default" onclick="sendRequestJQ('auth/tram/list','dashboard','initTramiteListPage()','GET');">Regresar a Mis Tramites</button>
											
											<c:if test="${authorization.canUserAuthorize || authorization.superUser}">
												<button type="button" class="btn default" id="cancelFormality" >Cancelar Solicitud</button>
											</c:if>
											<c:if test="${authorization.canUserAuthorize && authorization.moreSignatures}">
												<button type="button" class="btn green" id="authorizateFormality">Autorizar</button>
											</c:if>
											<c:if test="${(authorization.superUser) || (authorization.canUserAuthorize && !(authorization.moreSignatures))}">
												<button type="button" class="btn green" id="finishFormality">Finalizar Tramite</button>
											</c:if>
										</div>
									</div>
								</div>
								
							</form:form>	
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
