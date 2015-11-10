<!DOCTYPE html>
<!-- SPRING TAGS LIBS -->
<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

<html lang="es-MX" class="no-js">	

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
	<link href='<c:url value="/resources/css/plugins/morris.css"/>' rel="stylesheet" type="text/css"/>
	
	<link href='<c:url value="/resources/css/plugins/bootstrap-fileinput.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/profile.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins/tasks.css"/>' rel="stylesheet" type="text/css"/>
	
	
	
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
	<!-- END PAGE CUSTOM STYLES -->	
	<link rel="shortcut icon" href='<c:url value="/resources/img/favicon.ico"/>' type="image/vnd.microsoft.icon"/> 

</head>			
			
<body>	
<div class="profile-container">	
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Mi Perfil<small> Mi Cuenta</small></h1>
				</div>
			</div>
			<!-- END PAGE HEAD -->
			
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="index.html">Home</a>
					<i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="#">Mi Perfil</a>
				</li>
			</ul>
	
	<!-- END HEADER -->

			
			<div class="row">
			<div class="col-md-12">
		       <%@ include file="/WEB-INF/views/auth/admin/profile/menuProfile.jsp"%>
		       <%@ include file="/WEB-INF/views/auth/admin/profile/profileContent.jsp"%>
            </div>
           </div>
           </div>

<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
Demo.init(); // init demo features\
Profile.init(); // init page demo
});
</script>
</body>
<!-- END BODY -->
</html>		