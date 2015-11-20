<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catalogos<small> Partidas</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Administración </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					  Partidas Por Distrito
				</li>
			</ul>

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
									<div class="col-md-6">
										<label class="col-md-2 control-label" for="districtId" style="text-align:left;">Entidad</label>
										<form:select path="stateId" id="stateId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
		 									<form:options items="${entidadList}" />
										</form:select>
									</div>

										
									<div class="col-md-6">
										<label class="col-md-2 control-label" for="districtId" style="text-align:left;">Distrito</label>
										<form:select path="districtId" id="districtId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
										</form:select>
									</div>
																		
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class="col-md-2 control-label" for="entryId" style="text-align:left;">Partida</label>
										<form:select path="entryId" id="entryId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
										</form:select>
									</div>									
									
									<div class="col-md-4">
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
									<div class="col-md-2">
										<button type="button" class="btn green" id="submitRequestForm" onclick="submitAjaxJQ('submit_form', 'list_ByDistrict', 'initEntryByDistrict()');">Consultar</button>
									</div>
									
									<div class="row">
									</div>
								</div>					
							 </form:form>
						</div>
													
					</div>			
				</div>
			</div>
			<div id="list_ByDistrict">
				<%@ include file="/WEB-INF/views/auth/admin/config/entry/byDistrict.jsp"%>
			</div>

			<div class="portlet-body">
				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-3">
							<div class="btn-group">
								<button id="sample_editable_1_new" class="btn green"
									onclick="openResourceNative('report/download/18','dashboard','()','GET');">
									Obtener plantilla de Carga</button>
							</div>
						</div>
						<div class="col-md-3">
							<div class="btn-group">
								<button id="sample_editable_1_new" class="btn green"
									onclick="alert('Carga de archivos');">Cargar
									Presupuesto anual</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE FORM PORTLET-->
				<script>

			$(document).ready(function() {

										
			 });	
					
			</script>