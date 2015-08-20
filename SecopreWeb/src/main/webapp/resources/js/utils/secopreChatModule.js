/*utilidades*/
utils = new SecopreUtils();

/*modulo de chat*/
var SecopreChat = function(){
	this.userId = -1;
	
	/*
	 * funcion privada para realizar peticion ajax
	 */
	var _getAjaxRequest = function(urlRequest, callback ){
		$.ajax({
			url: urlRequest,
			dataType: 'json'
		}).done(function(r) {
			callback(r);
		});
	};
	
	/*
	 * funcion privada para activar una plantilla 
	 */
	var _activateTemplate = function(id) {
	    var t = document.querySelector(id);
	    return document.importNode(t.content, true);
	};
	
	/*
	 * funcion privada para procesar la informacion inicial de chat 
	 */
	var _processInitialData = function(r){
		//se oculta leyenda "no existen mensajes"
		if (r.length > 0){ $("#no__inbox__msgs").hide(); }
		
		var pendingMessages = 0;
		//iteracion sobre los resultados
		$.each(r, function(){
			var element = _activateTemplate(utils.Constants.Templates.INBOX_TEMPLATE);
			
			element.querySelector("[data-user]").innerHTML = this.userName;
			element.querySelector("[data-time]").innerHTML = utils.DateUtils.getUXDate(utils.DateUtils.getDateFromDBString(this.creationDate));		
			element.querySelector("[data-message]").innerHTML = ((this.msg.length > 25) ? this.msg.substring(0,23) + "..." : this.msg);

			var itemClass = "sp__message__readed";
			if(this.dir == 'IN' && parseInt(this.status) == 0){
				pendingMessages += 1;
				itemClass = "sp__message__pending";
			}
			var listItem = element.querySelector("[data-inbox-message]");
			listItem.className = itemClass;
			
			var $li = $(listItem);
			$li.attr('data-userid', this.userId);
			$li.attr('data-username', this.userName);
			$li.attr('data-cId', this.cId);
			
			element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
			
			$("#inbox__popup__msgs__list").append(element);
		});	
		
		document.querySelector("#sp_new_msgs_mumber").innerHTML = pendingMessages;
		$("#new_messages_badge").html(pendingMessages);
		if(pendingMessages == 0){
			$('#sp_new_messages_label').html("No hay msjs nuevos");
			$("#new_messages_badge").hide();
		}
	}
	
	/*
	 * funcion para actualizar el estatus del msg 
	 */
	var _updateSeen = function(cId, userId, userName, callback){
		$.ajax({
			url: "http://localhost:3000/v1/chat/updateSeen/" + cId,
			dataType: 'json'
		}).done(function(r) {
			callback(cId, userId, userName, r);
		});
	};
	
	/*
	 * funcion para procesar la conversacion 
	 */
	var _processConversation = function(cId, userId, userName, res){
		
		$("#chat_container").empty();
		
		$('.page-quick-sidebar-wrapper').find('.page-quick-sidebar-chat').addClass("page-quick-sidebar-content-item-shown");
        $('.sp__nav__basic').css("cssText", "display: none !important;");
        
        var complexNav = $('.sp__nav__comp');
        complexNav.css("cssText", "display: table-cell !important;");
        complexNav.find('a').html(userName);
        
        $('.post.in .message .name').empty().html(userName);
        
        //bloqueo de pantalla
        Metronic.blockUI({ target: '.page-container', textOnly: true,  message: '' });
        Metronic.blockUI({ target: '.page-header', textOnly: true, message: '' });
		
		//SE OBTIENEN LAS CONVERSACIONES
        var url =  "http://localhost:3000/v1/chat/getConversation/" + cId + "/" + userId;
		_getAjaxRequest(url, function(data){
			
			$.each(data, function(){
				var element = _activateTemplate("#chat_message");
				
				element.querySelector("[msg]").className = (this.direction == 'IN' ? 'post out' : 'post in');
				element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
				element.querySelector("[data-username]").innerHTML = this.userName;
				element.querySelector("[data-msg-body]").innerHTML = this.msg;
				element.querySelector("[data-time]").innerHTML= utils.DateUtils.getUXTimeFromDBDate(this.creationDate);
				
				$("#chat_container").append(element);
				
			});
		});	
	};
	
	/*
	 * funcion interna para procesar todas las conversaciones 
	 */
	var _processAllConversations = function(r){
		
		$('body').toggleClass('page-quick-sidebar-open'); 
	    //bloqueo de pantalla
	    Metronic.blockUI({ target: '.page-container', textOnly: true,  message: '' });
	    Metronic.blockUI({ target: '.page-header', textOnly: true, message: '' });
		
		//se oculta leyenda "no existen mensajes"
		if (r.length > 0){ $("#no_conversations_msg").hide(); }
		
		//iteracion sobre los resultados
		$.each(r, function(){
			var element = _activateTemplate(utils.Constants.Templates.CONVERSATION_TEMPLATE);
			
			element.querySelector("[data-user]").innerHTML = this.userName;
			element.querySelector("[data-time]").innerHTML = utils.DateUtils.getUXDate(utils.DateUtils.getDateFromDBString(this.creationDate));		
			element.querySelector("[data-msg]").innerHTML = ((this.msg.length > 25) ? this.msg.substring(0,23) + "..." : this.msg);

			var listItem = element.querySelector("[data-conversation]");
			
			var $li = $(listItem);
			$li.attr('data-userId', this.userId);
			$li.attr('data-userName', this.userName);
			$li.attr('data-cId', this.cId);
			
			element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
			
			var status = element.querySelector('[data-status]');
			
			var statusContent = "";
			if (this.dir == 'IN'){
				if (this.status == 0)
					statusContent = '<span class="badge badge-danger"> </span>';
			}else{
				statusContent = (this.status == 0 ? '<i class="fa fa-share"></i>' : '<i class="fa fa-check"></i>');
			}
			
			status.innerHTML = statusContent;
			
			$("#all_conversations_container").append(element);
		});	
	};
	
	/*funcion para cargar el popup*/
	this.loadInitialData = function(){
		_getAjaxRequest("http://localhost:3000/v1/chat/getConversations/" + this.userId + "/0/6", _processInitialData);
	};
	
	/*funcion para mostrar conversacion*/
	this.loadConversation = function(c){
		
		$('body').toggleClass('page-quick-sidebar-open');
		
		var userId = $(c).attr("data-userId");
	    var userName = $(c).attr("data-userName");
	    var conversationId = $(c).attr("data-cId");
	    var clases = $(c).attr("class");
	    
	    /*si es un msj no leido se actualiza el status*/
	    if (clases == "sp__message__pending"){
	    	_updateSeen(conversationId,userId, userName, _processConversation);
	    }else{
	    	_processConversation(conversationId, userId, userName, {});
	    }
	};
	
	/*funcion para mostrar todas las conversaciones*/
	this.loadAllConversations = function (){
		var userId = parseInt( $("#loggedUserId").val());
		_getAjaxRequest("http://localhost:3000/v1/chat/getConversations/" + userId + "/0/11", _processAllConversations);
	};
}

/*inicalizacion de objeto base*/
var Chat = new SecopreChat();

/*carga de datos al iniciar el documento*/
$(document).ready(function(){

	var userId = parseInt( $("#loggedUserId").val());
	var userHasChatModule = Boolean ($("#chatModuleActive").val());
	
	if (userHasChatModule){
		Chat.userId = userId;
		Chat.loadInitialData();
	}else{
		$("#header_inbox_bar").hide();
	}
});

/*evento al hacer click en los elementos del menu popup*/
$(document).on('click','.dropdown-menu-list.scroller li', function (e){
	Chat.loadConversation(this);
});

/*evento para mostrar todas las conversaciones*/
$(document).on('click', '.sp__aux__view__all', function (e) {
    Chat.loadAllConversations();
});

/*evento para cerrar chat y cerrar pantalla*/
$(document).on('click','.page-quick-sidebar-toggler',function (e) {
    $('body').removeClass('page-quick-sidebar-open'); 
    //bloqueo de pantalla
    Metronic.unblockUI('.page-container');
    Metronic.unblockUI('.page-header');
});