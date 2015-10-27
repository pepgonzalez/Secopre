
var movementController = {
	upGrid : "#addComponent",
	downGrid : "#substractComponent",
	slider : "SliderControl",
	
	/* funcion que oculta los grids */
	reset : function(){
		$(this.upGrid).hide();
		$(this.downGrid).hide();
	},
	
	/* funcion que elimina todos los registros del grid */
	clean : function(grid){
		$(grid).find("tbody tr").remove();
		$(grid).find("tbody").html('<tr id="noMovs"><td colspan="6">No hay Movimientos Capturados</td><tr>');
		this.updateTotal(this, grid, true);
	},
	
	/* titulos en función del tipo de movimiento seleccionado */
	titles : { al:'<i class="fa fa-cogs"></i>Ampliaci&oacute;n L&iacute;quida',rl:'<i class="fa fa-cogs"></i>Reducci&oacute;n L&iacute;quida', 
		       ac:'<i class="fa fa-cogs"></i>Ampliaci&oacute;n Compensada', rc:'<i class="fa fa-cogs"></i>Reducci&oacute;n Compensada'},
	
	/* funcion que muestra y actualiza el titulo del grid */
	showGrid : function(grid, name){
		var grd = $(grid);
		grd.find(".caption").html(name);
		grd.show();
	},
	
	/* funcion para mostrar el grid con el titulo correspondiente en funcion del tipo de movimiento */
	update : function(value){
		this.reset();
		
		if(value > 0){
			$(document).find("#movementTypeId").closest('[data-name="movementTypeContainer"]').removeClass("has-error");
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
					break;
			}
		}
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
	updateTotal : function(self, grid, turnDownIteration){
		turnDownIteration = turnDownIteration || false;
		var grd = $(grid);
		var totalId = (grid === self.upGrid ? "#upMovementsTotal" : "#downMovementsTotal");
		
		var gridTotal = 0;
		
		if(!turnDownIteration){
			//iteracion sobre las filas
			grd.find("tbody tr:not(#noMovs)").each(function(idx, e){
				var row = $(e);
				var isRemovedRow = row.find("[data-name='removedElement']").val();
				
				//solo considera las filas no eliminadas
				if(parseInt(isRemovedRow) == 0){
					var totalAmount = row.find("[data-name='totalAmount']");
					if (totalAmount.val().length > 0){
						gridTotal += parseFloat(totalAmount.val());
					}
				}
			});
		}
		grd.find(totalId).html((gridTotal));	
	},
	getSliderId : function(grid){
		var direction = (grid == this.upGrid ? "up" : "down");
		return "#" + direction + this.slider;
	},
	linkComponent : function(grid){
		var grd = $(grid);
		var self = this;
		
		//si no existe el row de "sin elementos, se iteran los objetos"
		if(grd.find("tbody #noMovs").length == 0){
			
			grd.find("tbody tr").each(function(idx, e){
				var element = $(e);
				var rowId = element.attr("id");	

				self.startSlider(self, idx, parseInt(new Date().getMonth()), grid);		
				self.addRemoveEvent(self, grid, idx);
				self.addInfoEvent(self, grid, idx);
				
				//asignar eventos de cambio
				self.addOnChangeEvent(self, grid, idx, "programaticKeyId",true);
				self.addOnChangeEvent(self, grid, idx, "entryId",false);	
				self.updateAmounts(self, grid, idx, "monthAmount");	
			});
			
			self.updateTotal(self, grid);
			
		}else{
			grd.find("tbody tr:not(#noMovs)").remove();
		}
		
		//evento para agregar movimientos
		var addBtn = grd.find(".actions #addMov").on("click", function(){
			self.addMovementRow(self, grid, false);
		});
	},
	addMovementRow : function(self, grid, isComplementary){	
		console.log("agregando movimiento");
		var grd = $(grid);
		var nextIndex = self.getNextIndex(grd);				
		
		if(nextIndex == 0){
			grd.find("tbody #noMovs").remove();
		}
		
		var e = $(self.activateTemplate("#movementRowTemplate"));
		
		//accion
		e.find("tr").attr("id","row"+nextIndex).attr("data-rowNumber", nextIndex);
		e.find("[data-name='deleteAction'] #rowDeleteButton").attr("id", "rmvIdx" + nextIndex);
		e.find("[data-name='deleteAction'] #rowInfoButton").attr("id", "infoIdx" + nextIndex);
		
		//llave programatica
		e.find("[data-name='programaticKey'] select").attr("name", self.getPath(grid, nextIndex, "programaticKeyId"))
			.attr("id", self.getId(grid, nextIndex, "programaticKeyId", 2)).removeAttr("multiple");
		e.find("[data-name='programaticKey']").find("input[type='hidden']").remove();
	
		//entry
		e.find("[data-name='entry'] select").attr("name", self.getPath(grid, nextIndex, "entryId")).attr("id", self.getId(grid, nextIndex, "entryId", 2))
		.removeAttr("multiple");
		e.find("[data-name='entry']").find("input[type='hidden']").remove();

		//sliderControl
		e.find("[data-name='sliderControl'] #sliderControl").attr("id", self.getSliderId(grid).substring(1) + nextIndex);
		
		//lowerOffset
		e.find("[data-name='lower-offset'] ").attr("id", self.getId(grid, nextIndex, "lower-offset", 2));
		e.find("[data-name='upper-offset'] ").attr("id", self.getId(grid, nextIndex, "upper-offset", 2));
		
		//monthAmount
		e.find("[data-name='monthAmount'] input").attr("name", self.getPath(grid, nextIndex, "monthAmount"))
		.attr("id", self.getId(grid, nextIndex, "monthAmount", 2)).attr("value", "0");
		
		//initialMonthId
		e.find("[data-name='initialMonthId']").attr("name", self.getPath(grid, nextIndex, "initialMonthId"))
		.attr("id", self.getId(grid, nextIndex, "initialMonthId", 2)).attr("value","");
		
		//finalMonthId
		e.find("[data-name='finalMonthId']").attr("name", self.getPath(grid, nextIndex, "finalMonthId"))
		.attr("id", self.getId(grid, nextIndex, "finalMonthId", 2)).attr("value","");
		
		//removedElement
		e.find("[data-name='removedElement']").attr("name", self.getPath(grid, nextIndex, "removedElement"))
		.attr("id", self.getId(grid, nextIndex, "removedElement", 2)).attr("value","0");
		
		e.find("[data-name='movementTypeId']").attr("name", self.getPath(grid, nextIndex, "movementTypeId"))
		.attr("id", self.getId(grid, nextIndex, "movementTypeId", 2)).attr("value",(grid == self.upGrid ? "1" : "-1"));
		
		e.find("[data-name='requestDetailId']").attr("name", self.getPath(grid, nextIndex, "requestDetailId"))
		.attr("id", self.getId(grid, nextIndex, "requestDetailId", 2)).attr("value","-1");
		
		e.find("[data-name='totalAmount']").attr("name", self.getPath(grid, nextIndex, "totalAmount"))
		.attr("id", self.getId(grid, nextIndex, "totalAmount", 2)).attr("value","0");
		
		if(isComplementary){
			//se oculta lo que no debe ir, o se desabilita
			e.find("[data-name='deleteAction'] a").hide();
			e.find("[data-name='programaticKey'] select").val("1").attr("disabled", "true");
			e.find("[data-name='sliderControl']").hide();
			e.find("[data-name='monthLabels']").attr("colspan","2");
			e.find("[data-name='monthAmount'] input").val("1").attr("disabled", "true");
		}
		
		grd.find("tbody").append(e);
		
		self.startSlider(self, nextIndex, parseInt(new Date().getMonth()), grid);
		self.addOnChangeEvent(self, grid, nextIndex,"programaticKeyId",true);
		self.addOnChangeEvent(self, grid, nextIndex,"entryId",false);
		self.addRemoveEvent(self, grid, nextIndex);
		self.addInfoEvent(self, grid, nextIndex);
		self.updateAmounts(self, grid, nextIndex, "monthAmount");
		
		grd.find("tbody #noMovs").remove();
	},
	updateAmounts : function(self, grid, nextIndex, element){
		var ma = $(document).find(self.getId(grid, nextIndex, element));
		
		// funcion para cambiar siempre a numericos
		ma.keyup(function(){this.value = this.value.replace(/[^0-9\.]/g,'');});
		
		// validacion de montos al perder el foco
		ma.blur(function(){
			
			var finalMonth = parseInt($(self.getId(grid, nextIndex, "finalMonthId")).val());
			var initialMonth = parseInt($(self.getId(grid, nextIndex, "initialMonthId")).val());
			var entryId = parseInt($(self.getId(grid, nextIndex, "entryId")).val());
			var programaticKeyId = parseInt($(self.getId(grid, nextIndex, "programaticKeyId")).val());
			
			var districtId = parseInt($("#districtId").val());
			var entryId = parseInt($(self.getId(grid, nextIndex, "entryId")).val());
			
			var total = 0;
			var that = this;
			
			function updateTotalAmounts(){
				//alert("actualizando montos totales");
				//se calcula el monto total del movimiento
				total = ((finalMonth - initialMonth) + 1) * that.value;					
				
				//si el monto es mayor a cero, se elimina el error
				if (parseInt(that.value) > 0){
					self.removeClassError(self.getId(grid, nextIndex, "monthAmount"));
				}
				
				//guardamos el monto total en total amount	
				$(self.getId(grid, nextIndex, "totalAmount")).val(total);
				
				//se invoca update para actualizar los totales del grid
				self.updateTotal(self, grid);
			}
			
			function addCompensatedMovement(){
				if(parseInt($("#movementTypeId").val()) == 3){
					alert("movimiento compensado, agregando amplicacion compensada");
					self.addMovementRow(self, self.upGrid, true);
				}else{
					alert("No es un movimiento compensado");
				}
			}
			
			if(entryId < 0 || programaticKeyId < 0){
				window.showNotification("error", "Seleccione llave programatica y Partida antes de continuar");
				ma.closest("[data-name='monthAmount']").addClass("has-error");
				updateTotalAmounts();
				return;
			}
			
			//si se capturó algo
			if ( this.value.length > 0){
				
				var movementType = parseInt($(self.getId(grid, nextIndex, "movementTypeId")).val());
				var calls = [];
				
				//si es un movimiento de reduccion
				if(movementType < 0){
					
					//por cada mes, arma la llamada de validacion de montos
					var isValidMovement = true;
					for(var i = initialMonth; i <= finalMonth; i++){
						
						
						var rowNumber = ma.closest("tr").attr("data-rowNumber");
						//se buscan en el grid otras partidas que afecten al mismo mes
						var totalGrid = self.obtenerTotalDePartidaPorMesEnGrid(self, grid, entryId, i, rowNumber);
						var totalActual = parseFloat(this.value);
						
						//alert("total por grid: " + totalGrid);
						//alert("total actual " + totalActual);
						var montoTotal = totalGrid + totalActual;
						var call = window.getPromise("auth/API/get/movOk/" + districtId + "/" + entryId + "/" + i + "/" + montoTotal, 
								function(data){
									if (data.result <= 0){
										isValidMovement = false;
										window.showNotification("error", data.msg);
									} 
								});
						calls.push(call);
					}
					
					//se bloquea la pantalla y se ejecutan las promesas
					window.blockPage();
					jQuery.when.apply(null, calls).done(function(){
				       if(!isValidMovement){
				    	   ma.closest("[data-name='monthAmount']").addClass("has-error");
				    	   ma.val("0");
				    	   updateTotalAmounts();
				    	   window.unblockPage();
				    	   return;
				       }
				       unblockPage();
				       updateTotalAmounts();
				       addCompensatedMovement();
					});
					
				}else{
					//si es un movimiento a la alza no valido montos solo actualizo
					updateTotalAmounts();
				}
			}else{
				window.showNotification("error", "Capture un monto para continuar");
				ma.closest("[data-name='monthAmount']").addClass("has-error");
				updateTotalAmounts();
			}
			
		});
	},
	obtenerTotalDePartidaPorMesEnGrid: function(self, grid, entryId, monthId, rowNumber ){
		
		//alert("grid: " + grid + ", partida: " + entryId + ", mes: " + monthId + "rowNumber: " + rowNumber);
		
		//todas las partidas que no soy yo, y que son de la misma partida
		var filteredRows = $(grid).find("tbody tr:not(#noMovs)").filter(function(){
			var row = $(this);
			var esRegistroEliminado = parseInt(row.find("[data-name='removedElement']").val());
			var indice = parseInt(row.attr("data-rowNumber"));
			var mesInicial = parseInt(row.find("[data-name='initialMonthId']").val());
			var mesFinal  = parseInt(row.find("[data-name='finalMonthId']").val());
			var monthEntry = parseInt(row.find("[data-name='entry'] select").val());
			
			//alert("Datos de fila: esRegistroEliminado:" + esRegistroEliminado + ", indice:" + indice + ", rowNumber: "+ indice +", mesInicial: " + mesInicial + ", mes final: " + mesFinal + ", entryId:" + monthEntry);
			
			if (esRegistroEliminado <= 0 && (mesInicial <= monthId) && (mesFinal >= monthId ) && (rowNumber != indice) && (entryId == monthEntry)){
				return true;
			}else{
				return false;
			}
		});
		
		var total = 0;
		filteredRows.each(function(){
			total += parseFloat($(this).find("[data-name='monthAmount'] input").val());
		});
		
		//alert("total de registros asociados a la partida ya existentes: " + filteredRows.length);
		return total;
	},
	addOnChangeEvent:function(self, grid, indice, element, ajaxCall){
		var id = self.getId(grid, indice, element);
		$(document).find(id).on("change", function (e) {
		    
			if(ajaxCall){
				
				//preguntamos el id del distrito
				var districtId = $("#districtId").val();
				
				self.apiCall('auth/API/get/entry/' + this.value + "/" + districtId , function(data){
					var entrySelect = $(document).find(self.getId(grid, indice, "entryId"));
			    	entrySelect.empty();
			    	entrySelect.append('<option value="-1">Seleccione..</option>');
			    	$.each(data, function(index, item){
			    		entrySelect.append('<option value="' + item.id +'">' + item.name + '</option>');
			    	});
			    });
			}
		    
			if(parseInt(this.value) > 0){
		    	self.removeClassError(id);
		    }
		});
	},
	removeClassError:function(id){
		//alert("eliminando clase error de id: " + id);
		$(id).closest(".has-error").removeClass("has-error");
	},
	addRemoveEvent : function(self, grid, indice){
		var a = $(grid).find("[data-name='deleteAction']").find("#rmvIdx"+indice);
		
		a.on("click", function(){
			var row = $(this).parent().parent();
			row.find(self.getId(grid, indice, "removedElement")).val("1");
	
			row.hide();
			self.updateTotal(self, grid);
			
			var filteredRows = $(grid).find("tbody tr:not(#noMovs)").filter(function(){
				var flag = $(this).find("[data-name='removedElement']");
				return (parseInt(flag.val()) <= 0);
			});
			
			if (filteredRows.length == 0){
				$(grid).find("tbody").html('<tr id="noMovs"><td colspan="6">No hay Movimientos Capturados</td><tr>');
			}
			
		});
	},
	addInfoEvent : function(self, grid, indice){
		var a = $(grid).find("[data-name='deleteAction']").find("#infoIdx"+indice);
		
		a.on("click", function(){
			var row = $(this).parent().parent();
			
			var programaticKey = row.find(self.getId(grid, indice, "programaticKeyId")).val();
			var entry = row.find(self.getId(grid, indice, "entryId")).val();
			var district = $("#districtId").val();
			
			//alert("valores: " + programaticKey+ ", partida: " + entry + ", distrito: " + district);
			
			if(parseInt(entry) <= 0){
				window.showNotification("error", "Debe Seleccionar una partida para ver el detalle");
			}else{
				window.showEntryAmount(district, programaticKey, entry);
			}
			
		});
	},
	startSlider : function(self, indice, initialMonth, grid){
					
		var id = self.getSliderId(grid) + indice;			
		
		var rowSlider = document.getElementById(id);
		
		
		$(document).find(id).noUiSlider({
	        connect: true, behaviour: 'tap', step:1, start: [initialMonth, 11],
	        range: {
	            'min': [initialMonth],
	            'max': [11]
	        }	
	    });
		
		//evento
		$(document).find(id).on('change', function(){
			$(self.getId(grid, indice, "monthAmount")).blur();
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
					
		$(document).find(id).Link('lower').to($(document).find(initialMonthId), intValue);
		$(document).find(id).Link('upper').to($(document).find(finalMonthId), intValue);

		$(document).find(id).Link('lower').to($(document).find(self.getId(grid, indice, "lower-offset")), myValue);
		$(document).find(id).Link('upper').to($(document).find(self.getId(grid, indice, "upper-offset")), myValue);
	},
	startComponent : function(){			
		this.linkComponent(this.upGrid);
		this.linkComponent(this.downGrid);
		
		//si no hay movementType Seleccionado, ocultamos los grids
		if(parseInt($("#movementTypeId").val()) < 0){
			this.reset();
		}
	},
	getNextIndex: function(grid){
		var rowNoExiste = grid.find("tbody #noMovs").length;
		
		var totalRows = grid.find("tbody tr:not(#noMovs)").filter(function(){
			var flag = $(this).find("[data-name='removedElement']");
			return (parseInt(flag.val()) <= 0);
		}).length;		
		return totalRows;
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
		var self = this;
		var validator = {
			//valida el tipo de movimiento
			movementType : function(movementType){
				if (parseInt(movementType.val()) <= 0){
					this.notif("error", "Seleccione un tipo de movimiento");
					movementType.closest('[data-name="movementTypeContainer"]').addClass("has-error");
					return false;
				}
				return true;
			},
			validateGrid : function(movementTypeId){
				switch(movementTypeId){
				case 1:
					var res = this.validateComponent(self.upGrid);
					if(res){	
						this.notif("success","Validación completa");
					}
					return res;
				case 2:
					//alert("validando 2 " +  self.downGrid);
					var res = this.validateComponent(self.downGrid);
					if(res){	
						this.notif("success","Validación completa");
					}
					return res;
				case 3:
					var resUp = this.validateComponent(self.upGrid);
					var resDown = this.validateComponent(self.downGrid);
					if (resUp && resDown){
						//validar que totales den cero
						self.updateTotal(self, self.upGrid);
						self.updateTotal(self, self.downGrid);
						
						var movAlza = parseFloat($(document).find("#upMovementsTotal").text());
						var movBaja = parseFloat($(document).find("#downMovementsTotal").text());
						
						if ((movAlza - movBaja) != 0){
							this.notif("error","la suma de los movimientos de aumento y disminución deben resultar 0.");
							return false;
						}else{
							this.notif("success","Validación completa en ambos grids");
						}	
					}
					return (resUp && resDown);
				}
			},
			validateComponent: function(gridId){
				var grid = $(gridId);
				var totalRows = grid.find("tbody tr:not(#noMovs)").length;
				
				var filteredRows = grid.find("tbody tr:not(#noMovs)").filter(function(){
					var flag = $(this).find("[data-name='removedElement']");
					return (parseInt(flag.val()) <= 0);
				});
				
				if (filteredRows.length <= 0){
					this.notif("error","debe capturar al menos un movimiento");
					return false;
				}
				//iteracion para procesar cada una de las filas
				
				var ok = true;
				//grid.find("tbody tr:not(#noMovs)").each(function(idx, e){
				filteredRows.each(function(idx, e){
					var row = $(e);
					var programaticKey = row.find("[data-name='programaticKey'] select");
					var entry = row.find("[data-name='entry'] select");
					var amount = row.find("[data-name='monthAmount'] input");
					
					if (parseInt(programaticKey.val()) <= 0){
						programaticKey.closest("[data-name='programaticKey']").addClass("has-error");
						ok = false;
					}
					if (parseInt(entry.val()) <= 0){
						entry.closest("[data-name='entry']").addClass("has-error");
						ok = false;
					}
					if (amount.val().length == 0 || parseInt(amount.val()) <= 0){
						amount.closest("[data-name='monthAmount']").addClass("has-error");
						ok = false;
					}
				});
				if(!ok){
					this.notif("error","Capture la información faltante");
				}
				return ok;
			},
			notif : function(type, msg){
				window.showNotification(type, msg);
			}
		};
					
		var movementType = $("#movementTypeId");
		
		var result = validator.movementType(movementType);
		if(result){
			result = validator.validateGrid(parseInt(movementType.val()));
			return result;
		}
		return result;
	}
};

