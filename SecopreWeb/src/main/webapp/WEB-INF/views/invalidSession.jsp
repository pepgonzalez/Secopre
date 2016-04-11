<%@ include file="/WEB-INF/views/auth/common/springTags.jsp"%>
	<form name="myform" action="Secopre/login">
		<div class="row">
			<div class="col-md-6 coming-soon-content">
				<h1>${title}</h1>
				<p>${message}</p>

				<br>
				<div class="form-actions">
					<button type="button" class="btn blue pull-right" onclick="document.myform.submit();">
						Entiendo <i class="m-icon-swapright m-icon-white"></i>
					</button>
				</div>			
			</div>
		</div>
	</form>