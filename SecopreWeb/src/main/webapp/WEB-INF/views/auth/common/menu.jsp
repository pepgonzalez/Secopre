<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>

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
			<ul id="menuList" class="page-sidebar-menu page-sidebar-menu-hover-submenu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<li class="start active ">
					<a href="javascript:;">
					<i class="icon-home"></i>
					<span class="title">Mi Dashboard</span>
					</a>
				</li>
				<c:forEach items="${menus}" var="itemMenu" varStatus="itemStatus">
					<li id="${itemMenu.parent.jsId}">
						<a href="javascript:;" onclick="${itemMenu.parent.jsFunction}">
							<i class="${itemMenu.parent.cssClass}"></i>	
							<span class="title">${itemMenu.parent.name}</span>	
							<span class="arrow "></span>
						</a>	
						<c:if test="${fn:length(itemMenu.childs) > 0}">
							<ul class="sub-menu">
								<c:forEach items="${itemMenu.childs}" var="itemSubMenu" varStatus="itemSubStatus">
									<li>
										<a href="javascript:;" onclick="${itemSubMenu.jsFunction}">
										<i class="${itemSubMenu.cssClass}">
										</i>${itemSubMenu.name}</a>
										<c:if test="${itemSubStatus.index == 0}">
											<span class="arrow "></span>
										</c:if>
									</li>								
								</c:forEach>
							</ul>														
						</c:if>							
					</li>
				</c:forEach>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	<!-- END SIDEBAR -->