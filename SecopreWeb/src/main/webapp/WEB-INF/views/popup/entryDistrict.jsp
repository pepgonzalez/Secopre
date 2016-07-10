
<!DOCTYPE html>
<!-- SPRING TAGS LIBS -->
<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

<!--[if IE 8]> <html lang="es-MX" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="es-MX" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es-MX" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>

	<meta charset="utf-8"/>
	<title><spring:message code="application"/> | <spring:message code="application.title"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Content-Language" content="es-MX" />
	<meta name="title" content="<spring:message code="application.title"/>" /> 
	<meta name="author" content="iDea SW" />
	<meta name="copyright" content="<spring:message code="application"/>" />
	<sec:csrfMetaTags/>
	
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/global/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/simple-line-icons.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/bootstrap.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/uniform.default.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/bootstrap-switch.min.css"/>' rel="stylesheet" type="text/css">
	<!-- END GLOBAL MANDATORY STYLES -->
	
	
	
	<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
	<!-- estilos para dropdowns -->
	<link href='<c:url value="/resources/css/plugins/bootstrap-select.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/resources/css/plugins/select2.css"/>' rel="stylesheet" type="text/css"/>
    
    <link href='<c:url value="/resources/css/plugins/bootstrap-datepicker.min.css"/>' rel="stylesheet" type="text/css"/>
    
	<!-- estilos para gestion de sliders -->
	<link href='<c:url value="/resources/css/global/jquery.nouislider.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/jquery.nouislider.pips.min.css"/>' rel="stylesheet" type="text/css">
	

	<link href='<c:url value="/resources/css/plugins/daterangepicker-bs3.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/fullcalendar.min.css"/>' rel="stylesheet" type="text/css"/>
	
	<link href='<c:url value="/resources/css/plugins/bootstrap-fileinput.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/profile.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/tasks.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/jquery.qtip.min.css"/>' rel="stylesheet" type="text/css"/>
	
	
	
	<!-- END PAGE LEVEL PLUGIN STYLES -->	
	<!-- BEGIN PAGE STYLES -->
	<link href='<c:url value="/resources/css/tasks.css"/>' rel="stylesheet" type="text/css"/>
	<!-- END PAGE STYLES -->	

	<!-- BEGIN THEME STYLES -->
	<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
	<link href='<c:url value="/resources/css/components-md.css"/>' id="style_components" rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/components-rounded.css"/>'  rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins-md.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/layout.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/light.css"/>' rel="stylesheet" type="text/css" id="style_color"/>
	<link href='<c:url value="/resources/css/custom.css"/>' rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
	<!-- BEGIN PAGE CUSTOM STYLES -->
	<link href='<c:url value="/resources/css/plugins/dataTables.bootstrap.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/multi-select.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/toastr.min.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/introjs.min.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/secopre.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/all.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/tooltipster.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/bootstrap-dialog.min.css"/>' rel="stylesheet" type="text/css"/>
	<!-- END PAGE CUSTOM STYLES -->	

	
	<link rel="shortcut icon" href='<c:url value="/resources/img/favicon.ico"/>' type="image/vnd.microsoft.icon"/> 
	

</head>		

<%@ page isELIgnored="false" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	context = "auth"
</script>

<body class="page-md page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">		

	<input type="hidden" name="loggedUserId" id="loggedUserId" value="${loggedUser.id}" />
	<input type="hidden" name="chatModuleActive" id="chatModuleActive" value="${loggedUser.hasChatActive}" />
	<input type="hidden" name="socketURL" id="socketURL" value="${socketURL}" />


	<!-- BEGIN HEADER -->
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">	
		<!-- BEGIN SIDEBAR -->
		<!-- END SIDEBAR -->
		
		
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

			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> Filtro <span class="step-title">de Busqueda</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- formulario -->	
							<form:form action="cfg/entry/search" class="form-horizontal" id="submit_form"  modelAttribute="entryFilter" method="GET"  novalidate="novalidate">
								<div class="row">
									<div class="col-md-6">
										<label class="col-md-2 control-label" for="stateId" style="text-align:left;">Entidad</label>
										<form:select path="stateId" id="stateId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
		 									<form:options items="${entidadList}" />
										</form:select>
									</div>

										
									<div class="col-md-6">
										<label class="col-md-2 control-label" for="districtId" style="text-align:left;">Distrito</label>
										<form:select path="districtId" id="districtId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
										</form:select>
									</div>
																		
								</div>
								<div class="row">
									<div class="col-md-4">
										<label class="col-md-2 control-label" for="entryId" style="text-align:left;">Partida</label>
										<form:select path="entryId" id="entryId" class="form-control">
										   	<form:option value="" label="Seleccione..."/>
										</form:select>
									</div>									
									
									<div class="col-md-3">
										<label for="select2-input-group-append" class="control-label">Meses</label>
										<div class="input-group select2-bootstrap-prepend">
	                                         <span class="input-group-btn">
	                                             <button class="btn btn-default" type="button" data-select2-open="select2-input-group-append">
	                                                 <span class="glyphicon glyphicon-search"></span>
	                                             </button>
	                                         </span>	
	                                         <select id="months" name="months" class="form-control select2-multiple" multiple>
	                                       		 <option value="0">Enero</option>
	                                             <option value="1">Febrero</option>
	                                             <option value="2">Marzo</option>
	                                             <option value="3">Abril</option>
	                                             <option value="4">Mayo</option>
	                                             <option value="5">Junio</option>
	                                             <option value="6">Julio</option>
	                                             <option value="7">Agosto</option>
	                                             <option value="8">Septiembre</option>
	                                             <option value="9">Octubre</option>
	                                             <option value="10">Noviembre</option>
	                                             <option value="11">Diciembre</option>
                                             </select>
										</div>
									</div>
									<div class="col-md-3">
										<label class="col-md-2 control-label" for="entryId" style="text-align:left;">Modificado</label>
										<form:select path="type"  name="type" class="form-control">
										    <form:option value="0" label="No"/>
										   <form:option value="1" label="Si"/>
										</form:select>
									</div>
									<div class="col-md-2">
										<a class="btn green" id="submitRequestFormFilter" onclick="submitAjaxJQPopup('submit_form', 'list_ByDistrict', 'initEntryByDistrict()');">Consultar</a>
									</div>									
									
									<div class="row">
									</div>
								</div>					
							 </form:form>
						</div>
													
					</div>			
				</div>
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
		
		
		
		
		
		
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->	
	<%@ include file="/WEB-INF/views/auth/common/footer.jsp"%>
	<!-- END FOOTER -->		
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
	<script src='<c:url value="/resources/js/plugins/respond.min.js"/>'></script>
	<script src='<c:url value="/resources/js/plugins/excanvas.min.js"/>'></script> 
	<![endif]-->
	<script src='<c:url value="/resources/js/global/jquery.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/jquery-migrate.min.js"/>' type="text/javascript"></script>
   
	
	<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src='<c:url value="/resources/js/plugins/jquery-ui.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/bootstrap.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.bootstrap.wizard.min.js"/>' type="text/javascript"></script>
	
<%-- 	<script src='<c:url value="/resources/js/plugins/icheck.min.js"/>' type="text/javascript"></script> --%>
	
	<script src='<c:url value="/resources/js/plugins/bootstrap-hover-dropdown.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.slimscroll.min.js"/>'type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.blockui.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.cokie.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.uniform.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/bootstrap-switch.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/plugins/bootstrap-datepicker.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/plugins/bootstrap-datepicker.es.js"/>' type="text/javascript"></script>
    
    
	
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag anddrop support -->
	<script src='<c:url value="/resources/js/plugins/raphael-min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/bootstrap-fileinput.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/plugins/jquery.sparkline.min.js"/>' type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src='<c:url value="/resources/js/global/ui-blockui.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/metronic.js"/>' type="text/javascript"></script>
	
	<!--plugin de js para slider-->
	<script src='<c:url value="/resources/js/global/jquery.nouislider.all.js"/>' type="text/javascript"></script>
	 	 <!-- scripts para chat -->
	 <script src='<c:url value="/resources/js/global/layout4.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/demo4.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/quick-sidebar2.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/index3.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/tasks.js"/>' type="text/javascript"></script>
	
    <script src='<c:url value="/resources/js/global/profile.js"/>' type="text/javascript"></script>
 	<script src='<c:url value="/resources/js/plugins/bootstrap-select.min.js"/>' type="text/javascript"></script> 
  	<script src='<c:url value="/resources/js/plugins/select2.min.js"/>' type="text/javascript"></script> 
	<script src='<c:url value="/resources/js/plugins/jquery.multi-select.js"/>' type="text/javascript"></script>		
	<script src='<c:url value="/resources/js/plugins/components-dropdowns.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/ui-alert-dialog-api.js"/>' type="text/javascript"></script>
	
	 
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL CUSTOM SCRIPTS -->
	<script src='<c:url value="/resources/js/plugins/jquery.validate.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/plugins/messages_es.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/additional-methods.min.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/plugins/jquery.dataTables.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/dataTables.bootstrap.js"/>' type="text/javascript"></script>
	
	<script src='<c:url value="/resources/js/plugins/intro.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/toastr.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/bootbox.min.js"/>' type="text/javascript"></script>		
	<script src='<c:url value="/resources/js/plugins/jquery.formatCurrency-1.4.0.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.tooltipster.min.js"/>' type="text/javascript"></script>		
	<script src='<c:url value="/resources/js/plugins/jquery.counterup.min.js"/>' type="text/javascript"></script>		
	<script src='<c:url value="/resources/js/plugins/jquery.waypoints.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.qtip.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/plugins/bootstrap-dialog.min.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/resources/js/plugins/ui-modals.min.js"/>' type="text/javascript"></script>

	<script src='<c:url value="/resources/js/utils/secopreUtils.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/utils/secopre.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/demo/table-managed.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/modules/movementController.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/modules/movementController2.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/modules/expenseController.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/modules/admin.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/modules/catalog.js"/>' type="text/javascript"></script>	
	
	<!-- Scripts para exportar -->
	<script src='<c:url value="/resources/js/export/tableExport.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/export/jquery.base64.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/export/jspdf/libs/sprintf.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/export/jspdf/jspdf.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/export/jspdf/libs/base64.js"/>' type="text/javascript"></script>	

	<!-- END PAGE LEVEL CUSTOM SCRIPTS -->
	
	<!-- scripts para modulo de chat -->
	<script src="http://www.tribunalesagrarios.gob.mx:3000/socket.io/socket.io.js" type="text/javascript"></script>
	<script src='<c:url value="/resources/js/utils/secopreChatModule.js"/>' type="text/javascript"></script>


	<script>
		jQuery(document).ready(function() {
				
		   Metronic.init(); // init metronic core componets
		   Layout.init(); // init layout
		   UIBlockUI.init(); //block ui
		   QuickSidebar.init(); // init quick sidebar
		   Index.init(); // init index page
		   Tasks.initDashboardWidget(); // init tash dashboard widget  

		   /*
	         $.getJSON("oper/notice/getNotice",{}, function(j){
	              var json = eval(j);
	              console.log("NOTICE ===>", json);
	              console.log("EVAL =====>", eval(json != null));
				  if(json != null ){
		              BootstrapDialog.show({
		            		title: 'Notificaciones Secopre',
		            	    message: json.noticeInfo,
		            	    size: BootstrapDialog.SIZE_WIDE,
		            	    draggable: true,
		            	    buttons: [{
		            	        id: 'btn-ok',   
		            	        icon: 'glyphicon glyphicon-check',       
		            	        label: 'Cerrar',
		            	        cssClass: 'btn-primary', 
		            	        autospin: false,
		            	        action: function(dialogRef){    
		            	            dialogRef.close();
		            	        }
		            	    }]
		            	});
				  }
	            });
		   */
		   
		   function initPopupEntryByDistrict(){
				$('select').select2();
			    $("[data-counter='counterup']").counterUp({
			        delay: 10,
			        time: 1000
			    });
			 
				$('#byDistrictTable').DataTable({		
					 "language": {
				            "lengthMenu": "_MENU_ Registros por pagina",
				            "zeroRecords": "No existen registros",
				            "info": "Mostrando pagina _PAGE_ de _PAGES_",
				            "infoEmpty": "No hay registros disponibles",
				            "infoFiltered": "(filtered from _MAX_ total records)"
				        }
				
				});
				
				var url1 = "http://" + document.location.host + "/Secopre/" + context + "/cfg/entry/getDistricts";
				
				$("select#stateId").change(function(){
					blockPage();
					 $("select#districtId").html('');
					 $("select#entryId").html('');
			         $.getJSON(url1, {stateId: $(this).val()}, function(j){
			              var options = '<option value="">Seleccione... </option>';
			              var json = eval(j);
			              $.each(json, function(key, value) {
			            	  options += '<option value="' + key + '">' + value + '</option>';
			              });        
			   			  unblockPage();            
			              $("select#districtId").html(options);
			            });
			     });	
				
				var url2 = "http://" + document.location.host + "/Secopre/" + context + "/cfg/entry/getEntries2";
				
				$("select#districtId").change(function(){
					$("select#entryId").html('');
					blockPage();
			         $.getJSON(url2, {districtId: $(this).val()}, function(j){
			        	  console.log(j);
			              var options = '<option value="">Seleccione... </option>';
			             
			              var json = eval(j);
			              $.each(json, function(index, element) {
			            	  options += '<option value="' + element.id + '">' + element.codeAndDescription + '</option>';
			              });
			              
			              
			   			  unblockPage();            
			              $("select#entryId").html(options);
			            });
			     });	
			}
		  		   
		   initPopupEntryByDistrict();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->

</html>	
