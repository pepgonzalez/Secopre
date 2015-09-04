function initPermPage() {
	initAdminPage('Perm');
}
function initRolePage() {
	initAdminPage('Role');
}
function initUserPage() {
	initAdminPage('User');
	initUserValidations();
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
				submitAjaxJQ(page + 'Form', 'dashboard', 'initAdminPage('
						+ page + ');');
			});

}
function initRoleValidations() {
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
			fullname : {
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
			fullname : {
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
						$(this).html(input.find('option:selected').text());
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else if ($(this).attr("data-display") == 'payment[]') {
						var payment = [];
						$('[name="payment[]"]:checked', form).each(function() {
							payment.push($(this).attr('data-title'));
						});
						$(this).html(payment.join("<br>"));
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
		alert('Finished! Hope you like it :)');
	}).hide();
}

function initUserTable() {
	var table = $('#userTable');

	// begin first table
	table.dataTable({

		// Internationalisation. For more info refer to
		// http://datatables.net/manual/i18n
		"language" : {
			"aria" : {
				"sortAscending" : ": activate to sort column ascending",
				"sortDescending" : ": activate to sort column descending"
			},
			"emptyTable" : "No existe informacion disponible",
			"info" : "Showing _START_ to _END_ of _TOTAL_ entries",
			"infoEmpty" : "Registros no encontrados",
			"infoFiltered" : "(filtered1 from _MAX_ total entries)",
			"lengthMenu" : "Show _MENU_ entries",
			"search" : "Buscar:",
			"zeroRecords" : "No se encontraron registros coincidentes"
		},

		// Uncomment below line("dom" parameter) to fix the dropdown overflow
		// issue in the datatable cells. The default datatable layout
		// setup uses scrollable div(table-scrollable) with overflow:auto to
		// enable vertical scroll(see:
		// assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
		// So when dropdowns used the scrollable div should be removed.
		// "dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6
		// col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7
		// col-sm-12'p>>",

		"bStateSave" : true, // save datatable state(pagination, sort, etc)
		// in cookie.

		"columns" : [ {
			"orderable" : false
		}, {
			"orderable" : true
		}, {
			"orderable" : true
		}, {
			"orderable" : true
		}, {
			"orderable" : true
		}, {
			"orderable" : true
		} ],
		"lengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per page
		// values
		// here
		],
		// set the initial value
		"pageLength" : 10,
		"pagingType" : "bootstrap_full_number",
		"language" : {
			"search" : "Mi Busqueda: ",
			"lengthMenu" : "  _MENU_ registros",
			"paginate" : {
				"previous" : "Prev",
				"next" : "Next",
				"last" : "Last",
				"first" : "First"
			}
		},
		"columnDefs" : [ { // set default column settings
			'orderable' : true,
			'targets' : [ 0 ]
		}, {
			"searchable" : false,
			"targets" : [ 0 ]
		} ],
		"order" : [ [ 1, "asc" ] ]
	// set first column as a default sort by asc
	});

	var tableWrapper = jQuery('#userTable_wrapper');

	table.find('.group-checkable').change(function() {
		var set = jQuery(this).attr("data-set");
		var checked = jQuery(this).is(":checked");
		jQuery(set).each(function() {
			if (checked) {
				$(this).attr("checked", true);
				$(this).parents('tr').addClass("active");
			} else {
				$(this).attr("checked", false);
				$(this).parents('tr').removeClass("active");
			}
		});
		jQuery.uniform.update(set);
	});

	table.on('change', 'tbody tr .checkboxes', function() {
		$(this).parents('tr').toggleClass("active");
	});

	tableWrapper.find('.dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify table per page
	// dropdown
}
function initRoleTable() {
	var table = $('#roleTable');

	// begin first table
	table.dataTable({

		// Internationalisation. For more info refer to
		// http://datatables.net/manual/i18n
		"language" : {
			"aria" : {
				"sortAscending" : ": activate to sort column ascending",
				"sortDescending" : ": activate to sort column descending"
			},
			"emptyTable" : "No existe informacion disponible",
			"info" : "Showing _START_ to _END_ of _TOTAL_ entries",
			"infoEmpty" : "Registros no encontrados",
			"infoFiltered" : "(filtered1 from _MAX_ total entries)",
			"lengthMenu" : "Show _MENU_ entries",
			"search" : "Buscar:",
			"zeroRecords" : "No se encontraron registros coincidentes"
		},

		// Uncomment below line("dom" parameter) to fix the dropdown overflow
		// issue in the datatable cells. The default datatable layout
		// setup uses scrollable div(table-scrollable) with overflow:auto to
		// enable vertical scroll(see:
		// assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js).
		// So when dropdowns used the scrollable div should be removed.
		// "dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6
		// col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7
		// col-sm-12'p>>",

		"bStateSave" : true, // save datatable state(pagination, sort, etc)
		// in cookie.

		"columns" : [ {
			"orderable" : false
		}, {
			"orderable" : true
		}, {
			"orderable" : true
		}, {
			"orderable" : true
		} ],
		"lengthMenu" : [ [ 5, 15, 20, -1 ], [ 5, 15, 20, "All" ] // change
		// per page
		// values
		// here
		],
		// set the initial value
		"pageLength" : 10,
		"pagingType" : "bootstrap_full_number",
		"language" : {
			"search" : "Mi Busqueda: ",
			"lengthMenu" : "  _MENU_ registros",
			"paginate" : {
				"previous" : "Prev",
				"next" : "Next",
				"last" : "Last",
				"first" : "First"
			}
		},
		"columnDefs" : [ { // set default column settings
			'orderable' : true,
			'targets' : [ 0 ]
		}, {
			"searchable" : false,
			"targets" : [ 0 ]
		} ],
		"order" : [ [ 1, "asc" ] ]
	// set first column as a default sort by asc
	});

	var tableWrapper = jQuery('#roleTable_wrapper');

	table.find('.group-checkable').change(function() {
		var set = jQuery(this).attr("data-set");
		var checked = jQuery(this).is(":checked");
		jQuery(set).each(function() {
			if (checked) {
				$(this).attr("checked", true);
				$(this).parents('tr').addClass("active");
			} else {
				$(this).attr("checked", false);
				$(this).parents('tr').removeClass("active");
			}
		});
		jQuery.uniform.update(set);
	});

	table.on('change', 'tbody tr .checkboxes', function() {
		$(this).parents('tr').toggleClass("active");
	});

	tableWrapper.find('.dataTables_length select').addClass(
			"form-control input-xsmall input-inline"); // modify table per page
	// dropdown
}

function initWizard() {

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
						$(this).html(input.find('option:selected').text());
					} else if (input.is(":radio") && input.is(":checked")) {
						$(this).html(input.attr("data-title"));
					} else if ($(this).attr("data-display") == 'payment[]') {
						var payment = [];
						$('[name="payment[]"]:checked', form).each(function() {
							payment.push($(this).attr('data-title'));
						});
						$(this).html(payment.join("<br>"));
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
			// alert('continuar');
			// alert(tab);
			// alert(index);

			if (index == 1) {
				var form = $('#submit_form');
			} else if (index == 2) {
				initRoleValidations();
				var form = $('#roleForm');
			} else if (index == 3) {

			}

			var error = $('.alert-danger', form);
			var success = $('.alert-success', form);

			success.hide();
			error.hide();

			if (form.valid() == false) {
				return false;
			}

			handleTitle(tab, navigation, index);
		},
		onPrevious : function(tab, navigation, index) {
			alert('atras');
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
		alert('Finished! Hope you like it :)');
	}).hide();

}


/*funciones de tramites **OJO** mala idea declarar las funciones en scope global*/
var TramiteClassHelper = function(){
	
	this.initTramitePage = function(){
		alert('Inicializando pagina de tramite');
	};
	
}

TramiteHelper = new TramiteClassHelper();