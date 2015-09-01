<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Administracion<small>Permisos</small></h1>
				</div>
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Administracion</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Permisos
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR ROLES -->
			<div id="add_Perm">
				<%@ include file="/WEB-INF/views/auth/admin/config/perm/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR ROLES -->
			
			<!-- LISTA ROLES -->
			<div id="list_Perm">
				<%@ include file="/WEB-INF/views/auth/admin/config/perm/list.jsp"%>
			</div>
			<!-- TERMINA LISTA ROLES -->			

	
	