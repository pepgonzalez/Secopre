function initRoleAdmin() {
	initRoleTable();
	$('#add_roles').hide();
	$('button.btn.green').click(function() {
		$('#add_roles').show();
		$('#list_roles').hide();
	});
	$('button.btn.default').click(function() {
		$('#add_roles').hide();
		$('#list_roles').show();
	});
	$('button.btn.blue').click(function() {
		submitAjaxJQ('roleForm', 'dashboard','initRoleAdmin();');
	});	
	initRoleValidations();
}
function initUserAdmin() {
	initUserTable();

	$('#add_users').hide();

	$('button.btn.green').click(function() {
		$('#add_users').show();
		$('#list_users').hide();
	});
	$('a.btn.default.button-previous').click(function() {
		$('#add_users').hide();
		$('#list_users').show();
	});
	$('button.btn.default').click(function() {
		sendRequestJQ('', '', '');
	});
	initUserValidations();
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
		}],
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