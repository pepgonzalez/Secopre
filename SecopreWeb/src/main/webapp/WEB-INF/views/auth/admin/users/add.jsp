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
											class="fa fa-check"></i>Distritos
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
												<div class="input-icon">
													<input id="username" name="username" type="text" value="${user.username}" class="form-control" placeholder='<spring:message code="application.pages.admin.users.username.placeholder"/>'
													aria-required="true" aria-describedby="name-error" aria-invalid="true" >
													<div class="form-control-focus"></div>
													<span id=username-error class="help-block-error help-block">
														<spring:message code="application.pages.admin.users.username.help" />
													</span>
													<i class="icon-user"></i>
												</div>
											</div>
										</div>

										<div class="form-group form-md-line-input has-danger">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.nickname" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-icon">
													<input class="form-control" id="nickname" name="nickname" type="text" value="${user.nickname}"
														placeholder='<spring:message code="application.pages.admin.users.nickname.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span id=nickname-error class="help-block help-block-error"><spring:message
															code="application.pages.admin.users.nickname.help" /></span>
													<i class="icon-user"></i>
												</div>
											</div>
										</div>
										
										<div class="form-group form-md-line-input">
											<label class="col-md-3 control-label" for="form_control_1">
												<spring:message code="application.pages.admin.users.email" />
												<span class="required">*</span>
											</label>
											<div class="col-md-9">
												<div class="input-icon">
													<input name="email" id="email" type="email" value="${user.email}" class="form-control" placeholder='<spring:message code="application.pages.admin.users.email"/>'>
													<div class="form-control-focus">
													</div>
													<span id="email-error" class="help-block help-block-error">
														<spring:message code="application.pages.admin.users.email.help" />
													</span>
													<i class="fa fa-envelope"></i>
												</div>
											</div>
										</div>
										
										
									   <div class="form-group form-md-line-input has-danger">
										<label class="col-md-3 control-label" for="form_control_1"><spring:message code="application.pages.admin.users.hasChatActive"/>
										   <span class="required">* </span> 
										</label>
										<div class="col-md-4">
										   <div class="input-icon">
												<form:select path="user.hasChatActive"  name="hasChatActive" class="form-control">
												    <form:option value="0" label="No"/>
												   <form:option value="1" label="Si"/>
	    										
												</form:select>
												<div class="form-control-focus"></div>
												<span id="person-error" class="help-block help-block-error">
												</span>
												 <i class="fa fa-weixin"></i>
										   </div>
										</div>
										</div>
										
<!-- 										<div class="form-group form-md-line-input has-danger"> -->
<%-- 										   <label class="col-md-3 control-label" for="form_control_1"><spring:message --%>
<%-- 													code="application.pages.admin.users.hasChatActive" /> <span --%>
<!-- 												class="required">* </span>  -->
<!-- 										   </label> -->
<!-- 										   <div class="col-md-9"> -->
<!-- 										       <div class="input-group">  -->
<!-- 										          <span class="input-group-addon"> <i -->
<!-- 														class="fa fa-weixin"></i> -->
<!-- 												  </span> -->
<!-- 												  <div class="md-checkbox"> -->
<!-- 											      <input type="checkbox" class="md-check" id="hasChatActive" name="hasChatActive"> -->
<!-- 											      <label for="hasChatActive"> -->
<!-- 											      <span class="inc"></span> -->
<!-- 											      <span class="check"></span> -->
<!-- 											      <span class="box"></span> -->
<!-- 											       </label> -->
<!-- 											      </div> -->
<!-- 											   </div> -->
<!-- 										   </div> -->
<!-- 										</div> -->
										
																			
										<div class="form-group form-md-line-input has-danger">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.password" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-icon">
													<input id="password" name="password" value="${user.password}" type="password"
														class="form-control"
														placeholder='<spring:message code="application.pages.admin.users.password.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span id="password-error" class="help-block help-block-error"><spring:message
															code="application.pages.admin.users.password.help" />
													</span>
													<i class="fa fa-key"></i>
												</div>
											</div>
										</div>
										
										<div class="form-group form-md-line-input has-danger">
											<label class="col-md-3 control-label" for="form_control_1"><spring:message
													code="application.pages.admin.users.password" /> <span
												class="required">* </span> </label>
											<div class="col-md-9">
												<div class="input-icon">
													<input id="rpassword" name="rpassword" type="password" value="${user.password}" class="form-control"
														placeholder='<spring:message code="application.pages.admin.users.password.placeholder"/>'>
													<div class="form-control-focus"></div>
													<span id="rpassword-error" class="help-block help-block-error"><spring:message
															code="application.pages.admin.users.password.help" /></span>
												    <i class="fa fa-key"></i>
												</div>
											</div>
										</div>
										
										<div class="form-group form-md-line-input has-danger">
										<label class="col-md-3 control-label" for="form_control_1"><spring:message code="application.pages.admin.users.selectPerson"/>
										   <span class="required">* </span> 
										</label>
										<div class="col-md-4">
										   <div class="input-icon">
												<form:select path="user.person.id"  name="person" class="form-control">
												   <form:option value="" label="Seleccione..."/>
	    										   <form:options items="${persons}" />
												</form:select>
												<div class="form-control-focus"></div>
												<span id="person-error" class="help-block help-block-error">
													<spring:message code="application.pages.admin.users.selectPerson"/>
												</span>
												 <i class="icon-user"></i>
										   </div>
										</div>
										</div>
										
										<div class="form-group form-md-line-input has-danger">
										<label class="col-md-3 control-label" for="form_control_1"><spring:message code="application.pages.admin.users.selectPosition"/>
										   <span class="required">* </span> 
										</label>
										<div class="col-md-4">
										   <div class="input-icon">
												<form:select path="user.position.id"  name="position" class="form-control">
												   <form:option value="" label="Seleccione..."/>
	    										   <form:options items="${positions}" />
												</form:select>
												<div class="form-control-focus"></div>
												<span id="person-error" class="help-block help-block-error">
													<spring:message code="application.pages.admin.users.selectPosition"/>
												</span>
												 <i class="icon-user"></i>
										   </div>
										</div>
										</div>
										
									</div>
								</div>
								<div class="tab-pane" id="tab2">
									<h3 class="block">Seleccione los Roles</h3>
									<div class="form-group">
										<label class="control-label col-md-3">Roles<span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<select multiple="multiple" class="multi-select" id="roles" name="roles">
												<c:forEach items="${roles}" var="role">
														<option value="${role.id}">${role.name} </option> 
												</c:forEach>
											</select>											
										</div>
									</div>
									<div class="form-group">&nbsp;</div>
									<div class="form-group">&nbsp;</div>
								</div>
								
								<div class="tab-pane" id="tab3">
									<h3 class="block">Seleccione los Distritos</h3>
									<div class="form-group">
										<label class="control-label col-md-3">Distritos<span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<select multiple="multiple" class="multi-select" id="distrs" name="distrs">
												<c:forEach items="${districts}" var="district">
														<option value="${district.id}">${district.number} </option> 
												</c:forEach>
											</select>											
										</div>
									</div>
									<div class="form-group">&nbsp;</div>
									<div class="form-group">&nbsp;</div>
								</div>
								
<!-- 								<div class="tab-pane" id="tab3"> -->
<!-- 									<h3 class="block">Seelccione los Permisos</h3> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">Permisos<span -->
<!-- 											class="required"> * </span> -->
<!-- 										</label> -->
<!-- 										<div class="col-md-4"> -->
<!-- 											<select multiple="multiple" class="multi-select" id="permissions" name="permissions"> -->
<%-- 												<c:forEach items="${permissions}" var="permission"> --%>
<%-- 													<option value="${permission.id}">${permission.name} </option> --%>
<%-- 												</c:forEach> --%>
<!-- 											</select>											 -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="form-group">&nbsp;</div> -->
<!-- 									<div class="form-group">&nbsp;</div> -->
<!-- 								</div> -->
								
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
									
									<h4 class="form-section">Distritos </h4>
									<div class="form-group">
										<label class="control-label col-md-3">Distritos:</label>
										<div class="col-md-4">
											<p class="form-control-static" data-display="distrs"></p>
										</div>
									</div>
<!-- 									<h4 class="form-section">Permisos</h4> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label class="control-label col-md-3">Permisos:</label> -->
<!-- 										<div class="col-md-4"> -->
<!-- 											<p class="form-control-static" data-display="permissions"></p> -->
<!-- 										</div> -->
<!-- 									</div> -->
								</div>
							</div>
						</div>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">									
										      <c:choose>
											     <c:when test="${user.id!=null}">
											        <a href="javascript:initUserList();" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  
												 </c:when>   
									             <c:otherwise>		
											           <a href="javascript:showList('User');" class="btn red" >
													   <spring:message code="application.cancel"/> <i class="fa fa-times"></i>
													   </a>  	
									             </c:otherwise>
									          </c:choose>	
									<a href="javascript:;" class="btn default button-previous">
										<i class="m-icon-swapleft"></i> <spring:message code="application.back"/>
									</a> <a href="javascript:;" class="btn blue button-next">
										<spring:message code="application.next"/> <i class="m-icon-swapright m-icon-white"></i>
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
