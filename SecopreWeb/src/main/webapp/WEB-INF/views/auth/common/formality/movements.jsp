<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<div class="portlet-body">
	<div class="table-toolbar">
		<div class="row">
			<div class="col-md-6">
				<div class="btn-group">
					<button id="sample_editable_1_new" class="btn green" onclick="void(0);">Agregar Movimiento<i class="fa fa-plus"></i></button>
				</div>
			</div>
		</div>
	</div>
	<table class="table table-striped table-bordered table-hover" id="UserTable">
		<thead>
			<tr>
				<th>Acciones</th>
				<th>Llave Programatica</th>
				<th>Partida</th>
				<!--
				<th>Mes Inicio</th>
				<th>Mes Fin</th>
				-->
				<th>Meses</th>
				<th>Monto Mensual</th>
			</tr>
		</thead>
		<tbody>
			<tr class="odd gradeX" id="movementId">
				<td>
					Acciones
				</td>
				<td>
					<form:select path="movements[0].programaticKeyId" id="programaticKeyId" class="form-control">
						<form:option value="-1" label="Seleccione..."/>
	    				<form:options items="${programaticKeys}" />
					</form:select>
				</td>
				<td>
					<form:select path="movements[0].entryId" id="entryId" class="form-control">
						<form:option value="-1" label="Seleccione..."/>
	    				<form:options items="${entries}" />
					</form:select>
				</td>
				<!--
				<td>
					<form:select path="movements[0].initialMonthId" id="initialMonthId" class="form-control">
						<form:option value="-1" label="Seleccione..."/>
	    				<form:options items="${months}" />
					</form:select>
				</td>
				<td>
					<form:select path="movements[0].finalMonthId" id="finalMonthId" class="form-control">
						<form:option value="-1" label="Seleccione..."/>
	    				<form:options items="${months}" />
					</form:select>
				</td>
				-->
				<td>
					<div id="sliderControl"></div>
					<div class="well margin-top-20">
						<span id="lower-offset"></span>-<span id="upper-offset"></span>
					</div>
				</td>
				<td>
					<form:input path="movements[0].monthAmount" id="monthAmount" class="form-control"/>
				</td>

				<form:hidden path="movements[0].initialMonthId" id="initialMonthId" class="form-control"/>
				<form:hidden path="movements[0].finalMonthId" id="finalMonthId" class="form-control"/>
			</tr>							
		</tbody>
	</table>