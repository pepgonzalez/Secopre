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
				
	</div>
	<!-- END CONTENT -->