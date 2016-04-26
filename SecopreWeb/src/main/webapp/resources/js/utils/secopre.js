/**
 * Project: Secopre MVC Author: Jorge Cano (jorge.canoc@gmail.com) &
 * JoseHernandez ()
 * 
 * Contributors:
 * 
 * Dependencies: jQuery
 * 
 * Utilerias genericas para el proyecto Secopre MVC
 * 
 */

var $textElements;
var $passwordElements;
var $emailElements;
var $selectElements;
var $textAreaElements;
var $datePickerElements;

/**
 * Funcion que permite bloquear un pagina miestras es rendereado el contenido
 * por AJAX
 */
function blockPage() {
	$('body').append('<div id="loading"></div>');
	$('#loading').fadeIn();
}

/**
 * Funcion que permite desbloquear un pagina miestras es rendereado el contenido
 * por AJAX
 */
function unblockPage() {
	$('#loading').fadeOut();
}
/**
 * Funcion que permite enviar un formulario via AJAX y mediante POST
 * 
 * @param formId:
 *            el id del formulario a enviar
 * @param targetId:
 *            el id del div donde se inyectara el HTML producido por la peticion
 */
function submitAjaxJQ(formId, targetId, after, isDownload) {
	var isDownload
	var method = 'POST';
	var frm = $('#' + formId);
	var action = frm.attr('action');
	
	
	var x = (frm !== undefined && frm !== null) ? frm.serialize(true) : null;
		
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	var path = context + '/' + action;
		
	blockPage();
	$
			.ajax({
				type : method,
				url : context + '/' + action,
				data : (frm !== undefined && frm !== null) ? frm.serialize(true) : null,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					$("#" + targetId).html("");
					$('#' + targetId).html(data);
					// Mensaje Exito
					showNotification('success',
							'La operacion se realizo correctamente!!');
				},
				complete : function(jqXHR) {
					if (after !== null) {
						eval(after);
					}
					unblockPage();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					unblockPage();
					// Mensaje error
					showNotification('error',
							'Ocurrio un error al ejecutar su peticion:'
									+ thrownError);
				}
			});
}

function submitAjaxJQPopup(formId, targetId, after, isDownload) {
	var isDownload
	var method = 'POST';
	var frm = $('#' + formId);
	var action = frm.attr('action');
	
	
	var x = (frm !== undefined && frm !== null) ? frm.serialize(true) : null;
		
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	var basePath = "http://" + document.location.host + "/Secopre/";
	
	var path = context + '/' + action;
	
	var finalPath = basePath + path;
		
	blockPage();
	$
			.ajax({
				type : method,
				url : finalPath,
				data : (frm !== undefined && frm !== null) ? frm.serialize(true) : null,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					$("#" + targetId).html("");
					$('#' + targetId).html(data);
					// Mensaje Exito
					showNotification('success',
							'La operacion se realizo correctamente!!');
				},
				complete : function(jqXHR) {
					if (after !== null) {
						eval(after);
					}
					unblockPage();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					unblockPage();
					// Mensaje error
					showNotification('error',
							'Ocurrio un error al ejecutar su peticion:'
									+ thrownError);
				}
			});
}

function submitAjaxJQWithAction(formId, targetId, after, action) {
	var method = 'POST';
	var frm = $('#' + formId);
	var action = action;
	
	
	var x = (frm !== undefined && frm !== null) ? frm.serialize(true) : null;
		
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	var path = context + '/' + action;
		
	blockPage();
	$.ajax({
		type : method,
		url : context + '/' + action,
		data : (frm !== undefined && frm !== null) ? frm.serialize(true) : null,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			$("#" + targetId).html("");
			$('#' + targetId).html(data);
			// Mensaje Exito
			showNotification('success', 'La operacion se realizo correctamente!!');
		},
		complete : function(jqXHR) {
			if (after !== null) {
				eval(after);
			}
			unblockPage();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			unblockPage();
			// Mensaje error
			showNotification('error','Ocurrio un error al ejecutar su peticion:' + thrownError);
		}
	});
}

//using FormData() object
function submitFileAjaxJQTest(formId, targetId, after, addExtraData){
  var frm = $('#' + formId);
  var action = frm.attr('action');
  var oMyForm = new FormData();
  oMyForm.append("attachment", attachment.files[0]);

  
  if(addExtraData == true){
	  var requestId = $('#requestId').val();
	  var stageConfigId = $('#stageConfigId').val();
	  
	  //alert("requestId: " + requestId);
	  //alert("stageConfigId: " + stageConfigId);
	  
	  //se cargan las propiedades del request y el stageConfig actual
	    oMyForm.append("requestId", requestId);  
	    oMyForm.append("stageConfigId", stageConfigId);  	  
  }

	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	blockPage();
  $.ajax({
	url : context + '/' + action,
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    beforeSend : function(xhr) {
		xhr.setRequestHeader(header, token);
	},   
    type: 'POST',
    success: function(data){
    	$("#" + targetId).html("");
		$('#' + targetId).html(data);
		unblockPage();
		// Mensaje Exito
		showNotification('success', 'La operacion se realizo correctamente!!');

    },
	complete : function(jqXHR) {
		if (after !== null) {
			eval(after);
		}
		unblockPage();
	},
	error : function(xhr, ajaxOptions, thrownError) {
		unblockPage();
		// Mensaje error
		showNotification('error','Ocurrio un error al ejecutar su peticion:' + thrownError);
	}
  });
}
function submitFileAjaxJQ(formId, targetId, after) {
	var method = 'POST';
	//alert("enviando archivo");
	var frm = $('#' + formId);
	var action = frm.attr('action');
	
	
	var dataDeForma = (frm !== undefined && frm !== null) ? frm.serialize(true) : null;
	
	//console.log(dataDeForma);
	
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	var path = context + '/' + action;
		
	blockPage();
	$
			.ajax({
				type : method,
				url : context + '/' + action,
				data : dataDeForma,
			    contentType: false,
			    processData: false,
			    beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					$("#" + targetId).html("");
					$('#' + targetId).html(data);
					// Mensaje Exito
					showNotification('success',
							'La operacion se realizo correctamente!!');
				},
				complete : function(jqXHR) {
					if (after !== null) {
						eval(after);
					}
					unblockPage();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					unblockPage();
					// Mensaje error
					showNotification('error',
							'Ocurrio un error al ejecutar su peticion:'
									+ thrownError);
				}
			});
}

/**
 * Funcion que permite enviar un formulario via AJAX y mediante POST
 * 
 * @param actionURL:
 *            el Path a cual se enviara la peticion AJAX
 * @param targetId:
 *            el id del div donde se inyectara el HTML producido por la peticion
 * @param after:
 *            funcion JS que sera evaluada al finalizar la peticion
 */
function sendRequestJQ(actionURL, targetId, after, method) {
	method = method || "POST";
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	blockPage();
	$.ajax({
		url : context + '/' + actionURL,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			$("#" + targetId).html("");
			$('#' + targetId).html(data);

		},
		complete : function(jqXHR) {
			if (after !== null && after.length > 2) {
				eval(after);
			}
			unblockPage();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			unblockPage();
			// Mensaje error
			showNotification('error',
					'Ocurrio un error al ejecutar su peticion:'
							+ thrownError);
		},
		type : method
	});
}

function sendRequestNewWindowJQ(actionURL, targetId, after, method) {
	method = method || "POST";
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	blockPage();
	
	var theURL = context;
	$.ajax({
		url : context + '/' + actionURL,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			/*
			$("#" + targetId).html("");
			$('#' + targetId).html(data);
			
			
			var url = "http://"+ window.location.host
			
			var popupHead = $("head");
			var phx = popupHead;
			
			var popupBody = $("body");
			var phb = popupBody;
			
			phx.find("link").each(function(index){
				var link = $(this);
				var path = $(link).attr("href");
				if(path.startsWith("/")){
					$(link).attr("href", url+path);
				}
			});
			
			phb.find("script").each(function(x){
				var link = $(this);
				var path = $(link).attr("src");
				if(path != undefined && path.startsWith("/")){
					$(link).attr("src", url+path);
				}
			});
			
			phb.find("#loading").remove();
			
			var headContent = phx.html();
			var bodyContent = phb.html();
			*/		
			var win = window.open(context + "/" + actionURL, "Title", "scrollbars=yes, resizable=yes, width=780, height=200, top="+(screen.height-400)+", left="+(screen.width-840));
			//win.document.head.innerHTML = headContent;
			//win.document.body.innerHTML = bodyContent;
		},
		complete : function(jqXHR) {
			if (after !== null && after.length > 2) {
				eval(after);
			}
			unblockPage();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			unblockPage();
			// Mensaje error
			showNotification('error',
					'Ocurrio un error al ejecutar su peticion:'
							+ thrownError);
		},
		type : method
	});
}

function apiCall(actionURL, callback) {
	var method = method || "POST";
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
}

function getPromise(actionURL, callback){
	var method = method || "POST";
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	var promise = $.ajax({
		url : context + '/' + actionURL,
		beforeSend : function(xhr) { xhr.setRequestHeader(header, token);},
		success : function(data) {
			callback(data);
		}
	});
	return promise;
}

function openResource(actionURL, targetId, after, method) {
	method = method || "POST";
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	blockPage();
	$.ajax({
		url : context + '/' + actionURL,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success : function(data) {
			window.open(data.fileUrl);
		},
		complete : function(jqXHR) {
			if (after !== null) {
				eval(after);
			}
			unblockPage();
		},
		type : method
	});
}

function openResourceNative(actionURL) {
    var downloadUrl = actionURL;
    // (optionally) provide the user with a message that the download is starting
    window.location.href = downloadUrl;
}

function rollbackMovement(path){
	bootbox.dialog({
        message: "La reversi&oacute;n del gasto vuelve los montos del gasto operado a su estado original. &iquest;Desea continuar?",
        title: "Revertir Operaci&oacute;n de Gasto",
   	 	buttons: {
   		    success: {
   		      label: "Continuar",
   		      className: "btn-success",
   		      callback: function() {
   		    	apiCall(path, function(data){
   		    		alert("ROLLBACK");
   		    		sendRequestJQ('auth/tram/mylist','dashboard','initMyTramiteListPage()','GET');
   				  });
   		      }
   		 	},
   		    cancel: {
   		      label: "Cancelar",
   		      className: "btn-danger",
   		      callback: function() {
   		      }
   		    }
   		 }
   });
}

function openParamResourceNative(actionURL, formId) {
    var downloadUrl = actionURL;
    
    var frm = $('#' + formId);	
	var formQuery = (frm !== undefined && frm !== null) ? frm.serialize(true) : null;
	
    
    // (optionally) provide the user with a message that the download is starting
    window.location.href = downloadUrl+"?"+formQuery;
}

function showNotification(notifType, notifMsg) {

	var shortCutFunction = notifType;// success, info, warning, error
	var msg = notifMsg;
	var title = 'Notificaciones Secopre';

	toastr.options = {
		closeButton : 'true',
		positionClass : 'toast-top-right',
		onclick : null,
		showDuration : '1000',
		hideDuration : '1000',
		timeOut : '5000',
		extendedTimeOut : '1000',
		showEasing : 'swing',
		hideEasing : 'linear',
		showMethod : 'fadeIn',
		hideMethod : 'fadeOut'
	};

	if (!msg) {
		msg = getMessage();
	}
	var $toast = toastr[shortCutFunction](msg, title); // Wire up an event
}

function updateMenu(id){
	console.log("actualizando menu");
	$(document).find("#menuList").find("li").removeClass("start").removeClass("active");
	$(document).find("#menuList").find(id).addClass("start").addClass("active");
}
