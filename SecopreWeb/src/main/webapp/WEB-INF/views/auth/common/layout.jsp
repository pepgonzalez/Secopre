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
	<link href='<c:url value="/resources/css/plugins/daterangepicker-bs3.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/fullcalendar.min.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/morris.css"/>' rel="stylesheet" type="text/css"/>
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
	<link href='<c:url value="/resources/css/plugins/introjs.min.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/secopre.css"/>' rel="stylesheet" type="text/css"/>
	<!-- END PAGE CUSTOM STYLES -->	
	<link rel="shortcut icon" href='<c:url value="/resources/img/favicon.ico"/>' type="image/vnd.microsoft.icon"/> 

</head>		
<c:set var="context" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
	var context = "${context}";
</script>

<body class="page-md page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">		

	<input type="hidden" name="loggedUserId" id="loggedUserId" value="${loggedUser.id}" />
	<input type="hidden" name="chatModuleActive" id="chatModuleActive" value="${loggedUser.hasChatActive}" />


	<!-- BEGIN HEADER -->
	<%@ include file="/WEB-INF/views/auth/common/header.jsp"%>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">	
		<!-- BEGIN SIDEBAR -->
		<%@ include file="/WEB-INF/views/auth/common/menu.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<%@ include file="/WEB-INF/views/auth/common/dashboard.jsp"%>		
		<!-- END CONTENT -->
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
	
	<script src='<c:url value="/resources/js/plugins/bootstrap-hover-dropdown.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.slimscroll.min.js"/>'type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.blockui.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.cokie.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.uniform.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/bootstrap-switch.min.js"/>' type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag anddrop support -->
	<script src='<c:url value="/resources/js/plugins/morris.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/raphael-min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/jquery.sparkline.min.js"/>' type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src='<c:url value="/resources/js/global/ui-blockui.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/metronic.js"/>' type="text/javascript"></script>
	
	<!-- test
	<script src='<c:url value="/resources/js/global/layout.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/index3.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/tasks.js"/>' type="text/javascript"></script>
	 -->
	 
	 <!-- scripts de mapas -->
	 <script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
	 <script src='<c:url value="/resources/js/plugins/gmaps.min.js"/>' type="text/javascript"></script>
	 
	 
	 <!-- scripts para chat -->
	 <script src='<c:url value="/resources/js/global/layout4.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/demo4.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/quick-sidebar2.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/index3.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/tasks.js"/>' type="text/javascript"></script>
	 
	<script src='<c:url value="/resources/js/global/maps-google.js"/>'  type="text/javascript"></script>
	 
	 
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN PAGE LEVEL CUSTOM SCRIPTS -->
	<script src='<c:url value="/resources/js/plugins/jquery.validate.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/additional-methods.min.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/plugins/jquery.dataTables.min.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/dataTables.bootstrap.js"/>' type="text/javascript"></script>		
	<script src='<c:url value="/resources/js/plugins/intro.min.js"/>' type="text/javascript"></script>
	
	<script src='<c:url value="/resources/js/utils/secopreUtils.js"/>' type="text/javascript"></script>
	
	<script src='<c:url value="/resources/js/utils/secopre.js"/>' type="text/javascript"></script>
	<script src='<c:url value="/resources/js/demo/table-managed.js"/>' type="text/javascript"></script>	
	<script src='<c:url value="/resources/js/modules/users.js"/>' type="text/javascript"></script>	
	
	<!-- END PAGE LEVEL CUSTOM SCRIPTS -->
	
	<!-- scripts para modulo de chat -->
	<script src="http://localhost:3000/socket.io/socket.io.js" type="text/javascript"></script>
	<script src='<c:url value="/resources/js/utils/secopreChatModule.js"/>' type="text/javascript"></script>
	
	
	<script>
		jQuery(document).ready(function() {
		   Metronic.init(); // init metronic core componets
		   Layout.init(); // init layout
		   Demo.init(); // init demo features
		   UIBlockUI.init(); //block ui
		   QuickSidebar.init(); // init quick sidebar
		   Index.init(); // init index page
		   Tasks.initDashboardWidget(); // init tash dashboard widget  
		   MapsGoogle.init();		
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>	
		
		
		