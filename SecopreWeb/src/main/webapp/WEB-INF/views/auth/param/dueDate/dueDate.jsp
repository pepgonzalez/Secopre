<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Parametros<small>  Fechas de Corte</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:initDueDateList();">Parámetros</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Fechas de Corte
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR FECHAS DE CORTE-->
			<div id="add_DueDate">
				<%@ include file="/WEB-INF/views/auth/param/dueDate/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO FECHAS DE CORTE -->
			
			<!-- LISTA FECHAS DE CORTE -->
			<div id="list_DueDate">
				<%@ include file="/WEB-INF/views/auth/param/dueDate/list.jsp"%>
			</div>
			<!-- TERMINA LISTA FECHAS DE CORTE -->			

	
	