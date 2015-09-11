<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue" id="form_wizard_1">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> <spring:message code="application.pages.admin.users.title"/> - <span class="step-title">
								Paso 1 de 2 </span>
							</div>
							<div class="tools hidden-xs">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" class="form-horizontal" id="submit_form" method="POST" action="#" novalidate="novalidate">
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
												<i class="fa fa-check"></i>Direccion </span>
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
																<input id="name" name="name" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.name.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.name.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.secondName"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input name="secondName"  id="secondName" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.secondName.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.secondName.help"/></span>
																<i class="icon-user"></i>
															</div>
														</div>
													</div>
													
													
													<div class="form-group form-md-line-input">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.fatherLastName"/> 
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-group">
																<span class="input-group-addon">
																<i class="fa fa-envelope"></i>
																</span>
																<input name="fatherLastName"  id="fatherLastName" type="text"  class="form-control" placeholder='<spring:message code="application.pages.admin.persons.fatherLastName.placeholder"/>'>
																<div class="form-control-focus">
																</div>																				
<!-- 																<span class="help-block"><spring:message code="application.pages.admin.users.email.help"/></span> -->
															</div>
														</div>
													</div>
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.motherLastName"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="motherLastName" name="motherLastName" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.fatherLastName.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.motherLastName.help"/></span>
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
													</div>	
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.telephone"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="telephone" name="telephone" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.telephone.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.telephone.help"/></span>
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
													</div>	
													
													

													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.mobileTelepone"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="mobileTelepone" name="mobileTelepone" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.mobileTelepone.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.mobileTelepone.help"/></span>
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
													</div>
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.twitter"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="twitter" name="twitter" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.twitter.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.twitter.help"/></span>
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
													</div>	
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.facebook"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="facebook" name="facebook" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.facebook.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.facebook.help"/></span>
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
													</div>	
													
													
													<div class="form-group form-md-line-input has-danger">
														<label class="col-md-2 control-label" for="form_control_1"><spring:message code="application.pages.admin.persons.webSite"/>
														<span class="required">* </span>
														</label>
														<div class="col-md-10">
															<div class="input-icon">
																<input id="webSite" name="webSite" type="text" class="form-control" placeholder='<spring:message code="application.pages.admin.persons.webSite.placeholder"/>'>
																<div class="form-control-focus">
																</div>
																<span class="help-block"><spring:message code="application.pages.admin.persons.webSite.help"/></span>
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
													</div>	
													
													

													
													
<!-- 													<div class="form-group"> -->
<%-- 													   <label class="control-label col-md-2" for="form_control_1"><spring:message code="application.pages.admin.users.chat"/> --%>
<!-- 													      <span class="required" aria-required="true"> * </span> -->
<!-- 													   </label> -->
<!-- 													      <div class="col-md-3"> -->
<!--                                                           <div class="radio-list"> -->
<!--                                                        <label> -->
<!--                                                           <div class="radio"> -->
<!--                                                              <span><input type="radio" data-title="Si" value="S" name="gender" > -->
<!-- 															</span> -->
<!-- 														</div> -->
<!-- 														Si -->
<!-- 												      </label> -->


<!-- 													  <label> -->
<!-- 													     <div class="radio"> -->
<!-- 													        <span> -->
<!-- 													        <input type="radio" data-title="No" value="N" name="gender"> -->
<!-- 													        </span> -->
<!-- 												         </div> -->
<!-- 															No -->
<!-- 															</label> -->
<!-- 														</div> -->
<!-- 														<div id="form_gender_error"> </div> -->
<!-- 														</div> -->
<!-- 														</div> -->
												       
												       																				
											    	</div>
											</div>

						
											<div class="tab-pane" id="tab2">
												<h3 class="block">Direccion</h3>
												<h4 class="form-section">Account</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Username:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="username">
														</p>
													</div>
												</div>
												<h4 class="form-section">Profile</h4>
												<div class="form-group">
													<label class="control-label col-md-3">Fullname:</label>
													<div class="col-md-4">
														<p class="form-control-static" data-display="fullname">
														</p>
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
												<a href="javascript:;" class="btn green button-submit">
												Submit <i class="m-icon-swapright m-icon-white"></i>
												</a>
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
