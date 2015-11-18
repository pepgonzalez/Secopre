<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

			<!-- BEGIN FILTER CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> Filtro <span class="step-title">de Busqueda</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form action="auth/cfg/entry/search" class="form-horizontal" id="submit_form"  modelAttribute="entryFilter" method="GET"  novalidate="novalidate">
								<div class="row">		
									<div class="col-md-5">
										<label class="col-md-2 control-label" for="districtId" style="text-align:left;">Distrito ${balance.budgetAsing}</label>
										<form:select path="districtId" id="districtId" class="form-control">
										   	<form:option value="-1" label="Seleccione..."/>
		 									<form:options items="${districtList}" />
										</form:select>
									</div>
									
									
									<div class="col-md-4">
										<label class="col-md-2 control-label" for="entryId" style="text-align:left;">Partida</label>
										<form:select path="entryId" id="entryId" class="form-control">
										   	<form:option value="-1" label="Seleccione..."/>
										</form:select>
									</div>									
								</div>
								<div class="row">
									<div class="col-md-5">
										<label for="select2-input-group-append" class="control-label">Meses</label>
										<div class="input-group select2-bootstrap-prepend">
	                                         <span class="input-group-btn">
	                                             <button class="btn btn-default" type="button" data-select2-open="select2-input-group-append">
	                                                 <span class="glyphicon glyphicon-search"></span>
	                                             </button>
	                                         </span>	
	                                         <select id="months" name="months" class="form-control select2-multiple" multiple>
	                                       		 <option value="0">Enero</option>
	                                             <option value="1">Febrero</option>
	                                             <option value="2">Marzo</option>
	                                             <option value="3">Abril</option>
	                                             <option value="4">Mayo</option>
	                                             <option value="5">Junio</option>
	                                             <option value="6">Julio</option>
	                                             <option value="7">Agosto</option>
	                                             <option value="8">Septiembre</option>
	                                             <option value="9">Octubre</option>
	                                             <option value="10">Noviembre</option>
	                                             <option value="11">Diciembre</option>
                                             </select>
										</div>
									</div>

							<div class="row">
								<div class="col-md-offset-9 col-md-6">
									<button type="button" class="btn green" id="submitRequestForm" onclick="submitAjaxJQ('submit_form', 'workarea', 'initEntryByDistrict()');">Consultar</button>
								</div>
							</div>
							<div class="clearfix"></div>
								</div>					
							 </form:form>
						</div>
													
					</div>			
				</div>
			</div>
		                    <!-- BEGIN DASHBOARD STATS 1-->
		                    <div class="row">
		                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		                            <div class="dashboard-stat blue">
		                                <div class="visual">
		                                    <i class="fa fa-comments"></i>
		                                </div>
		                                <div class="details">
		                                    <div class="number">
		                                        <span data-counter="counterup" data-value="${balance.annualAmount}">0</span> $
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
		                                        <span data-counter="counterup" data-value="${balance.budgetAmount}">0</span> $
		                                    	<div class="desc">Presupuesto</div>
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
		                                        <span data-counter="counterup" data-value="${balance.budgetCommit}">${balance.budgetCommit}</span> $
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
		                                        <span data-counter="counterup" data-value="${balance.budgetAsing}">${balance.budgetAsing}</span> $
		                                    	<div class="desc"> Total Aplicado</div>
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
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					BEGIN EXAMPLE TABLE PORTLET -->
<!-- 					<div class="portlet box grey-cascade"> -->
<!-- 						<div class="portlet-title"> -->
<!-- 							<div class="caption"> -->
<%-- 								<i class="fa fa-globe"></i><spring:message code="application.pages.catalog.entry.table.title"/> --%>
<!-- 							</div> -->
<!-- 							<div class="tools"> -->
<!-- 								<a href="javascript:;" class="collapse"> -->
<!-- 								</a> -->
<!-- 								<a href="javascript:;" class="reload"> -->
<!-- 								</a> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="portlet-body"> -->
<!-- 							<div class="table-toolbar"> -->
<!-- 								<div class="row"> -->
<!-- 									<div class="col-md-6"> -->
<!-- 										<div class="btn-group"> -->
<!-- 											<button id="sample_editable_1_new" class="btn green"> -->
<%-- 											<spring:message code="application.add"/> <i class="fa fa-plus"></i> --%>
<!-- 											</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="col-md-6"> -->
<!-- 										<div class="btn-group pull-right"> -->
<%-- 											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="application.tools"/><i class="fa fa-angle-down"></i> --%>
<!-- 											</button> -->
<!-- 											<ul class="dropdown-menu pull-right"> -->
<!-- 												<li> -->
<%-- 													<a href="javascript:;"><spring:message code="application.print"/></a> --%>
<!-- 												</li> -->
<!-- 												<li> -->
<%-- 													<a href="javascript:;"><spring:message code="application.export.pdf"/></a> --%>
<!-- 												</li> -->
<!-- 												<li> -->
<%-- 													<a href="javascript:;"><spring:message code="application.export.excel"/></a> --%>
<!-- 												</li> -->
<!-- 											</ul> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<table class="table table-striped table-bordered table-hover" id="EntryTable"> -->
<!-- 							<thead> -->
<!-- 							<tr> -->
<!-- 								<th class="table-checkbox"> -->
<!-- 									<input type="checkbox" class="group-checkable" data-set="#entryTable .checkboxes"/> -->
<!-- 								</th> -->
<%-- 								<th><spring:message code="application.pages.catalog.entry.code"/></th> --%>
<%-- 								<th><spring:message code="application.pages.catalog.entry.name"/></th> --%>
<%-- 								<th><spring:message code="application.pages.catalog.entry.description"/></th> --%>
<%-- 								<th><spring:message code="application.active"/></th> --%>
<%-- 								<th><spring:message code="application.actions"/></th> --%>
<!-- 							</tr> -->
<!-- 							</thead> -->
<!-- 							<tbody> -->

<!-- 							</tbody> -->
<!-- 							</table> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					END EXAMPLE TABLE PORTLET -->
<!-- 				</div> -->
<!-- 			</div> -->

				<script>

			$(document).ready(function() {

										
			 });	
					
			</script>