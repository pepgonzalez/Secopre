<!-- BEGIN CONTAINER -->
<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Distritos<small> Ubicación de Distritos</small></h1>
				</div>
				<!-- END PAGE TITLE -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="index.html">Inicio</a>
					<i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="#">Directorio</a>
					<i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="#">Distritos</a>
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			<!-- END PAGE HEADER-->
			
			<!-- BEGIN PAGE CONTENT-->
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN STATIC PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								Distritos
							</div>
						</div>
						<div class="portlet-body">
							<div id="gmap_static" class="gmaps">
								<div style="height:100%;display:block;background: url(http://maps.google.com/maps/api/staticmap?center=25.706969,-100.285032&zoom=16&path=color:0x0000FF80|weight:5|25.70859,-100.28269&size=700x700&sensor=TRUE_OR_FALSE) no-repeat 50% 50%;">
									<img src="http://maps.google.com/maps/api/staticmap?center=25.706969,-100.285032&zoom=16&path=color:0x0000FF80|weight:5|25.70859,-100.28269&size=700x700&sensor=TRUE_OR_FALSE" style="visibility:hidden" alt=""/>
								</div>
							</div>
						</div>
					</div>
					<!-- END STATIC PORTLET-->
				</div>
			</div>

			<!-- END PAGE CONTENT-->