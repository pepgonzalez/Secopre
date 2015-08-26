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
function submitAjaxJQ(formId, targetId,after) {
	var frm = $('#' + formId);
	var action = frm.attr('action')

	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");

	blockPage();
	$
			.ajax({
				type : "POST",
				url : context + '/' + action,
				data : (frm !== undefined && frm !== null) ? frm
						.serialize(true) : null,
				beforeSend : function(xhr) {
					xhr.setRequestHeader(header, token);
				},
				success : function(data) {
					$("#" + targetId).html("");
					$('#' + targetId).html(data);
				},
				complete : function(jqXHR) {
					if (after !== null) {
						eval(after);
					}					
					unblockPage();

				},
				error : function(xhr, ajaxOptions, thrownError) {
					unblockPage();
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
function sendRequestJQ(actionURL, targetId, after) {
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
			if (after !== null) {
				eval(after);
			}
			unblockPage();
		},
		type : 'POST'
	});
}
