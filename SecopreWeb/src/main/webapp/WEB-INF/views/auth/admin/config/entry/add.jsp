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
							<form action="auth/oper/entry/add?id=${entry.id}" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Partidas </span>
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
												<h3 class="block">Proporcionar Información de Partidas</h3>
												<div class="form-body">													

													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.entry.entry"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<form:textarea path="entry.code" id="code" name="code" value="${entry.code}" class="form-control" rows="1" />
																<div class="form-control-focus">
																</div>
																<span id="code-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.entry.entry.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>													
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.entry.name"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
															    <form:textarea path="entry.name" id="name" name="name" value="${entry.name}" class="form-control" rows="2" />
																<div class="form-control-focus">
																</div>
																<span id="name-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.entry.name.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.entry.description"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<form:textarea path="entry.description" id="description" name="description" value="${entry.description}" class="form-control" rows="2" />
																<div class="form-control-focus">
																</div>
																<span id="description-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.entry.description.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
													<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.entry.pk"/>
										  			<span class="required">* </span> 
												    </label>
													   <div class="col-md-4">
													      <div class="input-group">
																<span class="input-group-addon"> 
																  <i class="icon-user"></i>
																</span> 
																<form:select path="entry.programmaticKey.id"  value="${entry.programmaticKey.id}"  name="programmaticKey.id" class="form-control">
																   <form:option value="" label="Seleccione..."/>
					    										   <form:options items="${pks}" />
																</form:select>
																<div class="form-control-focus"></div>
												          </div>
													   </div>
												    </div>
												    
												    <div class="form-group form-md-line-input has-danger">
													<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.entry.accountingType"/>
										  			<span class="required">* </span> 
												    </label>
													   <div class="col-md-4">
													      <div class="input-group">
																<span class="input-group-addon"> 
																  <i class="icon-user"></i>
																</span> 																
																<form:select path="entry.accountingType"  name="accountingType" class="form-control">
												                    <form:option value="" label="Seleccione..."/>
												                    <form:options items="${accountingTypes}" />
												                </form:select>
						
																<div class="form-control-focus"></div>
												          </div>
													   </div>
												    </div>
													
																					       																				
										        </div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Confirmación</h3>
	
												
									            <h4 class="form-section">Partidas</h4>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.entry.entry" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="name"></p>
										            </div>
									            </div>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.entry.name" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="name"></p>
										            </div>
									            </div>
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.entry.description" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="description"></p>
										           </div>
									            </div>		
									            
									               <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.entry.pk" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="programmaticKey.id"></p>
										           </div>
									            </div>		
									            
									                    <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.entry.accountingType" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="accountingType"></p>
										           </div>
									            </div>					
													
																								
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">

									            
										      <c:choose>
											     <c:when test="${entry.id != null}">
											           <a href="javascript:initEntryList();" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  
												 </c:when>   
									             <c:otherwise>		
											           <a href="javascript:showList('Entry');" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  	
									             </c:otherwise>
									          </c:choose>									
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> <spring:message code="application.back"/> </a>
												<a href="javascript:;" class="btn blue button-next">
												<spring:message code="application.next"/> <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.catalog.entry.crear"/></button>
						
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
