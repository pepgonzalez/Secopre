<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><spring:message code="application.pages.catalog.district.title"/>
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
													<a href="javascript:;"><spring:message code="application.print"/></a>
												</li>
												<li>
													<a href="javascript:;"><spring:message code="application.export.pdf"/></a>
												</li>
												<li>
													<a href="javascript:;"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="DistrictTable">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#districtTable .checkboxes"/>
								</th>
								<th><spring:message code="application.pages.catalog.district.number"/></th>
								<th><spring:message code="application.pages.catalog.district.state"/></th>
								<th><spring:message code="application.pages.catalog.district.address"/></th>
								<th><spring:message code="application.pages.catalog.district.email"/></th>
								<th><spring:message code="application.pages.catalog.district.telephone"/></th>
								<th><spring:message code="application.pages.catalog.district.active"/></th>
								<th><spring:message code="application.actions"/></th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${districtList}" var="districtItem">
								<tr class="odd gradeX">
									<td>
										<input type="checkbox" class="checkboxes" value="1"/>
									</td>
									<td>
									     ${districtItem.number}
									</td>
									<td>
										 ${districtItem.state.name}
									</td>
									<td>
										 ${districtItem.address.street}
									</td>
									<td>
										 ${districtItem.email}
									</td>
									<td>
										 ${districtItem.telephone}
									</td>
									<td>
										 ${districtItem.activo}
									</td>
									<td>
										<button id="btn_edit"  type="button" class="btn edit-xs btn-success btn-xs" onclick="sendRequestJQ('auth/cat/district/edit?id=${districtItem.id}' ,'dashboard','editDistrictCat()');">
										 <i class="fa fa-edit xs"></i>
										</button>
										
										<button id="btndelete"  type="button" class="btn delete btn-danger btn-xs" onclick="borrarRegistro('auth/cat/district/delete?id=${districtItem.id}','dashboard','initDistrictList()');"   >
										<i class="fa fa-trash"></i> 
										</button>
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
