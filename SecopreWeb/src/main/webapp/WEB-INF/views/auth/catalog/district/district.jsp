<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catalogos<small> Distritos</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Catálogos </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Distritos
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR PERSONAS -->
			<div id="add_District">
				<%@ include file="/WEB-INF/views/auth/catalog/district/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR PERSONAS -->
			
			<!-- LISTA USUARIOS -->
			<div id="list_District">
				<%@ include file="/WEB-INF/views/auth/catalog/district/list.jsp"%>
			</div>
			<!-- TERMINA LISTA PERSONAS -->			

	
	