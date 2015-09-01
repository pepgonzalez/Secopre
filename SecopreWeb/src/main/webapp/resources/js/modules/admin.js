function initPermPage(){
	initAdminPage('Perm');
}
function initRolePage(){
	initAdminPage('Role');
}
function initUserPage(){
	initAdminPage('User');
	initUserValidations();
}

function initAdminPage(page){
	$('#'+page+'Table').DataTable();
	$('#add_'+page).hide();
	$('button.btn.green').click(function() {
		$('#add_'+page).show();
		$('#list_'+page).hide();
	});
	$('button.btn.default').click(function() {
		$('#add_'+page).hide();
		$('#list_'+page).show();
	});
	$('button.btn.blue').click(function() {
		submitAjaxJQ(page+'Form', 'dashboard','initAdminPage('+page+');');
	});	

}
function initRoleValidations(){
	var form = $('#roleForm');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);
	form.validate({
		errorElement : 'span', // default input error message container
		errorClass : 'help-block help-block-error', // default input error
													// message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		rules : {
			rolename : {
				minlength : 6,
				maxlength : 50,
				required : true
			}
		},

		invalidHandler : function(event, validator) { // display error alert
														// on form submit
			success.hide();
			error.show();
			Metronic.scrollTo(error, -50);
		},

		errorPlacement : function(error, element) { // render error placement
													// for each input type
			var icon = $(element).parent('.input-icon').children('i');
			icon.removeClass('fa-check').addClass("fa-warning");
			icon.attr("data-original-title", error.text()).tooltip({
				'container' : 'body'
			});
		},

		highlight : function(element) { // hightlight error inputs
			$(element).closest('.form-group').removeClass("has-success")
					.addClass('has-error'); // set error class to the control
											// group
		},

		unhighlight : function(element) { // revert the change done by
											// hightlight

		},

		success : function(label, element) {
			alert('sucess');
			var icon = $(element).parent('.input-icon').children('i');
			$(element).closest('.form-group').removeClass('has-error')
					.addClass('has-success'); // set success class to the
												// control group
			icon.removeClass("fa-warning").addClass("fa-check");
		},

		submitHandler : function(form) {
			alert('chidito');
			success.show();
			error.hide();
			form[0].submit(); // submit the form
		}
	});
	
}
function initUserValidations(){
	var form = $('#submit_form');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);
	form.validate({
		errorElement : 'span', // default input error message container
		errorClass : 'help-block help-block-error', // default input error
													// message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		rules : {
			username : {
				minlength : 6,
				required : true
			},
			email : {
				required : true,
				email : true
			},
			email : {
				required : true,
				email : true
			},
			url : {
				required : true,
				url : true
			},
			number : {
				required : true,
				number : true
			},
			digits : {
				required : true,
				digits : true
			},
			creditcard : {
				required : true,
				creditcard : true
			},
		},

		invalidHandler : function(event, validator) { // display error alert
														// on form submit
			success.hide();
			error.show();
			Metronic.scrollTo(error, -50);
		},

		errorPlacement : function(error, element) { // render error placement
													// for each input type
			var icon = $(element).parent('.input-icon').children('i');
			icon.removeClass('fa-check').addClass("fa-warning");
			icon.attr("data-original-title", error.text()).tooltip({
				'container' : 'body'
			});
		},

		highlight : function(element) { // hightlight error inputs
			$(element).closest('.form-group').removeClass("has-success")
					.addClass('has-error'); // set error class to the control
											// group
		},

		unhighlight : function(element) { // revert the change done by
											// hightlight

		},

		success : function(label, element) {
			var icon = $(element).parent('.input-icon').children('i');
			$(element).closest('.form-group').removeClass('has-error')
					.addClass('has-success'); // set success class to the
												// control group
			icon.removeClass("fa-warning").addClass("fa-check");
		},

		submitHandler : function(form) {
			success.show();
			error.hide();
			form[0].submit(); // submit the form
		}
	});
	
}

