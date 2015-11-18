
<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

<div class="row" style="display: inline;">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE FORM PORTLET-->
		<div class="portlet light">
			<div class="portlet-title">
				<div class="caption font-green-haze">
					<i class="icon-settings font-green-haze"></i> <span
						class="caption-subject bold uppercase">Presupuesto Anual de
						Partidas por distrito</span>
				</div>
				<div class="actions">
					<a class="btn btn-circle btn-icon-only btn-default fullscreen"
						href="javascript:;" data-original-title="" title=""> </a>
				</div>
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
		</div>
	</div>
</div>