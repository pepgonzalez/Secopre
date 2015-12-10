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
				   Parámetros<i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 <a href="javascript:initDirectorList();">Parámetros de Impresión</a>
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR FECHAS DE CORTE-->
			<div id="add_Director">
				<%@ include file="/WEB-INF/views/auth/param/director/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO FECHAS DE CORTE -->
			
			<!-- LISTA FECHAS DE CORTE -->
			<div id="list_Director">
				<%@ include file="/WEB-INF/views/auth/param/director/list.jsp"%>
			</div>
			<!-- TERMINA LISTA FECHAS DE CORTE -->			

	
	