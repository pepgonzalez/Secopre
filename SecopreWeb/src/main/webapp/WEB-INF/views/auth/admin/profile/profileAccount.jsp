<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

						<div class="row">
							<div class="col-md-12">
								<div class="portlet light">
									<div class="portlet-title tabbable-line">
										<div class="caption caption-md">
											<i class="icon-globe theme-font hide"></i>
											<span class="caption-subject font-blue-madison bold uppercase">Mi Cuenta</span>
										</div>
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#tab_1_1" data-toggle="tab">Personal Info</a>
											</li>
											<li>
												<a href="#tab_1_2" data-toggle="tab">Cambiar Avatar</a>
											</li>
											<li>
												<a href="#tab_1_3" data-toggle="tab">Cambiar Contraseña</a>
											</li>
<!-- 											<li> -->
<!-- 												<a href="#tab_1_4" data-toggle="tab">Aviso de Privacidad</a> -->
<!-- 											</li> -->
										</ul>
									</div>
									<div class="portlet-body">
										<div class="tab-content">
											<!-- PERSONAL INFO TAB -->
											<div class="tab-pane active" id="tab_1_1">
												<form id="personal_form" action="auth/adm/profile/changePersonalInfo/${user.id}/${person.id}"
												method="POST" novalidate="novalidate">
												
													<div class="form-group">
														<label class="control-label">Primer Nombre</label>
														<input readonly="true" name=name type="text" value="${person.name}"  placeholder="John" class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label">Segundo Nombre</label>
														<input readonly="true" name=secondName type="text"  value="${person.secondName}" placeholder="" class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label">Apellido Paterno</label>
														<input readonly="true" name=fatherLastName type="text" value="${person.fatherLastName}" placeholder="" class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label">Apellido Materno</label>
														<input readonly="true" name=motherLastName type="text" value="${person.motherLastName}"  placeholder="" class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label">Nickname</label>
														<input name=nickname type="text" value="${user.nickname}"  class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label"><spring:message code="application.pages.admin.users.email" /></label>
														<input readonly="true" name=email type="text" value="${user.email}"  class="form-control"/>
													</div>
																										
													<label class="control-label">Ocupación</label>			
													<div class="form-group">
					
													   		<form:select path="user.position.id"  name="positions" class="form-control input-large" disabled="true">
															   <form:option  value="" label="Seleccione..."/>
				    										   <form:options items="${positions}" />
															</form:select>
													</div>
													
													<div class="form-group">
														<label class="control-label"><spring:message code="application.pages.admin.persons.telephone"/></label>
														<input name=telephone type="text" value="${person.telephone}"  class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label"><spring:message code="application.pages.admin.persons.mobileTelepone"/></label>
														<input name=mobileTelepone type="text" value="${person.mobileTelepone}"  class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label"><spring:message code="application.pages.admin.persons.twitter"/></label>
														<input name=twitter type="text" value="${person.twitter}"  class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label"><spring:message code="application.pages.admin.persons.facebook"/></label>
														<input name=facebook type="text" value="${person.facebook}"  class="form-control"/>
													</div>
													
													<div class="form-group">
														<label class="control-label"><spring:message code="application.pages.admin.persons.webSite"/></label>
														<input name=webSite type="text" value="${person.webSite}"  class="form-control"/>
													</div>

									
													<div class="form-group">
													<label class="control-label">Acerca de mi</label>
														<textarea id="information" name="information"  class="form-control" rows="3" placeholder=""></textarea>
													</div>
															

													<div class="margiv-top-11">
														<button type="button" class="btn blue button-submit" id="submitRequestPersonalInfo"><spring:message code="application.save"/></button>
														<a href="javascript:;" class="btn default">
														Cancelar </a>
													</div>
												</form>
											</div>
											<!-- END PERSONAL INFO TAB -->
											<!-- CHANGE AVATAR TAB -->
											<div class="tab-pane" id="tab_1_2">
												<p>
													 Seleccione su Avantar, el cual será su identificador gráfico en este portal
												</p>
												<form id="avatar_form" action="auth/adm/profile/changeAvatar" method="POST" novalidate="novalidate">
														<div class="fileinput fileinput-new" data-provides="fileinput">
															<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
															   <img id="avatar" alt="" class="img-responsive" src='<c:url value="${avatar}"/>' />
<!-- 																<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/> -->
															</div>
															<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
															</div>
															<div>
																<span class="btn default btn-file">
																<span class="fileinput-new">
																Seleccione Imagen </span>
																<span class="fileinput-exists">
																Cambiar </span>
																<input type="file" name="avatar" id="avatar" value="${avatar}">
																</span>
																<a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput">
																Borrar </a>
															</div>
														</div>
														<div class="clearfix margin-top-10">
															<span class="label label-danger">NOTA! </span>
																	<span> Imagen de Máximo 2 MB </span> 
															
<!-- 															<span>Atache image thumbnail is supported in Latest Firefox, Chrome, Opera, Safari and Internet Explorer 10 only </span> -->
														</div>
													
													<div class="margin-top-10">
														<button type="button" class="btn blue button-submit" id="submitRequestAvatar"><spring:message code="application.save"/></button>
														<a href="javascript:;" class="btn default">
														Cancelar </a>
													</div>
												</form>
											</div>
											<!-- END CHANGE AVATAR TAB -->
											<!-- CHANGE PASSWORD TAB -->
											<div class="tab-pane" id="tab_1_3">
													<form id="password_form" action="auth/adm/profile/changePassword/${user.id}" method="POST" novalidate="novalidate">
													<div class="form-group">
														<label class="control-label">Contraseña Actual</label>
														<input name=apassword type="password" class="form-control"/>
													</div>
													<div class="form-group">
														<label class="control-label">Nueva Contraseña</label>
														<input id=password name=password type="password" class="form-control"/>
													</div>
													<div class="form-group">
														<label class="control-label">Nueva Contraseña</label>
														<input id=rpassword name=rpassword type="password" class="form-control"/>
													</div>
													<div class="margiv-top-10">
														<button type="button" class="btn blue button-submit" id="submitRequestPassword"><spring:message code="application.save"/></button>
														<a href="javascript:;" class="btn default">
														Cancelar </a>
													</div>
													
												</form>
											</div>
											<!-- END CHANGE PASSWORD TAB -->
											<!-- PRIVACY SETTINGS TAB -->
											<div class="tab-pane" id="tab_1_4">
												<form action="#">
													<table class="table table-light table-hover">
													<tr>
														<td>
															 Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus..
														</td>
														<td>
															<label class="uniform-inline">
															<input type="radio" name="optionsRadios1" value="option1"/>
															Yes </label>
															<label class="uniform-inline">
															<input type="radio" name="optionsRadios1" value="option2" checked/>
															No </label>
														</td>
													</tr>
													<tr>
														<td>
															 Enim eiusmod high life accusamus terry richardson ad squid wolf moon
														</td>
														<td>
															<label class="uniform-inline">
															<input type="checkbox" value=""/> Yes </label>
														</td>
													</tr>
													<tr>
														<td>
															 Enim eiusmod high life accusamus terry richardson ad squid wolf moon
														</td>
														<td>
															<label class="uniform-inline">
															<input type="checkbox" value=""/> Yes </label>
														</td>
													</tr>
													<tr>
														<td>
															 Enim eiusmod high life accusamus terry richardson ad squid wolf moon
														</td>
														<td>
															<label class="uniform-inline">
															<input type="checkbox" value=""/> Yes </label>
														</td>
													</tr>
													</table>
													<!--end profile-settings-->
													<div class="margin-top-10">
														<a href="javascript:;" class="btn green-haze">
														Guardar Cambios </a>
														<a href="javascript:;" class="btn default">
														Cancelar </a>
													</div>
												</form>
											</div>
											<!-- END PRIVACY SETTINGS TAB -->
										</div>
									</div>
								</div>
							</div>
						</div>
