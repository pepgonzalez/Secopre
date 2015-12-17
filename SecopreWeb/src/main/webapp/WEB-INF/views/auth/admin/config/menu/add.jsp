<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.admin.menu.title"/> - <span class="step-title">
								Paso 1 de 2</span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="auth/adm/menu/add?id=${menu.id}&pathid=${path.id}" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i><spring:message code="application.pages.admin.menu.title"/></span>
												</a>
											</li>
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
												2 </span>
												<span class="desc">
												<i class="fa fa-check"></i><spring:message code="application.confirmation"/></span>
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
												<h3 class="block"><spring:message code="application.pages.admin.menu.data"/></h3>
												<div class="form-body">													
													
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.name"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="name" name="name" type="text" value="${menu.name}" class="form-control" placeholder='<spring:message code="application.pages.admin.menu.name.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id=name-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.name.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.description"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="description"  id="description" type="text" value="${menu.description}" class="form-control" placeholder='<spring:message code="application.pages.admin.menu.description.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id=description-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.description.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.cssClass"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="cssClass"  id="cssClass" type="text" value="${menu.cssClass}" class="form-control" placeholder='<spring:message code="application.pages.admin.menu.cssClass.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id=description-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.cssClass.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>			
													
<!-- 												<div class="form-group form-md-line-input has-danger"> -->
<%--                                                 <label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.cssClass"/> --%>
<!--                                                 </label> -->
<!--                                                 <div class="col-md-10"> -->
<!--                                                     <div class="input-icon"> -->
<!--                                                     <select  id="cssClass" name="cssClass" class="selectpicker"> -->
<!--                                                         <option value="icon-settings" data-icon="glyphicon glyphicon-hand-right">   Seleccione..</option> -->
<!--                                                         <option value="glyphicon glyphicon-print" data-icon="glyphicon glyphicon-print">glyphicon-print</option> -->
<!--                                                         <option value="glyphicon glyphicon-user" data-icon="glyphicon glyphicon-user">glyphicon-user</option> -->
<!--                                                         <option value="glyphicon glyphicon-cog" data-icon="glyphicon glyphicon-cog">glyphicon-cog</option> -->
<!--                                                         <option value="glyphicon glyphicon-calendar" data-icon="glyphicon glyphicon-calendar">glyphicon-calendar</option> -->
<!--                                                         <option value="glyphicon glyphicon-usd" data-icon="glyphicon glyphicon-usd">glyphicon-usd</option> -->
<!--                                                         <option value="glyphicon glyphicon-stats" data-icon="glyphicon glyphicon-stats" >glyphicon glyphicon-pdf</option> -->
                                                        
<!--                                                     </select> -->
<!--                                                     	<div class="form-control-focus"> -->
<!-- 																</div> -->
<%-- 																<span id=cssClass-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.cssClass.help"/></span> --%>
<!-- 															</div> -->
<!--                                                 </div> -->
<!--                                                  </div> -->

													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.jsFunction"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="jsFunction"  id="jsFunction" type="text" value="${menu.jsFunction}" class="form-control" placeholder='<spring:message code="application.pages.admin.menu.jsFunction.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id=jsFunction-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.jsFunction.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
																										
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.order"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="order"  id="order" type="number" value="${menu.order}" class="form-control" placeholder='<spring:message code="application.pages.admin.menu.order.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id=order-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.order.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>	
																									
													<div class="form-group form-md-line-input has-danger">
													<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.parentId"/>
										  			<span class="required">* </span> 
												    </label>
													   <div class="col-md-4">
													      <div class="input-group">
																<span class="input-group-addon"> 
																  <i class="icon-user"></i>
																</span> 
																<form:select path="menu.parentId"  name="parentId" class="form-control">
																   <form:option value="" label="Seleccione..."/>
					    										   <form:options items="${parents}" />
																</form:select>
																<div class="form-control-focus"></div>
												          </div>
													   </div>
												    </div>	
																  
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.menu.path"/>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="url" id="url" type="text" value="${path.url}" class="form-control" placeholder='<spring:message code="application.pages.admin.menu.path.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id=url-error class="help-block help-block-error"><spring:message code="application.pages.admin.menu.path.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>													  																																			
																					       																				
										        </div>
											</div>
											
											
<!-- 											<div class="tab-pane" id="tab2"> -->
<!-- 									<h3 class="block">Seleccione los Roles</h3> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">Roles<span -->
<!-- 											class="required"> * </span> -->
<!-- 										</label> -->
<!-- 										<div class="col-md-4"> -->
<!-- 											<select multiple="multiple" class="multi-select" id="rols" name="rols"> -->
<%-- 												<c:forEach items="${rols}" var="role"> --%>
<%-- 														<option value="${role.id}">${role.rolename} </option>  --%>
<%-- 												</c:forEach> --%>
<!-- 											</select>											 -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="form-group">&nbsp;</div> -->
<!-- 									<div class="form-group">&nbsp;</div> -->
<!-- 								</div> -->

						
											<div class="tab-pane" id="tab2">
												<h3 class="block"><spring:message code="application.confirmation"/></h3>
	
												
									            <h4 class="form-section"><spring:message code="application.pages.admin.menu.title"/></h4>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.admin.menu.name" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="name"></p>
										            </div>
									            </div>
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.menu.description" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="description"></p>
										           </div>
									            </div>		
									            					
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.menu.cssClass" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="cssClass"></p>
										           </div>
									            </div>														
	
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.menu.jsFunction" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="jsFunction"></p>
										           </div>
									            </div>	
									            
<!-- 									            <div class="form-group"> -->
<%-- 										           <label class="control-label col-md-3"><spring:message --%>
<%-- 													  code="application.pages.admin.menu.order" /> --%>
<!-- 											       </label> -->
<!-- 										           <div class="col-md-4"> -->
<!-- 											         <p class="form-control-static" data-display="order"></p> -->
<!-- 										           </div> -->
<!-- 									            </div>			 -->
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.menu.parentId" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="parentId"></p>
										           </div>
									            </div>		
									            
<!-- 									            <h4 class="form-section">Roles</h4> -->
<!-- 													<div class="form-group"> -->
<!-- 														<label class="control-label col-md-3">Roles:</label> -->
<!-- 														<div class="col-md-4"> -->
<!-- 															<p class="form-control-static" data-display="rols"></p> -->
<!-- 														</div> -->
<!-- 													</div> -->
									            

																								
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">

										      <c:choose>
											     <c:when test="${menu.id!=null}">
											        <a href="javascript:initMenuList();" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  
												 </c:when>   
									             <c:otherwise>		
											           <a href="javascript:showList('Menu');" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  	
									             </c:otherwise>
									          </c:choose>
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> <spring:message code="application.back"/> </a>
												<a href="javascript:;" class="btn blue button-next">
												<spring:message code="application.next"/> <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.admin.menu.crear"/></button>
						
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
