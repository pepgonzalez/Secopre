<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catálogos<small>  Claves Programáticas</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Catálogos </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Claves Programáticas
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR PUESTOS-->
			<div id="add_ProgrammaticKey">
				<%@ include file="/WEB-INF/views/auth/oper/pk/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR PERSONAS -->
			
			<!-- LISTA PUESTOS -->
			<div id="list_ProgrammaticKey">
				<%@ include file="/WEB-INF/views/auth/oper/pk/list.jsp"%>
			</div>
			<!-- TERMINA LISTA PUESTOS -->			

	
	