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
		                                        <span data-counter="counterup" data-value='<fmt:formatNumber value="${balance.annualAmount}" maxFractionDigits="2"/>'><c:out value="${balance.annualAmount}"/></span> $
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
		                                        <span data-counter="counterup" data-value="<fmt:formatNumber value="${balance.budgetAsing}" maxFractionDigits="2"/>"><c:out value="${balance.budgetAsing}"/></span> $
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
		                                        <span data-counter="counterup" data-value="<fmt:formatNumber value="${balance.budgetCommit}" maxFractionDigits="2"/>"><c:out value="${balance.budgetCommit}"/></span> $
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
		                                        <span data-counter="counterup" data-value="<fmt:formatNumber value="${balance.budgetAsing - balance.budgetCommit }" maxFractionDigits="2"/>"><c:out value="${balance.budgetAsing - balance.budgetCommit}"/></span> $
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
																	<a href="javascript:;" onclick="$('#byDistrictTableHidden').tableExport({type:'excel',escape:'false'});"><spring:message code="application.export.excel"/></a>
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
												<th>Enero</th>
												<th>Febrero</th>
												<th>Marzo</th>
												<th>Abril</th>
												<th>Mayo</th>
												<th>Junio</th>
												<th>Julio</th>
												<th>Agosto</th>
												<th>Septiembre</th>
												<th>Octubre</th>
												<th>Noviembre</th>
												<th>Diciembre</th>
											</tr>
											</thead>
											<tbody>
												<c:forEach items="${balance.entries}" var="entryItem">
													<tr class="odd gradeX">
														<td>${entryItem.stateName}</td>
														<td>${entryItem.districtNumber}</td>
														<td>${entryItem.entryCode}</td>												
														<td><fmt:formatNumber value="${entryItem.january}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.february}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.march}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.april}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.may}" maxFractionDigits="2"/></td>												
														<td><fmt:formatNumber value="${entryItem.june}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.july}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.august}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.september}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.october}" maxFractionDigits="2"/></td>												
														<td><fmt:formatNumber value="${entryItem.november}" maxFractionDigits="2"/></td>
														<td><fmt:formatNumber value="${entryItem.december}" maxFractionDigits="2"/></td>													
													</tr>

												</c:forEach>
											</tbody>
											</table>
										</div>
									</div>
									END EXAMPLE TABLE PORTLET
								</div>
							</div>
			