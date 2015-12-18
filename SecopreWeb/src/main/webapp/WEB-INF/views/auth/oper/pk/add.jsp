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
												<h3 class="block">Información de Clave Programática</h3>
												<div class="form-body">													
													
													
<!-- 													<div class="form-group form-md-line-input"> -->
<%-- 														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.code"/> --%>
<!-- 															<span class="required">* </span> -->
<!-- 														</label> -->
<!-- 														<div class="col-md-10"> -->
<!-- 															<div class="input-icon"> -->
<%-- 																<input id="code" name="code" type="text" class="form-control"  value="${pk.code}"   placeholder='<spring:message code="application.pages.catalog.programmaticKey.code.placeholder"/>'> --%>
<!-- 																<div class="form-control-focus"> -->
<!-- 																</div> -->
<%-- 																<span id="code-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.code.help"/></span> --%>
<!-- 																<i class="icon-user"></i> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
													
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
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.ramo"/>
<!-- 														<span class="required">* </span> -->
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="ramo"  id="ramo" type="text" value="${pk.ramo}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.ramo.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="ramo-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.ramo.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.unitResponsable"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="unitResponsable"  id="unitResponsable" type="text" value="${pk.unitResponsable}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.unitResponsable.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="unitResponsable-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.unitResponsable.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.functionalGroup"/>
														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="functionalGroup"  id="functionalGroup" type="text" value="${pk.functionalGroup}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.functionalGroup.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="functionalGroup-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.functionalGroup.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.function"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="function"  id="function" type="text" value="${pk.function}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.function.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="function-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.function.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.subfunction"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="subfunction"  id="subfunction" type="text" value="${pk.subfunction}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.subfunction.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="subfunction-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.subfunction.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.program"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="program"  id="program" type="text" value="${pk.program}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.program.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="program-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.program.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.activity"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="activity"  id="activity" type="text" value="${pk.activity}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.activity.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="activity-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.activity.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
													

													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.programBudget"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="code"  id="code" type="text" value="${pk.code}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.programBudget.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="programBudget-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.programBudget.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
		
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.expenseType"/>
 														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="expenseType"  id="expenseType" type="text" value="${pk.expenseType}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.expenseType.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="expenseType-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.expenseType.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
																										
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.financingSource"/>
														<span class="required">* </span> 
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="financingSource"  id="financingSource" type="text" value="${pk.financingSource}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.financingSource.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="financingSource-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.financingSource.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
									
<!-- 													<div class="form-group form-md-line-input has-danger"> -->
<%-- 														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.catalog.programmaticKey.finality"/> --%>
<!-- <!-- 														<span class="required">* </span> --> 
<!-- 														</label> -->
<!-- 														<div class="col-md-10"> -->
<!-- 															<div class="input-icon"> -->
<%-- 																<input name="finality"  id="finality" type="text" value="${pk.finality}"  class="form-control" placeholder='<spring:message code="application.pages.catalog.programmaticKey.finality.placeholder"/>'> --%>
<!-- 																<div class="form-control-focus"> -->
<!-- 																</div> -->
<%-- 																<span id="finality-error" class="help-block help-block-error"><spring:message code="application.pages.catalog.programmaticKey.finality.help"/></span> --%>
<!-- 																<i class="icon-user"></i> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
																	
																					       																				
										        </div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Confirmación</h3>
												
									            <h4 class="form-section">Clave Programática</h4>
	
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.year" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="year"></p>
										           </div>
									            </div>													
												

									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.ramo" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="ramo"></p>
										           </div>
									            </div>													
																								

									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.catalog.programmaticKey.unitResponsable" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="unitResponsable"></p>
										            </div>
									            </div>
									            

									          
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.program" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="program"></p>
										           </div>
									            </div>
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.financingSource" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="financingSource"></p>
										           </div>
									            </div>	
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.functionalGroup" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="functionalGroup"></p>
										           </div>
									            </div>	
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.activity" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="activity"></p>
										           </div>
									            </div>	
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.function" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="function"></p>
										           </div>
									            </div>										            
									            									 									        						
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.programBudget" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="code"></p>
										           </div>
									            </div>	
									          
									         
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.subfunction" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="subfunction"></p>
										           </div>
									            </div>											         
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.expenseType" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="expenseType"></p>
										           </div>
									            </div>		
									          
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.catalog.programmaticKey.finality" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="finality"></p>
										           </div>
									            </div>									       													
																								
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">

									            
										      <c:choose>
											     <c:when test="${pk.id!=null}">
											        <a href="javascript:initProgrammaticKeyList();" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  
												 </c:when>   
									             <c:otherwise>		
											           <a href="javascript:showList('ProgrammaticKey');" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  	
									             </c:otherwise>
									          </c:choose>
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
