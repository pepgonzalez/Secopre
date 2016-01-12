<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
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
										<label class="col-md-2 control-label" for="stateId" style="text-align:left;">Entidad</label>
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
									<div class="col-md-4">
										<label class="col-md-2 control-label" for="entryId" style="text-align:left;">Partida</label>
										<form:select path="entryId" id="entryId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
										</form:select>
									</div>									
									
									<div class="col-md-3">
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
									<div class="col-md-3">
										<label class="col-md-2 control-label" for="entryId" style="text-align:left;">Modificado</label>
										<form:select path="type"  name="type" class="form-control">
										    <form:option value="0" label="No"/>
										   <form:option value="1" label="Si"/>
										</form:select>
									</div>
									<div class="col-md-2">
										<a class="btn green" id="submitRequestFormFilter" onclick="submitAjaxJQ('submit_form', 'list_ByDistrict', 'initEntryByDistrict()');">Consultar</a>
									</div>									
									
									<div class="row">
									</div>
								</div>					
							 </form:form>
						</div>
													
					</div>			
				</div>
			</div>
