<!DOCTYPE html>
<!-- SPRING TAGS LIBS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!--[if IE 8]> <html lang="es-MX" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="es-MX" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es-MX" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title><spring:message code="application.title" /> | <spring:message
		code="application.pages.403" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Language" content="es-MX" />
<meta name="title" content="<spring:message code="application.title"/>" />
<meta name="author" content="iDea SW" />
<meta name="copyright" content="<spring:message code="application"/>" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css" />
<link href='<c:url value="/resources/css/global/font-awesome.min.css"/>'
	rel="stylesheet" type="text/css">
<link
	href='<c:url value="/resources/css/global/simple-line-icons.min.css"/>'
	rel="stylesheet" type="text/css">
<link href='<c:url value="/resources/css/global/bootstrap.min.css"/>'
	rel="stylesheet" type="text/css">
<link href='<c:url value="/resources/css/global/uniform.default.css"/>'
	rel="stylesheet" type="text/css">
<link
	href='<c:url value="/resources/css/global/bootstrap-switch.min.css"/>'
	rel="stylesheet" type="text/css">
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href='<c:url value="/resources/css/plugins/coming-soon.css"/>'
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href='<c:url value="/resources/css/components-md.css"/>'
	id="style_components" rel="stylesheet" type="text/css" />
<link href='<c:url value="/resources/css/plugins-md.css"/>'
	rel="stylesheet" type="text/css" />
<link href='<c:url value="/resources/css/layout.css"/>' rel="stylesheet"
	type="text/css" />
<link href='<c:url value="/resources/css/light.css"/>' rel="stylesheet"
	type="text/css" id="style_color" />
<link href='<c:url value="/resources/css/custom.css"/>' rel="stylesheet"
	type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon"
	href='<c:url value="/resources/img/favicon.ico"/>'
	type="image/vnd.microsoft.icon" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 coming-soon-header">
				<a class="brand" href="javascript:history.back();"> <img
					src='<c:url value="/resources/img/tmp_logo_tsa.png"/>' alt="" />
				</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 coming-soon-content">
				<h1>${title}</h1>
				<p>${message}</p>

				<br>
				<div class="form-actions">
					<button type="button" class="btn blue pull-right" onclick="javascript:history.back()">
						Entiendo <i class="m-icon-swapright m-icon-white"></i>
					</button>
				</div>			
			</div>
			

		</div>
		<!--/end row-->
		<div class="row">
			<div class="col-md-12 coming-soon-footer">
				2015 &copy;
				<spring:message code="application" />
				-
				<spring:message code="application.title" />
			</div>
		</div>
	</div>
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src='<c:url value="/resources/js/plugins/respond.min.js"/>'></script>
<script src='<c:url value="/resources/js/plugins/excanvas.min.js"/>'></script> 
<![endif]-->
	<script src='<c:url value="/resources/js/global/jquery.min.js"/>'
		type="text/javascript"></script>
	<script
		src='<c:url value="/resources/js/global/jquery-migrate.min.js"/>'
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src='<c:url value="/resources/js/plugins/jquery-ui.min.js"/>'
		type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/bootstrap.min.js"/>'
		type="text/javascript"></script>
	<script
		src='<c:url value="/resources/js/plugins/jquery.blockui.min.js"/>'
		type="text/javascript"></script>
	<script
		src='<c:url value="/resources/js/plugins/jquery.cokie.min.js"/>'
		type="text/javascript"></script>
	<script
		src='<c:url value="/resources/js/plugins/jquery.uniform.min.js"/>'
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src='<c:url value="/resources/js/plugins/jquery.backstretch.min.js"/>'
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src='<c:url value="/resources/js/global/metronic.js"/>'
		type="text/javascript"></script>
	<script src='<c:url value="/resources/js/global/layout.js"/>'
		type="text/javascript"></script>
	<script src='<c:url value="/resources/js/plugins/coming-soon.js"/>'
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(
				function() {
					Metronic.init(); // init metronic core components
					Layout.init(); // init current layout
					// init background slide images
					$.backstretch([ '<c:url value="/resources/img/bg/1.jpg"/>',
							'<c:url value="/resources/img/bg/2.jpg"/>',
							'<c:url value="/resources/img/bg/3.jpg"/>',
							'<c:url value="/resources/img/bg/4.jpg"/>' ], {
						fade : 1000,
						duration : 8000
					});
				});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
