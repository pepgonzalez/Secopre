	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Presupuesto Anual</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>

						<jsp:include page="/WEB-INF/views/auth/tramite/exception.jsp" flush="true"/>
						
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form cssClass="form-horizontal" enctype="multipart/form-data" method="POST" accept-charset="utf-8" id="requestForm" action="auth/adm/bugget/upload">
							
								<div class="form-body">
									
									<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>
									
									<div class="form-group form-md-line-input">
										<div class="col-md-3">
											<c:if test="${entriesInConfig == true}">
												<div class="btn-group">
													<a id="cloneEntries" class="btn red">Liberar Presupuesto</a>
												</div>																						
											</c:if>
											<c:if test="${entriesInConfig == false}">
												<div class="btn-group">
													<a id="cloneEntries" class="btn red">Clonar Partidas</a>
												</div>											
											</c:if>
										</div>
										<div class="col-md-3">
											<c:if test="${entriesNextYear > 0}">
												<div class="btn-group">
													<a id="getEntriesTemplate" class="btn blue">Obtener plantilla de Carga</a>
												</div>
											</c:if>
										</div>
										<div class="col-md-4">
											<c:if test="${entriesNextYear > 0}">
												<input type="file" size="1" name="attachment" id="attachment" accept="*.doc,*.docx,*.pdf, *.xls, *.xlsx" class="btn default"/>
												<div class="form-control-focus">
												</div>
												<span class="help-block">
													<spring:message code="application.pages.tramite.add.notEmpty"/>
												</span>
											</c:if>
										</div>										
										<div class="col-md2">
											<c:if test="${entriesNextYear > 0}">
												<button type="button" class="btn green fileinput-button" id="uploadFile"><i class="fa fa-plus"></i>Subir Archivo</button>
											</c:if>
										</div>				
									</div>
									<div class="form-group form-md-line-input">
										<div class="col-md-6">
										</div>										
					
									</div>																	
							</form:form>	
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
			<c:if test="${entriesInConfig == true}">
				<div id="filter_ByDistrict">
					<%@ include file="/WEB-INF/views/auth/admin/config/entry/filter.jsp"%>
				</div>
	
				<div id="list_ByDistrict">
					<%@ include file="/WEB-INF/views/auth/admin/config/entry/byDistrict.jsp"%>
				</div>
			</c:if>
<script type="text/javascript">
<!--
$( document ).ready(function() {
	initUploadAnnualBudget();
});
//-->
</script>			