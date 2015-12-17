<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catalogos<small> Avisos</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Catalogos</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					  Avisos
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->

			<!-- FORMULARIO AGREGAR AVISOS-->
			<div id="add_Notice">
				<!-- 
<%-- 				<%@ include file="/WEB-INF/views/auth/oper/notice/add.jsp" flush="true"%> --%>
				-->
				<jsp:include page="/WEB-INF/views/auth/oper/notice/add.jsp" flush="true"/>
			</div>
			<!-- TERMINA FORMULARIO AGREGAR AVISOS -->
			
			<!-- LISTA AVISOS -->
			<div id="list_Notice">
<%-- 				<%@ include file="/WEB-INF/views/auth/oper/notice/list.jsp"%> --%>
				<jsp:include page="/WEB-INF/views/auth/oper/notice/list.jsp" flush="true"/>
			</div>
			<!-- TERMINA LISTA AVISOS -->			

	
	