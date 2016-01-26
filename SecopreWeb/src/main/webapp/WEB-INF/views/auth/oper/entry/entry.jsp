<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catalogos<small> Partidas</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Catálogos </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					  Partidas
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR PARTIDAS-->
			<div id="add_Entry">
				<%@ include file="/WEB-INF/views/auth/oper/entry/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR PARTIDAS -->
			
			<!-- LISTA PUESTOS -->
			<div id="list_Entry">
				<%@ include file="/WEB-INF/views/auth/oper/entry/list.jsp"%>
			</div>
			<!-- TERMINA LISTA PARTIDAS -->			

	
	