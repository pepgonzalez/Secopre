<!DOCTYPE html>
<!-- SPRING TAGS LIBS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--[if IE 8]> <html lang="es-MX" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="es-MX" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es-MX" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>

	<meta charset="utf-8"/>
	<title><spring:message code="application.title"/> | <spring:message code="application.pages.login"/> </title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Content-Language" content="es-MX" />
	<meta name="title" content="<spring:message code="application.title"/>" /> 
	<meta name="author" content="iDea SW" />
	<meta name="copyright" content="<spring:message code="application"/>" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/global/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/simple-line-icons.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/bootstrap.min.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/uniform.default.css"/>' rel="stylesheet" type="text/css">
	<link href='<c:url value="/resources/css/global/bootstrap-switch.min.css"/>' rel="stylesheet" type="text/css">
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<!-- <link href="../../assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/> -->
	<link href='<c:url value="/resources/css/login-soft.css"/>' rel="stylesheet" type="text/css"/>	
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME STYLES -->
	<link href='<c:url value="/resources/css/components-md.css"/>' id="style_components" rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/components-rounded.css"/>'  rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/plugins-md.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/layout.css"/>' rel="stylesheet" type="text/css"/>
	<link href='<c:url value="/resources/css/light.css"/>' rel="stylesheet" type="text/css" id="style_color"/>
	<link href='<c:url value="/resources/css/custom.css"/>' rel="stylesheet" type="text/css"/>	
	<!-- END THEME STYLES -->
	<link rel="shortcut icon" href='<c:url value="/resources/img/favicon.ico"/>' type="image/vnd.microsoft.icon"/> 
	
</head>	
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-md login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="#">
			<img src='<c:url value="/resources/img/tmp_logo_tsa.png"/>' alt=""/>
		</a>
	</div>
	<!-- END LOGO -->
	
	<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
	<div class="menu-toggler sidebar-toggler">
	</div>
	<!-- END SIDEBAR TOGGLER BUTTON -->
	<!-- BEGIN LOGIN -->
		<div class="content">
			<c:url value="/login" var="loginUrl"/>
			<form name='f' class="login-form" action="${loginUrl}" method='POST' class="loginForm">		
	
				<h3 class="form-title"><spring:message code="application.pages.login.message"/></h3>
				<c:if test="${not empty error}">
					<div class="alert alert-danger display-show">
						<button class="close" data-close="alert"></button>
						<span>${error}</span>
					</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="alert alert-success display-show">
						<button class="close" data-close="alert"></button>
						<span>${msg}</span>
					</div>
				</c:if>
				<div class="alert alert-danger display-hide">
					<button class="close" data-close="alert"></button>
					<span><spring:message code="application.pages.login.access"/></span>
				</div>
				<div class="form-group">
					<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
					<label class="control-label visible-ie8 visible-ie9"><spring:message code="application.pages.login.username"/></label>
					<div class="input-icon">
						<i class="fa fa-user"></i>
						<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder='<spring:message code="application.pages.login.username"/>' name="username"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label visible-ie8 visible-ie9"><spring:message code="application.pages.login.password"/></label>
					<div class="input-icon">
						<i class="fa fa-lock"></i>
						<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder='<spring:message code="application.pages.login.password"/>' name="password"/>
					</div>
				</div>
				<div class="form-actions">
					<label class="checkbox">
					<input type="checkbox" name="remember" value="1"/><spring:message code="application.pages.login.rememberme"/></label>
					<button type="submit" class="btn blue pull-right">
					Login <i class="m-icon-swapright m-icon-white"></i>
					</button>
				</div>
				<div class="forget-password">
					<h4><spring:message code="application.pages.login.forgot"/></h4>
					<p><spring:message code="application.pages.login.forgot.message1"/><a href="javascript:;" id="forget-password">
						<spring:message code="application.pages.login.forgot.message2"/> </a>
						<spring:message code="application.pages.login.forgot.message3"/>
					</p>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<!-- END LOGIN FORM -->
			<!-- BEGIN FORGOT PASSWORD FORM -->
			<form class="forget-form" action="index.html" method="post">
				<h3><spring:message code="application.pages.login.forgot"/></h3>
				<p>
					 <spring:message code="application.pages.login.forgot.instruction"/>
				</p>
				<div class="form-group">
					<div class="input-icon">
						<i class="fa fa-envelope"></i>
						<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email"/>
					</div>
				</div>
				<div class="form-actions">
					<button type="button" id="back-btn" class="btn">
					<i class="m-icon-swapleft"></i> <spring:message code="application.back"/> </button>
					<button type="submit" class="btn blue pull-right">
					<spring:message code="application.submit"/> <i class="m-icon-swapright m-icon-white"></i>
					</button>
				</div>
			</form>
			<!-- END FORGOT PASSWORD FORM -->
		</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
	 2015 &copy; <spring:message code="application"/> - <spring:message code="application.title"/>
</div>
<!-- END COPYRIGHT -->
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
<script src='<c:url value="/resources/js/plugins/jquery.blockui.min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/plugins/jquery.cokie.min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/plugins/jquery.uniform.min.js"/>' type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src='<c:url value="/resources/js/plugins/jquery.validate.min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/plugins/jquery.backstretch.min.js"/>'  type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src='<c:url value="/resources/js/global/metronic.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/global/layout.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/plugins/login-soft.js"/>' type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	jQuery(document).ready(
			function() {
				Metronic.init(); // init metronic core components
				Layout.init(); // init current layout
				Login.init();

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
