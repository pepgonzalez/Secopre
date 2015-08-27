	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- INICIA DIV DE FORM DE ROLES -->			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase"><spring:message code="application.pages.admin.roles.title"/></span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only blue" href="javascript:;">
								<i class="icon-cloud-upload"></i>
								</a>
								<a class="btn btn-circle btn-icon-only green" href="javascript:;">
								<i class="icon-wrench"></i>
								</a>
								<a class="btn btn-circle btn-icon-only red" href="javascript:;">
								<i class="icon-trash"></i>
								</a>
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form:form cssClass="form-horizontal" id="roleForm" commandName="role" action="auth/adm/role/add">
								<div class="form-body">
								<!-- Se incluyen los DIV de alertamiento en formularios -->
									<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>						
									<div class="form-group form-md-line-input">
										<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.roles.name"/>
											<span class="required">* </span>
										</label>
										<div class="col-md-10">
											<div class="input-icon">
												<input required id="rolename"  name="rolename" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.roles.name.placeholder"/>'>
												<div class="form-control-focus">
												</div>
												<span class="help-block"><spring:message code="application.pages.admin.roles.name.help"/></span>
												<i class="icon-user"></i>
											</div>
										</div>
									</div>								
								</div>
								<div class="form-actions margin-top-10">
									<div class="row">
										<div class="col-md-offset-2 col-md-10">
											<button type="button" class="btn default"><spring:message code="application.back"/></button>
											<button type="button" class="btn blue"><spring:message code="application.submit"/></button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>
