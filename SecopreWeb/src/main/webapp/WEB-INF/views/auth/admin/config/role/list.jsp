<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><spring:message code="application.pages.admin.roles.table.title"/>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="javascript:;" class="reload">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<button id="sample_editable_1_new" class="btn green">
											<spring:message code="application.add"/> <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
									<div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="application.tools"/><i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li>
													<a href="javascript:;" onclick="exportToExcel('#RoleTable', initRoleList());"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="RoleTable">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#roleTable .checkboxes"/>
								</th>
								<th><spring:message code="application.pages.admin.roles.name"/></th>
								<th><spring:message code="application.pages.admin.roles.description"/></th>
								<th><spring:message code="application.pages.admin.roles.active"/></th>
								<th><spring:message code="application.actions"/></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${roleList}" var="roleItem">
								<tr class="odd gradeX">
									<td>
										<input type="checkbox" class="checkboxes" value="1"/>
									</td>
									<td>${roleItem.name}</td>
									<td>${roleItem.description}</td>
									
									<td>
									<c:choose>
									    <c:when test="${roleItem.active}">
									   
									        Activo
									    
									    </c:when>    
									    <c:otherwise>
									      
									        Inactivo 
									       
									    </c:otherwise>
									</c:choose>	
									</td>		

									<td>
										<button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="sendRequestJQ('auth/adm/role/edit?id=${roleItem.id}' ,'dashboard','initRolePage(${roleItem.id})');">
										 <i class="fa fa-edit xs"></i>
										</button>
										
										<button id="btndelete"  type="button" class="btn delete btn-danger btn-xs" onclick="borrarRegistro('auth/adm/role/delete?id=${roleItem.id}','dashboard','initRoleList()');"   >
										<i class="fa fa-trash"></i> 
										</button>

									    <c:choose>
									       <c:when test="${roleItem.active}">
									          <button id="btn_edit"  type="button" class="btn edit-xs btn-warning btn-xs" onclick="changeStatus('auth/adm/role/changeStatus?id=${roleItem.id}&activo=${!roleItem.active}' ,'dashboard','initRoleList()');">
										      <i class="fa fa-minus-square xs"></i>
										      </button>
									       </c:when>    
									       <c:otherwise>
									          <button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="changeStatus('auth/adm/role/changeStatus?id=${roleItem.id}&activo=${!roleItem.active}' ,'dashboard','initRoleList()');">
										      <i class="fa fa-check-square xs"></i>
										      </button> 
									       
									       </c:otherwise>
									    </c:choose>	
									</td>			

								</tr>							
							</c:forEach>
							</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
