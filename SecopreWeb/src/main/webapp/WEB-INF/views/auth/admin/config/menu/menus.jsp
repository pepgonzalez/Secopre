<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catalogos<small> Menu</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Administración </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Menus
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR MENUS-->
			<div id="add_Menu">
				<%@ include file="/WEB-INF/views/auth/admin/config/menu/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR MENUS -->
			
			<!-- LISTA PUESTOS -->
			<div id="list_Menu">
				<%@ include file="/WEB-INF/views/auth/admin/config/menu/list.jsp"%>
			</div>
			<!-- TERMINA LISTA MENUS -->			

	
	