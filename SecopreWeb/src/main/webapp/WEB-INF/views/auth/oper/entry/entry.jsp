<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Catalogos<small>Ingresos</small></h1>
				</div>
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Catalogos</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Ingresos
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR PUESTOS-->
			<div id="add_Position">
				<%@ include file="/WEB-INF/views/auth/oper/entry/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR PERSONAS -->
			
			<!-- LISTA PUESTOS -->
			<div id="list_Position">
				<%@ include file="/WEB-INF/views/auth/oper/entry/list.jsp"%>
			</div>
			<!-- TERMINA LISTA PUESTOS -->			

	
	