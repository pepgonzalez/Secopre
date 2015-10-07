function initPermPage() {
	initPage('Perm');
}
function initRolePage() {
	initPage('Role');
}
function initUserPage() {
	initPage('User');
	initUserValidations();
	$('#roles').multiSelect();
	$('#permissions').multiSelect();
//	$('input').iCheck({
//		checkboxClass : 'icheckbox_square',
//		radioClass : 'iradio_square',
//		increaseArea : '20%' // optional
//	});
	
	 $('select').select2();
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
}

function initPage(page) {
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
				submitAjaxJQ(page + 'Form', 'dashboard', 'initPage('
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
			"person.id" : {
				required : true
			},
			roles : "required",
			permissions : "required"
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
				required : true
			},
			secondName : {},
			fatherLastName : {
				required : true
			},
			motherLastName : {
				required : true
			},
			telephone : {
				required : true
			},
			mobileTelepone : {
				required : true
			},
			twitter : {},
			facebook : {},
			webSite : {}

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
}

function initTramiteListPage() {
}

function initUpload() {

	var requestForm = $('#requestForm');

	$('#uploadFile').click(function(e) {
		var file = requestForm.find('#attachment').val();
		if(file.length <= 0){
			return;
		}
		submitFileAjaxJQTest('requestForm', 'dashboard', '');
//		submitFileAjaxJQ('requestForm', 'dashboard', '');
	});
}

function initFullCapture() {
	
	var movementController = {
		upGrid : "#addComponent",
		downGrid : "#substractComponent",
		slider : "SliderControl",
		reset : function(){
			$(this.upGrid).hide();
			$(this.downGrid).hide();
		},
		titles : {
			al:"Ampliación Líquida",
			rl:"Reducción Líquida",
			ac:"Ampliación Compensada",
			rc:"Reducción Compensada"
		},
		update : function(value){
			this.reset();
			
			if(value > 0){
				switch(value){
				case 1:
					this.showGrid(this.upGrid, this.titles.al);
					break;
				case 2:
					this.showGrid(this.downGrid, this.titles.rl);
					break;
				case 3:
					this.showGrid(this.upGrid, this.titles.ac);
					this.showGrid(this.downGrid, this.titles.rc);
				}
			}
		},
		showGrid : function(grid, name){
			var grd = $(grid);
			grd.find(".caption").text(name);
			grd.show();
		},
		getId : function(grid, idx, attr, escaped){
			escaped = escaped || 1;
			var list = (grid == this.upGrid ? "upMovements" : "downMovements");
			return (escaped == 1 ? "#" : "") + list + idx + (escaped == 1 ? "\\." : ".") + attr;
		},
		getPath : function(grid, idx, attr){
			var list = (grid == this.upGrid ? "upMovements" : "downMovements");
			return "" + list + "[" + idx + "]." + attr;
		},
		updateTotal : function(grid, value, add){
			var grd = $(grid);
			var totalElement = grd.find("#upMovementsTotal");
			var total = parseInt(totalElement.text());
			total = (add ? total + value : total - value);
			totalElement.text(total);
			
		},
		getSliderId : function(grid){
			var direction = (grid == this.upGrid ? "up" : "down");
			return "#" + direction + this.slider;
		},
		linkComponent : function(grid){
			var grd = $(grid);
			
			//si no existe el row de "sin elementos, se iteran los objetos"
			if(grd.find("tbody #noMovs").length == 0){
				var self = this;
				
				grd.find("tbody tr").each(function(idx, e){
					var element = $(e);
					var rowId = element.attr("id");					
					var sliderControlId = self.getSliderId(grid) + idx;
							
					var currentMonth = parseInt(new Date().getMonth());
					var months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
					
					var storedInitialMonth = parseInt(element.find(self.getId(grid, idx, "initialMonthId")).val());
					var storedFinalMonth = parseInt(element.find(self.getId(grid, idx, "finalMonthId")).val());
					var monthAmount = parseInt(element.find(self.getId(grid, idx, "monthAmount")).val());
					
					//iniciar slider
					$(sliderControlId).noUiSlider({
				        connect: true, behaviour: 'tap', step:1, start: [storedInitialMonth, 11],
				        range: {
				            'min': [(storedInitialMonth < currentMonth ? storedInitialMonth : currentMonth)],
				            'max': [11]
				        }	
				    });
				    
					function myValue(value){
						$(this).text(months[parseInt(value)]);
					}
					
					function intValue(value){
						return parseInt(value);
					}

					//SE ASOCIA EL VALOR DEL SLIDER A una propiedad de la forma
					$(sliderControlId).Link('lower').to($(self.getId(grid, idx, "initialMonthId")), intValue);
					$(sliderControlId).Link('upper').to($(self.getId(grid, idx, "finalMonthId")), intValue);

					$(sliderControlId).Link('lower').to($( self.getId(grid, idx, "lower-offset")), myValue);
					$(sliderControlId).Link('upper').to($( self.getId(grid, idx, "upper-offset")), myValue);
					
					//sumar el monto actual al total
					var amount = ((storedFinalMonth - storedInitialMonth) + 1) * monthAmount;
					self.updateTotal(grid, amount, true);
					
					//asignar evento de eliminar al elemento
					element.find("[data-name='deleteAction'] a").on("click", function(){
						var row = $(this).parent().parent();
						row.find(self.getId(grid, idx, "removedElement")).val("1");
						row.hide();
					});
					
					//agregar evento al cambiar de llave programatica
					element.find(self.getId(grid, idx, "programaticKeyId")).on("change", function (e) {
					    alert("cambiando llave programatica");
					    alert(this.value);
					    self.apiCall('auth/API/get/entry/'+this.value, function(data){
					    	var entrySelect = element.find(self.getId(grid, idx, "entryId"));
					    	entrySelect.empty();
					    	entrySelect.append('<option value="-1">Seleccione..</option>');
					    	$.each(data, function(index, item){
					    		entrySelect.append('<option value="' + item.id +'">' + item.name + '</option>');
					    	});
					    });
					});
										
					//sendRequestJQ('auth/cat/person/list',
					
				});
				
			}else{
				alert("no hay elementos en el componente");
			}
			
			//evento para agregar movimientos
			var addBtn = grd.find(".actions #addMov").on("click", function(){
				
				var nextIndex = self.getNextIndex(grd);
				
				if(nextIndex == 0){
					grd.find("tbody #noMovs").remove();
				}
				
				var nodo = self.activateTemplate("#movementRowTemplate");
				var e = $(nodo);
				
				//accion
				e.find("[data-name='action'] a").attr("id", "rmvIdx" + nextIndex);
				
				//llave programatica
				e.find("[data-name='programaticKey'] select")
					.attr("name", self.getPath(grid, nextIndex, "programaticKeyId"))
					.attr("id", self.getId(grid, nextIndex, "programaticKeyId", 2))
					.removeAttr("multiple");
				
				e.find("[data-name='programaticKey']").find("input[type='hidden']").remove();
			
				//entry
				e.find("[data-name='entry'] select")
				.attr("name", self.getPath(grid, nextIndex, "entryId"))
				.attr("id", self.getId(grid, nextIndex, "entryId", 2))
				.removeAttr("multiple");
				
				e.find("[data-name='entry']").find("input[type='hidden']").remove();

				
				//sliderControl
				e.find("[data-name='sliderControl'] #sliderControl").attr("id", self.getSliderId(grid).substring(1) + nextIndex);
				
				//lowerOffset
				e.find("[data-name='lower-offset'] ").attr("id", self.getId(grid, nextIndex, "lower-offset", 2));
				e.find("[data-name='upper-offset'] ").attr("id", self.getId(grid, nextIndex, "upper-offset", 2));
				
				//monthAmount
				e.find("[data-name='monthAmount'] input")
				.attr("name", self.getPath(grid, nextIndex, "monthAmount"))
				.attr("id", self.getId(grid, nextIndex, "monthAmount", 2))
				.removeAttr("value");
				
				//initialMonthId
				e.find("[data-name='initialMonthId']")
				.attr("name", self.getPath(grid, nextIndex, "initialMonthId"))
				.attr("id", self.getId(grid, nextIndex, "initialMonthId", 2))
				.attr("value","");
				
				//finalMonthId
				e.find("[data-name='finalMonthId']")
				.attr("name", self.getPath(grid, nextIndex, "finalMonthId"))
				.attr("id", self.getId(grid, nextIndex, "finalMonthId", 2))
				.attr("value","");
				
				//removedElement
				e.find("[data-name='removedElement']")
				.attr("name", self.getPath(grid, nextIndex, "removedElement"))
				.attr("id", self.getId(grid, nextIndex, "removedElement", 2))
				.attr("value","0");
				
				e.find("[data-name='movementTypeId']")
				.attr("name", self.getPath(grid, nextIndex, "movementTypeId"))
				.attr("id", self.getId(grid, nextIndex, "movementTypeId", 2))
				.attr("value",(grid == self.upGrid ? "1" : "-1"));
				
				grd.find("tbody").append(e);
				
				self.startSlider(self, nextIndex, parseInt(new Date().getMonth()), grid);
				
				self.addRemoveEvent(self, grid, nextIndex);
				
			});
			
		},
		addRemoveEvent : function(self, grid, indice){
			var a = $(document).find("#rmvIdx" + indice);
			
			a.on("click", function(){
				var row = $(this).parent().parent();
				row.find(self.getId(grid, indice, "removedElement")).val("1");
				row.hide();
			});
		},
		startSlider : function(self, indice, initialMonth, grid){
						
			var id = self.getSliderId(grid) + indice;
			alert("id: " + id);
			
			$(document).find(id).noUiSlider({
		        connect: true, behaviour: 'tap', step:1, start: [initialMonth, 11],
		        range: {
		            'min': [initialMonth],
		            'max': [11]
		        }	
		    });
			
			var months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
			
			function myValue(value){
				$(this).text(months[parseInt(value)]);
			}
			
			function intValue(value){
				$(this).val(parseInt(value));
			}

			var initialMonthId = self.getId(grid, indice, "initialMonthId");
			var finalMonthId = self.getId(grid, indice, "finalMonthId");
			
			alert("valores: " + initialMonthId + ", " + finalMonthId);
			
			$(document).find(id).Link('lower').to($(document).find(initialMonthId), intValue);
			$(document).find(id).Link('upper').to($(document).find(finalMonthId), intValue);

			$(document).find(id).Link('lower').to($(document).find(self.getId(grid, indice, "lower-offset")), myValue);
			$(document).find(id).Link('upper').to($(document).find(self.getId(grid, indice, "upper-offset")), myValue);
		},
		startComponent : function(){
			this.linkComponent(this.upGrid);
			this.linkComponent(this.downGrid);
		},
		getNextIndex: function(grid){
			var rowNoExiste = grid.find("tbody #noMovs").length;
			var totalRows = grid.find("tbody tr").length;
			if(totalRows == 1 && rowNoExiste == 1){
				return 0;
			}if(totalRows > 0 && rowNoExiste == 0){
				return totalRows;
			}
		},
		activateTemplate: function(id) {
		    var t = document.querySelector(id);
		    return document.importNode(t.content, true);
		},
		apiCall: function(actionURL, callback) {
			var method = "GET";
			var header = $("meta[name='_csrf_header']").attr("content");
			var token = $("meta[name='_csrf']").attr("content");
			blockPage();
			$.ajax({
				url : context + '/' + actionURL,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					callback(data);
					unblockPage();
				}
			});
		},
		validate : function(){
			console.log("Inicio de validacion de captura");
			
			//se valida que exista un tipo de movimiento seleccionado
			var movementTypeSelect = $("#movementTypeId");
			var movementTypeId = parseInt(movementTypeSelect.val());
			console.log("tipo de movimiento: " + movementTypeId);
			if (movementTypeId <= 0){
				console.log("error tipo de mov");
				movementTypeSelect.closest('[data-name="movementTypeContainer"]').addClass("has-error");
				return false;
			}
			return true;
		}
	};
	
	//Controlador tipo de movimiento
	$("#movementTypeId").on("change", function (e) {
	    movementController.update(parseInt(this.value));    
	});
	
	movementController.startComponent();

	var requestForm = $('#requestForm');

	$('#partialSave').click(function(e) {
		alert("haciendo guardado parcial");
		var isCorrect = movementController.validate();
		if (isCorrect){
			requestForm.find('#nextStageValueCode').val("SOLPEND");
			submitAjaxJQ('requestForm', 'dashboard', '');
		}
	});

	$('#saveAndContinue').click(function(e) {
		alert("Finalizando captura y avanzando tramite");
		requestForm.find('#nextStageValueCode').val("SOLCOMP");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});
}

function initAuthorization() {
	alert("Iniciando autorizacion");

	var requestForm = $('#requestForm');

	$('#cancelFormality').click(function(e) {
		alert("Cancelando Tramite");
		requestForm.find('#nextStageValueCode').val("CANCELAR");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});

	$('#authorizateFormality').click(function(e) {
		alert("autorizando Tramite");
		requestForm.find('#nextStageValueCode').val("SIGFIRMA");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});

	$('#finishFormality').click(function(e) {
		alert("autorizando Tramite y finalizar");
		requestForm.find('#nextStageValueCode').val("CONTINUAR");
		submitAjaxJQ('requestForm', 'dashboard', '');
	});
}
