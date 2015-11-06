<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Administracion<small>Roles</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Administracion</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Roles
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR ROLES -->
			<div id="add_Role">
				<%@ include file="/WEB-INF/views/auth/admin/config/role/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR ROLES -->
			
			<!-- LISTA ROLES -->
			<div id="list_Role">
				<%@ include file="/WEB-INF/views/auth/admin/config/role/list.jsp"%>
			</div>
			<!-- TERMINA LISTA ROLES -->			

	
	