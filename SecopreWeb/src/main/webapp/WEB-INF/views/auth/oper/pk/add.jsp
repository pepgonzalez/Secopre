<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.catalog.programmaticKey.title"/> - <span class="step-title">
								Paso 1 de 2 </span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="auth/oper/pk/add?id=${pk.id}" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Clave Programática </span>
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
												<h3 class="block">Proporcionar Información de Clave Programática</h3>
												<div class="form-body">													
													
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.code"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="code" name="code" type="text" class="form-control"  value="${pk.code}"   placeholder='<spring:message code="application.pages.catalog.programmaticKey.code.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="code-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.code.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.year"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="year"  id="year" type="text" value="${pk.year}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.year.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="year-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.year.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
																					       																				
										        </div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Confirmación</h3>
	
												
									            <h4 class="form-section">Clave Programática</h4>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.programmaticKey.code" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="code"></p>
										            </div>
									            </div>
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.year" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="year"></p>
										           </div>
									            </div>							
													
																								
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> <spring:message code="application.back"/> </a>
												<a href="javascript:;" class="btn blue button-next">
												<spring:message code="application.next"/> <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.catalog.programmaticKey.crear"/></button>
						
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
