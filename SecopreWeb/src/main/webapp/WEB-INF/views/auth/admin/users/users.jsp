<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Administracion<small>Usuarios</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Administración</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Usuarios
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR USUARIOS -->
			<div id="add_User">
				<%@ include file="/WEB-INF/views/auth/admin/users/add.jsp"%>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR USUARIOS -->
			
			<!-- LISTA USUARIOS -->
			<div id="list_User">
				<%@ include file="/WEB-INF/views/auth/admin/users/list.jsp"%>
			</div>
			<!-- TERMINA LISTA USUARIOS -->			

	
	