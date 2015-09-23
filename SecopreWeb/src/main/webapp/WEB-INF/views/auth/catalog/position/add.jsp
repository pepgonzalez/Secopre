<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.catalog.position.title"/> - <span class="step-title">
								Paso 1 de 2 </span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="auth/cat/position/add" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Puestos </span>
												</a>
											</li>
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
												2 </span>
												<span class="desc">
												<i class="fa fa-check"></i>Confirmación </span>
												</a>
											</li>
											
										</ul>
										
										<div id="bar" class="progress progress-striped" role="progressbar">
											<div class="progress-bar progress-bar-success">
											</div>
										</div>
									
										<div class="tab-content">
										<!-- Se incluyen los DIV de alertamiento en formularios -->
											<%@ include file="/WEB-INF/views/auth/common/alertForm.jsp"%>

											<div class="tab-pane active" id="tab1">
												<h3 class="block">Proporcionar Información de Puesto</h3>
												<div class="form-body">													
													
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.position.name"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="name" name="name" type="text" class="form-control" placeholder='<spring:message code="application.pages.catalog.position.name.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.catalog.position.name.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.position.description"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="description"  id="description" type="text" class="form-control" placeholder='<spring:message code="application.pages.catalog.position.description.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.catalog.position.description.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
																					       																				
										        </div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Confirmación</h3>
	
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.street"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="street" name="street" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.street.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.street.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	

												
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.number"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="number" name="number" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.number.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.number.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	
													
													
							     					<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.state"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="state" name="state" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.state.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.state.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>																									
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> Back </a>
												<a href="javascript:;" class="btn blue button-next">
												Continue <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.catalog.position.crear"/></button>
						
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
