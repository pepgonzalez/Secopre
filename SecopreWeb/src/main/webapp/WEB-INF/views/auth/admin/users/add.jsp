<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue" id="form_wizard_1">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-gift"></i>
					<spring:message code="application.pages.admin.users.title" />
					- <span class="step-title"> Paso 1 de 4 </span>
				</div>
				<div class="tools hidden-xs">
					<a href="javascript:;" class="collapse"> </a>
				</div>
			</div>
			<div class="portlet-body form">
				<form action="auth/adm/usr/add" class="form-horizontal" id="submit_form"
					method="POST" novalidate="novalidate">
					<div class="form-wizard">
						<div class="form-body">
							<ul class="nav nav-pills nav-justified steps">
								<li><a href="#tab1" data-toggle="tab" class="step"> <span
										class="number"> 1 </span> <span class="desc"> <i
											class="fa fa-check"></i> Información de Cuenta
									</span>
								</a></li>
								<li><a href="#tab2" data-toggle="tab" class="step"> <span
										class="number"> 2 </span> <span class="desc"> <i
											class="fa fa-check"></i>Roles
									</span>
								</a></li>
								<li><a href="#tab3" data-toggle="tab" class="step active">
										<span class="number"> 3 </span> <span class="desc"> <i
											class="fa fa-check"></i>Permisos
									</span>
								</a></li>
								<li><a href="#tab4" data-toggle="tab" class="step"> <span
										class="number"> 4 </span> <span class="desc"> <i
											class="fa fa-check"></i> Confirmación
									</span>
								</a></li>
							</ul>
							<div id="bar" class="progress progress-striped"
								role="progressbar">
								<div class="progress-bar progress-bar-success"></div>
							</div>
							<div class="tab-content">
								<!-- Se incluyen los DIV de alertamiento en formularios -->
								<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>

								<div class="tab-pane active" id="tab1">
									<h3 class="block">Proporcionar datos de su cuenta</h3>
									<div class="form-body">
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message code="application.pages.admin.users.username" />
												<span class="required">*</span>
											</label>
											<div class="col-md-9">
												<div class="input-group">
													<span class="input-group-addon"> 
														<i class="icon-user"></i>
													</span> 
													<input id="username" name="username" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.users.username.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span class="help-block">
														<spring:message code="application.pages.admin.users.username.help" />
													</span>
												</div>
											</div>
										</div>

										<div class="form-group form-md-line-input has-danger">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.nickname" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="icon-user"></i>
													</span> <input id="nickname" name="nickname" type="text"
														class="form-control"
														placeholder='<spring:message code="application.pages.admin.users.nickname.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span class="help-block"><spring:message
															code="application.pages.admin.users.nickname.help" /></span>
												</div>
											</div>
										</div>
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">
												<spring:message code="application.pages.admin.users.email" />
												<span class="required">*</span>
											</label>
											<div class="col-md-9">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="fa fa-envelope"></i>
													</span> 
													<input name="email" id="email" type="email" class="form-control" placeholder='<spring:message code="application.pages.admin.users.email"/>'>
													<div class="form-control-focus">
													</div>
													<span class="help-block">
														<spring:message code="application.pages.admin.users.email.help" />
													</span>
												</div>
											</div>
										</div>
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.hasChatActive" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa-weixin"></i>
													</span> <input name="hasChatActive" id="hasChatActive" type="checkbox"
														class="icheck">
													<div class="form-control-focus"></div>
													<span class="help-block"><spring:message
															code="application.pages.admin.users.email.help" /></span>
												</div>
											</div>
										</div>										
										<div class="form-group form-md-line-input has-danger">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.password" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-key"></i>
													</span> <input id="password" name="password" type="password"
														class="form-control"
														placeholder='<spring:message code="application.pages.admin.users.password.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span class="help-block"><spring:message
															code="application.pages.admin.users.password.help" /></span>
												</div>
											</div>
										</div>
										<div class="form-group form-md-line-input has-danger">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.password" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-key"></i>
													</span> <input id="rpassword" name="rpassword" type="password"
														class="form-control"
														placeholder='<spring:message code="application.pages.admin.users.password.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span class="help-block"><spring:message
															code="application.pages.admin.users.password.help" /></span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="tab2">
									<h3 class="block">Seelccione los Roles</h3>
									<div class="form-group">
										<label class="control-label col-md-3">Roles<span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<select multiple="multiple" class="multi-select" id="roles" name="roles">
												<c:forEach items="${roles}" var="role">
													<option>${role.rolename} </option>
												</c:forEach>
											</select>											
										</div>
									</div>
									<div class="form-group">&nbsp;</div>
									<div class="form-group">&nbsp;</div>
								</div>
								<div class="tab-pane" id="tab3">
									<h3 class="block">Seelccione los Permisos</h3>
									<div class="form-group">
										<label class="control-label col-md-3">Permisos<span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<select multiple="multiple" class="multi-select" id="permissions" name="permissions">
												<c:forEach items="${permissions}" var="permission">
													<option>${permission.name} </option>
												</c:forEach>
											</select>											
										</div>
									</div>
									<div class="form-group">&nbsp;</div>
									<div class="form-group">&nbsp;</div>
								</div>
								<div class="tab-pane" id="tab4">
									<h3 class="block">Confirme la cuenta de usuario</h3>
									<h4 class="form-section">Cuenta</h4>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message
													code="application.pages.admin.users.username" /></label>
										<div class="col-md-4">
											<p class="form-control-static" data-display="username"></p>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message
													code="application.pages.admin.users.nickname" /></label>
										<div class="col-md-4">
											<p class="form-control-static" data-display="nickname"></p>
										</div>
									</div>									
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message
													code="application.pages.admin.users.email" /></label>
										<div class="col-md-4">
											<p class="form-control-static" data-display="email"></p>
										</div>
									</div>									
									<h4 class="form-section">Roles</h4>
									<div class="form-group">
										<label class="control-label col-md-3">Roles:</label>
										<div class="col-md-4">
											<p class="form-control-static" data-display="roles"></p>
										</div>
									</div>
									<h4 class="form-section">Permisos</h4>
									<div class="form-group">
										<label class="control-label col-md-3">Permisos:</label>
										<div class="col-md-4">
											<p class="form-control-static" data-display="permissions"></p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<a href="javascript:;" class="btn default button-previous">
										<i class="m-icon-swapleft"></i> Back
									</a> <a href="javascript:;" class="btn blue button-next">
										Continue <i class="m-icon-swapright m-icon-white"></i>
									</a> 
									<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.admin.users.crear"/></button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- END PAGE CONTENT-->
