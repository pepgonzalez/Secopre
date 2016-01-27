<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

		                    <!-- BEGIN DASHBOARD STATS 1-->
		                    <div class="row">
		                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		                            <div class="dashboard-stat blue">
		                                <div class="visual">
		                                    <i class="fa fa-comments"></i>
		                                </div>
		                                <div class="details">
		                                    <div class="number">
		                                       <span data-counter="counterup" data-value='<c:out value="${balance.annualAmountStr}"/>'><c:out value="${balance.annualAmountStr}"/></span>
		                                    </div>
		                                    <div class="desc">Presupuesto Anual </div>
		                                </div>
		                                <a class="more" href="javascript:;">
		                                    <i class="m-icon-swapright m-icon-white"></i>
		                                </a>
		                            </div>
		                        </div>
		                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		                            <div class="dashboard-stat red">
		                                <div class="visual">
		                                    <i class="fa fa-bar-chart-o"></i>
		                                </div>
		                                <div class="details">
		                                    <div class="number">
		                                        <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsingStr}"/>"><c:out value="${balance.budgetAsing}"/></span>
		                                    	<div class="desc">Presupuesto Modificado </div>
		                                    </div>
		                                </div>
		                                <a class="more" href="javascript:;">
		                                    <i class="m-icon-swapright m-icon-white"></i>
		                                </a>

		                            </div>
		                        </div>
		                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		                            <div class="dashboard-stat green">
		                                <div class="visual">
		                                    <i class="fa fa-shopping-cart"></i>
		                                </div>
		                                <div class="details">
		                                    <div class="number">
		                                       <span data-counter="counterup" data-value="<c:out value="${balance.budgetCommitStr}"/>"><c:out value="${balance.budgetCommitStr}"/></span>
		                                    </div>
		                                    <div class="desc">Total Comprometido</div>
		                                </div>
		                                <a class="more" href="javascript:;">
		                                    <i class="m-icon-swapright m-icon-white"></i>
		                                </a>

		                            </div>
		                        </div>
		                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		                            <div class="dashboard-stat purple">
		                                <div class="visual">
		                                    <i class="fa fa-globe"></i>
		                                </div>
		                                <div class="details">
		                                    <div class="number"> 
		                                        <span data-counter="counterup" data-value="<c:out value="${balance.availableStr}"/>"><c:out value="${balance.availableStr}"/></span>
		                                    	<div class="desc"> Disponible</div>
		                                    </div>
		                                </div>
		                                <a class="more" href="javascript:;">
		                                    <i class="m-icon-swapright m-icon-white"></i>
		                                </a>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="clearfix"></div>
		                    <!-- END DASHBOARD STATS 1-->	

		                    			<!-- BEGIN PAGE CONTENT INNER -->
							<div class="row">
								<div class="col-md-12">
				<!-- 					BEGIN EXAMPLE TABLE PORTLET -->
									<div class="portlet box grey-cascade">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-globe"></i>Partidas por Distrito
											</div>
											<div class="tools">
												<a href="javascript:;" class="collapse">
												</a>
											</div>
										</div>
										<div class="portlet-body">
											<div class="table-toolbar">
												<div class="row">
													<div class="col-md-6">
														<div class="btn-group">
<!-- 															<button id="sample_editable_1_new" class="btn green"> -->
<%-- 															<spring:message code="application.add"/> <i class="fa fa-plus"></i> --%>
<!-- 															</button> -->
														</div>
													</div>
													<div class="col-md-6">
														<div class="btn-group pull-right">
															<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="application.tools"/><i class="fa fa-angle-down"></i>
															</button>
															<ul class="dropdown-menu pull-right">
																<li>
																	<a href="javascript:;" onclick="exportToExcel('#byDistrictTable', initEntryByDistrict);"><spring:message code="application.export.excel"/></a>
																</li>
															</ul>
														</div>
													</div>
												</div>
											</div>
											<table class="table table-striped table-bordered table-hover" id="byDistrictTable">
											<thead>
											<tr>
												<th>Entidad</th>
												<th>Distrito</th>
												<th>Partida</th>
												<th>--Enero--</th>
												<th>--Febrero--</th>
												<th>---Marzo---</th>
												<th>---Abril---</th>
												<th>---Mayo---</th>
												<th>---Junio---</th>
												<th>---Julio---</th>
												<th>--Agosto--</th>
												<th>Septiembre</th>
												<th>--Octubre--</th>
												<th>-Noviembre-</th>
												<th>-Diciembre-</th>
											</tr>
											</thead>
											<tbody>
												
												<c:forEach items="${balance.entries}" var="entryItem">
													<tr class="odd gradeX">
														<td>${entryItem.stateName}</td>
														<td>${entryItem.districtNumber}</td>
														<td>${entryItem.entryCode}</td>												
														<td align="right">${entryItem.januaryStr}</td>
														<td align="right">${entryItem.februaryStr}</td>
														<td align="right">${entryItem.marchStr}</td>
														<td align="right">${entryItem.aprilStr}</td>
														<td align="right">${entryItem.mayStr}</td>												
														<td align="right">${entryItem.juneStr}</td>
														<td align="right">${entryItem.julyStr}</td>
														<td align="right">${entryItem.augustStr}</td>
														<td align="right">${entryItem.septemberStr}</td>
														<td align="right">${entryItem.octoberStr}</td>												
														<td align="right">${entryItem.novemberStr}</td>
														<td align="right">${entryItem.decemberStr}</td>													
													</tr>

												</c:forEach>
											</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
			