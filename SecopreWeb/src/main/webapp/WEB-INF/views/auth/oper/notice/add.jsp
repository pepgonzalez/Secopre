<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.catalog.notice.title"/> - <span class="step-title">
								Paso 1 de 2 </span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="auth/oper/notice/add?id=${notice.id}" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Aviso </span>
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
												<h3 class="block">Proporcionar Información de Avisos</h3>
												<div class="form-body">													

													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.notice.registerDate"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="registerDateStr" name="registerDateStr" type="text" class="form-control form-control-inline input-medium date-picker"  value="${notice.registerDate}"   placeholder='<spring:message code="application.pages.catalog.notice.registerDate.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.catalog.notice.registerDate.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.notice.displayDate"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="displayDateStr" name="displayDateStr"  type="text" class="form-control form-control-inline input-medium date-picker"  value="${notice.displayDate}"   placeholder='<spring:message code="application.pages.catalog.notice.displayDate.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.catalog.notice.displayDate.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.notice.notice"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<form:textarea path="notice.noticeInfo" id="noticeInfo" name="noticeInfo" value="${notice.noticeInfo}" class="form-control" rows="1" />
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.catalog.notice.notice.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
			
												    <div class="form-group">
															<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.notice.district"/>
															<span class="required"> * </span>
															</label>
															<div class="col-md-4">
																<select multiple="multiple" class="multi-select" id="districts" name="districts">
																	<c:forEach items="${districts}" var="district">
																			<option value="${district.id}">${district.number} </option> 
																	</c:forEach>
																</select>											
															</div>
													</div>
														
													
													
																																												       																				
										        </div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Confirmación</h3>
	
												
									            <h4 class="form-section">Avisos</h4>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.notice.registerDate" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="registerDateStr"></p>
										            </div>
									            </div>
					            
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.notice.displayDate" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="displayDateStr"></p>
										            </div>
									            </div>
									            
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.notice.notice" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="noticeInfo"></p>
										            </div>
									            </div>
									            
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.notice.district" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="districts"></p>
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
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.catalog.notice.crear"/></button>
						
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
