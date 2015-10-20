	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Error en el sistema</span>
							</div>
						</div>
						
						<div class="portlet-body">
							
							<c:forEach items="${errors}" var="error">
								<div class="alert alert-danger">
									${error}
								</div>						
							</c:forEach>
							
							<div class="col-md-6">
								<div class="btn-group">
									<button id="sample_editable_1_new" class="btn red" onclick="${nextAction}">
										Continuar
									</button>
								</div>
							</div>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
