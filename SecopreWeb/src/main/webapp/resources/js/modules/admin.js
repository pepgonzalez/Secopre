function annualBudget(){
	initEntryByDistrict();
}

function showProfile() {
	initProfileValidations();
	sendRequestJQ('auth/adm/profile/show', 'dashboard', 'initProfilePage()');
}

function showProfileAccount() {
	sendRequestJQ('auth/adm/profile/showProfileAccount', 'dashboard2','initProfilePage()');
}

function showOverview()
{   initProfileValidations();
	sendRequestJQ('auth/adm/profile/show', 'dashboard', 'initProfilePage()');
}

function initProfilePage() {
	initProfileValidations();
	initPasswordValidations();
	$('select').select2();
}


function initPermPage() {
	initPage('Perm');
	initPermValidations();
	$('select').select2();
}

function initPermList() {
sendRequestJQ('auth/adm/perm/list', 'dashboard', 'initPermPage()');
}

function initProfileValidations() {

	var form = $('#personal_form');
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
				required : true,
				maxlength : 30
			},
			secondName : {
				maxlength : 30
			},
			fatherLastName : {
				required : true,
				maxlength : 30
			},
			motherLastName : {
				required : true,
				maxlength : 30
			},
			telephone : {
				required : true,
				maxlength:12
			},
			mobileTelepone : {
				maxlength:12
			},
			webSite : {maxlength:100},
			nickname : {
				minlength : 6,
				maxlength : 20,
				required : true
			},
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 8,
				maxlength : 20,
				pwcheck_valid : true,
				pwcheck_digit : true,
				pwcheck_upper : true
			},
			rpassword : {
				required : true,
				equalTo : "#password"
			},
			"position.id" : {
				required : true
			},
			information : {
				maxlength:150
			}
		},
	    messages : {
		password : {
    		pwcheck_valid : "El password contiene caracteres no válidos. Verifique",
    		pwcheck_digit : "El password debe de tener al menos un número",
    		pwcheck_upper : "El password debe tener al menos una letra mayúscula"
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
			
			 if (element.parent(".input-group").size() > 0) {
                 error.insertAfter(element.parent(".input-group"));
             } else if (element.attr("data-error-container")) { 
                 error.appendTo(element.attr("data-error-container"));
             } else if (element.parents('.radio-list').size() > 0) { 
                 error.appendTo(element.parents('.radio-list').attr("data-error-container"));
             } else if (element.parents('.radio-inline').size() > 0) { 
                 error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
             } else if (element.parents('.checkbox-list').size() > 0) {
                 error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
             } else if (element.parents('.checkbox-inline').size() > 0) { 
                 error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
             } else {
                 error.insertAfter(element); // for other inputs, just
												// perform default behavior
             }
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
	
	var validator;
	
	var apiCallUnblock = function(actionURL, callback) {
		var method = method || "POST";
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url : context + '/' + actionURL,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				callback(data);
				
			}
		});
	};

	$.validator.addMethod(
		    "check_username",
		    function(value, element) {
		    	apiCallUnblock("auth/adm/usr/checkUsername2/" + value, function(data){
					
					result=data.result;
					if (result==0){
						validator = true;
		    		}else{
		    			validator = false;
		    		}	
		    	});
		    	
		    return validator;	
		    });
	
	$.validator.addMethod("pwcheck_valid", function(value) {
		   return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value)
		});
	
	$.validator.addMethod("pwcheck_upper", function(value) {
		   return  /[A-Z]/.test(value) // has a lowercase letter
		});
	
	$.validator.addMethod("pwcheck_digit", function(value) {
		   return /\d/.test(value) // has a digit
		});
   

	var displayConfirm = function() {
		$('#tab3 .form-control-static', form).each(
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
							var selectedOption = $(this)
									.find('option:selected');
							elements.push(selectedOption.text());
						});
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else {
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
		// formId, targetId,after
		// submitAjaxJQ('submit_form','dashboard','');
	}).hide();

	$('#submitRequestPersonalInfo').click(function() {
		if (form.valid()) {
			submitAjaxJQ('personal_form', 'dashboard', 'showProfile()');
		}
	});
}

function initPasswordValidations() {

	var form = $('#password_form');
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
			apassword : {
				pwcheck_exist : true,
				required : true
			},
			password : {
				required : true,
				minlength : 8,
				maxlength : 20,
				pwcheck_valid : true,
				pwcheck_digit : true,
				pwcheck_upper : true
			},
			rpassword : {
				required : true,
				equalTo : "#password"
			}
		},
	    messages : {
		password : {
    		pwcheck_valid : "El password contiene caracteres no válidos. Verifique",
    		pwcheck_digit : "El password debe de tener al menos un número",
    		pwcheck_upper : "El password debe tener al menos una letra mayúscula"
	     },
	    apassword : {
	    	pwcheck_exist : "El password no coincide con el actual. Verifique"
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
			
			 if (element.parent(".input-group").size() > 0) {
                 error.insertAfter(element.parent(".input-group"));
             } else if (element.attr("data-error-container")) { 
                 error.appendTo(element.attr("data-error-container"));
             } else if (element.parents('.radio-list').size() > 0) { 
                 error.appendTo(element.parents('.radio-list').attr("data-error-container"));
             } else if (element.parents('.radio-inline').size() > 0) { 
                 error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
             } else if (element.parents('.checkbox-list').size() > 0) {
                 error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
             } else if (element.parents('.checkbox-inline').size() > 0) { 
                 error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
             } else {
                 error.insertAfter(element); // for other inputs, just
												// perform default behavior
             }
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
	
	var validator;
	
	var apiCallUnblock = function(actionURL, callback) {
		var method = method || "POST";
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url : context + '/' + actionURL,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				callback(data);
				
			}
		});
	};

	$.validator.addMethod(
		    "pwcheck_exist",
		    function(value, element) {
		    	apiCallUnblock("auth/adm/profile/checkPasswordExist/" + value, function(data){
					
					result=data.result;
					if (result==0){
						validator = true;
		    		}else{
		    			validator = false;
		    		}	
		    	});
		    	
		    return validator;	
		    });
	
	$.validator.addMethod("pwcheck_valid", function(value) {
		   return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value)
		});
	
	$.validator.addMethod("pwcheck_upper", function(value) {
		   return  /[A-Z]/.test(value) // has a lowercase letter
		});
	
	$.validator.addMethod("pwcheck_digit", function(value) {
		   return /\d/.test(value) // has a digit
		});
   

	var displayConfirm = function() {
		$('#tab3 .form-control-static', form).each(
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
							var selectedOption = $(this)
									.find('option:selected');
							elements.push(selectedOption.text());
						});
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else {
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
		// formId, targetId,after
		// submitAjaxJQ('submit_form','dashboard','');
	}).hide();

	$('#submitRequestPassword').click(function() {
		if (form.valid()) {
			submitAjaxJQ('password_form', 'dashboard', 'showProfile()');
		}
	});
}


function initRolePage(idRole) {
	initPage('Role');
	initRoleValidations();
	$('select').select2();
	$('#perms').multiSelect({
		 includeSelectAllOption: true
	});
	
	var apiCallUnblock = function(actionURL, callback) {
		var method = method || "POST";
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url : context + '/' + actionURL,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				callback(data);
				
			}
		});
	};
	
	if(idRole!=null)
	{
	   apiCallUnblock("auth/adm/role/getPermissions/" + idRole, function(data)
	   {	
	      var valArr=data.result.split(',');
	      $("#perms").val(valArr);
	      $("#perms").multiSelect("refresh");
	   });
	}
	
}

function initRoleList() {
	sendRequestJQ('auth/adm/role/list', 'dashboard', 'initRolePage()');
}

function initUserPage(idUser) {
	initPage('User');
	initUserValidations();
	$('#roles').multiSelect({
		 includeSelectAllOption: true
	});
	$('#distrs').multiSelect({
		 includeSelectAllOption: true
	});

	var apiCallUnblock = function(actionURL, callback) {
		var method = method || "POST";
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url : context + '/' + actionURL,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				callback(data);
				
			}
		});
	};
	
	if(idUser!=null)
	{
	   apiCallUnblock("auth/adm/usr/getRoles/" + idUser, function(data)
	   {	
	      var valArr=data.result.split(',');
	      $("#roles").val(valArr);
	      $("#roles").multiSelect("refresh");
	   });
	   
	   apiCallUnblock("auth/adm/usr/getDistrictsByUser/" + idUser, function(data)
			   {	
			      var valArr=data.result.split(',');
			      $("#distrs").val(valArr);
			      $("#distrs").multiSelect("refresh");
			   });	   
	   
	}
	
// $('#permissions').multiSelect({
// includeSelectAllOption: true
// });
	
	 $('select').select2();
}

function initEntryByDistrict(){
	$('select').select2();
    $("[data-counter='counterup']").counterUp({
        delay: 10,
        time: 1000
    });
 
	$('#byDistrictTable').DataTable({		
		 "language": {
	            "lengthMenu": "_MENU_ Registros por página",
	            "zeroRecords": "No existen registros",
	            "info": "Mostrando pagina _PAGE_ de _PAGES_",
	            "infoEmpty": "No hay registros disponibles",
	            "infoFiltered": "(filtered from _MAX_ total records)"
	        }
	
	});
	$("select#stateId").change(function(){
		blockPage();
		 $("select#districtId").html('');
		 $("select#entryId").html('');
         $.getJSON("cfg/entry/getDistricts",{stateId: $(this).val()}, function(j){
              var options = '<option value="">Seleccione... </option>';
              var json = eval(j);
              $.each(json, function(key, value) {
            	  options += '<option value="' + key + '">' + value + '</option>';
              });        
   			  unblockPage();            
              $("select#districtId").html(options);
            });
     });		
	$("select#districtId").change(function(){
		$("select#entryId").html('');
		blockPage();
         $.getJSON("cfg/entry/getEntries",{districtId: $(this).val()}, function(j){
              var options = '<option value="">Seleccione... </option>';
              var json = eval(j);
              $.each(json, function(key, value) {
            	  options += '<option value="' + key + '">' + value + '</option>';
              });        
   			  unblockPage();            
              $("select#entryId").html(options);
            });
     });	
}

function initUserList() {
	sendRequestJQ('auth/adm/usr/list', 'dashboard', 'initUserPage()');
}

function initPersonList() {
	sendRequestJQ('auth/cat/person/list', 'dashboard', 'initPersonPage()');
}

function initPersonPage() {
	initPage('Person');
	initPersonValidations();
	$('#my_multi_select1').multiSelect();
	$('select').select2();
}

function borrarRegistro(url, workarea, nextfunction){
	bootbox.confirm("Se borrar\u00E1 el registro. \u00BFDesea Continuar?",function(result) {
       if(result){ sendRequestJQ(url,workarea,nextfunction);}
    }); 
}

function initPage(page) {
	 bootbox.setDefaults({
          /**
			 * @optional String
			 * @default: en which locale settings to use to translate the three
			 *           standard button labels: OK, CONFIRM, CANCEL
			 */
          locale: "es"
    });
	$('#' + page + 'Table').DataTable({		
			 "language": {
		            "lengthMenu": "_MENU_ Registros por página",
		            "zeroRecords": "No existen registros",
		            "info": "Mostrando pagina _PAGE_ de _PAGES_",
		            "infoEmpty": "No hay registros disponibles",
		            "infoFiltered": "(filtered from _MAX_ total records)"
		        }	
	 });
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
				submitAjaxJQ(page + 'Form', 'dashboard', 'initPage('
						+ page + ');');
			});
}

function showList(page){
		$('#add_' + page).hide();
		$('#list_' + page).show();		
}

function initRoleValidations() {

	var form = $('#submit_form');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);
	form.validate({
		doNotHideMessage : true,
		errorElement : 'span', // default input error message container
		errorClass : 'help-block help-block-error', // default input error
		// message class
		focusInvalid : false, // do not focus the last invalid input
		// ignore : "", // validate all fields including form hidden input
		rules : {
			rolename : {
				maxlength : 50,
				required : true
			},
			name : {
				maxlength : 50,
				required : true
			},
			description : {
				maxlength : 100,
				required : true
			},
			perms : "required"
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
			
			 if (element.parent(".input-group").size() > 0) {
                 error.insertAfter(element.parent(".input-group"));
             } else if (element.attr("data-error-container")) { 
                 error.appendTo(element.attr("data-error-container"));
             } else if (element.parents('.radio-list').size() > 0) { 
                 error.appendTo(element.parents('.radio-list').attr("data-error-container"));
             } else if (element.parents('.radio-inline').size() > 0) { 
                 error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
             } else if (element.parents('.checkbox-list').size() > 0) {
                 error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
             } else if (element.parents('.checkbox-inline').size() > 0) { 
                 error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
             } else {
                 error.insertAfter(element); // for other inputs, just
												// perform default behavior
             }
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
		$('#tab3 .form-control-static', form).each(
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
							var selectedOption = $(this)
									.find('option:selected');
							elements.push(selectedOption.text());
						});
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else {
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
		// formId, targetId,after
		// submitAjaxJQ('submit_form','dashboard','');
	}).hide();

	$('#submitRequestForm').click(function() {
		if (form.valid()) {
			submitAjaxJQ('submit_form', 'dashboard', 'initRoleList()');
		}
	});
}

function initPermValidations() {

	var form = $('#submit_form');
	var error = $('.alert-danger', form);
	var success = $('.alert-success', form);
	form.validate({
		doNotHideMessage : true,
		errorElement : 'span', // default input error message container
		errorClass : 'help-block help-block-error', // default input error
		// message class
		focusInvalid : false, // do not focus the last invalid input
		// ignore : "", // validate all fields including form hidden input
		rules : {
			name : {
				maxlength : 50,
				required : true
			},
			"path.id" : {
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
			
			 if (element.parent(".input-group").size() > 0) {
                 error.insertAfter(element.parent(".input-group"));
             } else if (element.attr("data-error-container")) { 
                 error.appendTo(element.attr("data-error-container"));
             } else if (element.parents('.radio-list').size() > 0) { 
                 error.appendTo(element.parents('.radio-list').attr("data-error-container"));
             } else if (element.parents('.radio-inline').size() > 0) { 
                 error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
             } else if (element.parents('.checkbox-list').size() > 0) {
                 error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
             } else if (element.parents('.checkbox-inline').size() > 0) { 
                 error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
             } else {
                 error.insertAfter(element); // for other inputs, just
												// perform default behavior
             }
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
		$('#tab2 .form-control-static', form).each(
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
							var selectedOption = $(this)
									.find('option:selected');
							elements.push(selectedOption.text());
						});
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else {
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
	}).hide();

	$('#submitRequestForm').click(function() {
		if (form.valid()) {
			submitAjaxJQ('submit_form', 'dashboard', 'initPermList()');
		}
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
				check_username : true,
				pwcheck_digit : true,
				minlength : 6,
				maxlength : 20,
				required : true
			},
			nickname : {
				minlength : 6,
				maxlength : 20,
				required : true
			},
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				minlength : 6,
				maxlength : 20,
				pwcheck_valid : true,
				pwcheck_digit : true,
				pwcheck_upper : true,
			},
			rpassword : {
				required : true,
				equalTo : "#password"
			},
			"person.id" : {
				required : true
			},
			"position.id" : {
				required : true
			},
			roles : "required",
			distrs : "required",
			gender : "required"
		},
	    messages : {
	    	username : {
	    		check_username : "Por favor, escriba otro username. Ya existe",
	    		 pwcheck_digit : "El username debe de tener al menos un número",
		},
		password : {
    		pwcheck_valid : "El password contiene caracteres no válidos. Verifique",
    		pwcheck_digit : "El password debe de tener al menos un número",
    		pwcheck_upper : "El password debe tener al menos una letra mayúscula"
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
			
			 if (element.parent(".input-group").size() > 0) {
                 error.insertAfter(element.parent(".input-group"));
             } else if (element.attr("data-error-container")) { 
                 error.appendTo(element.attr("data-error-container"));
             } else if (element.parents('.radio-list').size() > 0) { 
                 error.appendTo(element.parents('.radio-list').attr("data-error-container"));
             } else if (element.parents('.radio-inline').size() > 0) { 
                 error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
             } else if (element.parents('.checkbox-list').size() > 0) {
                 error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
             } else if (element.parents('.checkbox-inline').size() > 0) { 
                 error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
             } else {
                 error.insertAfter(element); // for other inputs, just
												// perform default behavior
             }
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
	
	var validator;
	
	var apiCallUnblock = function(actionURL, callback) {
		var method = method || "POST";
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$.ajax({
			url : context + '/' + actionURL,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success : function(data) {
				callback(data);
				
			}
		});
	};

	$.validator.addMethod(
		    "check_username",
		    function(value, element) {
		    	apiCallUnblock("auth/adm/usr/checkUsername2/" + value, function(data){
					
					result=data.result;
					if (result==0){
						validator = true;
		    		}else{
		    			validator = false;
		    		}	
		    	});
		    	
		    return validator;	
		    });
	
	$.validator.addMethod("pwcheck_valid", function(value) {
		   return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value)
		});
	
	$.validator.addMethod("pwcheck_upper", function(value) {
		   return  /[A-Z]/.test(value) // has a lowercase letter
		});
	
	$.validator.addMethod("pwcheck_digit", function(value) {
		   return /\d/.test(value) // has a digit
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
							var selectedOption = $(this)
									.find('option:selected');
							elements.push(selectedOption.text());
						});
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else {
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
		// formId, targetId,after
		// submitAjaxJQ('submit_form','dashboard','');
	}).hide();

	$('#submitRequestForm').click(function() {
		if (form.valid()) {
			submitAjaxJQ('submit_form', 'dashboard', 'initUserList()');
		}
	});
}

/*
 * funciones de tramites **OJO** mala idea declarar las funciones en scope
 * global
 */
function initTramitePage() {

	// se obtiene la forma
	var requestForm = $("#requestForm");
	var error = $('.alert-danger', requestForm);
	var success = $('.alert-success', requestForm);

	// se define la validacion
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
			districtId : {
				required: true,
				min : 1
			},
			justification : {
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
			icon.attr("data-original-title", error.text()).tooltip({
				'container' : 'body'
			});
		},

		highlight : function(element) {
			$(element).closest('.form-group').removeClass('has-success')
					.addClass('has-error');
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
		if (requestForm.valid()) {
			submitAjaxJQ('requestForm', 'dashboard', '');
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
				required : true,
				maxlength : 30
			},
			secondName : {
				maxlength : 30
			},
			fatherLastName : {
				required : true,
				maxlength : 30
			},
			motherLastName : {
				required : true,
				maxlength : 30
			},
			telephone : {
				// phone : true,
				required : true,
				// minlength:10,
				maxlength:12
			},
			mobileTelepone : {
				// phone : true,
				// minlength:10,
				maxlength:12
			},
			twitter : {},
			facebook : {},
			webSite : {url: true},
			"stateDTO.id" : {
				required : true
			},
			gender : {
				required : true
			},
			street : {
				required : true,
				maxlength : 30
			},
			number : {
				required : true,
				maxlength : 30
			},
			city : {
				required : true,
				maxlength : 30
			},
			zipCode : {
				required : true,
				maxlength : 5,
				number : true
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
	
	$.validator.addMethod(
		    "telephone",
		    function(value, element) {
		        // put your own logic here, this is just a (crappy) example
		        return value.match(/^\d\d?\/\d\d?\/\d\d\d\d$/); "[0-9\-\(\)\s]+"
		    }
		);

	$.validator.addMethod("phone", function(phone_number, element) {
	    phone_number = phone_number.replace(/\s+/g, ""); 
		return this.optional(element) || phone_number.length > 9 &&
			phone_number.match(/^(1-?)?(\([2-9]\d{2}\)|[2-9]\d{2})-?[2-9]\d{2}-?\d{4}$/);
	}, "Especifica un telefono valido");

	var displayConfirm = function() {
		$('#tab3 .form-control-static', form).each(
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
							var selectedOption = $(this)
									.find('option:selected');
							elements.push(selectedOption.text());
						});
						$(this).html(elements.join("<br>"));
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else {
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
		// formId, targetId,after
		// submitAjaxJQ('submit_form','dashboard','');
	}).hide();

	$('#submitRequestForm').click(function() {
		if (form.valid()) {
			submitAjaxJQ('submit_form', 'dashboard', 'initPersonList()');
		}
	});
	
	updateMenu("#formalityMenu");
}

function initMyTramiteListPage(){
	var formalityDatatable = $('#formalityList').DataTable({
        "language": {
            "lengthMenu": "_MENU_ Registros por página",
            "zeroRecords": "No existen registros",
            "info": "Mostrando pagina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registros disponibles",
            "infoFiltered": "(filtered from _MAX_ total records)"
        },
        bFilter: true, bInfo: true, bLengthChange:false, ordering:true,
        "order": [[ 0, "desc" ]],
        "columnDefs": [
           {
               "targets": [ 0 ],
               "visible": false
           }
       ]
    });
	
	// Filtro de datatable por fecha
	$(document).find('#formalityDateSearch').on( 'keyup', function () {
		formalityDatatable.search( this.value ).draw();
	});
	
	$(document).find(".dataTables_filter").hide();
}

function initTramiteListPage() {
	
	var canEdit = $(document).find("#canCaptureValue").val();	
	if(canEdit == "false"){
		console.log("usuario no puede capturar iniciando tooltip");
		$(document).find(".canCaptureButton").each(function(){
			console.log("seteando tooltip");
			$(this).qtip({
				 content: {
					 text: $(this).next(".tooltip-popup")
			     },
			     position:{
			    	 my: 'top left',
			         at: 'bottom right'
			     },
			     hide: {
		           fixed: true,
		           delay: 400
		         },
			     style: {
			    	 classes: "ui-tooltip-shadow"
			     }
			});
		});
	}else{
		console.log("usuario puede capturar");
	}
	
	var formalityDatatable = $('#formalityList').DataTable({
        "language": {
            "lengthMenu": "_MENU_ Registros por página",
            "zeroRecords": "No existen registros",
            "info": "Mostrando pagina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registros disponibles",
            "infoFiltered": "(filtered from _MAX_ total records)"
        },
        bFilter: true,
        bInfo: true, 
        bLengthChange:false, 
        ordering:true,
        "order": [[ 0, "desc" ]],
        "columnDefs": [
           {
               "targets": [ 0 ],
               "visible": false
           }
       ]
    });
	
	// Filtro de datatable por fecha
	$(document).find('#formalityDateSearch').on( 'keyup', function () {
		formalityDatatable.search( this.value ).draw();
	});
	
	$(document).find(".dataTables_filter").hide();
	
	//tooltip
	console.log("iniciando tooltip de elementos en lista");
	$(document).find(".tooltip-control").each(function(){
		$(this).qtip({
			 content: {
				 text: $(this).next(".tooltip-popup")
		     },
		     position:{
		    	 my: 'top right',
		         at: 'bottom left'
		     },
		     hide: {
	           fixed: true,
	           delay: 400
	         },
		     style: {
		    	 classes: "ui-tooltip-shadow"
		     }
		});
	});
	
	updateMenu("#formalityMenu");
}

function showDataHistory(requestId){
	
	apiCall("auth/wf/requestDetail/" + requestId, function(data){
		bootbox.dialog({
	        message: data,
	        title: "Historial del folio"	    
	    }).find(".modal-dialog").css({"width":"70%"});
	});
}

function showEntryAmount(district, programaticKey, entry){
	apiCall("auth/wf/entryAmounts/" + district + "/" + programaticKey + "/" + entry, function(data){
		bootbox.dialog({
	        message: data,
	        title: "Totales Distrito - Llave Programatica - Partida"	    
	    }).find(".modal-dialog").css({"width":"70%"});
	});
}

function showEntryTotals(district, programaticKey, entry){
	apiCall("auth/wf/entryTotals/" + district + "/" + entry, function(data){
		$(document).find("#currentTotals").html("").html(data).show();
	});
}

function showKeyData(programaticKeyId){
	
	function clousure(data){
		return data;
	}
	
	apiCall("auth/wf/pk/" + programaticKeyId, function(data){
		clousure(data);
	});
}

function initUploadAnnualBudget(){
	var requestForm = $('#requestForm');
	
	var file = requestForm.find('#attachment').val();
	
	if(file.length <= 0){
		window.showNotification("error", "Debe seleccionar un archivo para continuar");
		return;
	}

	submitFileAjaxJQTest('requestForm', 'dashboard', '',false);	
}
function initUpload() {

	var requestForm = $('#requestForm');

	$('#uploadFile').click(function(e) {
		var file = requestForm.find('#attachment').val();
		
		if(file.length <= 0){
			window.showNotification("error", "Debe seleccionar un archivo para continuar");
			return;
		}
		var size = parseInt($("#attachment")[0].files[0].size);
		if (size > 10000000){
			window.showNotification("error", "El archivo seleccionado excede el limite de 20MB permitido. Peso de archivo seleccionado: "+ size / 1000000 + " MB.");
			return; 
		}
		
		submitFileAjaxJQTest('requestForm', 'dashboard', '', true);
// submitFileAjaxJQ('requestForm', 'dashboard', '');
	});
}

function movementsCapture() {

	// var movementController = {};
	
	// Controlador tipo de movimiento
	$("#movementTypeId").on("change", function (e) {
		movementController.clean(movementController.upGrid);
		movementController.clean(movementController.downGrid);
	    movementController.update(parseInt(this.value));
	    $(movementController.upGrid).find('tbody tr:not(#noMovs)').remove();
	    $(movementController.downGrid).find('tbody tr:not(#noMovs)').remove();
	    
	    if(movementController.isCompensatedMovement()){
	    	$(movementController.upGrid).find("#addMov").hide();
	    }
	    
	});
	
	movementController.startComponent();
	
	// se carga el movimiento seleccionado
	movementController.update(parseInt($("#movementTypeId").val()));

	var requestForm = $('#requestForm');

	$('#partialSave').click(function(e) {
		// alert("haciendo guardado parcial");
		var isCorrect = movementController.validate();
		if (isCorrect){
			requestForm.find('#nextStageValueCode').val("SOLPEND");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}
	});

	$('#saveAndContinue').click(function(e) {
		var isCorrect = movementController.validate();
		if (isCorrect){
			requestForm.find('#nextStageValueCode').val("SOLCOMP");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}
	});
}


/* MOVEMENTS CAPTURE 2*/

function movements2Capture() {
	// var movementController = {};
	
	// Controlador tipo de movimiento
	$("#movementTypeId").on("change", function (e) {
		movementController2.clean(movementController2.upGrid);
		movementController2.clean(movementController2.downGrid);
	    
		movementController2.update(parseInt(this.value));
	    
		$(movementController2.upGrid).find('tbody tr:not(#noMovs)').remove();
	    $(movementController2.downGrid).find('tbody tr:not(#noMovs)').remove();
	    
	    $(movementController2.upGrid).find("#saveMov").hide();
	    $(movementController2.downGrid).find("#saveMov").hide();
	    
	});
	
	movementController2.startComponent();
	
	// se carga el movimiento seleccionado
	movementController2.update(parseInt($("#movementTypeId").val()));

	var requestForm = $('#requestForm');

	$('#saveAndContinue').click(function(e) {
		requestForm.find(".pk").prop("disabled",false);
		requestForm.find(".entry").prop("disabled",false);
		requestForm.find(".monthAmount").prop("disabled",false);
		//var isCorrect = movementController.validate();
		requestForm.find('#nextStageValueCode').val("SOLCOMP");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});
}




function initAuthorization() {
	
	$(document).find("input").attr("readonly","true");
	$(document).find("select").attr("readonly","true");

	movementController2.startComponent();
	
	$(document).find("[data-name='sliderControl']").hide();
	$(document).find("[data-name='deleteAction']").html("");
	$(document).find("[data-name='monthLabels']").attr("colspan","2");
	
	var tipoMov = parseInt($(document).find("#movementTypeId").val());
	// alert("tipo de movimiento: " + tipoMov);
	
	if(tipoMov == 1){
		$("#substractComponent").hide();
	}
	if(tipoMov == 2){
		$("#addComponent").hide();
	}
	
	var requestForm = $('#requestForm');

	
	/* botones para owners de autorizacion */
	
	//manda a siguiente firma
	$('#backToCapture').click(function(e) {
		alert("Regresando tramite a captura");
		if($("#comments").val().length > 0){
			requestForm.find('#nextStageValueCode').val("REGRESAR");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}else{
			window.showNotification("error", "Capture comentarios sobre el tramite");
		}
	});
	
	//manda a siguiente firma
	$('#authorizateFormality').click(function(e) {
		// alert("autorizando Tramite");
		requestForm.find('#nextStageValueCode').val("SIGFIRMA");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});
	
	
	
	/* botones propios de super usuario */
	
	//manda a tramite cancelado
	$('#cancelFormality').click(function(e) {
		// alert("Cancelando Tramite");
		if($("#comments").val().length > 0){
			requestForm.find('#nextStageValueCode').val("CANCELAR");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}else{
			window.showNotification("error", "capture comentarios de cancelacion");
		}
	});
	
	//finalizar tramite
	$('#finishFormality').click(function(e) {
		// alert("autorizando Tramite y finalizar");
		requestForm.find('#nextStageValueCode').val("CONTINUAR");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});
	
	$(document).find(".addButton").hide();
}



function expenseCapture() {

	// var movementController = {};
	
	expenseController.startComponent();
	
	$(expenseController.downGrid).find("#saveMov").hide();
	
	//movementController2.startComponent();	
	// se carga el movimiento seleccionado
	// expenseController.update(parseInt($("#movementTypeId").val()));

	var requestForm = $('#requestForm');

	/*
	$(document).find('#partialSave').on("click", function(e) {
		alert("haciendo guardado parcial");
		var isCorrect = expenseController.validate();
		if (isCorrect){
			requestForm.find('#nextStageValueCode').val("SOLPEND");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}
	});
	*/

	/*
	$(document).find('#saveAndContinue').on("click", function(e) {
		//alert("finalizando captura");
		//var isCorrect = expenseController.validate();
		if (isCorrect){
			requestForm.find('#nextStageValueCode').val("SOLCOMP");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}
	});
	*/
	

	$('#saveAndContinue').click(function(e) {
		
		var clave = $(document).find("#certifiedAccountId").val();
		
		if(clave.length <= 0){
			window.showNotification("error", "Capture una cuenta para continuar");
		}else{
			//var isCorrect = movementController.validate();
			
			$(document).find("#requestForm").find(".pk").prop("disabled",false);
			$(document).find("#requestForm").find(".entry").prop("disabled",false);
			$(document).find("#requestForm").find(".monthAmount").prop("disabled",false);
			
			requestForm.find('#nextStageValueCode').val("SOLCOMP");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}
	});
}


function initReports(){
	console.log("iniciando listado de reportes");
	
	var reportTable = $('#reportList').DataTable({
        "language": {
            "lengthMenu": "_MENU_ Registros por página",
            "zeroRecords": "No existen registros",
            "info": "Mostrando pagina _PAGE_ de _PAGES_",
            "infoEmpty": "No hay registros disponibles",
            "infoFiltered": "(filtered from _MAX_ total records)"
        },
        bFilter: true, bInfo: true, bLengthChange:false, ordering:false
    });
	
	updateMenu("#reportsMenu");
}

function initReportParamCapture(){
	console.log("iniciando js de captura de parametros");
	
	$(document).find("#reportParametersForm").find("input").each(function(idx,e){
		var input = $(e);
		var inputType = input.attr("data-parametertype");
		
		// si es un date, se inicializa el date
		if(inputType == "date"){
			input.datepicker({
			  	  format: 'dd/mm/yyyy',
			  	  autoclose: true
			  });
		}
	});
	
	$('#downloadReport').click(function(e) {
		console.log("descargando forma");
		
		var isFormOk = true;
		$(document).find("#reportParametersForm").find("input").each(function(idx, e){
			var input = $(e);
			var isRequired = input.attr("data-required") === 'true';
			if (isRequired && input.val().length == 0){
				window.showNotification("error", input.attr("data-parametername") + " no puede ser vacio. Verifique");
				isFormOk = false;
			}
		});
		
		$(document).find("#reportParametersForm").find("select").each(function(idx, e){
			var input = $(e);
			var isRequired = input.attr("data-required") === 'true';
			if (isRequired && parseInt(input.val()) <= 0){
				window.showNotification("error", "Debe seleccionar un valor en " + input.attr("data-parametername") + ". Verifique.");
				isFormOk = false;
			}
		});
		
		if(isFormOk){
			openParamResourceNative('report/download/paramReport', 'reportParametersForm');
		}
	});
}

function showDueDates(){
	
	apiCall("auth/wf/getDueDates", function(data){
		bootbox.dialog({
	        message: data,
	        title: "Fechas disponibles para captura"	    
	    }).find(".modal-dialog").css({"width":"40%"});
	});
}

function noAction(){
	console.log("Window js content embed in jsp");
}
