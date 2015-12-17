<div class="profile-content">
	<div id="dashboard2" class="profile-content">
						<div class="row">
							<div class="col-md-12">
								<!-- BEGIN PORTLET -->
								<div class="portlet light ">
									<div class="portlet-title">
										<div class="caption caption-md">
											<i class="icon-bar-chart theme-font hide"></i>
											<span class="caption-subject font-blue-madison bold uppercase">Tus Trámites Recientes</span>
											<span class="caption-helper hide">weekly stats...</span>
										</div>
<!-- 										<div class="actions"> -->
<!-- 											<div data-toggle="buttons" class="btn-group btn-group-devided"> -->
<!-- 												<label class="btn btn-transparent grey-salsa btn-circle btn-sm active"> -->
<!-- 												<input type="radio" id="option1" class="toggle" name="options">Today</label> -->
<!-- 												<label class="btn btn-transparent grey-salsa btn-circle btn-sm"> -->
<!-- 												<input type="radio" id="option2" class="toggle" name="options">Week</label> -->
<!-- 												<label class="btn btn-transparent grey-salsa btn-circle btn-sm"> -->
<!-- 												<input type="radio" id="option2" class="toggle" name="options">Month</label> -->
<!-- 											</div> -->
<!-- 										</div> -->
									</div>
									<div class="portlet-body">
<!-- 										<div class="row number-stats margin-bottom-30"> -->
<!-- 											<div class="col-md-6 col-sm-6 col-xs-6"> -->
<!-- 												<div class="stat-left"> -->
<!-- 													<div class="stat-chart"> -->
<!-- 														do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
<!-- 														<div id="sparkline_bar"><canvas style="display: inline-block; width: 90px; height: 45px; vertical-align: top;" width="90" height="45"></canvas></div> -->
<!-- 													</div> -->
<!-- 													<div class="stat-number"> -->
<!-- 														<div class="title"> -->
<!-- 															 Total -->
<!-- 														</div> -->
<!-- 														<div class="number"> -->
<!-- 															 500 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-md-6 col-sm-6 col-xs-6"> -->
<!-- 												<div class="stat-right"> -->
<!-- 													<div class="stat-chart"> -->
<!-- 														do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
<!-- 														<div id="sparkline_bar2"><canvas style="display: inline-block; width: 90px; height: 45px; vertical-align: top;" width="90" height="45"></canvas></div> -->
<!-- 													</div> -->
<!-- 													<div class="stat-number"> -->
<!-- 														<div class="title"> -->
<!-- 															 New -->
<!-- 														</div> -->
<!-- 														<div class="number"> -->
<!-- 															 719 -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="table-scrollable table-scrollable-borderless">
											<table class="table table-hover table-light">
											<thead>
											<tr class="uppercase">
												<th>
<!-- 												colspan="2" -->
													 FOLIO
												</th>
												<th>
													 TIPO MOVIMIENTO
												</th>
												<th>
													 TRAMITE
												</th>
												<th>
													 ESTATUS
												</th>
												<th>
													 FECHA
												</th>
											</tr>
											</thead>
											
											<tbody>
											<c:forEach items="${createdMovements}" var="createdMovement">
											<tr>
												<td>
													<a class="primary-link" href="javascript:;">  ${createdMovement.folio}</a>
												</td>
												<td>
													 ${createdMovement.movementType}
												</td>
												<td>
													 ${createdMovement.justification}
												</td>
												<td>
													 ${createdMovement.formality}
												</td>
												<td>
													<span class="bold theme-font">${createdMovement.creationDate}</span>
												</td>
											</tr>
											</c:forEach>
											
											
											
											
<!-- 											<tr> -->
<!-- 												<td class="fit"> -->
<!-- 													<img src="../../assets/admin/layout3/img/avatar4.jpg" class="user-pic"> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<a class="primary-link" href="javascript:;">Brain</a> -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 $345 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 45 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													 124 -->
<!-- 												</td> -->
<!-- 												<td> -->
<!-- 													<span class="bold theme-font">80%</span> -->
<!-- 												</td> -->
<!-- 											</tr> -->
											
				
											
											</tbody>
										  </table>
										</div>
									</div>
								</div>
								<!-- END PORTLET -->
							</div>
<!-- 							<div class="col-md-6"> -->
<!-- 								BEGIN PORTLET -->
<!-- 								<div class="portlet light"> -->
<!-- 									<div class="portlet-title tabbable-line"> -->
<!-- 										<div class="caption caption-md"> -->
<!-- 											<i class="icon-globe theme-font hide"></i> -->
<!-- 											<span class="caption-subject font-blue-madison bold uppercase">Feeds</span> -->
<!-- 										</div> -->
<!-- 										<ul class="nav nav-tabs"> -->
<!-- 											<li class="active"> -->
<!-- 												<a data-toggle="tab" href="#tab_1_1"> -->
<!-- 												System </a> -->
<!-- 											</li> -->
<!-- 											<li> -->
<!-- 												<a data-toggle="tab" href="#tab_1_2"> -->
<!-- 												Activities </a> -->
<!-- 											</li> -->
<!-- 										</ul> -->
<!-- 									</div> -->
<!-- 									<div class="portlet-body"> -->
<!-- 										BEGIN TABS -->
<!-- 										<div class="tab-content"> -->
<!-- 											<div id="tab_1_1" class="tab-pane active"> -->
<!-- 												<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 320px;"><div data-handle-color="#D7DCE2" data-rail-visible1="0" data-always-visible="1" style="height: 320px; overflow: hidden; width: auto;" class="scroller" data-initialized="1"> -->
<!-- 													<ul class="feeds"> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 You have 4 pending tasks. <span class="label label-sm label-info"> -->
<!-- 																			Take action <i class="fa fa-share"></i> -->
<!-- 																			</span> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New version v1.4 just lunched! -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 20 mins -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-danger"> -->
<!-- 																			<i class="fa fa-bolt"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Database server #12 overloaded. Please fix the issue. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 24 mins -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-info"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New order received and pending for process. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 30 mins -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New payment refund and pending approval. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 40 mins -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-warning"> -->
<!-- 																			<i class="fa fa-plus"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New member registered. Pending approval. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 1.5 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Web server hardware needs to be upgraded. <span class="label label-sm label-default "> -->
<!-- 																			Overdue </span> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 2 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-default"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Prod01 database server is overloaded 90%. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 3 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-warning"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New group created. Pending manager review. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 5 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-info"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Order payment failed. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 18 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-default"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New application received. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 21 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-info"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Dev90 web server restarted. Pending overall system check. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 22 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-default"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New member registered. Pending approval -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 21 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-info"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 L45 Network failure. Schedule maintenance. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 22 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-default"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Order canceled with failed payment. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 21 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-info"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Web-A2 clound instance created. Schedule full scan. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 22 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-default"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Member canceled. Schedule account review. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 21 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-info"> -->
<!-- 																			<i class="fa fa-bullhorn"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New order received. Please take care of it. -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 22 hours -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 													</ul> -->
<!-- 												</div><div class="slimScrollBar" style="background: rgb(215, 220, 226) none repeat scroll 0% 0%; width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 127.84px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(234, 234, 234) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;"></div></div> -->
<!-- 											</div> -->
<!-- 											<div id="tab_1_2" class="tab-pane"> -->
<!-- 												<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 337px;"><div data-handle-color="#D7DCE2" data-rail-visible1="0" data-always-visible="1" style="height: 337px; overflow: hidden; width: auto;" class="scroller" data-initialized="1"> -->
<!-- 													<ul class="feeds"> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New order received -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 10 mins -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-danger"> -->
<!-- 																			<i class="fa fa-bolt"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 Order #24DOP4 has been rejected. <span class="label label-sm label-danger "> -->
<!-- 																			Take action <i class="fa fa-share"></i> -->
<!-- 																			</span> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 24 mins -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 														<li> -->
<!-- 															<a href="javascript:;"> -->
<!-- 															<div class="col1"> -->
<!-- 																<div class="cont"> -->
<!-- 																	<div class="cont-col1"> -->
<!-- 																		<div class="label label-sm label-success"> -->
<!-- 																			<i class="fa fa-bell-o"></i> -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																	<div class="cont-col2"> -->
<!-- 																		<div class="desc"> -->
<!-- 																			 New user registered -->
<!-- 																		</div> -->
<!-- 																	</div> -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															<div class="col2"> -->
<!-- 																<div class="date"> -->
<!-- 																	 Just now -->
<!-- 																</div> -->
<!-- 															</div> -->
<!-- 															</a> -->
<!-- 														</li> -->
<!-- 													</ul> -->
<!-- 												</div><div class="slimScrollBar" style="background: rgb(215, 220, 226) none repeat scroll 0% 0%; width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(234, 234, 234) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;"></div></div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										END TABS -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								END PORTLET -->
<!-- 							</div> -->
						</div>
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-6"> -->
<!-- 								BEGIN PORTLET -->
<!-- 								<div class="portlet light"> -->
<!-- 									<div class="portlet-title"> -->
<!-- 										<div class="caption caption-md"> -->
<!-- 											<i class="icon-bar-chart theme-font hide"></i> -->
<!-- 											<span class="caption-subject font-blue-madison bold uppercase">Customer Support</span> -->
<!-- 											<span class="caption-helper">45 pending</span> -->
<!-- 										</div> -->
<!-- 										<div class="inputs"> -->
<!-- 											<div class="portlet-input input-inline input-small "> -->
<!-- 												<div class="input-icon right"> -->
<!-- 													<i class="icon-magnifier"></i> -->
<!-- 													<input type="text" placeholder="search..." class="form-control form-control-solid"> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="portlet-body"> -->
<!-- 										<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 305px;"><div data-handle-color="#D7DCE2" data-rail-visible1="0" data-always-visible="1" style="height: 305px; overflow: hidden; width: auto;" class="scroller" data-initialized="1"> -->
<!-- 											<div class="general-item-list"> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar4.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Nick Larson</a> -->
<!-- 															<span class="item-label">3 hrs ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-success"></span> Open</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar3.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Mark</a> -->
<!-- 															<span class="item-label">5 hrs ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-warning"></span> Pending</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat tincidunt ut laoreet. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar6.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Nick Larson</a> -->
<!-- 															<span class="item-label">8 hrs ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-primary"></span> Closed</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar7.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Nick Larson</a> -->
<!-- 															<span class="item-label">12 hrs ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-danger"></span> Pending</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Consectetuer adipiscing elit Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar9.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Richard Stone</a> -->
<!-- 															<span class="item-label">2 days ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-danger"></span> Open</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, ut laoreet dolore magna aliquam erat volutpat. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar8.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Dan</a> -->
<!-- 															<span class="item-label">3 days ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-warning"></span> Pending</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Lorem ipsum dolor sit amet, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 												<div class="item"> -->
<!-- 													<div class="item-head"> -->
<!-- 														<div class="item-details"> -->
<!-- 															<img src="../../assets/admin/layout3/img/avatar2.jpg" class="item-pic"> -->
<!-- 															<a class="item-name primary-link" href="">Larry</a> -->
<!-- 															<span class="item-label">4 hrs ago</span> -->
<!-- 														</div> -->
<!-- 														<span class="item-status"><span class="badge badge-empty badge-success"></span> Open</span> -->
<!-- 													</div> -->
<!-- 													<div class="item-body"> -->
<!-- 														 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div><div class="slimScrollBar" style="background: rgb(215, 220, 226) none repeat scroll 0% 0%; width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 143.336px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(234, 234, 234) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;"></div></div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								END PORTLET -->
<!-- 							</div> -->
<!-- 							<div class="col-md-6"> -->
<!-- 								BEGIN PORTLET -->
<!-- 								<div class="portlet light tasks-widget"> -->
<!-- 									<div class="portlet-title"> -->
<!-- 										<div class="caption caption-md"> -->
<!-- 											<i class="icon-bar-chart theme-font hide"></i> -->
<!-- 											<span class="caption-subject font-blue-madison bold uppercase">Tasks</span> -->
<!-- 											<span class="caption-helper">16 pending</span> -->
<!-- 										</div> -->
<!-- 										<div class="inputs"> -->
<!-- 											<div class="portlet-input input-small input-inline"> -->
<!-- 												<div class="input-icon right"> -->
<!-- 													<i class="icon-magnifier"></i> -->
<!-- 													<input type="text" placeholder="search..." class="form-control form-control-solid"> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="portlet-body"> -->
<!-- 										<div class="task-content"> -->
<!-- 											<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 282px;"><div data-handle-color="#D7DCE2" data-rail-visible1="0" data-always-visible="1" style="height: 282px; overflow: hidden; width: auto;" class="scroller" data-initialized="1"> -->
<!-- 												START TASK LIST -->
<!-- 												<ul class="task-list"> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<input type="hidden" name="test" value="1"> -->
<!-- 															<div class="checker"><span><input type="checkbox" name="test" value="2" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Present 2013 Year IPO Statistics at Board Meeting </span> -->
<!-- 															<span class="label label-sm label-success">Company</span> -->
<!-- 															<span class="task-bell"> -->
<!-- 															<i class="fa fa-bell-o"></i> -->
<!-- 															</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Hold An Interview for Marketing Manager Position </span> -->
<!-- 															<span class="label label-sm label-danger">Marketing</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															AirAsia Intranet System Project Internal Meeting </span> -->
<!-- 															<span class="label label-sm label-success">AirAsia</span> -->
<!-- 															<span class="task-bell"> -->
<!-- 															<i class="fa fa-bell-o"></i> -->
<!-- 															</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Technical Management Meeting </span> -->
<!-- 															<span class="label label-sm label-warning">Company</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Kick-off Company CRM Mobile App Development </span> -->
<!-- 															<span class="label label-sm label-info">Internal Products</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Prepare Commercial Offer For SmartVision Website Rewamp </span> -->
<!-- 															<span class="label label-sm label-danger">SmartVision</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Sign-Off The Comercial Agreement With AutoSmart </span> -->
<!-- 															<span class="label label-sm label-default">AutoSmart</span> -->
<!-- 															<span class="task-bell"> -->
<!-- 															<i class="fa fa-bell-o"></i> -->
<!-- 															</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															Company Staff Meeting </span> -->
<!-- 															<span class="label label-sm label-success">Cruise</span> -->
<!-- 															<span class="task-bell"> -->
<!-- 															<i class="fa fa-bell-o"></i> -->
<!-- 															</span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 													<li class="last-line"> -->
<!-- 														<div class="task-checkbox"> -->
<!-- 															<div class="checker"><span><input type="checkbox" value="" class="liChild"></span></div> -->
<!-- 														</div> -->
<!-- 														<div class="task-title"> -->
<!-- 															<span class="task-title-sp"> -->
<!-- 															KeenThemes Investment Discussion </span> -->
<!-- 															<span class="label label-sm label-warning">KeenThemes </span> -->
<!-- 														</div> -->
<!-- 														<div class="task-config"> -->
<!-- 															<div class="task-config-btn btn-group"> -->
<!-- 																<a data-close-others="true" data-hover="dropdown" data-toggle="dropdown" href="javascript:;" class="btn btn-xs default"> -->
<!-- 																<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i> -->
<!-- 																</a> -->
<!-- 																<ul class="dropdown-menu pull-right"> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-check"></i> Complete </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-pencil"></i> Edit </a> -->
<!-- 																	</li> -->
<!-- 																	<li> -->
<!-- 																		<a href="javascript:;"> -->
<!-- 																		<i class="fa fa-trash-o"></i> Cancel </a> -->
<!-- 																	</li> -->
<!-- 																</ul> -->
<!-- 															</div> -->
<!-- 														</div> -->
<!-- 													</li> -->
<!-- 												</ul> -->
<!-- 												END START TASK LIST -->
<!-- 											</div><div class="slimScrollBar" style="background: rgb(215, 220, 226) none repeat scroll 0% 0%; width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 224.011px;"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(234, 234, 234) none repeat scroll 0% 0%; opacity: 0.2; z-index: 90; right: 1px;"></div></div> -->
<!-- 										</div> -->
<!-- 										<div class="task-footer"> -->
<!-- 											<div class="btn-arrow-link pull-right"> -->
<!-- 												<a href="javascript:;">See All Tasks</a> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								END PORTLET -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
				</div>