	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Calendarizacion Saldos</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>

						<jsp:include page="/WEB-INF/views/auth/tramite/exception.jsp" flush="true"/>
						
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form cssClass="form-horizontal" enctype="multipart/form-data" method="POST" accept-charset="utf-8" id="requestForm" action="auth/adm/sche/apply">
							
								<div class="form-body">
									
									<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>

									<div class="row">
										<div id="filter_ByDistrict">
											<%@ include file="/WEB-INF/views/auth/admin/config/scheduling/filter.jsp"%>
										</div>									
									</div>	
								</div>																
							</form:form>	
						</div>
						
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>

	
<script type="text/javascript">
<!--
$( document ).ready(function() {
	initScheduling();
});
//-->
</script>			