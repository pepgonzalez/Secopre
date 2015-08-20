<!DOCTYPE html>
<!-- SPRING TAGS LIBS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true"%>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es-MX" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Metronic | Admin Dashboard Template</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>


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
<%-- 	<link href='<c:url value="/resources/css/plugins/jqvmap.css"/>' rel="stylesheet" type="text/css"/> --%>
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

	<link href='<c:url value="/resources/css/secopre.css"/>' rel="stylesheet" type="text/css"/>
	

	<link rel="shortcut icon" href='<c:url value="/resources/img/favicon.ico"/>' type="image/vnd.microsoft.icon"/> 
</head>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<script>
	var context = "${context}";
</script>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body class="page-md page-header-fixed page-sidebar-closed-hide-logo page-sidebar-closed-hide-logo">
<!-- BEGIN HEADER -->
<div class="page-header md-shadow-z-1-i navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="start">
			<img src='<c:url value="/resources/img/logo_180x35px.png"/>' alt="logo" class="logo-default"/>
			</a>
			<div class="menu-toggler sidebar-toggler">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN PAGE ACTIONS -->
		<!-- DOC: Remove "hide" class to enable the page header actions -->
		<div class="page-actions">
			<div class="btn-group">
				<button type="button" class="btn red-haze btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
				<span class="hidden-sm hidden-xs">Actions&nbsp;</span><i class="fa fa-angle-down"></i>
				</button>
				<ul class="dropdown-menu" role="menu">
					<li>
						<a href="javascript:;" onclick="sendRequestJQ('auth/adm/usr','page-content');">
						<i class="icon-docs"></i> New Post </a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-tag"></i> New Comment </a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-share"></i> Share </a>
					</li>
					<li class="divider">
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-flag"></i> Comments <span class="badge badge-success">4</span>
						</a>
					</li>
					<li>
						<a href="javascript:;">
						<i class="icon-users"></i> Feedbacks <span class="badge badge-danger">2</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<!-- END PAGE ACTIONS -->
		<!-- BEGIN PAGE TOP -->
		<div class="page-top">
			<!-- BEGIN HEADER SEARCH BOX -->
			<!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
			<form class="search-form" action="extra_search.html" method="GET">
				<div class="input-group">
					<input type="text" class="form-control input-sm" placeholder="Search..." name="query">
					<span class="input-group-btn">
					<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
					</span>
				</div>
			</form>
			<!-- END HEADER SEARCH BOX -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					<li class="separator hide">
					</li>
					<!-- BEGIN NOTIFICATION DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended dropdown-notification dropdown-dark" id="header_notification_bar">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<i class="icon-bell"></i>
						<span class="badge badge-success">
						7 </span>
						</a>
						<ul class="dropdown-menu">
							<li class="external">
								<h3><span class="bold">12 pending</span> notifications</h3>
								<a href="extra_profile.html">view all</a>
							</li>
							<li>
								<ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
									<li>
										<a href="javascript:;">
										<span class="time">just now</span>
										<span class="details">
										<span class="label label-sm label-icon label-success">
										<i class="fa fa-plus"></i>
										</span>
										New user registered. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">3 mins</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Server #12 overloaded. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">10 mins</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>
										Server #2 not responding. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">14 hrs</span>
										<span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span>
										Application error. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">2 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Database overloaded 68%. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">3 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										A user IP blocked. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">4 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-warning">
										<i class="fa fa-bell-o"></i>
										</span>
										Storage Server #4 not responding dfdfdfd. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">5 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-info">
										<i class="fa fa-bullhorn"></i>
										</span>
										System Error. </span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="time">9 days</span>
										<span class="details">
										<span class="label label-sm label-icon label-danger">
										<i class="fa fa-bolt"></i>
										</span>
										Storage server failed. </span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<!-- END NOTIFICATION DROPDOWN -->
					<li class="separator hide">
					</li>
					<!-- BEGIN INBOX DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended dropdown-inbox dropdown-dark" id="header_inbox_bar">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
							<i class="icon-envelope-open"></i>
							<span class="badge badge-danger">1</span>
						</a>
						<ul class="dropdown-menu">
							<li class="external">
								<h3>Tiene <span class="bold">1</span> Nuevo(s) Mensajes(s)</h3>
								<a class="sp__aux__view__all">ver todos</a>
							</li>
							<li>
								<ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
									<li class="sp__message__pending" data-userId="1" data-userName="Usuario 1">
										<a href="javascript:;">
											<span class="photo">
												<img src='<c:url value="/resources/img/avatar.png"/>' class="img-circle" alt="">
											</span>
											<span class="subject">
												<span class="from">Usuario 1</span>
												<span class="time">Ahora</span>
											</span>
											<span class="message">Mensaje de prueba 1 usuario 1</span>
										</a>
									</li>
									<li class="sp__message__readed" data-userId="2" data-userName="Usuario 2">
										<a href="javascript:;">
											<span class="photo">
												<img src='<c:url value="/resources/img/avatar.png"/>' class="img-circle" alt="">
											</span>
											<span class="subject">
												<span class="from">Usuario 2</span>
												<span class="time">16 mins </span>
											</span>
											<span class="message">Mensaje de Prueba 2 usuario 2</span>
										</a>
									</li>
									<li class="sp__message__readed" data-userId="3" data-userName="Usuario 3">
										<a href="javascript:;">
											<span class="photo">
												<img src='<c:url value="/resources/img/avatar.png"/>' class="img-circle" alt="">
											</span>
											<span class="subject">
												<span class="from">Usuario 3</span>
												<span class="time">1 hora </span>
											</span>
											<span class="message">Mensaje de Prueba 1 usuario 3</span>
										</a>
									</li>
									<li class="sp__message__readed" data-userId="4" data-userName="Usuario 4">
										<a href="javascript:;">
											<span class="photo">
												<img src='<c:url value="/resources/img/avatar.png"/>' class="img-circle" alt="">
											</span>
											<span class="subject">
												<span class="from">Usuario 4</span>
												<span class="time">6 horas</span>
											</span>
											<span class="message">Mensaje de Prueba 1 usuario 4</span>
										</a>
									</li>
									<li class="sp__message__readed" data-userId="5" data-userName="Usuario 5">
										<a href="javascript:;">
											<span class="photo">
												<img src='<c:url value="/resources/img/avatar.png"/>' class="img-circle" alt="">
											</span>
											<span class="subject">
												<span class="from">Usuario 5</span>
												<span class="time">1 dia</span>
											</span>
											<span class="message">Mensaje de Prueba 1 usuario 5</span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<!-- END INBOX DROPDOWN -->
					<li class="separator hide">
					</li>
					<!-- BEGIN TODO DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-extended dropdown-tasks dropdown-dark" id="header_task_bar">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<i class="icon-calendar"></i>
						<span class="badge badge-primary">
						3 </span>
						</a>
						<ul class="dropdown-menu extended tasks">
							<li class="external">
								<h3>You have <span class="bold">12 pending</span> tasks</h3>
								<a href="page_todo.html">view all</a>
							</li>
							<li>
								<ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">New release v1.2 </span>
										<span class="percent">30%</span>
										</span>
										<span class="progress">
										<span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">40% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Application deployment</span>
										<span class="percent">65%</span>
										</span>
										<span class="progress">
										<span style="width: 65%;" class="progress-bar progress-bar-danger" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">65% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Mobile app release</span>
										<span class="percent">98%</span>
										</span>
										<span class="progress">
										<span style="width: 98%;" class="progress-bar progress-bar-success" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">98% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Database migration</span>
										<span class="percent">10%</span>
										</span>
										<span class="progress">
										<span style="width: 10%;" class="progress-bar progress-bar-warning" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">10% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Web server upgrade</span>
										<span class="percent">58%</span>
										</span>
										<span class="progress">
										<span style="width: 58%;" class="progress-bar progress-bar-info" aria-valuenow="58" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">58% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">Mobile development</span>
										<span class="percent">85%</span>
										</span>
										<span class="progress">
										<span style="width: 85%;" class="progress-bar progress-bar-success" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">85% Complete</span></span>
										</span>
										</a>
									</li>
									<li>
										<a href="javascript:;">
										<span class="task">
										<span class="desc">New UI release</span>
										<span class="percent">38%</span>
										</span>
										<span class="progress progress-striped">
										<span style="width: 38%;" class="progress-bar progress-bar-important" aria-valuenow="18" aria-valuemin="0" aria-valuemax="100"><span class="sr-only">38% Complete</span></span>
										</span>
										</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<!-- END TODO DROPDOWN -->
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-user dropdown-dark">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<span class="username username-hide-on-mobile">
						${username} </span>
						<!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
						<img alt="" class="img-circle" src='<c:url value="/resources/img/avatar.png"/>'/>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a href="extra_profile.html">
								<i class="icon-user"></i> My Profile </a>
							</li>
							<li>
								<a href="page_calendar.html">
								<i class="icon-calendar"></i> My Calendar </a>
							</li>
							<li>
								<a href="inbox.html">
								<i class="icon-envelope-open"></i> My Inbox <span class="badge badge-danger">
								3 </span>
								</a>
							</li>
							<li>
								<a href="page_todo.html">
								<i class="icon-rocket"></i> My Tasks <span class="badge badge-success">
								7 </span>
								</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="extra_lock.html">
								<i class="icon-lock"></i> Lock Screen </a>
							</li>
							<li>
								<c:url var="logoutUrl" value="/logout"/>
								<form action="${logoutUrl}" method="post">
  									<input type="submit" value="Salir" />
  									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								</form>
							</li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown dropdown-extended quick-sidebar-toggler">
	                    <span class="sr-only">Toggle Quick Sidebar</span>
	                    <i class="icon-logout"></i>
	                </li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END PAGE TOP -->
	</div>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar md-shadow-z-2-i  navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="start active ">
					<a href="index.html">
					<i class="icon-home"></i>
					<span class="title">Dashboard</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-basket"></i>
					<span class="title">eCommerce</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="ecommerce_index.html">
							<i class="icon-home"></i>
							Dashboard</a>
						</li>
						<li>
							<a href="ecommerce_orders.html">
							<i class="icon-basket"></i>
							Orders</a>
						</li>
						<li>
							<a href="ecommerce_orders_view.html">
							<i class="icon-tag"></i>
							Order View</a>
						</li>
						<li>
							<a href="ecommerce_products.html">
							<i class="icon-handbag"></i>
							Products</a>
						</li>
						<li>
							<a href="ecommerce_products_edit.html">
							<i class="icon-pencil"></i>
							Product Edit</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-rocket"></i>
					<span class="title">Page Layouts</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="layout_sidebar_reversed.html">
							<span class="badge badge-warning">new</span>Right Sidebar Page</a>
						</li>
						<li>
							<a href="layout_sidebar_fixed.html">
							Sidebar Fixed Page</a>
						</li>
						<li>
							<a href="layout_sidebar_closed.html">
							Sidebar Closed Page</a>
						</li>
						<li>
							<a href="layout_blank_page.html">
							Blank Page</a>
						</li>
						<li>
							<a href="layout_boxed_page.html">
							Boxed Page</a>
						</li>
						<li>
							<a href="layout_language_bar.html">
							Language Switch Bar</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-diamond"></i>
					<span class="title">UI Features</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="ui_general.html">
							General Components</a>
						</li>
						<li>
							<a href="ui_buttons.html">
							Buttons</a>
						</li>
						<li>
							<a href="ui_icons.html">
							<span class="badge badge-danger">new</span>Font Icons</a>
						</li>
						<li>
							<a href="ui_colors.html">
							Flat UI Colors</a>
						</li>
						<li>
							<a href="ui_typography.html">
							Typography</a>
						</li>
						<li>
							<a href="ui_tabs_accordions_navs.html">
							Tabs, Accordions & Navs</a>
						</li>
						<li>
							<a href="ui_tree.html">
							<span class="badge badge-danger">new</span>Tree View</a>
						</li>
						<li>
							<a href="ui_page_progress_style_1.html">
							<span class="badge badge-warning">new</span>Page Progress Bar - Style 1</a>
						</li>
						<li>
							<a href="ui_blockui.html">
							Block UI</a>
						</li>
						<li>
							<a href="ui_bootstrap_growl.html">
							<span class="badge badge-roundless badge-warning">new</span>Bootstrap Growl Notifications</a>
						</li>
						<li>
							<a href="ui_notific8.html">
							Notific8 Notifications</a>
						</li>
						<li>
							<a href="ui_toastr.html">
							Toastr Notifications</a>
						</li>
						<li>
							<a href="ui_alert_dialog_api.html">
							<span class="badge badge-danger">new</span>Alerts & Dialogs API</a>
						</li>
						<li>
							<a href="ui_session_timeout.html">
							Session Timeout</a>
						</li>
						<li>
							<a href="ui_idle_timeout.html">
							User Idle Timeout</a>
						</li>
						<li>
							<a href="ui_modals.html">
							Modals</a>
						</li>
						<li>
							<a href="ui_extended_modals.html">
							Extended Modals</a>
						</li>
						<li>
							<a href="ui_tiles.html">
							Tiles</a>
						</li>
						<li>
							<a href="ui_datepaginator.html">
							<span class="badge badge-success">new</span>Date Paginator</a>
						</li>
						<li>
							<a href="ui_nestable.html">
							Nestable List</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-puzzle"></i>
					<span class="title">UI Components</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="components_pickers.html">
							Date & Time Pickers</a>
						</li>
						<li>
							<a href="components_context_menu.html">
							Context Menu</a>
						</li>
						<li>
							<a href="components_dropdowns.html">
							Custom Dropdowns</a>
						</li>
						<li>
							<a href="components_form_tools.html">
							Form Widgets & Tools</a>
						</li>
						<li>
							<a href="components_form_tools2.html">
							Form Widgets & Tools 2</a>
						</li>
						<li>
							<a href="components_editors.html">
							Markdown & WYSIWYG Editors</a>
						</li>
						<li>
							<a href="components_ion_sliders.html">
							Ion Range Sliders</a>
						</li>
						<li>
							<a href="components_noui_sliders.html">
							NoUI Range Sliders</a>
						</li>
						<li>
							<a href="components_jqueryui_sliders.html">
							jQuery UI Sliders</a>
						</li>
						<li>
							<a href="components_knob_dials.html">
							Knob Circle Dials</a>
						</li>
					</ul>
				</li>
				<!-- BEGIN ANGULARJS LINK -->
				<li class="tooltips" data-container="body" data-placement="right" data-html="true" data-original-title="AngularJS version demo">
					<a href="angularjs" target="_blank">
					<i class="icon-paper-plane"></i>
					<span class="title">
					AngularJS Version </span>
					</a>
				</li>
				<!-- END ANGULARJS LINK -->
				<li>
					<a href="javascript:;">
					<i class="icon-settings"></i>
					<span class="title">Form Stuff</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="form_controls_md.html">
							<span class="badge badge-roundless badge-danger">new</span>Material Design<br>
							Form Controls</a>
						</li>
						<li>
							<a href="form_controls.html">
							Bootstrap<br>
							Form Controls</a>
						</li>
						<li>
							<a href="form_layouts.html">
							Form Layouts</a>
						</li>
						<li>
							<a href="form_editable.html">
							<span class="badge badge-warning">new</span>Form X-editable</a>
						</li>
						<li>
							<a href="form_wizard.html">
							Form Wizard</a>
						</li>
						<li>
							<a href="form_validation.html">
							Form Validation</a>
						</li>
						<li>
							<a href="form_image_crop.html">
							<span class="badge badge-danger">new</span>Image Cropping</a>
						</li>
						<li>
							<a href="form_fileupload.html">
							Multiple File Upload</a>
						</li>
						<li>
							<a href="form_dropzone.html">
							Dropzone File Upload</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-briefcase"></i>
					<span class="title">Data Tables</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="table_basic.html">
							Basic Datatables</a>
						</li>
						<li>
							<a href="table_tree.html">
							Tree Datatables</a>
						</li>
						<li>
							<a href="table_responsive.html">
							Responsive Datatables</a>
						</li>
						<li>
							<a href="table_managed.html">
							Managed Datatables</a>
						</li>
						<li>
							<a href="table_editable.html">
							Editable Datatables</a>
						</li>
						<li>
							<a href="table_advanced.html">
							Advanced Datatables</a>
						</li>
						<li>
							<a href="table_ajax.html">
							Ajax Datatables</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-wallet"></i>
					<span class="title">Portlets</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="portlet_general.html">
							General Portlets</a>
						</li>
						<li>
							<a href="portlet_general2.html">
							<span class="badge badge-danger">new</span>New Portlets #1</a>
						</li>
						<li>
							<a href="portlet_general3.html">
							<span class="badge badge-danger">new</span>New Portlets #2</a>
						</li>
						<li>
							<a href="portlet_ajax.html">
							Ajax Portlets</a>
						</li>
						<li>
							<a href="portlet_draggable.html">
							Draggable Portlets</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-bar-chart"></i>
					<span class="title">Charts</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="charts_amcharts.html">
							Amchart</a>
						</li>
						<li>
							<a href="charts_flotcharts.html">
							Flotchart</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-docs"></i>
					<span class="title">Pages</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="page_timeline.html">
							<i class="icon-paper-plane"></i>
							<span class="badge badge-warning">2</span>New Timeline</a>
						</li>
						<li>
							<a href="extra_profile.html">
							<i class="icon-user-following"></i>
							<span class="badge badge-success badge-roundless">new</span>New User Profile</a>
						</li>
						<li>
							<a href="page_todo.html">
							<i class="icon-hourglass"></i>
							<span class="badge badge-danger">4</span>Todo</a>
						</li>
						<li>
							<a href="inbox.html">
							<i class="icon-envelope"></i>
							<span class="badge badge-danger">4</span>Inbox</a>
						</li>
						<li>
							<a href="extra_faq.html">
							<i class="icon-info"></i>
							FAQ</a>
						</li>
						<li>
							<a href="page_portfolio.html">
							<i class="icon-feed"></i>
							Portfolio</a>
						</li>
						<li>
							<a href="page_timeline.html">
							<i class="icon-clock"></i>
							<span class="badge badge-info">4</span>Timeline</a>
						</li>
						<li>
							<a href="page_coming_soon.html">
							<i class="icon-flag"></i>
							Coming Soon</a>
						</li>
						<li>
							<a href="page_calendar.html">
							<i class="icon-calendar"></i>
							<span class="badge badge-danger">14</span>Calendar</a>
						</li>
						<li>
							<a href="extra_invoice.html">
							<i class="icon-flag"></i>
							Invoice</a>
						</li>
						<li>
							<a href="page_blog.html">
							<i class="icon-speech"></i>
							Blog</a>
						</li>
						<li>
							<a href="page_blog_item.html">
							<i class="icon-link"></i>
							Blog Post</a>
						</li>
						<li>
							<a href="page_news.html">
							<i class="icon-eye"></i>
							<span class="badge badge-success">9</span>News</a>
						</li>
						<li>
							<a href="page_news_item.html">
							<i class="icon-bell"></i>
							News View</a>
						</li>
						<li>
							<a href="page_timeline_old.html">
							<i class="icon-paper-plane"></i>
							<span class="badge badge-warning">2</span>Old Timeline</a>
						</li>
						<li>
							<a href="extra_profile_old.html">
							<i class="icon-user"></i>
							Old User Profile</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-present"></i>
					<span class="title">Extra</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="page_about.html">
							About Us</a>
						</li>
						<li>
							<a href="page_contact.html">
							Contact Us</a>
						</li>
						<li>
							<a href="extra_search.html">
							Search Results</a>
						</li>
						<li>
							<a href="extra_pricing_table.html">
							Pricing Tables</a>
						</li>
						<li>
							<a href="extra_404_option1.html">
							404 Page Option 1</a>
						</li>
						<li>
							<a href="extra_404_option2.html">
							404 Page Option 2</a>
						</li>
						<li>
							<a href="extra_404_option3.html">
							404 Page Option 3</a>
						</li>
						<li>
							<a href="extra_500_option1.html">
							500 Page Option 1</a>
						</li>
						<li>
							<a href="extra_500_option2.html">
							500 Page Option 2</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-folder"></i>
					<span class="title">Multi Level Menu</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="javascript:;">
							<i class="icon-settings"></i> Item 1 <span class="arrow"></span>
							</a>
							<ul class="sub-menu">
								<li>
									<a href="javascript:;">
									<i class="icon-user"></i>
									Sample Link 1 <span class="arrow"></span>
									</a>
									<ul class="sub-menu">
										<li>
											<a href="#"><i class="icon-power"></i> Sample Link 1</a>
										</li>
										<li>
											<a href="#"><i class="icon-paper-plane"></i> Sample Link 1</a>
										</li>
										<li>
											<a href="#"><i class="icon-star"></i> Sample Link 1</a>
										</li>
									</ul>
								</li>
								<li>
									<a href="#"><i class="icon-camera"></i> Sample Link 1</a>
								</li>
								<li>
									<a href="#"><i class="icon-link"></i> Sample Link 2</a>
								</li>
								<li>
									<a href="#"><i class="icon-pointer"></i> Sample Link 3</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-globe"></i> Item 2 <span class="arrow"></span>
							</a>
							<ul class="sub-menu">
								<li>
									<a href="#"><i class="icon-tag"></i> Sample Link 1</a>
								</li>
								<li>
									<a href="#"><i class="icon-pencil"></i> Sample Link 1</a>
								</li>
								<li>
									<a href="#"><i class="icon-graph"></i> Sample Link 1</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">
							<i class="icon-bar-chart"></i>
							Item 3 </a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-user"></i>
					<span class="title">Login Options</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="login.html">
							Login Form 1</a>
						</li>
						<li>
							<a href="login_2.html">
							Login Form 2</a>
						</li>
						<li>
							<a href="login_3.html">
							Login Form 3</a>
						</li>
						<li>
							<a href="login_soft.html">
							Login Form 4</a>
						</li>
						<li>
							<a href="extra_lock.html">
							Lock Screen 1</a>
						</li>
						<li>
							<a href="extra_lock2.html">
							Lock Screen 2</a>
						</li>
					</ul>
				</li>

				<li class="last ">
					<a href="javascript:;">
					<i class="icon-pointer"></i>
					<span class="title">Maps</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="maps_google.html">
							Google Maps</a>
						</li>
						<li>
							<a href="maps_vector.html">
							Vector Maps</a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content" id="page-content">
			<!-- BEGIN PAGE HEAD -->
			<p>
								<a href="javascript:;" class="btn green" id="blockui_sample_2_1">
								Block Page With Default Message </a>
							</p>
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Dashboard <small>statistics & reports</small></h1>
				</div>
				<!-- END PAGE TITLE -->
				<!-- BEGIN PAGE TOOLBAR -->
				<div class="page-toolbar">
					<!-- BEGIN THEME PANEL -->
					<div class="btn-group btn-theme-panel">
						<a href="javascript:;" class="btn dropdown-toggle" data-toggle="dropdown">
						<i class="icon-settings"></i>
						</a>
						<div class="dropdown-menu theme-panel pull-right dropdown-custom hold-on-click">
							<div class="row">
								<div class="col-md-4 col-sm-4 col-xs-12">
									<h3>THEME</h3>
									<ul class="theme-colors">
										<li class="theme-color theme-color-default" data-theme="default">
											<span class="theme-color-view"></span>
											<span class="theme-color-name">Dark Header</span>
										</li>
										<li class="theme-color theme-color-light active" data-theme="light">
											<span class="theme-color-view"></span>
											<span class="theme-color-name">Light Header</span>
										</li>
									</ul>
								</div>
								<div class="col-md-8 col-sm-8 col-xs-12 seperator">
									<h3>LAYOUT</h3>
									<ul class="theme-settings">
										<li>
											 Theme Style
											<select class="layout-style-option form-control input-small input-sm">
												<option value="square">Square corners</option>
												<option value="rounded" selected="selected">Rounded corners</option>
											</select>
										</li>
										<li>
											 Layout
											<select class="layout-option form-control input-small input-sm">
												<option value="fluid" selected="selected">Fluid</option>
												<option value="boxed">Boxed</option>
											</select>
										</li>
										<li>
											 Header
											<select class="page-header-option form-control input-small input-sm">
												<option value="fixed" selected="selected">Fixed</option>
												<option value="default">Default</option>
											</select>
										</li>
										<li>
											 Top Dropdowns
											<select class="page-header-top-dropdown-style-option form-control input-small input-sm">
												<option value="light">Light</option>
												<option value="dark" selected="selected">Dark</option>
											</select>
										</li>
										<li>
											 Sidebar Mode
											<select class="sidebar-option form-control input-small input-sm">
												<option value="fixed">Fixed</option>
												<option value="default" selected="selected">Default</option>
											</select>
										</li>
										<li>
											 Sidebar Menu
											<select class="sidebar-menu-option form-control input-small input-sm">
												<option value="accordion" selected="selected">Accordion</option>
												<option value="hover">Hover</option>
											</select>
										</li>
										<li>
											 Sidebar Position
											<select class="sidebar-pos-option form-control input-small input-sm">
												<option value="left" selected="selected">Left</option>
												<option value="right">Right</option>
											</select>
										</li>
										<li>
											 Footer
											<select class="page-footer-option form-control input-small input-sm">
												<option value="fixed">Fixed</option>
												<option value="default" selected="selected">Default</option>
											</select>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- END THEME PANEL -->
				</div>
				<!-- END PAGE TOOLBAR -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb hide">
				<li>
					<a href="javascript:;">Home</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Dashboard
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row margin-top-10">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat2">
						<div class="display">
							<div class="number">
								<h3 class="font-green-sharp">7800<small class="font-green-sharp">$</small></h3>
								<small>TOTAL PROFIT</small>
							</div>
							<div class="icon">
								<i class="icon-pie-chart"></i>
							</div>
						</div>
						<div class="progress-info">
							<div class="progress">
								<span style="width: 76%;" class="progress-bar progress-bar-success green-sharp">
								<span class="sr-only">76% progress</span>
								</span>
							</div>
							<div class="status">
								<div class="status-title">
									 progress
								</div>
								<div class="status-number">
									 76%
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat2">
						<div class="display">
							<div class="number">
								<h3 class="font-red-haze">1349</h3>
								<small>NEW FEEDBACKS</small>
							</div>
							<div class="icon">
								<i class="icon-like"></i>
							</div>
						</div>
						<div class="progress-info">
							<div class="progress">
								<span style="width: 85%;" class="progress-bar progress-bar-success red-haze">
								<span class="sr-only">85% change</span>
								</span>
							</div>
							<div class="status">
								<div class="status-title">
									 change
								</div>
								<div class="status-number">
									 85%
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat2">
						<div class="display">
							<div class="number">
								<h3 class="font-blue-sharp">567</h3>
								<small>NEW ORDERS</small>
							</div>
							<div class="icon">
								<i class="icon-basket"></i>
							</div>
						</div>
						<div class="progress-info">
							<div class="progress">
								<span style="width: 45%;" class="progress-bar progress-bar-success blue-sharp">
								<span class="sr-only">45% grow</span>
								</span>
							</div>
							<div class="status">
								<div class="status-title">
									 grow
								</div>
								<div class="status-number">
									 45%
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat2">
						<div class="display">
							<div class="number">
								<h3 class="font-purple-soft">276</h3>
								<small>NEW USERS</small>
							</div>
							<div class="icon">
								<i class="icon-user"></i>
							</div>
						</div>
						<div class="progress-info">
							<div class="progress">
								<span style="width: 57%;" class="progress-bar progress-bar-success purple-soft">
								<span class="sr-only">56% change</span>
								</span>
							</div>
							<div class="status">
								<div class="status-title">
									 change
								</div>
								<div class="status-number">
									 57%
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-bar-chart theme-font-color hide"></i>
								<span class="caption-subject theme-font-color bold uppercase">Sales Summary</span>
								<span class="caption-helper hide">weekly stats...</span>
							</div>
							<div class="actions">
								<div class="btn-group btn-group-devided" data-toggle="buttons">
									<label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
									<input type="radio" name="options" class="toggle" id="option1">Today</label>
									<label class="btn btn-transparent grey-salsa btn-circle btn-sm">
									<input type="radio" name="options" class="toggle" id="option2">Week</label>
									<label class="btn btn-transparent grey-salsa btn-circle btn-sm">
									<input type="radio" name="options" class="toggle" id="option2">Month</label>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row list-separated">
								<div class="col-md-3 col-sm-3 col-xs-6">
									<div class="font-grey-mint font-sm">
										 Total Sales
									</div>
									<div class="uppercase font-hg font-red-flamingo">
										 13,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<div class="font-grey-mint font-sm">
										 Revenue
									</div>
									<div class="uppercase font-hg theme-font-color">
										 4,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<div class="font-grey-mint font-sm">
										 Expenses
									</div>
									<div class="uppercase font-hg font-purple">
										 11,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</div>
								<div class="col-md-3 col-sm-3 col-xs-6">
									<div class="font-grey-mint font-sm">
										 Growth
									</div>
									<div class="uppercase font-hg font-blue-sharp">
										 9,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</div>
							</div>
							<ul class="list-separated list-inline-xs hide">
								<li>
									<div class="font-grey-mint font-sm">
										 Total Sales
									</div>
									<div class="uppercase font-hg font-red-flamingo">
										 13,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</li>
								<li>
								</li>
								<li class="border">
									<div class="font-grey-mint font-sm">
										 Revenue
									</div>
									<div class="uppercase font-hg theme-font-color">
										 4,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</li>
								<li class="divider">
								</li>
								<li>
									<div class="font-grey-mint font-sm">
										 Expenses
									</div>
									<div class="uppercase font-hg font-purple">
										 11,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</li>
								<li class="divider">
								</li>
								<li>
									<div class="font-grey-mint font-sm">
										 Growth
									</div>
									<div class="uppercase font-hg font-blue-sharp">
										 9,760 <span class="font-lg font-grey-mint">$</span>
									</div>
								</li>
							</ul>
							<div id="sales_statistics" class="portlet-body-morris-fit morris-chart" style="height: 260px">
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-bar-chart theme-font-color hide"></i>
								<span class="caption-subject theme-font-color bold uppercase">Member Activity</span>
								<span class="caption-helper hide">weekly stats...</span>
							</div>
							<div class="actions">
								<div class="btn-group btn-group-devided" data-toggle="buttons">
									<label class="btn btn-transparent grey-salsa btn-circle btn-sm active">
									<input type="radio" name="options" class="toggle" id="option1">Today</label>
									<label class="btn btn-transparent grey-salsa btn-circle btn-sm">
									<input type="radio" name="options" class="toggle" id="option2">Week</label>
									<label class="btn btn-transparent grey-salsa btn-circle btn-sm">
									<input type="radio" name="options" class="toggle" id="option2">Month</label>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row number-stats margin-bottom-30">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<div class="stat-left">
										<div class="stat-chart">
											<!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
											<div id="sparkline_bar"></div>
										</div>
										<div class="stat-number">
											<div class="title">
												 Total
											</div>
											<div class="number">
												 2460
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<div class="stat-right">
										<div class="stat-chart">
											<!-- do not line break "sparkline_bar" div. sparkline chart has an issue when the container div has line break -->
											<div id="sparkline_bar2"></div>
										</div>
										<div class="stat-number">
											<div class="title">
												 New
											</div>
											<div class="number">
												 719
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="table-scrollable table-scrollable-borderless">
								<table class="table table-hover table-light">
								<thead>
								<tr class="uppercase">
									<th colspan="2">
										 MEMBER
									</th>
									<th>
										 Earnings
									</th>
									<th>
										 CASES
									</th>
									<th>
										 CLOSED
									</th>
									<th>
										 RATE
									</th>
								</tr>
								</thead>
								<tr>
									<td class="fit">
										<img class="user-pic" src='<c:url value="/resources/img/avatar.png"/>'>
									</td>
									<td>
										<a href="javascript:;" class="primary-link">Brain</a>
									</td>
									<td>
										 $345
									</td>
									<td>
										 45
									</td>
									<td>
										 124
									</td>
									<td>
										<span class="bold theme-font-color">80%</span>
									</td>
								</tr>
								<tr>
									<td class="fit">
										<img class="user-pic" src='<c:url value="/resources/img/avatar.png"/>'>
									</td>
									<td>
										<a href="javascript:;" class="primary-link"> ${username} </a>
									</td>
									<td>
										 $560
									</td>
									<td>
										 12
									</td>
									<td>
										 24
									</td>
									<td>
										<span class="bold theme-font-color">67%</span>
									</td>
								</tr>
								<tr>
									<td class="fit">
										<img class="user-pic" src='<c:url value="/resources/img/avatar.png"/>'>
									</td>
									<td>
										<a href="javascript:;" class="primary-link">Tim</a>
									</td>
									<td>
										 $1,345
									</td>
									<td>
										 450
									</td>
									<td>
										 46
									</td>
									<td>
										<span class="bold theme-font-color">98%</span>
									</td>
								</tr>
								<tr>
									<td class="fit">
										<img class="user-pic" src='<c:url value="/resources/img/avatar.png"/>'>
									</td>
									<td>
										<a href="javascript:;" class="primary-link">Tom</a>
									</td>
									<td>
										 $645
									</td>
									<td>
										 50
									</td>
									<td>
										 89
									</td>
									<td>
										<span class="bold theme-font-color">58%</span>
									</td>
								</tr>
								</table>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div class="portlet light tasks-widget">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-bar-chart theme-font-color hide"></i>
								<span class="caption-subject theme-font-color bold uppercase">TASKS</span>
								<span class="caption-helper">16 pending</span>
							</div>
							<div class="inputs">
								<div class="portlet-input input-small input-inline">
									<div class="input-icon right">
										<i class="icon-magnifier"></i>
										<input type="text" class="form-control form-control-solid" placeholder="search...">
									</div>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="task-content">
								<div class="scroller" style="height: 282px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
									<!-- START TASK LIST -->
									<ul class="task-list">
										<li>
											<div class="task-checkbox">
												<input type="hidden" value="1" name="test"/>
												<input type="checkbox" class="liChild" value="2" name="test"/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Present 2013 Year IPO Statistics at Board Meeting </span>
												<span class="label label-sm label-success">Company</span>
												<span class="task-bell">
												<i class="fa fa-bell-o"></i>
												</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Hold An Interview for Marketing Manager Position </span>
												<span class="label label-sm label-danger">Marketing</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												AirAsia Intranet System Project Internal Meeting </span>
												<span class="label label-sm label-success">AirAsia</span>
												<span class="task-bell">
												<i class="fa fa-bell-o"></i>
												</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Technical Management Meeting </span>
												<span class="label label-sm label-warning">Company</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Kick-off Company CRM Mobile App Development </span>
												<span class="label label-sm label-info">Internal Products</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Prepare Commercial Offer For SmartVision Website Rewamp </span>
												<span class="label label-sm label-danger">SmartVision</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Sign-Off The Comercial Agreement With AutoSmart </span>
												<span class="label label-sm label-default">AutoSmart</span>
												<span class="task-bell">
												<i class="fa fa-bell-o"></i>
												</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li>
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												Company Staff Meeting </span>
												<span class="label label-sm label-success">Cruise</span>
												<span class="task-bell">
												<i class="fa fa-bell-o"></i>
												</span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
										<li class="last-line">
											<div class="task-checkbox">
												<input type="checkbox" class="liChild" value=""/>
											</div>
											<div class="task-title">
												<span class="task-title-sp">
												KeenThemes Investment Discussion </span>
												<span class="label label-sm label-warning">KeenThemes </span>
											</div>
											<div class="task-config">
												<div class="task-config-btn btn-group">
													<a class="btn btn-xs default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
													<i class="fa fa-cog"></i><i class="fa fa-angle-down"></i>
													</a>
													<ul class="dropdown-menu pull-right">
														<li>
															<a href="javascript:;">
															<i class="fa fa-check"></i> Complete </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-pencil"></i> Edit </a>
														</li>
														<li>
															<a href="javascript:;">
															<i class="fa fa-trash-o"></i> Cancel </a>
														</li>
													</ul>
												</div>
											</div>
										</li>
									</ul>
									<!-- END START TASK LIST -->
								</div>
							</div>
							<div class="task-footer">
								<div class="btn-arrow-link pull-right">
									<a href="javascript:;">See All Tasks</a>
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
				<div class="col-md-6 col-sm-12">
					<!-- BEGIN PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-bar-chart theme-font-color hide"></i>
								<span class="caption-subject theme-font-color bold uppercase">Customer Support</span>
								<span class="caption-helper">45 pending</span>
							</div>
							<div class="inputs">
								<div class="portlet-input input-inline input-small ">
									<div class="input-icon right">
										<i class="icon-magnifier"></i>
										<input type="text" class="form-control form-control-solid" placeholder="search...">
									</div>
								</div>
							</div>
						</div>
						<div class="portlet-body">
							<div class="scroller" style="height: 305px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
								<div class="general-item-list">
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Nick Larson</a>
												<span class="item-label">3 hrs ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-success"></span> Open</span>
										</div>
										<div class="item-body">
											 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
										</div>
									</div>
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Mark</a>
												<span class="item-label">5 hrs ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-warning"></span> Pending</span>
										</div>
										<div class="item-body">
											 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat tincidunt ut laoreet.
										</div>
									</div>
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Nick Larson</a>
												<span class="item-label">8 hrs ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-primary"></span> Closed</span>
										</div>
										<div class="item-body">
											 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh.
										</div>
									</div>
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Nick Larson</a>
												<span class="item-label">12 hrs ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-danger"></span> Pending</span>
										</div>
										<div class="item-body">
											 Consectetuer adipiscing elit Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
										</div>
									</div>
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Richard Stone</a>
												<span class="item-label">2 days ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-danger"></span> Open</span>
										</div>
										<div class="item-body">
											 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, ut laoreet dolore magna aliquam erat volutpat.
										</div>
									</div>
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Dan</a>
												<span class="item-label">3 days ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-warning"></span> Pending</span>
										</div>
										<div class="item-body">
											 Lorem ipsum dolor sit amet, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
										</div>
									</div>
									<div class="item">
										<div class="item-head">
											<div class="item-details">
												<img class="item-pic" src='<c:url value="/resources/img/avatar.png"/>'>
												<a href="" class="item-name primary-link">Larry</a>
												<span class="item-label">4 hrs ago</span>
											</div>
											<span class="item-status"><span class="badge badge-empty badge-success"></span> Open</span>
										</div>
										<div class="item-body">
											 Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<!-- BEGIN REGIONAL STATS PORTLET-->
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-bar-chart theme-font-color hide"></i>
								<span class="caption-subject theme-font-color bold uppercase">Regional Stats</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
								<i class="icon-cloud-upload"></i>
								</a>
								<a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
								<i class="icon-wrench"></i>
								</a>
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;">
								</a>
								<a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
								<i class="icon-trash"></i>
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div id="region_statistics_loading">
								<img src="../../assets/admin/layout/img/loading.gif" alt="loading"/>
							</div>
							<div id="region_statistics_content" class="display-none">
								<div class="btn-toolbar margin-bottom-10">
									<div class="btn-group btn-group-circle" data-toggle="buttons">
										<a href="" class="btn grey-salsa btn-sm active">
										Users </a>
										<a href="" class="btn grey-salsa btn-sm">
										Orders </a>
									</div>
									<div class="btn-group pull-right">
										<a href="" class="btn btn-circle grey-salsa btn-sm dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
										Select Region <span class="fa fa-angle-down">
										</span>
										</a>
										<ul class="dropdown-menu pull-right">
											<li>
												<a href="javascript:;" id="regional_stat_world">
												World </a>
											</li>
											<li>
												<a href="javascript:;" id="regional_stat_usa">
												USA </a>
											</li>
											<li>
												<a href="javascript:;" id="regional_stat_europe">
												Europe </a>
											</li>
											<li>
												<a href="javascript:;" id="regional_stat_russia">
												Russia </a>
											</li>
											<li>
												<a href="javascript:;" id="regional_stat_germany">
												Germany </a>
											</li>
										</ul>
									</div>
								</div>
								<div id="vmap_world" class="vmaps display-none">
								</div>
								<div id="vmap_usa" class="vmaps display-none">
								</div>
								<div id="vmap_europe" class="vmaps display-none">
								</div>
								<div id="vmap_russia" class="vmaps display-none">
								</div>
								<div id="vmap_germany" class="vmaps display-none">
								</div>
							</div>
						</div>
					</div>
					<!-- END REGIONAL STATS PORTLET-->
				</div>
				<div class="col-md-6 col-sm-6">
					<!-- BEGIN PORTLET-->
					<div class="portlet light">
						<div class="portlet-title tabbable-line">
							<div class="caption caption-md">
								<i class="icon-globe theme-font-color hide"></i>
								<span class="caption-subject theme-font-color bold uppercase">Feeds</span>
							</div>
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#tab_1_1" data-toggle="tab">
									System </a>
								</li>
								<li>
									<a href="#tab_1_2" data-toggle="tab">
									Activities </a>
								</li>
							</ul>
						</div>
						<div class="portlet-body">
							<!--BEGIN TABS-->
							<div class="tab-content">
								<div class="tab-pane active" id="tab_1_1">
									<div class="scroller" style="height: 337px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
										<ul class="feeds">
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 You have 4 pending tasks. <span class="label label-sm label-info">
																Take action <i class="fa fa-share"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New version v1.4 just lunched!
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 20 mins
													</div>
												</div>
												</a>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-danger">
																<i class="fa fa-bolt"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Database server #12 overloaded. Please fix the issue.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 24 mins
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-info">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New order received and pending for process.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 30 mins
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New payment refund and pending approval.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 40 mins
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-warning">
																<i class="fa fa-plus"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New member registered. Pending approval.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 1.5 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Web server hardware needs to be upgraded. <span class="label label-sm label-default ">
																Overdue </span>
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 2 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-default">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Prod01 database server is overloaded 90%.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 3 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-warning">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New group created. Pending manager review.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 5 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-info">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Order payment failed.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 18 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-default">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New application received.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 21 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-info">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Dev90 web server restarted. Pending overall system check.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 22 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-default">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New member registered. Pending approval
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 21 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-info">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 L45 Network failure. Schedule maintenance.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 22 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-default">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Order canceled with failed payment.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 21 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-info">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Web-A2 clound instance created. Schedule full scan.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 22 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-default">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Member canceled. Schedule account review.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 21 hours
													</div>
												</div>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-info">
																<i class="fa fa-bullhorn"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New order received. Please take care of it.
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 22 hours
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
								<div class="tab-pane" id="tab_1_2">
									<div class="scroller" style="height: 337px;" data-always-visible="1" data-rail-visible1="0" data-handle-color="#D7DCE2">
										<ul class="feeds">
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New order received
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 10 mins
													</div>
												</div>
												</a>
											</li>
											<li>
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-danger">
																<i class="fa fa-bolt"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 Order #24DOP4 has been rejected. <span class="label label-sm label-danger ">
																Take action <i class="fa fa-share"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 24 mins
													</div>
												</div>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
											<li>
												<a href="javascript:;">
												<div class="col1">
													<div class="cont">
														<div class="cont-col1">
															<div class="label label-sm label-success">
																<i class="fa fa-bell-o"></i>
															</div>
														</div>
														<div class="cont-col2">
															<div class="desc">
																 New user registered
															</div>
														</div>
													</div>
												</div>
												<div class="col2">
													<div class="date">
														 Just now
													</div>
												</div>
												</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!--END TABS-->
						</div>
					</div>
					<!-- END PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>

		<!-- BEGIN QUICK SIDEBAR -->
		<a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-close"></i></a>
		<div class="page-quick-sidebar-wrapper">
			<div class="page-quick-sidebar">
				<div class="nav-justified">
					<ul class="nav nav-tabs nav-justified" id="sp__nav__list">
						<li class="active sp__nav__basic" >
							<a href="#quick_sidebar_tab_1" data-toggle="tab">Conversaciones<span class="badge badge-danger">1</span></a>
						</li>
						<li class="sp__nav__basic">
							<a href="#quick_sidebar_tab_2" data-toggle="tab">Usuarios</a>
						</li>
						<li class="active sp__nav__comp" style="display:none !important;">
							<a href="#quick_sidebar_tab_2" data-toggle="tab">Usuario 1</a>
						</li>
					</ul>
					<div class="tab-content">
						<!-- div de conversaciones -->
						<div class="tab-pane active page-quick-sidebar-chat" id="quick_sidebar_tab_1">
							
							<!-- listado de conversaciones -->
							<div class="page-quick-sidebar-chat-users" data-rail-color="#ddd" data-wrapper-class="page-quick-sidebar-list">
								<ul class="media-list list-items">
									<li class="media" data-userId="1" data-userName="Usuario 1">
										<div class="media-status">
											<span class="badge badge-danger">1</span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 1</h4>
											<div class="date">
												 Mensaje de Prueba 1 Usuario 1
											</div>
											<div class="media-heading-small">
												15 min
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="2" data-userName="Usuario 2">
										<div class="media-status">
											<i class="fa fa-share"></i>
										</div>									
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 2</h4>
											<div class="date">
												 Mensaje de Prueba 1 Usuario 2
											</div>
											<div class="media-heading-small">
												1 hora
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="3" data-userName="Usuario 3">
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 3</h4>
											<div class="date">
												 Mensaje de Prueba 1 Usuario 3
											</div>
											<div class="media-heading-small">
												3 horas
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="4" data-userName="Usuario 4">
									<div class="media-status">
											<i class="fa fa-check"></i>
										</div>		
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 4</h4>
											<div class="date">
												 Mensaje de Prueba 1 Usuario 4
											</div>
											<div class="media-heading-small">
												6 horas
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="5" data-userName="Usuario 5">
									<div class="media-status">
											<i class="fa fa-share"></i>
										</div>		
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 5</h4>
											<div class="date">
												 Mensaje de Prueba 1 Usuario 5
											</div>
											<div class="media-heading-small">
												ayer 
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="6" data-userName="Usuario 6">
									<div class="media-status">
											<i class="fa fa-share"></i>
										</div>		
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 6</h4>
											<div class="date">
												 Mensaje de Prueba 1 Usuario 6
											</div>
											<div class="media-heading-small">
												30/jun/2015
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="7" data-userName="Usuario 7">
										<div class="media-status">
											<i class="fa fa-check"></i>
										</div>		
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 7</h4>
											<div class="date">
												 Mensaje de Prueba Usuario 7
											</div>
											<div class="media-heading-small">
												30/jun/2015
											</div>
										</div>
									</li>
									
									<li class="media" data-userId="8" data-userName="Usuario 8">
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario 8</h4>
											<div class="date">
												 Mensaje de Prueba Usuario 8
											</div>
											<div class="media-heading-small">
												30/jun/2015
											</div>
										</div>
									</li>	
								</ul>
							</div>
						</div>
					
						<!-- tab de usuarios conectados y contactos -->
						<div class="tab-pane page-quick-sidebar-chat" id="quick_sidebar_tab_2">
							<!-- usuarios -->
							<div class="page-quick-sidebar-chat-users" data-rail-color="#ddd" data-wrapper-class="page-quick-sidebar-list">
								<h3 class="list-heading">Contactos Frecuentes</h3>
								<ul class="media-list list-items">
									<li class="media" data-userId="1" data-userName="Usuario Frecuente 1">
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario Frecuente 1</h4>
											<div class="media-heading-sub">
												 Puesto Usuario Frecuente 1
											</div>
											<div class="media-heading-small">
												 �ltima Conexi�n: 31/07/2105
											</div>
										</div>
									</li>
									<li class="media" data-userId="2" data-userName="Usuario Frecuente 2">
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario Frecuente 2</h4>
											<div class="media-heading-sub">
												 Puesto Usuario Frecuente 2
											</div>
											<div class="media-heading-small">
												 �ltima Conexi�n: 28/07/2105
											</div>
										</div>
									</li>
									<li class="media" data-userId="3" data-userName="Usuario Frecuente 3">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario Frecuente 3</h4>
											<div class="media-heading-sub">
												 Puesto Usuario Frecuente 3
											</div>
										</div>
									</li>
									<li class="media" data-userId="4" data-userName="Usuario Frecuente 4">
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario Frecuente 4</h4>
											<div class="media-heading-sub">
												 Puesto Usuario Frecuente 4
											</div>
											<div class="media-heading-small">
												 �ltima Conexi�n: hace 1 hora
											</div>
										</div>
									</li>
									<li class="media" data-userId="5" data-userName="Usuario Frecuente 5">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario Frecuente 5</h4>
											<div class="media-heading-sub">
												 Puesto Usuario Frecuente 5
											</div>
										</div>
									</li>
								</ul>
								<!-- esto va para el div de usuarios -->
								<h3 class="list-heading">En L�nea</h3>
								<ul class="media-list list-items">								
									<li class="media" data-userId="1" data-userName="Usuario en Linea 1">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario en Linea 1</h4>
											<div class="media-heading-sub">
												 Puesto usuario en Linea 1
											</div>
										</div>
									</li>
									<li class="media" data-userId="2" data-userName="Usuario en Linea 2">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario en Linea 2</h4>
											<div class="media-heading-sub">
												 Puesto usuario en Linea 2
											</div>
										</div>
									</li>
									<li class="media" data-userId="3" data-userName="Usuario en Linea 3">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario en Linea 3</h4>
											<div class="media-heading-sub">
												 Puesto usuario en Linea 3
											</div>
										</div>
									</li>
									<li class="media" data-userId="4" data-userName="Usuario en Linea 4">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario en Linea 4</h4>
											<div class="media-heading-sub">
												 Puesto usuario en Linea 4
											</div>
										</div>
									</li>
									<li class="media" data-userId="5" data-userName="Usuario en Linea 5">
										<div class="media-status">
											<span class="badge badge-success"> </span>
										</div>
										<img class="media-object" src='<c:url value="/resources/img/avatar.png"/>' alt="...">
										<div class="media-body">
											<h4 class="media-heading">Usuario en Linea 5</h4>
											<div class="media-heading-sub">
												 Puesto usuario en Linea 5
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>

						<!-- contenedor de chat per se -->
						<div class="page-quick-sidebar-item">
							<div class="page-quick-sidebar-chat-user">
								<!-- boton de regresar -->
								<div class="page-quick-sidebar-nav">
									<a href="javascript:;" class="page-quick-sidebar-back-to-list"><i class="icon-arrow-left"></i>Regresar</a>
								</div>
								<!-- listado de mensajes -->
								<div class="page-quick-sidebar-chat-user-messages">
									<div class="post out">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">${username}</a>
											<span class="datetime">20:15</span>
											<span class="body">Saludo de prueba 1</span>
										</div>
									</div>
									<div class="post in">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">Ella Wong</a>
											<span class="datetime">20:15</span>
											<span class="body">Respondiendo saludo 1</span>
										</div>
									</div>
									<div class="post out">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">${username}</a>
											<span class="datetime">20:15</span>
											<span class="body">Saludo de prueba 2</span>
										</div>
									</div>
									<div class="post in">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">Ella Wong</a>
											<span class="datetime">20:16</span>
											<span class="body">Respondiendo saludo 2</span>
										</div>
									</div>
									<div class="post out">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">${username}</a>
											<span class="datetime">20:17</span>
											<span class="body">Saludos de prueba 3</span>
										</div>
									</div>
									<div class="post in">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">Ella Wong</a>
											<span class="datetime">20:40</span>
											<span class="body">Respondiento saludo 3</span>
										</div>
									</div>
									<div class="post out">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">${username}</a>
											<span class="datetime">20:17</span>
											<span class="body">Saludo de prueba 4</span>
										</div>
									</div>
									<div class="post in">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">Ella Wong</a>
											<span class="datetime">20:40</span>
											<span class="body">Respondiendo saludo 4</span>
										</div>
									</div>
									<div class="post out">
										<img class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
										<div class="message">
											<span class="arrow"></span>
											<a href="javascript:;" class="name">${username}</a>
											<span class="datetime">20:17</span>
											<span class="body">Saludo de prueba 5</span>
										</div>
									</div>
								</div>
								<div class="page-quick-sidebar-chat-user-form">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="Escriba un mensaje...">
										<div class="input-group-btn">
											<button type="button" class="btn blue">Enviar</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END QUICK SIDEBAR -->
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
		 2014 &copy; Metronic by keenthemes. <a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" title="Purchase Metronic just for 27$ and get lifetime updates for free" target="_blank">Purchase Metronic!</a>
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
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
<!--  
<script src="../../assets/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
-->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag anddrop support -->
<script src='<c:url value="/resources/js/plugins/morris.min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/plugins/raphael-min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/plugins/jquery.sparkline.min.js"/>' type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src='<c:url value="/resources/js/global/ui-blockui.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/global/metronic.js"/>' type="text/javascript"></script>

<script src='<c:url value="/resources/js/global/layout4.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/global/demo4.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/global/quick-sidebar2.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/global/index3.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/global/tasks.js"/>' type="text/javascript"></script>
<script src='<c:url value="/resources/js/utils/secopre.js"/>' type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   Demo.init(); // init demo features
   UIBlockUI.init(); //block ui
   QuickSidebar.init(); // init quick sidebar
   Index.init(); // init index page
   Tasks.initDashboardWidget(); // init tash dashboard widget  
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>