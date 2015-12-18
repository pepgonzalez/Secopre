<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><spring:message code="application.pages.param.director.table.title"/>
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
											<button id="agregar_pk" class="btn green">
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
													<a href="javascript:;" onclick="exportToExcel('#DirectorTable', initDirectorList());"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="DirectorTable">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#directorTable .checkboxes"/>
								</th>
<%-- 								<th><spring:message code="application.pages.param.director.usuario"/></th> --%>

							    <th><spring:message code="application.pages.param.director.director"/></th>
								<th><spring:message code="application.pages.param.director.initialDate"/></th>
								<th><spring:message code="application.pages.param.director.finalDate"/></th>
								<th><spring:message code="application.pages.param.director.legend"/></th>
								<th><spring:message code="application.active"/></th>
								<th><spring:message code="application.actions"/></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${directorList}" var="directorItem">
								<tr class="odd gradeX">
									<td>
										<input type="checkbox" class="checkboxes" value="1"/>
									</td>
<!-- 									<td> -->
<%-- 									     ${directorItem.initialDateStr} --%>
<!-- 									</td> -->
									<td>
									     ${directorItem.user.person.name}
									     ${directorItem.user.person.fatherLastName}
									</td>
									<td>
									     ${directorItem.initialDateStr}
									</td>
									<td>
										 ${directorItem.finalDateStr}
									</td>
									<td>
									     ${directorItem.legend}
									</td>

									<td>
									<c:choose>
									    <c:when test="${directorItem.active}">
									   
									        Activo
									    
									    </c:when>    
									    <c:otherwise>
									      
									        Inactivo 
									       
									    </c:otherwise>
									</c:choose>	
									</td>		

									<td>
										<button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="sendRequestJQ('auth/param/director/edit?id=${directorItem.id}' ,'dashboard','initDirectorCat()');">
										 <i class="fa fa-edit xs"></i>
										</button>
										
										<button id="btndelete"  type="button" class="btn delete btn-danger btn-xs" onclick="borrarRegistro('auth/param/director/delete?id=${directorItem.id}','dashboard','initDirectorList()');"   >
										<i class="fa fa-trash"></i> 
										</button>

									    <c:choose>
									       <c:when test="${directorItem.active}">
									          <button id="btn_edit"  type="button" class="btn edit-xs btn-warning btn-xs" onclick="changeStatus('auth/param/director/changeStatus?id=${directorItem.id}&activo=${!directorItem.active}' ,'dashboard','initDirectorList()');">
										      <i class="fa fa-minus-square xs"></i>
										      </button>
									       </c:when>    
									       <c:otherwise>
									          <button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="changeStatus('auth/param/director/changeStatus?id=${directorItem.id}&activo=${!directorItem.active}' ,'dashboard','initDirectorList()');">
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
