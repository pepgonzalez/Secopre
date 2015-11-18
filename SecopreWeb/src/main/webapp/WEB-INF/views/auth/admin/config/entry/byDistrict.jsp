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
		                                        <span data-counter="counterup" data-value='<c:out value="${balance.annualAmount}"/>'><c:out value="${balance.annualAmount}"/></span> $
		                                    </div>
		                                    <div class="desc">Presupuesto Anual</div>
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
		                                        <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsing}"/>"><c:out value="${balance.budgetAsing}"/></span> $
		                                    	<div class="desc">Presupuesto Real</div>
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
		                                        <span data-counter="counterup" data-value="<c:out value="${balance.budgetCommit}"/>"><c:out value="${balance.budgetCommit}"/></span> $
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
		                                        <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsing - balance.budgetCommit}"/>"><c:out value="${balance.budgetAsing - balance.budgetCommit}"/></span> $
		                                    	<div class="desc"> Total Post Compromiso</div>
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
											<table class="table table-striped table-bordered table-hover" id="byDistrictTable">
											<thead>
											<tr>
												<th>Clabe</th>
												<th>Nombre</th>
												<th>Presupuesto Anual</th>
												<th>Mes</th>
												<th>Monto</th>
											</tr>
											</thead>
											<tbody>
												<c:forEach items="${balance.entries}" var="entryItem">
													<tr class="odd gradeX">
														<td>${entryItem}</td>
														<td>${entryItem}</td>
														<td>${entryItem}</td>												
														<td>${entryItem}</td>
														<td>${entryItem}</td>
													</tr>
												</c:forEach>
											</tbody>
											</table>
										</div>
									</div>
									END EXAMPLE TABLE PORTLET
								</div>
							</div>
			