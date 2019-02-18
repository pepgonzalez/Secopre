var movementController2 = {
	upGrid : "#addComponent",
	downGrid : "#substractComponent",
	slider : "SliderControl",
	monthsController : false,

	/* funcion que oculta los grids */
	reset : function() {
		$(this.upGrid).hide();
		$(this.downGrid).hide();
	},

	/* funcion que elimina todos los registros del grid */
	clean : function(grid) {
		$(grid).find("tbody tr").remove();
		$(grid)
				.find("tbody")
				.html(
						'<tr id="noMovs"><td colspan="6">No hay Movimientos Capturados</td><tr>');
		this.updateTotal(this, grid, true);
	},

	/* titulos en función del tipo de movimiento seleccionado */
	titles : {
		al : '<i class="fa fa-cogs"></i>Ampliaci&oacute;n L&iacute;quida',
		rl : '<i class="fa fa-cogs"></i>Reducci&oacute;n L&iacute;quida',
		ac : '<i class="fa fa-cogs"></i>Ampliaci&oacute;n Compensada',
		rc : '<i class="fa fa-cogs"></i>Reducci&oacute;n Compensada'
	},

	/* funcion que muestra y actualiza el titulo del grid */
	showGrid : function(grid, name) {
		var grd = $(grid);
		grd.find(".caption").html(name);
		grd.show();
	},

	/*
	 * funcion para mostrar el grid con el titulo correspondiente en funcion del
	 * tipo de movimiento
	 */
	update : function(value) {
		this.reset();

		if (value > 0) {
			$(document).find("#movementTypeId").closest('[data-name="movementTypeContainer"]').removeClass("has-error");
			switch (value) {
			case 1:
				this.showGrid(this.upGrid, this.titles.al);
				break;
			case 2:
				this.showGrid(this.downGrid, this.titles.rl);
				break;
			case 3:
				this.showGrid(this.upGrid, this.titles.ac);
				this.showGrid(this.downGrid, this.titles.rc);
				break;
			}
		}
	},

	getId : function(grid, idx, attr, escaped) {
		escaped = escaped || 1;
		var list = (grid == this.upGrid ? "upMovements" : "downMovements");
		return (escaped == 1 ? "#" : "") + list + idx
				+ (escaped == 1 ? "\\." : ".") + attr;
	},
	getPath : function(grid, idx, attr) {
		var list = (grid == this.upGrid ? "upMovements" : "downMovements");
		return "" + list + "[" + idx + "]." + attr;
	},
	updateTotal : function(self, grid, turnDownIteration) {
		turnDownIteration = turnDownIteration || false;
		var grd = $(grid);
		var totalId = (grid === self.upGrid ? "#upMovementsTotal" : "#downMovementsTotal");

		var gridTotal = parseFloat(0);

		if (!turnDownIteration) {
			// iteracion sobre las filas
			grd.find("tbody tr:not(#noMovs)").each(function(idx, e) {
						var row = $(e);
						var isRemovedRow = row.find("[data-name='removedElement']").val();

						// solo considera las filas no eliminadas
						if (parseInt(isRemovedRow) == 0) {
							var totalAmount = row.find("[data-name='totalAmount'] input");
							if (totalAmount.val().length > 0) {
								gridTotal += parseFloat(totalAmount.val().replace(/[^0-9\.]/g, ''));
							}
						}
					});
		}
		
		gridTotal = gridTotal.toFixed(2);
		
		function formatNumberField(x) {
		    // unformat the value
		    var value = x.replace(/[^0-9\.]/g, '');
		    
		    function format(num){
		        var n = num.toString(), p = n.indexOf('.');
		        return n.replace(/\d(?=(?:\d{3})+(?:\.|$))/g, function($0, i){
		            return p<0 || i<p ? ($0+',') : $0;
		        });
		    }
		    
		    x  = format(value);
		    return x;
		}
		grd.find(totalId).find(".val").empty().html((formatNumberField(gridTotal)));
	},
	getSliderId : function(grid) {
		var direction = (grid == this.upGrid ? "up" : "down");
		return "#" + direction + this.slider;
	},
	linkComponent : function(grid) {
		var grd = $(grid);
		var self = this;

		// si no existe el row de "sin elementos, se iteran los objetos"
		if (grd.find("tbody #noMovs").length == 0) {

			grd.find("tbody tr").each(function(idx, e) {
				var element = $(e);
				var rowId = element.attr("id");

				
				//fix para sacar las fechas actuales guardadas del movimiento
				
				var initialMonth = parseInt(element.find(self.getId(grid, idx, "initialMonthId")).val());
				var finalMonth = parseInt(element.find(self.getId(grid, idx, "finalMonthId")).val());
				
				self.startSlider(self, idx, initialMonth, finalMonth, grid);
				
				//fix para dar formato a los montos
				var monthOriginalValue = element.find(self.getId(grid, idx, "monthAmount")).val();				
				var monthStr = monthOriginalValue.replace(",","");				
				//var monthAmount = parseFloat(element.find(self.getId(grid, idx, "monthAmount")).val());
				var monthAmount = parseFloat(monthStr);
				monthAmount = monthAmount.toFixed(2);				
				element.find(self.getId(grid, idx, "monthAmount")).val(monthAmount);
				
				/*
				var totalOriginalValue = element.find(self.getId(grid, idx, "totalAmount")).val();
				var totalStr = totalOriginalValue.replace(",","");
				//var totalAmount = parseFloat(element.find(self.getId(grid, idx, "totalAmount")).val()).toFixed(2);
				var totalAmount = parseFloat(totalStr).toFixed(2);
				element.find(self.getId(grid, idx, "totalAmount")).val(totalAmount);
				 */
				
				self.addRemoveEvent(self, grid, idx);
				self.addInfoEvent(self, grid, idx);
				self.addCloneEvent(self, grid, idx);
				self.addEditEvent(self, grid, idx);

				// asignar eventos de cambio
				self.addOnChangeEvent(self, grid, idx,"programaticKeyId", true);
				self.addOnChangeEvent(self, grid, idx,"entryId", false);
				self.updateAmounts(self, grid, idx,"monthAmount");

				$(grid).find("[data-name='programaticKey'] select").each(function() {
					var currentSelect = $(this);
					currentSelect.qtip({
						content : {
							text : function(event,api) {

								var header = $("meta[name='_csrf_header']").attr("content");
								var token = $("meta[name='_csrf']").attr("content");
								$.ajax({
									url : 'wf/pk/' + currentSelect.val(),
									beforeSend : function(xhr) {
										xhr.setRequestHeader(header,token);
									},
									success : function(data) {
										api.set('content.text',data);
									}
								});

								return '<div class="tooltip-popup" style="display:block;"><div class="qtip-titlebar"><div id="qtip-{id}-title" class="qtip-title">Cargando...</div></div></div>';
							},
							position : {
								my : 'top left',
								at : 'bottom right'
							},
							hide : {
								fixed : true,
								delay : 400
							},
							style : {
								classes : "ui-tooltip-shadow"
							}
						}
					});
				});

			});

			self.updateTotal(self, grid);

		} else {
			grd.find("tbody tr:not(#noMovs)").remove();
		}

		// evento para agregar movimientos
		var addBtn = grd.find(".actions #addMov").on("click", function() {
			self.addMovementRow(self, grid, false, null);
			// al agregar me bloqueo hasta que la validacion correcta me desbloquee
			grd.find(".actions #addMov").hide();
			
			//se borran todos los botones de clonacion que existan
			$(document).find(".cloneButton").hide();
			$(document).find(".editButton").hide();
			$(document).find(".addButton").hide();
			
			$(document).find("#saveAndContinue").hide();
		});

		var saveBtn = grd.find(".actions #saveMov").on("click",function() {
			
			$(document).find("#requestForm").find(".pk").prop("disabled",false);
			$(document).find("#requestForm").find(".entry").prop("disabled",false);
			$(document).find("#requestForm").find(".entryType").prop("disabled",false);
			$(document).find("#requestForm").find(".monthAmount").prop("disabled",false);
			
			submitAjaxJQWithAction('requestForm', 'dashboard','movements2Capture','auth/wf/capture/partial/movements2');
		});
		
		// se ocultan los botones de informacion
		$(document).find(".infoButton").hide();
		
		// se oculta el boton de guardar
		saveBtn.hide();
	},
	addMovementRow : function(self, grid, isComplementary, data, minMonth) {
		var grd = $(grid);
		var nextIndex = self.getNextIndex(grd);

		if (nextIndex == 0) {
			grd.find("tbody #noMovs").remove();
		}

		var e = $(self.activateTemplate("#movementRowTemplate"));

		// accion
		e.find("tr").attr("id", "row" + nextIndex).attr("data-rowNumber",nextIndex);
		e.find("[data-name='deleteAction'] #rowDeleteButton").attr("id", "rmvIdx" + nextIndex);
		e.find("[data-name='deleteAction'] #rowInfoButton").attr("id", "infoIdx" + nextIndex);
		e.find("[data-name='deleteAction'] #cloneInfoButton").attr("id", "cloneIdx" + nextIndex);

		// llave programatica
		e.find("[data-name='programaticKey'] select").attr("name",self.getPath(grid, nextIndex, "programaticKeyId")).attr("id",self.getId(grid, nextIndex, "programaticKeyId", 2)).removeAttr("multiple");
		e.find("[data-name='programaticKey']").find("input[type='hidden']").remove();

		// entry
		e.find("[data-name='entry'] select").attr("name",self.getPath(grid, nextIndex, "entryId"))
		.attr("id",self.getId(grid, nextIndex, "entryId", 2)).removeAttr("multiple");
		e.find("[data-name='entry']").find("input[type='hidden']").remove();
		
		// entryType
		e.find("[data-name='entryType'] select").attr("name",self.getPath(grid, nextIndex, "entryTypeId"))
		.attr("id",self.getId(grid, nextIndex, "entryTypeId", 2)).removeAttr("multiple");	
		e.find("[data-name='entryType']").find("input[type='hidden']").remove();
		
		// sliderControl
		e.find("[data-name='sliderControl'] #sliderControl").attr("id",self.getSliderId(grid).substring(1) + nextIndex);

		// lowerOffset
		e.find("[data-name='lower-offset'] ").attr("id",self.getId(grid, nextIndex, "lower-offset", 2));
		e.find("[data-name='upper-offset'] ").attr("id",self.getId(grid, nextIndex, "upper-offset", 2));

		// monthAmount
		e.find("[data-name='monthAmount'] input").attr("name",self.getPath(grid, nextIndex, "monthAmount"))
		.attr("id",self.getId(grid, nextIndex, "monthAmount", 2)).attr("value","0");

		// totalAmount
		e.find("[data-name='totalAmount'] input").attr("name",self.getPath(grid, nextIndex, "totalAmount"))
		.attr("id",self.getId(grid, nextIndex, "totalAmount", 2)).attr("value","0");

		// initialMonthId
		e.find("[data-name='initialMonthId']").attr("name",self.getPath(grid, nextIndex, "initialMonthId"))
		.attr("id",self.getId(grid, nextIndex, "initialMonthId", 2)).attr("value","");

		// finalMonthId
		e.find("[data-name='finalMonthId']").attr("name",self.getPath(grid, nextIndex, "finalMonthId"))
		.attr("id",self.getId(grid, nextIndex, "finalMonthId", 2)).attr("value","");

		// removedElement
		e.find("[data-name='removedElement']").attr("name",self.getPath(grid, nextIndex, "removedElement"))
		.attr("id",self.getId(grid, nextIndex, "removedElement", 2)).attr("value","0");

		e.find("[data-name='movementTypeId']").attr("name",self.getPath(grid, nextIndex, "movementTypeId"))
		.attr("id",self.getId(grid, nextIndex, "movementTypeId", 2)).attr("value",(grid == self.upGrid ? "1" : "-1"));

		e.find("[data-name='requestDetailId']").attr("name",self.getPath(grid, nextIndex, "requestDetailId"))
		.attr("id",self.getId(grid, nextIndex, "requestDetailId", 2)).attr("value", "-1");

		/*
		 * e.find("[data-name='totalAmount']").attr("name", self.getPath(grid,
		 * nextIndex, "totalAmount")) .attr("id", self.getId(grid, nextIndex,
		 * "totalAmount", 2)).attr("value","0");
		 */

		if (isComplementary) {
			// se oculta lo que no debe ir, o se desabilita
			e.find("[data-name='deleteAction'] a").hide();
			e.find("[data-name='programaticKey'] select").val(data.programaticKeyId).attr("readonly", "true");

			e.find("[data-name='sliderControl']").hide();
			e.find("[data-name='monthLabels']").attr("colspan", "2");
			e.find("[data-name='initialMonthId']").val(data.initialMonth);
			e.find("[data-name='finalMonthId']").val(data.finalMonthId);
			e.find("[data-name='monthAmount'] input").val(data.monthAmount).attr("readonly", "true");
		}

		grd.find("tbody").append(e);

		grd.find('tbody').find('tr').each(function() {

			$(this).find("[data-name='programaticKey'] select").each(function() {
				var currentSelect = $(this);
				currentSelect.qtip({
					content : {
						text : function(event,api) {
							var header = $("meta[name='_csrf_header']").attr("content");
							var token = $("meta[name='_csrf']").attr("content");
							$.ajax({
								url : 'wf/pk/'+ currentSelect.val(),
								beforeSend : function(xhr) {
									xhr.setRequestHeader(header,token);
								},
								success : function(data) {
									api.set('content.text',data);
								}
							});

							return '<div class="tooltip-popup" style="display:block;"><div class="qtip-titlebar"><div id="qtip-{id}-title" class="qtip-title">Cargando...</div></div></div>';
						},
						position : {
							my : 'top left',
							at : 'bottom right'
						},
						hide : {
							fixed : true,
							delay : 400
						},
						style : {
							classes : "ui-tooltip-shadow"
						}
					}
				});
			});
		});

		if (minMonth != null && parseInt(minMonth) > 0){
			self.startSlider(self, nextIndex, parseInt(minMonth), 11, grid);
		}else{
			self.startSlider(self, nextIndex, parseInt(new Date().getMonth()), 11, grid);
		}
		self.addOnChangeEvent(self, grid, nextIndex, "programaticKeyId", true);
		self.addOnChangeEvent(self, grid, nextIndex, "entryId", false);
		self.addRemoveEvent(self, grid, nextIndex);
		self.addInfoEvent(self, grid, nextIndex);
		self.addCloneEvent(self, grid, nextIndex);
		self.updateAmounts(self, grid, nextIndex, "monthAmount");

		$(document).find("#cloneIdx" + nextIndex).hide();

		if (isComplementary) {
			// se filtra
			self.getEntriesByProgramaticKeyAndDistrict(data.programaticKeyId,function(response) {

				var entrySelect = $(document).find(self.getId(grid, nextIndex, "entryId"));

				entrySelect.empty();
				entrySelect.append('<option value="-1">Seleccione..</option>');
				$.each(response,function(index, item) {
					if (parseInt(item.id) != parseInt(data.entryId)) {
						entrySelect.append('<option value="'+ item.id+ '">' + item.name + '</option>');
					}
				});
			});
			// se dispara la actualizacion de montos
			$(self.getId(grid, nextIndex, "monthAmount")).blur();

		}
		grd.find("tbody #noMovs").remove();
	},
	getRowData : function(self, grid, index) {
		var finalMonth = parseInt($(self.getId(grid, index, "finalMonthId"))
				.val());
		var initialMonth = parseInt($(self.getId(grid, index, "initialMonthId"))
				.val());
		var entryId = parseInt($(self.getId(grid, index, "entryId")).val());
		var programaticKeyId = parseInt($(
				self.getId(grid, index, "programaticKeyId")).val());
		var monthAmount = parseFloat($(self.getId(grid, index, "monthAmount"))
				.val());
		var data = {}
		data.programaticKeyId = programaticKeyId;
		data.entryId = entryId;
		data.initialMonth = initialMonth;
		data.finalMonth = finalMonth;
		data.monthAmount = monthAmount;
		return data;
	},
	blockRow : function(self, grid, index, keepSlider, forceDisabled) {
		var keepSlider = keepSlider || false;
		$(self.getId(grid, index, "programaticKeyId")).attr("readonly", "true");
		$(self.getId(grid, index, "entryId")).attr("readonly", "true");
		$(self.getId(grid, index, "monthAmount")).attr("readonly", "true").keyup();
		$(self.getId(grid, index, "totalAmount")).attr("readonly", "true");
		
		if(forceDisabled){
			$(self.getId(grid, index, "programaticKeyId")).attr("readonly", "true").prop("disabled",true);
			$(self.getId(grid, index, "entryId")).attr("readonly", "true").prop("disabled",true);
			$(self.getId(grid, index, "monthAmount")).attr("readonly", "true").prop("disabled",true);
		}
		
		var sliderId = self.getSliderId(grid) + index;
		if (keepSlider) {
			var sl = $(sliderId)[0];
			if(sl){
				sl.setAttribute('disabled', true);
			}
		} else {
			$(sliderId).hide();
		}
	},
	unblockRow : function(self, grid, index, keepSlider, forceDisabled){
		var keepSlider = keepSlider || false;
		$(self.getId(grid, index, "programaticKeyId")).removeAttr("readonly", "false");
		$(self.getId(grid, index, "entryId")).removeAttr("readonly", "false");
		$(self.getId(grid, index, "monthAmount")).removeAttr("readonly", "false");
		$(self.getId(grid, index, "totalAmount")).removeAttr("readonly", "false");
		
		if(forceDisabled){
			$(self.getId(grid, index, "programaticKeyId")).removeAttr("readonly", "false").prop("disabled",false);
			$(self.getId(grid, index, "entryId")).removeAttr("readonly", "false").prop("disabled",false);
			$(self.getId(grid, index, "monthAmount")).removeAttr("readonly", "false").prop("disabled",false);
		}
		
		var sliderId = self.getSliderId(grid) + index;
		if (keepSlider) {
			var sl = $(sliderId)[0];
			if(sl){
				//sl.setAttribute('disabled', false);
				//sl.removeAttribute('disabled');
				sl.removeAttribute('disabled');
			}
		} else {
			$(sliderId).show();
		}
	},
	updateAmounts : function(self, grid, nextIndex, element) {
		var ma = $(document).find(self.getId(grid, nextIndex, element));
		
		function formatNumberField() {
		    // unformat the value
		    var value = this.value.replace(/[^0-9\.]/g, '');
		    
		    function format(num){
		        var n = num.toString(), p = n.indexOf('.');
		        return n.replace(/\d(?=(?:\d{3})+(?:\.|$))/g, function($0, i){
		            return p<0 || i<p ? ($0+',') : $0;
		        });
		    }
		    
		    this.value  = format(value);
		}

		var totalInput = $(document).find(self.getId(grid, nextIndex, "totalAmount"));
		totalInput.keyup(formatNumberField);
		
		// funcion para cambiar siempre a numericos
		ma.keyup(formatNumberField);

		// validacion de montos al perder el foco
		ma
		.blur(function() {
			var numeric = this.value.replace(/[^0-9\.]/g, '');
			this.value = numeric;
			
			var float = parseFloat(this.value);
			this.value = float.toFixed(2);

			var finalMonth = parseInt($(self.getId(grid, nextIndex, "finalMonthId")).val());
			var initialMonth = parseInt($(self.getId(grid, nextIndex, "initialMonthId")).val());
			var entryId = parseInt($(self.getId(grid, nextIndex, "entryId")).val());
			var programaticKeyId = parseInt($(self.getId(grid, nextIndex, "programaticKeyId")).val());

			var districtId = parseInt($("#districtId").val());
			var entryId = parseInt($(self.getId(grid, nextIndex, "entryId")).val());

			var total = parseFloat(0);
			var that = this;

			function updateTotalAmounts() {
				// se calcula el monto total del movimiento
				total = parseFloat(((finalMonth - initialMonth) + 1) * that.value);

				// si el monto es mayor a cero, se elimina el error
				if (parseFloat(that.value) > 0) {
					self.removeClassError(self.getId(grid, nextIndex,"monthAmount"));
				}

				function format(num){
			        var n = num.toString(), p = n.indexOf('.');
			        return n.replace(/\d(?=(?:\d{3})+(?:\.|$))/g, function($0, i){
			            return p<0 || i<p ? ($0+',') : $0;
			        });
			    }
				
				var montoTotalPorRegistro = format(total.toFixed(2));
				
				// guardamos el monto total en total amount
				$(self.getId(grid, nextIndex, "totalAmount")).val(montoTotalPorRegistro).keyup();

				// se invoca update para actualizar los totales del grid
				self.updateTotal(self, grid);
			}

			function blockRecord() {
				// se bloquea la version actual
				self.blockRow(self, grid, nextIndex, true, false);
				//$(grid).find("[data-name='deleteAction']").find("#cloneIdx" + nextIndex).show();
				//$(grid).find("[data-name='deleteAction']").find("#infoIdx" + nextIndex).hide();
				$(grid).find(".actions #saveMov").show();
			}

			function addCompensatedMovement() {
			}

			// se dispara el evento de validacion del slider que tambien
			// determina el cambio de partida y clave
			var sliderId = self.getSliderId(grid) + nextIndex;
			$(document).find(sliderId).trigger("change");

			var validationResult = self.monthsController;
			if (!validationResult) {
				return;
			}

			// si se capturó algo
			if ((this.value.length > 0) && (parseFloat(this.value) > 0)) {

				var movementType = parseInt($(self.getId(grid, nextIndex, "movementTypeId")).val());
				var calls = [];

				// si es un movimiento de reduccion
				if (movementType < 0) {

					// por cada mes, arma la llamada de validacion de
					// montos
					var isValidMovement = true;
					for (var i = initialMonth; i <= finalMonth; i++) {

						var rowNumber = ma.closest("tr").attr("data-rowNumber");
						// se buscan en el grid otras partidas que afecten al mismo mes
						var totalGrid = self.obtenerTotalDePartidaPorMesEnGrid(self, grid, entryId, i, rowNumber);
						var totalActual = parseFloat(this.value);

						var montoTotal = totalGrid + totalActual;
						var call = window.getPromise("auth/API/get/movOk/" + districtId + "/" + entryId + "/" + i + "/" + montoTotal, function(data) {
							if (data.result <= 0) {
								isValidMovement = false;
								window.showNotification("error", data.msg);
							}
						});
						calls.push(call);
					}

					// se bloquea la pantalla y se ejecutan las promesas
					window.blockPage();
					jQuery.when.apply(null, calls).done(function() {
						if (!isValidMovement) {
							ma.closest("[data-name='monthAmount']").addClass("has-error");
							ma.val("0");
							updateTotalAmounts();
							window.unblockPage();
							return;
						}
						unblockPage();
						updateTotalAmounts();
						addCompensatedMovement();
						blockRecord();
					});

				} else {
					// si es un movimiento a la alza no valido montos
					// solo actualizo
					updateTotalAmounts();
					// se bloquea el registro actual
					blockRecord();
				}
			} else {
				window.showNotification("error","Capture un monto para continuar");
				ma.closest("[data-name='monthAmount']").addClass("has-error");
				updateTotalAmounts();
			}
		});
	},
	obtenerTotalDePartidaPorMesEnGrid : function(self, grid, entryId, monthId,
			rowNumber) {

		// todas las partidas que no soy yo, y que son de la misma partida
		var filteredRows = $(grid).find("tbody tr:not(#noMovs)").filter(
				function() {
					var row = $(this);
					var esRegistroEliminado = parseInt(row.find(
							"[data-name='removedElement']").val());
					var indice = parseInt(row.attr("data-rowNumber"));
					var mesInicial = parseInt(row.find(
							"[data-name='initialMonthId']").val());
					var mesFinal = parseInt(row.find(
							"[data-name='finalMonthId']").val());
					var monthEntry = parseInt(row.find(
							"[data-name='entry'] select").val());

					if (esRegistroEliminado <= 0 && (mesInicial <= monthId)
							&& (mesFinal >= monthId) && (rowNumber != indice)
							&& (entryId == monthEntry)) {
						return true;
					} else {
						return false;
					}
				});

		var total = 0;
		filteredRows.each(function() {
			total += parseFloat($(this).find("[data-name='monthAmount'] input")
					.val());
		});

		return total;
	},
	getCurrentEntries : function(grid, index) {
		var currentEntries = [];
		var filteredRows = $(grid).find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			var rowNumber = parseInt($(this).attr("data-rownumber"));
			return ((parseInt(flag.val()) <= 0) && (index != rowNumber));
		});

		filteredRows.each(function(idx, e) {
			var row = $(e);
			var entry = parseInt(row.find("[data-name='entry'] select").val());
			currentEntries.push(entry);
		});
		return currentEntries;
	},
	getAllSelectedEntries:function(self, grid, index){
		var currentEntries = [];
		var downFilteredRows = $(self.downGrid).find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			var rowNumber = parseInt($(this).attr("data-rownumber"));
			return ((parseInt(flag.val()) <= 0) && (grid != self.downGrid || index != rowNumber));
		});

		downFilteredRows.each(function(idx, e) {
			var row = $(e);
			var entry = parseInt(row.find("[data-name='entry'] select").val());
			currentEntries.push(entry);
		});
		
		var upFilteredRows = $(self.upGrid).find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			var rowNumber = parseInt($(this).attr("data-rownumber"));
			return ((parseInt(flag.val()) <= 0) && (grid != self.upGrid || index != rowNumber));
		});

		upFilteredRows.each(function(idx, e) {
			var row = $(e);
			var entry = parseInt(row.find("[data-name='entry'] select").val());
			currentEntries.push(entry);
		});
		return currentEntries;
	},
	addOnChangeEvent : function(self, grid, indice, element, ajaxCall) {
		var id = self.getId(grid, indice, element);
		$(document).find(id).on("change",function(e) {

			var currentEntries = [];
			if ((self.isCompensatedMovement()) || (grid == self.downGrid)) {
				//currentEntries = self.getCurrentEntries(grid,indice);
				currentEntries = self.getAllSelectedEntries(self, grid, indice);
			}

			var currentSelect = $(this);

			/*
			 * logica que se ejecuta en claves programaticas
			 * para actualizar la partida
			 */

			if (ajaxCall) {

				currentSelect.qtip("destroy");

				currentSelect.qtip({
					content : {
						text : function(event, api) {

							var header = $("meta[name='_csrf_header']").attr("content");
							var token = $("meta[name='_csrf']").attr("content");
							$.ajax({
								url : 'wf/pk/'+ currentSelect.val(),
								beforeSend : function(xhr) {
									xhr.setRequestHeader(header,token);
								},
								success : function(data) {
									api.set('content.text',data);
								}
							});	

							return '<div class="tooltip-popup" style="display:block;"><div class="qtip-titlebar"><div id="qtip-{id}-title" class="qtip-title">Cargando...</div></div></div>';
						},
						position : {
							my : 'top left',
							at : 'bottom right'
						},
						hide : {
							fixed : true,
							delay : 400
						},
						style : {
							classes : "ui-tooltip-shadow"
						}
					}
				});

				// preguntamos el id del distrito
				var districtId = $("#districtId").val();

				var data = self.getEntriesByProgramaticKeyAndDistrict(this.value,function(data) {

					var entrySelect = $(document).find(self.getId(grid,indice,"entryId"));
					entrySelect.empty();
					entrySelect.append('<option value="-1">Seleccione..</option>');
					$.each(data,function(index,item) {
						if (currentEntries.length > 0) {
							var existe = false;
							for (var i = 0; i < currentEntries.length; i++) {
								if (currentEntries[i] == item.id) {
									existe = true;
									break;
								}
							}
							if (!existe) {
								entrySelect.append('<option value="'+ item.id + '">' + item.code + " - " + item.name + '</option>');
							}
						} else {
							entrySelect.append('<option value="'+ item.id + '">' + item.code + " - " + item.name + '</option>');
						}
					});
				});
			}

			if (parseInt(this.value) > 0) {
				self.removeClassError(id);
			}
		});
	},
	getEntriesByProgramaticKeyAndDistrict : function(programaticKeyId, callback) {
		var districtId = $("#districtId").val();

		// clousure
		function returnMethod(data) {
			callback(data);
		}

		// se ejecuta el ajax
		self.apiCall('auth/API/get/entry/' + programaticKeyId + "/" + districtId, function(data) {
			returnMethod(data);
		});
	},
	removeClassError : function(id) {
		$(id).closest(".has-error").removeClass("has-error");
	},
	removeRow : function(self, grid, indice) {
		var a = $(grid).find("[data-name='deleteAction']").find("#rmvIdx" + indice);

		var row = a.parent().parent();
		row.find(self.getId(grid, indice, "removedElement")).val("1");

		//row.hide()
		row.remove();
		self.updateTotal(self, grid);

		var filteredRows = $(grid).find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			return (parseInt(flag.val()) <= 0);
		});

		if (filteredRows.length == 0) {
			$(grid).find("tbody").html('<tr id="noMovs"><td colspan="6">No hay Movimientos Capturados</td><tr>');
		}

		//$(grid).find(".actions #addMov").show();
		$(grid).find(".actions #saveMov").hide();
		
		//se borran todos los botones de clonacion que existan
		$(document).find(".cloneButton").show();
		$(document).find(".editButton").show();
		$(document).find(".addButton").show();
		$(document).find("#saveAndContinue").show();
		$(document).find("#currentTotals").hide();
	},
	addRemoveEvent : function(self, grid, indice) {
		var a = $(grid).find("[data-name='deleteAction']").find(
				"#rmvIdx" + indice);

		a.on("click", function() {

			var row = $(this).parent().parent();
			// se valida si es un elemento ya salvado, si es asi, se elimina y
			// se recarga la pagina
			var isSaved = row.find(self.getId(grid, indice, "isSaved")).val();

			if (isSaved == "true") {

				var requestDetailId = row.find(self.getId(grid, indice, "requestDetailId")).val();
				var formalityCode = $(document).find("#formalityCode").val();
				var stageConfigId = $(document).find("#stageConfigId").val();
				var requestId = $(document).find("#requestId").val();

				var url = "auth/wf/capture/delete/" + requestDetailId + "/"+ formalityCode + "/" + requestId + "/" + stageConfigId + "/1";
				submitAjaxJQWithAction('requestForm', 'dashboard','movements2Capture', url);
			} else {
				self.removeRow(self, grid, indice);
			}
		});
	},
	isCompensatedMovement : function() {
		var mt = parseInt($("#movementTypeId").val());
		return (mt == 3);
	},
	addInfoEvent : function(self, grid, indice) {
		var a = $(grid).find("[data-name='deleteAction']").find(
				"#infoIdx" + indice);

		a.on("click", function() {
			var row = $(this).parent().parent();

			var programaticKey = row.find(self.getId(grid, indice, "programaticKeyId")).val();
			var entry = row.find(self.getId(grid, indice, "entryId")).val();
			var district = $("#districtId").val();

			if (parseInt(entry) <= 0) {
				window.showNotification("error","Debe Seleccionar una partida para ver el detalle");
			} else {
				window.showEntryTotals(district, programaticKey, entry);
			}

		});
	},
	addCloneEvent : function(self, grid, indice) {
		var a = $(grid).find("[data-name='deleteAction']").find("#cloneIdx" + indice);

		a.on("click", function() {

			//se validan los meses actuales
			
			var programaticKey = $(document).find(self.getId(grid, indice, "programaticKeyId")).val();
			var entry = $(document).find(self.getId(grid, indice, "entryId")).val();
			var usedMonths = [];
			var availableMonths = [];
			
			function addToUsedMonths(initialMonth, finalMonth){
				for(var i = parseInt(initialMonth); i <= parseInt(finalMonth); i++){
					usedMonths.push(i);
				}
			}
			
			var brotherEntries = $(grid).find("tbody tr:not(#noMovs)").filter(
					function() {
		
						var flag = $(this).find("[data-name='removedElement']").val();
						var initialMonthId = $(this).find("[data-name='initialMonthId']").val();
						var finalMonthId = $(this).find("[data-name='finalMonthId']").val();
		
						var currentProgramaticKeyId = $(this).find("[data-name='programaticKey'] select").val();
						var currentEntryId = $(this).find("[data-name='entry'] select").val();
						var index = $(this).attr("data-rownumber");
		
						if (flag <= 0 && programaticKey == currentProgramaticKeyId && entry == currentEntryId) {
							return true;
						} else {
							return false;
						}
					});
			
			// si hay al menos un registro hermano
			if (brotherEntries.length > 0) {
				// por cada hermano, se agregan los meses que ya
				// tiene marcados
				brotherEntries.each(function(idx, r) {
					var e = $(r);
					var brotherInitialMonthId = e.find("[data-name='initialMonthId']").val();
					var brotherFinalMonthId = e.find("[data-name='finalMonthId']").val();
					addToUsedMonths(brotherInitialMonthId,brotherFinalMonthId);
				});
			}
			
			for (var i = parseInt(new Date().getMonth()); i <= 11; i++){
				var monthSelected = false;
				
				for (var j = 0; j < usedMonths.length; j++){
					if (i == usedMonths[j]){
						monthSelected = true;
						break;
					}
				}
				
				if(!monthSelected){
					availableMonths.push(i);
				}
			}
			
			if(availableMonths.length == 0){
				window.showNotification("error","No existen meses disponibles para la captura de partida. Verifique.");
				return;
			}
			
			var minMonth = parseInt(availableMonths[0]);
			
			if(availableMonths.length > 1){
				for(var i = 1; i < availableMonths.length; i++){
					if(parseInt(availableMonths[i]) < minMonth){
						minMonth = availableMonths[i];
					}
				}
			}
			
			self.addMovementRow(self, grid, false, null, minMonth);
			var row = $(this).parent().parent();

			$(document).find(self.getId(grid, indice + 1, "programaticKeyId")).val(programaticKey);
			$(document).find(self.getId(grid, indice + 1, "entryId")).val(entry);
			
			
			a.hide();
			$(grid).find("#addMov").hide();
			//se borran todos los botones de clonacion que existan
			$(document).find(".cloneButton").hide();
			$(document).find(".addButton").hide();
			$(document).find(".editButton").hide();
			$(document).find("#saveAndContinue").hide();
		});
	},
	addEditEvent : function(self, grid, indice){
		var a = $(grid).find("[data-name='deleteAction']").find("#editIdx" + indice);

		a.on("click", function() {

			self.unblockRow(self, grid, indice, true, true);
						
			a.hide();
			$(grid).find("#addMov").hide();
			//se borran todos los botones de clonacion que existan
			$(document).find(".cloneButton").hide();
			$(document).find(".addButton").hide();
			$(document).find(".editButton").hide();
			$(document).find("#saveAndContinue").hide();
			
			$(grid).find("[data-name='deleteAction']").find("#infoIdx" + indice).show();
		});
	},
	startSlider : function(self, indice, initialMonth, finalMonth, grid) {

		var id = self.getSliderId(grid) + indice;

		var rowSlider = document.getElementById(id);

		$(document).find(id).noUiSlider({
			connect : true,
			behaviour : 'tap',
			step : 1,
			start : [ initialMonth, finalMonth ],
			range : {
				'min' : [ initialMonth ],
				'max' : [ 11 ]
			}
		});

		var months = [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio","Julio", "Agosto", "Septiembre", "Octubre", "Noviembre","Diciembre" ];
		function myValue(value) {
			$(this).text(months[parseInt(value)]);
		}
		function intValue(value) {
			$(this).val(parseInt(value));
		}

		var initialMonthId = self.getId(grid, indice, "initialMonthId");
		var finalMonthId = self.getId(grid, indice, "finalMonthId");

		$(document).find(id).Link('lower').to($(document).find(initialMonthId),intValue);
		$(document).find(id).Link('upper').to($(document).find(finalMonthId),intValue);

		$(document).find(id).Link('lower').to($(document).find(self.getId(grid, indice, "lower-offset")),myValue);
		$(document).find(id).Link('upper').to($(document).find(self.getId(grid, indice, "upper-offset")),myValue);
		
		$(document).find(initialMonthId).val(initialMonth);
		$(document).find(finalMonthId).val(finalMonth);
		
		$(document).find(self.getId(grid, indice, "lower-offset")).text(months[initialMonth]);
		$(document).find(self.getId(grid, indice, "upper-offset")).text(months[finalMonth]);

		var availableMonths = {};
		for (var i = parseInt(new Date().getMonth()); i <= 11; i++) {
			availableMonths[i] = false;
		}

		function setAvailableMonths(currentInitial, currentFinal) {
			for (var j = parseInt(currentInitial); j <= parseInt(currentFinal); j++) {
				availableMonths[j] = true;
			}
		}

		// evento para actualizar los totales cuando cambia el valor del slider
		$(document).find(id).on('change',function() {
			// $(self.getId(grid, indice,"monthAmount")).blur();
	
			self.monthsController = true;
	
			// obtenemos los valores de partida y llave
			// programatica
			var currentEntryId = parseInt($(self.getId(grid, indice, "entryId")).val());
			var currentProgramaticKeyId = parseInt($(self.getId(grid, indice,"programaticKeyId")).val());
			var currentInitialMonthId = parseInt($(self.getId(grid, indice, "initialMonthId")).val());
			var currentFinalMonthId = parseInt($(self.getId(grid, indice, "finalMonthId")).val());
	
			if (currentEntryId < 0 || currentProgramaticKeyId < 0) {
				window.showNotification("error","Seleccione llave programatica y Partida antes de continuar");
				self.monthsController = false;
				return;
			}
	
			// obtenemos todos los registros activos en el grid
			// que no son yo, activos y con misma partida
			var brotherEntries = $(grid).find("tbody tr:not(#noMovs)").filter(
				function() {
	
					var flag = $(this).find("[data-name='removedElement']").val();
					var initialMonthId = $(this).find("[data-name='initialMonthId']").val();
					var finalMonthId = $(this).find("[data-name='finalMonthId']").val();
	
					var programaticKeyId = $(this).find("[data-name='programaticKey'] select").val();
					var entry = $(this).find("[data-name='entry'] select").val();
					var index = $(this).attr("data-rownumber");
	
					if (flag <= 0 && index != indice && programaticKeyId == currentProgramaticKeyId && entry == currentEntryId) {
						return true;
					} else {
						return false;
					}
				});
	
			// si hay al menos un registro hermano
			if (brotherEntries.length > 0) {
				// por cada hermano, se agregan los meses que ya
				// tiene marcados
				brotherEntries.each(function(idx, r) {
					var e = $(r);
					var brotherInitialMonthId = e.find("[data-name='initialMonthId']").val();
					var brotherFinalMonthId = e.find("[data-name='finalMonthId']").val();
					setAvailableMonths(brotherInitialMonthId,brotherFinalMonthId);
				});
			}
	
			for (var y = currentInitialMonthId; y <= currentFinalMonthId; y++) {
				var isSelected = availableMonths[y];
				if (isSelected) {
					window.showNotification("error","El mes de "+ months[y]+ " ya fue seleccionado en otro movimiento. Verifique.");
					$(self.getId(grid, indice, "monthAmount")).val("0");
					self.monthsController = false;
				}
			}
		});
	},
	startComponent : function() {
		this.linkComponent(this.upGrid);
		this.linkComponent(this.downGrid);

		// si no hay movementType Seleccionado, ocultamos los grids
		if (parseInt($("#movementTypeId").val()) < 0) {
			this.reset();
		}

		// si es movimiento compensado, hacer bloqueos correspondientes:
		//if (this.isCompensatedMovement()) {
		this.blockCompensatedData();
		//}
	},
	blockCompensatedData : function() {
		// downgrid, se bloquean los rows

		var self = this;
		var totalRows = $(this.downGrid).find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			return (parseInt(flag.val()) <= 0);
		}).length;

		for (var i = 0; i < totalRows; i++) {
			self.blockRow(self, self.downGrid, i, true, true);
		}

		// upGrid

		var upRows = $(this.upGrid).find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			return (parseInt(flag.val()) <= 0);
		}).length;

		for (var j = 0; j < upRows; j++) {
			self.blockRow(self, self.upGrid, j, true, true);
		}
	},
	getNextIndex : function(grid) {
		var rowNoExiste = grid.find("tbody #noMovs").length;

		var totalRows = grid.find("tbody tr:not(#noMovs)").filter(function() {
			var flag = $(this).find("[data-name='removedElement']");
			return (parseInt(flag.val()) <= 0);
		}).length;
		return totalRows;
	},
	activateTemplate : function(id) {
		var t = document.querySelector(id);
		return document.importNode(t.content, true);
	},
	apiCall : function(actionURL, callback) {
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
	validate : function() {
		var self = this;
		var validator = {
			// valida el tipo de movimiento
			movementType : function(movementType) {
				if (parseInt(movementType.val()) <= 0) {
					this.notif("error", "Seleccione un tipo de movimiento");
					movementType.closest('[data-name="movementTypeContainer"]').addClass("has-error");
					return false;
				}
				return true;
			},
			validateGrid : function(movementTypeId) {
				switch (movementTypeId) {
				case 1:
					var res = this.validateComponent(self.upGrid);
					return res;
				case 2:
					var res = this.validateComponent(self.downGrid);
					return res;
				case 3:
					var resUp = this.validateComponent(self.upGrid);
					var resDown = this.validateComponent(self.downGrid);
					if (resUp && resDown) {
						// validar que totales den cero
						self.updateTotal(self, self.upGrid);
						self.updateTotal(self, self.downGrid);

						var movAlza = parseFloat($(document).find("#upMovementsTotal").text());
						var movBaja = parseFloat($(document).find("#downMovementsTotal").text());

						if ((movAlza - movBaja) != 0) {
							this.notif("error","la suma de los movimientos de aumento y disminución deben resultar 0.");
							return false;
						}
					}
					return (resUp && resDown);
				}
			},
			validateComponent : function(gridId) {
				var grid = $(gridId);
				var totalRows = grid.find("tbody tr:not(#noMovs)").length;

				var filteredRows = grid.find("tbody tr:not(#noMovs)").filter(function() {
					var flag = $(this).find(
							"[data-name='removedElement']");
					return (parseInt(flag.val()) <= 0);
				});

				if (filteredRows.length <= 0) {
					this.notif("error", "debe capturar al menos un movimiento");
					return false;
				}
				// iteracion para procesar cada una de las filas

				var ok = true;
				// grid.find("tbody tr:not(#noMovs)").each(function(idx, e){
				filteredRows
						.each(function(idx, e) {
							var row = $(e);
							var programaticKey = row
									.find("[data-name='programaticKey'] select");
							var entry = row.find("[data-name='entry'] select");
							var amount = row
									.find("[data-name='monthAmount'] input");

							if (parseInt(programaticKey.val()) <= 0) {
								programaticKey.closest(
										"[data-name='programaticKey']")
										.addClass("has-error");
								ok = false;
							}
							if (parseInt(entry.val()) <= 0) {
								entry.closest("[data-name='entry']").addClass(
										"has-error");
								ok = false;
							}
							if (amount.val().length == 0
									|| parseInt(amount.val()) <= 0) {
								amount.closest("[data-name='monthAmount']")
										.addClass("has-error");
								ok = false;
							}
						});
				if (!ok) {
					this.notif("error", "Capture la información faltante");
				}
				return ok;
			},
			notif : function(type, msg) {
				window.showNotification(type, msg);
			}
		};

		var movementType = $("#movementTypeId");

		var result = validator.movementType(movementType);
		if (result) {
			result = validator.validateGrid(parseInt(movementType.val()));
			return result;
		}
		return result;
	}
};
