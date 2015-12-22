<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.param.director.title"/> - <span class="step-title">
								Paso 1 de 2 </span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="auth/param/director/add?id=${director.id}" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Captura Histórico de Directores </span>
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
												<h3 class="block">Información de Histórico de Directores</h3>
												<div class="form-body">		
												
													<div class="form-group">
														<label class="control-label col-md-2">Director<span
															class="required"> * </span>
														</label>
														<div class="col-md-4">
																<form:select path="director.user.id"  name="directors" class="form-control">
																	   <form:option value="" label="Seleccione..."/>
						    										   <form:options items="${directors}" />
															   </form:select>										
														</div>
													</div>											
																									
													<div class="form-group form-md">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.param.director.initialDate"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-4">
<!-- 															<div class="input-icon"> -->
                                                                <div data-date-format="dd/mm/yyyy" class="input-group date date-picker">
																 <div class="input-group-addon">
															        <span class="fa fa-calendar"></span>
															    </div>
																<input name="initialDateStr"  id="initialDateStr"  type="text" value="${director.initialDateStr}" class="form-control">
																<div class="form-control-focus">
																</div>
																</div>
																<span id="initialDateStr-error" class="help-block help-block-error">
																<spring:message code="application.pages.param.director.initialDate.help"/>
																</span>														
														</div>
													</div>	
																	
													<div class="form-group form-md">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.param.director.finalDate"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-4">
                                                                <div data-date-format="dd/mm/yyyy" class="input-group date date-picker">
																<div class="input-group-addon">
															        <span class="fa fa-calendar"></span>
															    </div>
																<input name="finalDateStr"  id="finalDateStr"  type="text" value="${director.finalDateStr}" class="form-control">
																</div>
																
																<span id ="finalDateStr-error" class="help-block help-block-error">
																<spring:message code="application.pages.param.director.finalDate.help"/>
																</span>														
														</div>
													</div>	
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.param.director.legend"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<form:textarea path="director.legend" id="legend" name="legend" value="${director.legend}" class="form-control" rows="1" />
																<div class="form-control-focus">
																</div>
																<span id="noticeInfo-error" class="help-block help-block-error"><spring:message code="application.pages.param.director.legend.help"/>
																</span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
													
																																       																				
										        </div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Confirmación</h3>
	
												
									            <h4 class="form-section">Histórico de Directores</h4>
									         
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.param.director.director" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="user.id"></p>
										           </div>
									            </div>		
									            
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.param.director.initialDate" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="initialDateStr"></p>
										            </div>
									            </div>
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.param.director.finalDate" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="finalDateStr"></p>
										           </div>
									            </div>		
									            
					
													
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.param.director.legend" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="legend"></p>
										           </div>
									            </div>							
																		
													
																								
											</div>
										</div>
									</div>
									
									<div class="form-actions">
										<div class="row">
									       <div class="col-md-offset-3 col-md-9">
										      <c:choose>
											     <c:when test="${director.id!=null}">
											        <a href="javascript:initDirectorList();" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  
												 </c:when>   
									             <c:otherwise>		
											           <a href="javascript:showList('Director');" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  	
									             </c:otherwise>
									          </c:choose>	
											
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> <spring:message code="application.back"/> </a>
												<a href="javascript:;" class="btn blue button-next">
												<spring:message code="application.next"/> <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.param.director.crear"/></button>
						
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
