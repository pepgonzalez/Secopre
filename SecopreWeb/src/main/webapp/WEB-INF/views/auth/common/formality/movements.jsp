<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="portlet box green" id="addComponent">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>Ampliación Líquida
		</div>
		<div class="actions">
			<a href="javascript:;" class="btn green btn-sm"><i class="fa fa-plus"></i>Agregar Movimiento </a>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Llave Programatica</th>
						<th>Partida</th>
						<th>Rango meses</th>
						<th></th>
						<th>Monto Mensual</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<a href="javascript:;" class="btn default btn-xs red"><i class="fa fa-times"></i></a>
						</td>
						<td>
							<form:select path="movements[0].programaticKeyId" id="programaticKeyId" class="form-control input-small">
								<form:option value="-1" label="Seleccione..."/>
			    				<form:options items="${programaticKeys}" />
							</form:select>
						</td>
						<td>
							<form:select path="movements[0].entryId" id="entryId" class="form-control input-medium">
								<form:option value="-1" label="Seleccione..."/>
			    				<form:options items="${entries}" />
							</form:select>
						</td>
						<td>
							<div class="input-small" style="padding-top:8px;">
								<div id="sliderControl"></div>
							</div>
						</td>
						<td>
							<div class="input-xsmall" style="padding-top:2px;">
								<span id="lower-offset"></span>-<span id="upper-offset"></span>
							</div>
						<td>
							<form:input path="movements[0].monthAmount" id="monthAmount" class="form-control input-small"/>
						</td>
		
						<form:hidden path="movements[0].initialMonthId" id="initialMonthId" class="form-control"/>
						<form:hidden path="movements[0].finalMonthId" id="finalMonthId" class="form-control"/>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<div class="text-rigth">
				<div class="btn">Total:</div><a href="#myModal1" role="button" class="btn green" data-toggle="modal">$ 0.00</a>
			</div>
		</div>
	</div>
</div>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="portlet box green" id="substractComponent">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-cogs"></i>Reducción Líquida
		</div>
		<div class="actions">
			<a href="javascript:;" class="btn green btn-sm"><i class="fa fa-plus"></i>Agregar Movimiento </a>
		</div>
	</div>
	<div class="portlet-body">
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Llave Programatica</th>
						<th>Partida</th>
						<th>Rango meses</th>
						<th></th>
						<th>Monto Mensual</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<a href="javascript:;" class="btn default btn-xs red"><i class="fa fa-times"></i></a>
						</td>
						<td>
							<form:select path="movements[0].programaticKeyId" id="programaticKeyId" class="form-control input-small">
								<form:option value="-1" label="Seleccione..."/>
			    				<form:options items="${programaticKeys}" />
							</form:select>
						</td>
						<td>
							<form:select path="movements[0].entryId" id="entryId" class="form-control input-medium">
								<form:option value="-1" label="Seleccione..."/>
			    				<form:options items="${entries}" />
							</form:select>
						</td>
						<td>
							<div class="input-small" style="padding-top:8px;">
								<div id="sliderControl"></div>
							</div>
						</td>
						<td>
							<div class="input-xsmall" style="padding-top:2px;">
								<span id="lower-offset"></span>-<span id="upper-offset"></span>
							</div>
						<td>
							<form:input path="movements[0].monthAmount" id="monthAmount" class="form-control input-small"/>
						</td>
		
						<form:hidden path="movements[0].initialMonthId" id="initialMonthId" class="form-control"/>
						<form:hidden path="movements[0].finalMonthId" id="finalMonthId" class="form-control"/>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<div class="text-rigth">
				<div class="btn">Total:</div><a href="#myModal1" role="button" class="btn green" data-toggle="modal">$ 0.00</a>
			</div>
		</div>
	</div>
</div>