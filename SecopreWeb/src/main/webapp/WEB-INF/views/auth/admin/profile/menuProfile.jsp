<div style="width: 250px;" class="profile-sidebar">
						<!-- PORTLET MAIN -->
						<div class="portlet light profile-sidebar-portlet">
							<!-- SIDEBAR USERPIC -->
							<div class="profile-userpic">
<!-- 								<img alt="" class="img-responsive" src="../../assets/admin/pages/media/profile/profile_user.jpg"> -->
								<img id="loggedUserAvatar" alt="" class="img-responsive" src='<c:url value="${avatar}"/>' />
							</div>
							<!-- END SIDEBAR USERPIC -->
							<!-- SIDEBAR USER TITLE -->
							<div class="profile-usertitle">
								<div class="profile-usertitle-name">
									 ${username}
								</div>
								<div class="profile-usertitle-job">
									 ${position}
								</div>
							</div>
							<!-- END SIDEBAR USER TITLE -->
							<!-- SIDEBAR BUTTONS -->
							<div class="profile-userbuttons">
								<button class="btn btn-circle green-haze btn-sm" type="button">Follow</button>
								<button class="btn btn-circle btn-danger btn-sm" type="button">Message</button>
							</div>
							<!-- END SIDEBAR BUTTONS -->
							<!-- SIDEBAR MENU -->
							<div class="profile-usermenu">
								<ul class="nav">
									<li class="active">
										<a href="javascript:showOverview();">
										<i class="icon-home"></i>
										Overview </a>
									</li>
									<li>
										<a href="javascript:showProfileAccount();">
										<i class="icon-settings"></i>
										Mi Cuenta </a>
									</li>
<!-- 									<li> -->
<!-- 										<a target="_blank" href="page_todo.html"> -->
<!-- 										<i class="icon-check"></i> -->
<!-- 										Tareas </a> -->
<!-- 									</li> -->
<!-- 									<li> -->
<!-- 										<a href="extra_profile_help.html"> -->
<!-- 										<i class="icon-info"></i> -->
<!-- 										Ayuda </a> -->
<!-- 									</li> -->
								</ul>
							</div>
							<!-- END MENU -->
						</div>
						<!-- END PORTLET MAIN -->
						<!-- PORTLET MAIN -->
						<div class="portlet light">
							<!-- STAT -->
<!-- 							<div class="row list-separated profile-stat"> -->
<!-- 								<div class="col-md-4 col-sm-4 col-xs-6"> -->
<!-- 									<div class="uppercase profile-stat-title"> -->
<!-- 										 37 -->
<!-- 									</div> -->
<!-- 									<div class="uppercase profile-stat-text"> -->
<!-- 										 Projects -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-4 col-sm-4 col-xs-6"> -->
<!-- 									<div class="uppercase profile-stat-title"> -->
<!-- 										 51 -->
<!-- 									</div> -->
<!-- 									<div class="uppercase profile-stat-text"> -->
<!-- 										 Tasks -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-4 col-sm-4 col-xs-6"> -->
<!-- 									<div class="uppercase profile-stat-title"> -->
<!-- 										 61 -->
<!-- 									</div> -->
<!-- 									<div class="uppercase profile-stat-text"> -->
<!-- 										 Uploads -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<!-- END STAT -->
							<div>
								<h4 class="profile-desc-title">Acerca de ${username}</h4>
								<span class="profile-desc-text">  ${user.information} </span>
								<div class="margin-top-20 profile-desc-link">
									<i class="fa fa-globe"></i>
									<a href="">${person.webSite}</a>
								</div>
								<div class="margin-top-20 profile-desc-link">
									<i class="fa fa-twitter"></i>
									<a href="">${person.twitter}</a>
								</div>
								<div class="margin-top-20 profile-desc-link">
									<i class="fa fa-facebook"></i>
									<a href="">${person.facebook}</a>
								</div>
							</div>
						</div>
						<!-- END PORTLET MAIN -->
					</div>