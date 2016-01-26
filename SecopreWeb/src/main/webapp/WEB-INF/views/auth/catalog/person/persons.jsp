<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Administracion<small> Personas</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Administración</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Personas
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR PERSONAS -->
			<div id="add_Person">
				<%@ include file="/WEB-INF/views/auth/catalog/person/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR PERSONAS -->
			
			<!-- LISTA USUARIOS -->
			<div id="list_Person">
				<%@ include file="/WEB-INF/views/auth/catalog/person/list.jsp"%>
			</div>
			<!-- TERMINA LISTA PERSONAS -->			

	
	