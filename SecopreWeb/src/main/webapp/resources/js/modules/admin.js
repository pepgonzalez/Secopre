function initPermPage() {
	initAdminPage('Perm');
}
function initRolePage() {
	initAdminPage('Role');
}
function initUserPage() {
	initAdminPage('User');
	initUserValidations();
	$('#roles').multiSelect();
	$('#permissions').multiSelect();
}

function initUserList() {
	sendRequestJQ('auth/adm/usr/list','dashboard','initUserPage()');
}

function initPersonList() {
	sendRequestJQ('auth/cat/person/list','dashboard','initPersonPage()');
}

function initPersonPage() {
	initAdminPage('Person');
	initPersonValidations();
	$('#my_multi_select1').multiSelect();
}

function initAdminPage(page) {
	$('#' + page + 'Table').DataTable();
	$('#add_' + page).hide();
	$('button.btn.green').click(function() {
		$('#add_' + page).show();
		$('#list_' + page).hide();
	});
	$('button.btn.default').click(function() {
		$('#add_' + page).hide();
		$('#list_' + page).show();
	});
	$('button.btn.blue').click(
			function() {
				console.log("submit boton blue");
				submitAjaxJQ(page + 'Form', 'dashboard', 'initAdminPage('
						+ page + ');');
			});

}
function initUserValidations() {

	var form = $('#submit_form');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);
	// alert('validando');
	form.validate({
		doNotHideMessage : true,
		errorElement : 'span', // default input error message container
		errorClass : 'help-block help-block-error', // default input error
		// message class
		focusInvalid : false, // do not focus the last invalid input
		// ignore : "", // validate all fields including form hidden input
		rules : {
			username : {
				minlength : 6,
				required : true
			},
			nickname : {
				minlength : 6,
				required : true
			},
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 6
			},
			rpassword : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			roles: "required",
			permissions: "required"
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
			$(element).closest('.form-group').removeClass('has-success')
					.addClass('has-error'); // set error class to the control
			// group
		},

		unhighlight : function(element) { // revert the change done by
			// hightlight
			$(element).closest('.form-group').removeClass('has-error'); // set
			// error
			// class
			// to
			// the
			// control
			// group
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

	var displayConfirm = function() {
		$('#tab4 .form-control-static', form).each(
				function() {
					var input = $('[name="' + $(this).attr("data-display")
							+ '"]', form);
					if (input.is(":radio")) {
						input = $('[name="' + $(this).attr("data-display")
								+ '"]:checked', form);
					}
					if (input.is(":text") || input.is("textarea")) {
						$(this).html(input.val());
					} else if (input.is("select")) {
						var elements = [];
						input.each(function() {
						    var selectedOption = $(this).find('option:selected');
						    elements.push(selectedOption.text());
						});					
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					}else {
						$(this).html($("input[name='email']").val());
					}
				});
	}

	var handleTitle = function(tab, navigation, index) {
		var total = navigation.find('li').length;
		var current = index + 1;
		// set wizard title
		$('.step-title', $('#form_wizard_1')).text(
				'Paso ' + (index + 1) + ' de ' + total);
		// set done steps
		jQuery('li', $('#form_wizard_1')).removeClass("done");
		var li_list = navigation.find('li');
		for (var i = 0; i < index; i++) {
			jQuery(li_list[i]).addClass("done");
		}

		if (current == 1) {
			$('#form_wizard_1').find('.button-previous').hide();
		} else {
			$('#form_wizard_1').find('.button-previous').show();
		}

		if (current >= total) {
			$('#form_wizard_1').find('.button-next').hide();
			$('#form_wizard_1').find('.button-submit').show();
			displayConfirm();
		} else {
			$('#form_wizard_1').find('.button-next').show();
			$('#form_wizard_1').find('.button-submit').hide();
		}
		Metronic.scrollTo($('.page-title'));
	}

	// default form wizard
	$('#form_wizard_1').bootstrapWizard({
		'nextSelector' : '.button-next',
		'previousSelector' : '.button-previous',
		onTabClick : function(tab, navigation, index, clickedIndex) {
			return false;
			/*
			 * success.hide(); error.hide(); if (form.valid() == false) { return
			 * false; } handleTitle(tab, navigation, clickedIndex);
			 */
		},
		onNext : function(tab, navigation, index) {

			success.hide();
			error.hide();

			if (form.valid() == false) {
				return false;
			}

			handleTitle(tab, navigation, index);
		},
		onPrevious : function(tab, navigation, index) {

			success.hide();
			error.hide();

			handleTitle(tab, navigation, index);
		},
		onTabShow : function(tab, navigation, index) {
			var total = navigation.find('li').length;
			var current = index + 1;
			var $percent = (current / total) * 100;
			$('#form_wizard_1').find('.progress-bar').css({
				width : $percent + '%'
			});
		}
	});

	$('#form_wizard_1').find('.button-previous').hide();
	$('#form_wizard_1 .button-submit').click(function() {
	//	formId, targetId,after
	//	submitAjaxJQ('submit_form','dashboard','');
	}).hide();
	
	$('#submitRequestForm').click(function() {
		if (form.valid()){
			submitAjaxJQ('submit_form','dashboard','initUserList()');
		}
	});	
}

/*funciones de tramites **OJO** mala idea declarar las funciones en scope global*/
function initTramitePage(){
	
		//se obtiene la forma
		var requestForm = $("#requestForm");
		var error = $('.alert-danger', requestForm);
		var success = $('.alert-success', requestForm);
		
		//se define la validacion
		requestForm.validate({
			doNotHideMessage : true,
			errorElement : 'span', // default input error message container
			errorClass : 'help-block help-block-error', // default input error
			// message class
			focusInvalid : false, // do not focus the last invalid input
			
			rules : {
				formalityId : {
					required : true,
					min : 1
				},
				firstName : {
					required : true
				},
				parentLastName : {
					required : true
				},
				motherLastName :{
					required : true
				}
			},

			invalidHandler : function(event, validator) { // display error alert
				// on form submit
				success.hide();
				error.show();
				Metronic.scrollTo(error, -50);
			},

			errorPlacement : function(error, element) {
				var icon = $(element).parent('.input-icon').children('i');
				icon.removeClass('fa-check').addClass("fa-warning");
				icon.attr("data-original-title", error.text()).tooltip({'container' : 'body'});
			},

			highlight : function(element) { 
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},

			unhighlight : function(element) { 
				$(element).closest('.form-group').removeClass('has-error');
			},

			success : function(label, element) {
				var icon = $(element).parent('.input-icon').children('i');
				$(element).closest('.form-group').removeClass('has-error')
						.addClass('has-success'); // set success class to the
				// control group
				icon.removeClass("fa-warning").addClass("fa-check");
			},

			submitHandler : function(form) {
				form[0].submit(); // submit the form
			}
		});
		
		$('#submitRequestForm').click(function() {
			if (requestForm.valid()){
				submitAjaxJQ('requestForm','dashboard','');
			}
		});		
}


function initPersonValidations() {

	var form = $('#submit_form');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);
	// alert('validando');
	form.validate({
		doNotHideMessage : true,
		errorElement : 'span', // default input error message container
		errorClass : 'help-block help-block-error', // default input error
		// message class
		focusInvalid : false, // do not focus the last invalid input
		// ignore : "", // validate all fields including form hidden input
		rules : {
			name : {
				required : true
			},
			secondName : {
			},
			fatherLastName : {
				required : true
			},
			motherLastName : {
				required : true
			},
			telephone : {
				required : true
			},
			mobileTelepone:{
				required : true
			},
			twitter:{
			},
			facebook:{
			},
			webSite:{
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
			$(element).closest('.form-group').removeClass('has-success')
					.addClass('has-error'); // set error class to the control
			// group
		},

		unhighlight : function(element) { // revert the change done by
			// hightlight
			$(element).closest('.form-group').removeClass('has-error'); // set
			// error
			// class
			// to
			// the
			// control
			// group
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

	var displayConfirm = function() {
		$('#tab4 .form-control-static', form).each(
				function() {
					var input = $('[name="' + $(this).attr("data-display")
							+ '"]', form);
					if (input.is(":radio")) {
						input = $('[name="' + $(this).attr("data-display")
								+ '"]:checked', form);
					}
					if (input.is(":text") || input.is("textarea")) {
						$(this).html(input.val());
					} else if (input.is("select")) {
						var elements = [];
						input.each(function() {
						    var selectedOption = $(this).find('option:selected');
						    elements.push(selectedOption.text());
						});					
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					}else {
						$(this).html($("input[name='email']").val());
					}
				});
	}

	var handleTitle = function(tab, navigation, index) {
		var total = navigation.find('li').length;
		var current = index + 1;
		// set wizard title
		$('.step-title', $('#form_wizard_1')).text(
				'Paso ' + (index + 1) + ' de ' + total);
		// set done steps
		jQuery('li', $('#form_wizard_1')).removeClass("done");
		var li_list = navigation.find('li');
		for (var i = 0; i < index; i++) {
			jQuery(li_list[i]).addClass("done");
		}

		if (current == 1) {
			$('#form_wizard_1').find('.button-previous').hide();
		} else {
			$('#form_wizard_1').find('.button-previous').show();
		}

		if (current >= total) {
			$('#form_wizard_1').find('.button-next').hide();
			$('#form_wizard_1').find('.button-submit').show();
			displayConfirm();
		} else {
			$('#form_wizard_1').find('.button-next').show();
			$('#form_wizard_1').find('.button-submit').hide();
		}
		Metronic.scrollTo($('.page-title'));
	}

	// default form wizard
	$('#form_wizard_1').bootstrapWizard({
		'nextSelector' : '.button-next',
		'previousSelector' : '.button-previous',
		onTabClick : function(tab, navigation, index, clickedIndex) {
			return false;
			/*
			 * success.hide(); error.hide(); if (form.valid() == false) { return
			 * false; } handleTitle(tab, navigation, clickedIndex);
			 */
		},
		onNext : function(tab, navigation, index) {

			success.hide();
			error.hide();

			if (form.valid() == false) {
				return false;
			}

			handleTitle(tab, navigation, index);
		},
		onPrevious : function(tab, navigation, index) {

			success.hide();
			error.hide();

			handleTitle(tab, navigation, index);
		},
		onTabShow : function(tab, navigation, index) {
			var total = navigation.find('li').length;
			var current = index + 1;
			var $percent = (current / total) * 100;
			$('#form_wizard_1').find('.progress-bar').css({
				width : $percent + '%'
			});
		}
	});

	$('#form_wizard_1').find('.button-previous').hide();
	$('#form_wizard_1 .button-submit').click(function() {
//		formId, targetId,after
//		submitAjaxJQ('submit_form','dashboard','');
	}).hide();
	
	$('#submitRequestForm').click(function() {
		if (form.valid()){
			submitAjaxJQ('submit_form','dashboard','initPersonList()');
		}
	});		
}

function initTramiteListPage(){
}

function initFullCapture(){
	alert("Iniciando captura completa");
	
	var requestForm = $('#requestForm');
	
	$('#partialSave').click(function(e){
		alert("haciendo guardado parcial");
		requestForm.find('#nextStageValueCode').val("SOLPEND");
		submitAjaxJQ('requestForm','dashboard','');
	});
	
	$('#saveAndContinue').click(function(e){
		alert("Finalizando captura y avanzando tramite");
		requestForm.find('#nextStageValueCode').val("SOLCOMP");
		submitAjaxJQ('requestForm','dashboard','');
	});
}

function initFullCapture(){
	alert("Iniciando captura completa");
	
	var requestForm = $('#requestForm');
	
	$('#partialSave').click(function(e){
		alert("haciendo guardado parcial");
		requestForm.find('#nextStageValueCode').val("SOLPEND");
		submitAjaxJQ('requestForm','dashboard','');
	});
	
	$('#saveAndContinue').click(function(e){
		alert("Finalizando captura y avanzando tramite");
		requestForm.find('#nextStageValueCode').val("SOLCOMP");
		submitAjaxJQ('requestForm','dashboard','');
	});
}

function initAuthorization(){
	alert("Iniciando autorizacion");
	
	var requestForm = $('#requestForm');
	
	$('#cancelFormality').click(function(e){
		alert("Cancelando Tramite");
		requestForm.find('#nextStageValueCode').val("CANCELAR");
		submitAjaxJQ('requestForm','dashboard','');
	});
	
	$('#authorizateFormality').click(function(e){
		alert("autorizando Tramite");
		requestForm.find('#nextStageValueCode').val("SIGFIRMA");
		submitAjaxJQ('requestForm','dashboard','');
	});
	
	$('#finishFormality').click(function(e){
		alert("autorizando Tramite y finalizar");
		requestForm.find('#nextStageValueCode').val("CONTINUAR");
		submitAjaxJQ('requestForm','dashboard','');
	});
}