	<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
			
			<div class="row" style="display: inline;">
				<div class="col-md-12">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption font-green-haze">
								<i class="icon-settings font-green-haze"></i>
								<span class="caption-subject bold uppercase">Rectificaciones</span>
							</div>
							<div class="actions">
								<a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title="">
								</a>
							</div>
						</div>
						
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
									</div>
									<div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="application.tools"/><i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li>
													<a href="javascript:;" onclick="exportToExcel('#rectificationList', rectificationList);"><spring:message code="application.export.excel"/></a>
												</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="row" style="margin-top:10px;">
									<div class="col-md-12">
										<div class="btn-group">
											<input type="text" id="rectificationDateSearch" placeholder="Buscar..." class="form-control input-medium">
										</div>
									</div>
								</div>
							</div>
						<div>	
					
							<table class="table table-striped table-bordered table-hover" id="rectificationList">
							<thead>
							<tr>
								<th style="visible:false;">id</th>
								<th>Folio</th>
								<th>Justificación</th>
								<th>Trámite</th>
								<th>Importe</th>
								<th>Fecha Creación</th>
								<th>Usuario que autorizó</th>
								<th>Rectificación / Acciones</th>
							</tr>
							</thead>
							<tbody>
								<c:forEach items="${inboxList}" var="inboxItem">
									<tr class="odd gradeX">
										<td style="visible:false;">${inboxItem.requestId}</td>
										<td>${inboxItem.folio}</td>
										<td>${inboxItem.justification}</td>
										<td>${inboxItem.formalityDescription}</td>
										<td>${inboxItem.totalAmount}</td>
										<td>${inboxItem.creationDate}</td>
										<td>${inboxItem.userName}</td>
										<td>
											<c:if test="${inboxItem.hasRectification == false}">
												<a href="#" onclick="sendRequestJQ('auth/wf/rectification/${inboxItem.requestId}','dashboard','noAction()','GET');">
													Rectificar
												</a>
											</c:if>
											<c:if test="${inboxItem.hasRectification == true}">
												${inboxItem.rectificationFolio}
											</c:if>
										</td>
									</tr>							
								</c:forEach>
							</tbody>
							</table>
						</div>
					</div>
					<!-- END SAMPLE FORM PORTLET-->
				</div>
			</div>

	<script type="text/javascript">
		$(document).ready(function(){
			rectificationList();
		});
	</script>