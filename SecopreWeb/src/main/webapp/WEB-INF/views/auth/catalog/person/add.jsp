<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.admin.persons.title"/> - <span class="step-title">
								Paso 1 de 3 </span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="auth/cat/person/add?id=${person.id}&addressid=${address.id}" class="form-horizontal" id="submit_form"  modelAttribute="submit_form" method="POST"  novalidate="novalidate">
								<div class="form-wizard">
									<div class="form-body">

										<ul class="nav nav-pills nav-justified steps">
											<li>
												<a href="#tab1" data-toggle="tab" class="step">
												<span class="number">
												1 </span>
												<span class="desc">
												<i class="fa fa-check"></i> Datos Generales </span>
												</a>
											</li>
											<li>
												<a href="#tab2" data-toggle="tab" class="step">
												<span class="number">
												2 </span>
												<span class="desc">
												<i class="fa fa-check"></i>Dirección </span>
												</a>
											</li>
											<li>
												<a href="#tab3" data-toggle="tab" class="step">
												<span class="number">
												3 </span>
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
												<h3 class="block">Proporcionar datos personales</h3>
												<div class="form-body">													
													
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.name"/>
															<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="name" name="name" value="${person.name}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.name.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="name-error" class="help-block help-block-error">
																<spring:message code="application.pages.admin.persons.name.help"/>
																</span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.secondName"/>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="secondName"  id="secondName" value="${person.secondName}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.secondName.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="secondName-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.secondName.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.fatherLastName"/> 
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="fatherLastName"  id="fatherLastName" value="${person.fatherLastName}" type="text"  class="form-control" placeholder='<spring:message code="application.pages.admin.persons.fatherLastName.placeholder"/>'>
																<div class="form-control-focus">
																</div>																				
 																<span id="fatherLastName-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.fatherLastName.help"/></span> 
															    <i class="fa fa-male"></i>
															</div>
														</div>
													</div>
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.motherLastName"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="motherLastName" name="motherLastName" value="${person.motherLastName}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.motherLastName.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id = "motherLastName-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.motherLastName.help"/></span>
																<i class="fa fa-female"></i>
															</div>
														</div>
													</div>	
													
															
													<div class="form-group form-md-line-input has-danger">
													<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.gender"/>
										  			<span class="required">* </span> 
												    </label>
													   <div class="col-md-4">
										                            <div class="md-radio-inline">
																				<div class="md-radio">
																				    <form:radiobutton path="person.gender"  id="gender1" value="MASCULINO" class="md-radiobtn" name="gender"/>
																					<label for="gender1">
																					<span></span>
																					<span class="check"></span>
																					<span class="box"></span>
																					<spring:message code="application.pages.admin.persons.gender1"/>
																					 </label>
																				</div>
																				<div class="md-radio">
																				    <form:radiobutton path="person.gender" id="gender2" value="FEMENINO" class="md-radiobtn" name="gender"/>
																					<label for="gender2">
																					<span></span>
																					<span class="check"></span>
																					<span class="box"></span>
																					<spring:message code="application.pages.admin.persons.gender2"/>
																					</label>
																				</div>
																			</div>
																<div class="form-control-focus"></div>
																<span id="gender-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.motherLastName.help"/></span>
												          </div>
												    </div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.telephone"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="telephone" name="telephone" value="${person.telephone}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.telephone.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="telephone-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.telephone.help"/></span>
																<i class="fa fa-tty"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.mobileTelepone"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="mobileTelepone" name="mobileTelepone" value="${person.mobileTelepone}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.mobileTelepone.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="mobileTelepone-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.mobileTelepone.help"/></span>
																<i class="icon-phone"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.twitter"/>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="twitter" name="twitter" value="${person.twitter}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.twitter.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="twitter-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.twitter.help"/></span>
																<i class="fa fa-twitter on fa-square-o"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.facebook"/>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="facebook" name="facebook" value="${person.facebook}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.facebook.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.facebook.help"/></span>
																<i class="fa fa-facebook"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.webSite"/>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="webSite" name="webSite" value="${person.webSite}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.webSite.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="webSite-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.webSite.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	 																				
											    	</div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Dirección</h3>
												
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.street"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="street" name="street" value="${address.street}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.street.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="street-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.street.help"/></span>
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
																<input id="exteriorNumber" name="exteriorNumber" value="${address.exteriorNumber}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.number.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="number-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.number.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.colony"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="colony" name="colony"  value="${address.colony}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.colony.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="colony-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.colony.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	
													
				   								    <div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.city"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="city" name="city" value="${address.city}" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.city.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="city-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.city.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	
													
													
													
													<div class="form-group form-md-line-input has-danger">
													<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.state"/>
										  			<span class="required">* </span> 
												    </label>
													   <div class="col-md-4">
													      <div class="input-group">
																<span class="input-group-addon"> 
																  <i class="icon-user"></i>
																</span> 
																<form:select path="address.stateDTO.id"  name="state" class="form-control">
																   <form:option value="" label="Seleccione..."/>
					    										   <form:options items="${states}" />
																</form:select>
																<div class="form-control-focus"></div>
												          </div>
													   </div>
												    </div>
																										
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.zipCode"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="zipCode" name="zipCode" value="${address.zipCode}"  type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.zipCode.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span id="zipCode-error" class="help-block help-block-error"><spring:message code="application.pages.admin.persons.zipCode.help"/></span>
																<i class="fa fa-home"></i>
															</div>
														</div>
													</div>	
											</div>
											
											
											<div class="tab-pane" id="tab3">
												<h3 class="block">Confirmación</h3>
	
												
									            <h4 class="form-section">Datos Generales</h4>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.admin.persons.name" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="name"></p>
										            </div>
									            </div>
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.secondName" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="secondName"></p>
										           </div>
									            </div>	
									            
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.fatherLastName" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="fatherLastName"></p>
										           </div>
									            </div>		
									            
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.motherLastName" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="motherLastName"></p>
										           </div>
									            </div>		
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.gender" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="gender"></p>
										           </div>
									            </div>		
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.telephone" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="telephone"></p>
										           </div>
									            </div>	
									            
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.mobileTelepone" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="mobileTelepone"></p>
										           </div>
									            </div>	
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.twitter" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="twitter"></p>
										           </div>
									            </div>										            									            									            					
													
													
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.facebook" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="facebook"></p>
										           </div>
									            </div>	
									            
									            													
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.webSite" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="webSite"></p>
										           </div>
									            </div>	
									            
  												<h4 class="form-section">Dirección</h4>
									            <div class="form-group">
										            <label class="control-label col-md-3"><spring:message
													code="application.pages.admin.persons.street" />
													</label>
										            <div class="col-md-4">
											           <p class="form-control-static" data-display="street"></p>
										            </div>
									            </div>
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.number" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="exteriorNumber"></p>
										           </div>
									            </div>	
									            
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.colony" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="colony"></p>
										           </div>
									            </div>		
									            
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.city" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="city"></p>
										           </div>
									            </div>		
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.state" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="stateDTO.id"></p>
										           </div>
									            </div>		
									            
									            <div class="form-group">
										           <label class="control-label col-md-3"><spring:message
													  code="application.pages.admin.persons.zipCode" />
											       </label>
										           <div class="col-md-4">
											         <p class="form-control-static" data-display="zipCode"></p>
										           </div>
									            </div>										            
																						
											</div>
										</div>
									</div>
									<div class="form-actions">
										<div class="row">
											<div class="col-md-offset-3 col-md-9">
											    <a href="javascript:showList('Person');" class="btn red" >
												 <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
												</a>
												<a href="javascript:;" class="btn default button-previous">
												<i class="m-icon-swapleft"></i> <spring:message code="application.back"/> </a>
												<a href="javascript:;" class="btn blue button-next">
												<spring:message code="application.next"/> <i class="m-icon-swapright m-icon-white"></i>
												</a>
												<button type="button" class="btn green button-submit" id="submitRequestForm"><spring:message code="application.pages.cat.persons.crear"/></button>
						
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
