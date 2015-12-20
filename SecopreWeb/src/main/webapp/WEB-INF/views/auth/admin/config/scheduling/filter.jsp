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
									<div class="col-md-6">
									</div>
									<div class="col-md-6">
									    <div class="btn-group">
											<a id="shcedulingAction" class="btn blue">Calendarizar</a>
										</div>
									</div>
								</div>				
							 </form:form>
						</div>
													
					</div>			
				</div>
			</div>
