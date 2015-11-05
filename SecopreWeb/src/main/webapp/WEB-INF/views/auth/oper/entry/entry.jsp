<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			<!-- BEGIN PAGE HEAD -->
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
<!-- 				<div class="page-title"> -->
<!-- 					<h1>Catalogos<small> Partidas</small></h1> -->
<!-- 				</div> -->
			</div>
			<!-- END PAGE HEAD -->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="javascript:;">Catalogos </a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					  Partidas
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bubble font-green-sharp"></i>
                                        <span class="caption-subject font-green-sharp bold uppercase">Configuración de Partidas</span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <ul class="nav nav-pills">
                                        <li class="active">
                                            <a href="#tab_2_1" data-toggle="tab">Catalogo Partidas</a>
                                        </li>
                                        <li>
                                            <a href="#tab_2_2" data-toggle="tab">Saldo de Partidas</a>
                                        </li>
                                        <li>
                                            <a href="#tab_2_3" data-toggle="tab">Presupuesto Anual</a>
                                        </li>
                                        <li>
                                            <a href="#tab_2_4" data-toggle="tab">Calendarizacion de Saldos</a>
                                        </li>
                                    </ul>

                                     <div class="tab-content">
                                        <div class="tab-pane fade active in" id="tab_2_1">
											<!-- FORMULARIO AGREGAR PARTIDAS-->
											<div id="add_Entry">
												<%@ include file="/WEB-INF/views/auth/oper/entry/add.jsp"%>
											</div>
											<!-- TERMINA FORMULARIO AGREGAR PARTIDAS -->
											
											<!-- LISTA PUESTOS -->
											<div id="list_Entry">
												<%@ include file="/WEB-INF/views/auth/oper/entry/list.jsp"%>
											</div>
											<!-- TERMINA LISTA PARTIDAS -->			
                                         </div>
                                        <div class="tab-pane fade" id="tab_2_2">
                                        </div>
                                        <div class="tab-pane fade" id="tab_2_3">
                                        </div>
                                        <div class="tab-pane fade" id="tab_2_4">
                                        </div>
                                        
                                     </div>                               
                            	</div>
                            </div>


	
	