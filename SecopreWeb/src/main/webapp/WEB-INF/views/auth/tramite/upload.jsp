	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Carga de documento adjunto</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form cssClass="form-horizontal" enctype="multipart/form-data" method="POST" accept-charset="utf-8" id="requestForm" action="auth/wf/upload">
							
								<div class="form-body">
									<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>
									
									<!-- campos ocultos con propiedades del request -->
									<input id="requestId" name="requestId" type="hidden" value="${requestForm.requestId}"/>
									<input id="stageConfigId" name="stageConfigId" type="hidden" value="${requestForm.stageConfigId}"/>

									<!-- justificacion -->
									<div class="form-group form-md-line-input">
										<label class="col-md-12 control-label" for="justification" style="text-align:left;">Seleccione el archivo a subir:</label>
										<div class="col-md-12">
											<input type="file" size="1" name="attachment" id="attachment" accept="*.doc,*.docx,*.pdf, *.xls, *.xlsx" class="btn default"/>
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
											<button type="button" class="btn green fileinput-button" id="uploadFile"><i class="fa fa-plus"></i>Subir Archivo</button>
										</div>
									</div>
								</div>
								
							</form:form>	
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
