	<!-- BEGIN CONTENT -->
	<div  class="page-content-wrapper">
		<div id="dashboard" class="page-content">
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Dashboard <small>estadisticas</small></h1>
				</div>
			</div>
			
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Home</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Dashboard
				</li>
			</ul>

            <!-- BEGIN DASHBOARD STATS 1-->
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat blue">
                        <div class="visual">
                            <i class="fa fa-comments"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value='<c:out value="${balance.annualAmount}"/>'><c:out value="${balance.annualAmount}"/></span> $
                            </div>
                            <div class="desc">Presupuesto Anual</div>
                        </div>
                        <a class="more" href="javascript:;">
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat red">
                        <div class="visual">
                            <i class="fa fa-bar-chart-o"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsing}"/>"><c:out value="${balance.budgetAsing}"/></span> $
                            	<div class="desc">Presupuesto Modificado</div>
                            </div>
                        </div>
                        <a class="more" href="javascript:;">
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>

                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat green">
                        <div class="visual">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <div class="details">
                            <div class="number">
                                <span data-counter="counterup" data-value="<c:out value="${balance.budgetCommit}"/>"><c:out value="${balance.budgetCommit}"/></span> $
                            </div>
                            <div class="desc">Total Comprometido</div>
                        </div>
                        <a class="more" href="javascript:;">
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>

                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                    <div class="dashboard-stat purple">
                        <div class="visual">
                            <i class="fa fa-globe"></i>
                        </div>
                        <div class="details">
                            <div class="number"> 
                                <span data-counter="counterup" data-value="<c:out value="${balance.budgetAsing - balance.budgetCommit}"/>"><c:out value="${balance.budgetAsing - balance.budgetCommit}"/></span> $
                            	<div class="desc"> Disponible</div>
                            </div>
                        </div>
                        <a class="more" href="javascript:;">
                            <i class="m-icon-swapright m-icon-white"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>

		</div>
		
		
		<!-- inicio chat -->
		<a href="javascript:;" class="page-quick-sidebar-toggler"><i class="icon-close"></i></a>
		<div class="page-quick-sidebar-wrapper">
			<div class="page-quick-sidebar">
				<div class="nav-justified">
					<ul class="nav nav-tabs nav-justified" id="sp__nav__list">
						
						<!-- tabs del menu de conversaciones y usuarios -->
						<li class="active sp__nav__basic" >
							<a id="conversationTab" href="#quick_sidebar_tab_1" data-toggle="tab">Conversaciones<span id="pndConvCounter" class="badge badge-danger">1</span></a>
						</li>
						<li class="sp__nav__basic">
							<a id="userTab" href="#quick_sidebar_tab_2" data-toggle="tab">Usuarios</a>
						</li>
						
						<!-- encabezado de apartado de chat -->
						<li class="active sp__nav__comp" style="display:none !important;">
							<a href="#quick_sidebar_tab_2" data-toggle="tab"></a>
						</li>
						
					</ul>
					<div class="tab-content">
						<!-- div de conversaciones -->
						<div class="tab-pane active page-quick-sidebar-chat" id="quick_sidebar_tab_1">
							
							<!-- listado de conversaciones -->
							<div class="page-quick-sidebar-chat-users" data-rail-color="#ddd" data-wrapper-class="page-quick-sidebar-list">
								<ul id="all_conversations_container" class="media-list list-items">
										<li id="no_conversations_msg">No hay conversaciones</li>								
								</ul>
								<!-- template de conversaciones -->
								<template id="conversation_template">
									<li data-conversation class="media" data-userId="" data-userName="">
										<div data-cStatus class="media-status"></div>
										<img data-avatar class="media-object" src="" alt="" style="max-width:50px!important;max-height:50px!important;">
										<div class="media-body">
											<h4 data-user class="media-heading"></h4>
											<div data-msg class="date"></div>
											<div data-time class="media-heading-small"></div>
										</div>
									</li>
								</template>	
							</div>
						</div>
					
						<!-- tab de usuarios conectados y contactos -->
						<div class="tab-pane page-quick-sidebar-chat" id="quick_sidebar_tab_2">
							<!-- usuarios -->
							<div class="page-quick-sidebar-chat-users" data-rail-color="#ddd" data-wrapper-class="page-quick-sidebar-list">
								
								<div id="searchPanel">
									<input id="searchUserInput" type="text" class="form-control" placeholder="Buscar un usuario..." style="margin-left:10%;width:80%;text-align:center;">
									<h3 id="finded_users" class="list-heading">Usuarios encontrados</h3>
									<ul id="finded_users_list" class="media-list list-items">
									</ul>
									
									<template id="finded_users_template">
									<li data-findedUser class="media" data-userId="" data-userName="">
										<div data-online class="media-status"><span class="badge badge-success"> </span></div>
										<img data-avatar class="media-object" src="" alt="">
										<div class="media-body">
											<h4 data-name class="media-heading"></h4>
											<div data-employment class="media-heading-sub"></div>
											<div data-lastConnection class="media-heading-small"></div>
										</div>
									</li>
								</template>
								</div>
								
								
								<h3 class="list-heading">Contactos Frecuentes</h3>
								
								<ul id="frecuent__users__list" class="media-list list-items">
									<li id="NoFrecuentUsrsMsgs">No existen usuarios frecuentes</li>
								</ul>
								
								<template id="frecuent_users_template">
									<li data-frecuentUser class="media" data-userId="" data-userName="">
										<div data-online class="media-status"><span class="badge badge-success"> </span></div>
										<img data-avatar class="media-object" src="" alt="">
										<div class="media-body">
											<h4 data-name class="media-heading"></h4>
											<div data-employment class="media-heading-sub"></div>
											<div data-lastConnection class="media-heading-small"></div>
										</div>
									</li>
								</template>
								
								
								
								<!-- esto va para el div de usuarios -->
								<h3 class="list-heading">En Línea</h3>
								<ul id="online__users__list" class="media-list list-items">								
									<li id="not__online__users__msg">No hay usuarios en línea</li>
								</ul>
								
								<template id="onlineUserTemplate">
									<li data-onlineUser class="media" data-userId="" data-userName="">
										<div class="media-status"><span class="badge badge-success"> </span></div>
										<img data-avatar class="media-object" src="" alt="">
										<div class="media-body">
											<h4 data-name class="media-heading"></h4>
											<div data-employment class="media-heading-sub"></div>
										</div>
									</li>
								</template>
								
							</div>
						</div>

						<!-- contenedor de chat per se -->
						<div class="page-quick-sidebar-item">
							<div class="page-quick-sidebar-chat-user">
								
								<!-- boton de regresar -->
								<div class="page-quick-sidebar-nav">
									<a href="javascript:;" id="chatReturnButton" class="page-quick-sidebar-back-to-list">
										<i class="icon-arrow-left"></i>Regresar
									</a>
								</div>
								
								<!-- listado de mensajes -->
								<div id="chat_container" class="page-quick-sidebar-chat-user-messages">
								</div>
								
								<template id="has_more_msgs">
									<div id="more_msgs_container" style="text-align:center;"></div>
										<a id="load_more_msgs" href="#" style="text-align:center;">Cargar mensajes anteriores...</a>
									</div>
								</template>
								
								<!-- plantilla de cuerpo de msg -->
								<template id="chat_message">
										<!-- post out post in -->
										<div msg>
											<img data-avatar class="avatar" alt="" src='<c:url value="/resources/img/avatar.png"/>'/>
											<div data-msg class="message">
												<span class="arrow"></span>
												<a data-username href="javascript:;" class="name"></a>
												<span data-time class="datetime"></span>
												<span data-msg-body class="body" style="word-wrap: break-word;"></span>
											</div>
										</div>
									</template>
								
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
		<!-- fin chat -->
				
	</div>
	<!-- END CONTENT -->