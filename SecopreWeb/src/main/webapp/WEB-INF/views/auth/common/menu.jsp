	<!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div id="step1" class="page-sidebar md-shadow-z-2-i  navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<ul class="page-sidebar-menu page-sidebar-menu-hover-submenu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="start active ">
					<a href="javascript:;">
					<i class="icon-home"></i>
					<span class="title">Mi Dashboard</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-settings"></i>
					<span class="title">Administracion</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="javascript:;" onclick="sendRequestJQ('auth/adm/usrList','dashboard','initUserAdmin()');">
							<i class="icon-home"></i>
							Usuarios</a>
							<span class="arrow "></span>
						</li>
						<li>
							<a href="javascript:;">
							<i class="icon-basket"></i>
							Configuracion Usuarios</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-layers"></i>
					<span class="title">Catalogos</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="javascript:;">
							<span class="badge badge-warning">new</span>Mantenimiento
							<span class="arrow "></span>
							</a>
							<ul class="sub-menu">
								<li>
									<a href="javascript:;">
									<span class="title">Claves Programaticas</span>
									</a>								
								</li>
								<li>
									<a href="javascript:;">
									<span class="title">Distritos</span>
									</a>								
								</li>
								<li>
									<a href="javascript:;">
									<span class="title">Parametros de Impresion</span>
									</a>								
								</li>
								<li>
									<a href="javascript:;">
									<span class="title">Partidas</span>
									</a>								
								</li>
								<li>
									<a href="javascript:;">
									<span class="title">Partidas por Distrito</span>
									</a>								
								</li>																																
							</ul>
						</li>
						<li>
							<a href="layout_sidebar_fixed.html">
							Operacion
							<span class="arrow "></span>
							</a>
							<ul class="sub-menu">
								<li>
									<a href="javascript:;">
									<span class="title">Gestion de Avisos</span>
									</a>								
								</li>
								<li>
									<a href="javascript:;">
									<span class="title">Presupuesto Anual</span>
									</a>								
								</li>
								<li>
									<a href="javascript:;">
									<span class="title">Personas</span>
									</a>								
								</li>																
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-puzzle"></i>
					<span class="title">Modulos</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="ui_general.html">
							Calendarizacion de Saldos</a>
						</li>
						<li>
							<a href="ui_buttons.html">
							Movimientos</a>
						</li>
						<li>
							<a href="ui_icons.html">
							<span class="badge badge-danger">new</span>Rectificaciones</a>
						</li>
						<li>
							<a href="ui_colors.html">
							Gastos</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:;">
					<i class="icon-notebook"></i>
					<span class="title">Reportes</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="components_pickers.html">
							Reporteador</a>
						</li>
					</ul>
				</li>
				<li class="last ">
					<a href="javascript:;">
					<i class="icon-pointer"></i>
					<span class="title">Directorio</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="javascript:;" onclick="sendRequestJQ('auth/adm/directory','dashboard');">
							Distritos</a>
							<span class="arrow"></span>
						</li>
					</ul>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->