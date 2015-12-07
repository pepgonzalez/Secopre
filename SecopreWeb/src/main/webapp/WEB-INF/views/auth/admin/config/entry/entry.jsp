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
					<a href="javascript:;">Administración </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					  Partidas Por Distrito
				</li>
			</ul>

				<!-- BEGIN FILTER CONTENT-->
			<div id="filter_ByDistrict">
				<%@ include file="/WEB-INF/views/auth/admin/config/entry/filter.jsp"%>
			</div>

			<div id="list_ByDistrict">
				<%@ include file="/WEB-INF/views/auth/admin/config/entry/byDistrict.jsp"%>
			</div>

			<!-- END SAMPLE FORM PORTLET-->

<script type="text/javascript">
<!--
$( document ).ready(function() {
	$('#submitRequestForm').click(function(e){
		submitAjaxJQ('submit_form', 'list_ByDistrict', 'initEntryByDistrict()');
	});	
});
//-->
</script>			