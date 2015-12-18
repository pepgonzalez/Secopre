<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><spring:message code="application.pages.catalog.programmaticKey.table.title"/>
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
													<a href="javascript:;" onclick="exportToExcel('#ProgrammaticKeyTable', initProgrammaticKeyList());"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="ProgrammaticKeyTable">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#programmaticKeyTable .checkboxes"/>
								</th>
<%-- 								<th><spring:message code="application.pages.catalog.programmaticKey.code"/></th> --%>
								<th><spring:message code="application.pages.catalog.programmaticKey.year"/></th>
								<th><spring:message code="application.pages.catalog.programmaticKey.ramo"/></th>
								<th><spring:message code="application.pages.catalog.programmaticKey.unitResponsable"/></th>
								<th><spring:message code="application.pages.catalog.programmaticKey.functionalGroup"/></th>	
							    <th><spring:message code="application.pages.catalog.programmaticKey.function"/></th>
								<th><spring:message code="application.pages.catalog.programmaticKey.subfunction"/></th>
								
								<th><spring:message code="application.pages.catalog.programmaticKey.program"/></th>
								<th><spring:message code="application.pages.catalog.programmaticKey.activity"/></th>	
									<th><spring:message code="application.pages.catalog.programmaticKey.programBudget"/></th>	
									<th><spring:message code="application.pages.catalog.programmaticKey.expenseType"/></th>
								<th><spring:message code="application.pages.catalog.programmaticKey.financingSource"/></th>								
									
														
							
														
				
								
								<th><spring:message code="application.active"/></th>
								<th><spring:message code="application.actions"/></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${pkList}" var="programmaticKeyItem">
								<tr class="odd gradeX">
									<td>
										<input type="checkbox" class="checkboxes" value="1"/>
									</td>
<!-- 									<td> -->
<%-- 									     ${programmaticKeyItem.code} --%>
<!-- 									</td> -->
									<td>
										 ${programmaticKeyItem.year}
									</td>
									<td>
										 ${programmaticKeyItem.ramo}
									</td>
									<td>
										 ${programmaticKeyItem.unitResponsable}
									</td>
									<td>
										 ${programmaticKeyItem.functionalGroup}										
									</td>
									<td>
										 ${programmaticKeyItem.function}
									</td>
									<td>
										 ${programmaticKeyItem.subfunction}
									</td>	
									<td>
										 ${programmaticKeyItem.program}
									</td>	
									
									<td>
										 ${programmaticKeyItem.activity}
									</td>
									
									

									<td>
										 ${programmaticKeyItem.code}
									</td>
									
									
									<td>
										 ${programmaticKeyItem.expenseType}
									</td>
									
																																		
									<td>
										 ${programmaticKeyItem.financingSource}
									</td>


									<td>
									<c:choose>
									    <c:when test="${programmaticKeyItem.activo}">
									   
									        Activo
									    
									    </c:when>    
									    <c:otherwise>
									      
									        Inactivo 
									       
									    </c:otherwise>
									</c:choose>	
									</td>	
									

									<td>
										<button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="sendRequestJQ('auth/oper/pk/edit?id=${programmaticKeyItem.id}' ,'dashboard','initProgrammaticKeyCat()');">
										 <i class="fa fa-edit xs"></i>
										</button>
										
<%-- 										<button id="btndelete"  type="button" class="btn delete btn-danger btn-xs" onclick="borrarRegistro('auth/oper/pk/delete?id=${programmaticKeyItem.id}','dashboard','initProgrammaticKeyList()');"   > --%>
<!-- 										<i class="fa fa-trash"></i>  -->
<!-- 										</button> -->

									    <c:choose>
									       <c:when test="${programmaticKeyItem.activo}">
									          <button id="btn_edit"  type="button" class="btn edit-xs btn-warning btn-xs" onclick="changeStatus('auth/oper/pk/changeStatus?id=${programmaticKeyItem.id}&activo=${!programmaticKeyItem.activo}' ,'dashboard','initProgrammaticKeyList()');">
										      <i class="fa fa-minus-square xs"></i>
										      </button>
									       </c:when>    
									       <c:otherwise>
									          <button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="changeStatus('auth/oper/pk/changeStatus?id=${programmaticKeyItem.id}&activo=${!programmaticKeyItem.activo}' ,'dashboard','initProgrammaticKeyList()');">
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
