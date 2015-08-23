/*utilidades*/
utils = new SecopreUtils();

/*modulo de chat*/
var SecopreChat = function(){
	
	/* propiedades internas del modulo */
	this.userId = -1;
	this.socket = {};
	
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
			$li.attr('data-dir', this.dir);
			$li.attr('data-status', this.status);
			
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
	 * funcion privada para actualizar el estatus del msg 
	 */
	var _updateSeen = function(c, callback){
		
		/*actualiza el estatus si es necesario*/
		if (c.dir == 'IN' && c.status == 0){
			$.ajax({
				url: "http://localhost:3000/v1/chat/updateSeen/" + c.id,
				dataType: 'json'
			}).done(function(r) {
				if (parseInt(r.affectedRows) > 0){
					callback(c, r);
				}else{
					alert("Error de conexion con API de mensajeria");
				}
			});
		}else{
			callback(c,{});
		}
	};
	
	/*
	 * funcion privada para actualizar el estatus del encabezado del sidebar 
	 */
	var _updateSideBarHeader = function(type, name){
		if (type = 'chat'){
			$('.page-quick-sidebar-wrapper').find('.page-quick-sidebar-chat').addClass("page-quick-sidebar-content-item-shown");
	        $('.sp__nav__basic').css("cssText", "display: none !important;");
	        var complexNav = $('.sp__nav__comp');
	        complexNav.css("cssText", "display: table-cell !important;");
	        complexNav.find('a').html(name);
		}else{
			console.log("reversa");
		}
	}
	
	/*
	 * funcion para procesar la conversacion 
	 */
	var _processConversation = function(c, res){
		
		/*funcion interna para actualizar el estatus del popup*/
		var _updatePopupState = function (){
			/*se actualiza el estatus de la conversacion*/
			var conversation = $('[data-inbox-message][data-cId="'+ c.id +'"]');
			conversation.removeClass();
			conversation.attr('class', 'sp__message__readed');
			
			var currentPending = parseInt($("#new_messages_badge").text());
			if (currentPending > 0){
				if ((currentPending - 1 ) > 0){
					$('#sp_new_msgs_mumber').html(currentPending - 1);
				}else{
					$('#sp_new_messages_label').html("No hay mensajes nuevos");
				}
				_updateConvCounter("#new_messages_badge", currentPending - 1);
			}
		};
		
		/*funcion interna para actualizar el estado de la barra lateral*/
		var _updateSideBarState = function(){
			var conversation = $('[data-conversation][data-cId="'+ c.id +'"]');
			conversation.find('[data-cStatus]').html('');
			var currentPending = parseInt($('#pndConvCounter').text());
			if (currentPending > 0){
				_updateConvCounter('#pndConvCounter', currentPending - 1);
			}
		};
		
		if (c.dir == 'IN' && c.status == 0){
			_updatePopupState();
			_updateSideBarState();
		}
		
		$("#chat_container").empty();
		
		_updateSideBarHeader('chat', c.userName);
        
        $('.post.in .message .name').empty().html(c.userName);
        
        //bloqueo de pantalla
        Metronic.blockUI({ target: '.page-container', textOnly: true,  message: '' });
        Metronic.blockUI({ target: '.page-header', textOnly: true, message: '' });
		
		//SE OBTIENEN LAS CONVERSACIONES
        var url =  "http://localhost:3000/v1/chat/getConversation/" + c.id + "/" + c.userId;
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
		
		/*se agrega la fuente de invocacion al chat*/
		_addChatSource(c.source);
	};
	
	
	/*
	 * funcion interna para agregar el attributo de fuente al boton regresar del chat
	 */
	var _addChatSource = function(source){
		$('#chatReturnButton').attr('data-source', source);
	}
	
	/*
	 * funcion interna para procesar todas las conversaciones 
	 */
	var _processAllConversations = function(r){
		
		if (!($('body').hasClass('page-quick-sidebar-open'))){
			
			$('body').addClass('page-quick-sidebar-open'); 
			//bloqueo de pantalla
		    Metronic.blockUI({ target: '.page-container', textOnly: true,  message: '' });
		    Metronic.blockUI({ target: '.page-header', textOnly: true, message: '' });
		};
		
		//se oculta leyenda "no existen mensajes"
		if (r.length > 0){ 
			$("#all_conversations_container").empty();
		}
		
		var pendingMessages = 0;
		//iteracion sobre los resultados
		$.each(r, function(){
			var element = _activateTemplate(utils.Constants.Templates.CONVERSATION_TEMPLATE);
			
			console.log(element);
			console.log(this);
			
			element.querySelector("[data-user]").innerHTML = this.userName;
			element.querySelector("[data-time]").innerHTML = utils.DateUtils.getUXDate(utils.DateUtils.getDateFromDBString(this.creationDate));		
			element.querySelector("[data-msg]").innerHTML = ((this.msg.length > 25) ? this.msg.substring(0,23) + "..." : this.msg);

			var listItem = element.querySelector("[data-conversation]");
			
			var $li = $(listItem);
			$li.attr('data-userId', this.userId);
			$li.attr('data-userName', this.userName);
			$li.attr('data-cId', this.cId);
			$li.attr('data-dir', this.dir);
			$li.attr('data-status', this.status);
			
			element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
			
			var status = element.querySelector('[data-cStatus]');
			
			var statusContent = "";
			if (this.dir == 'IN'){
				if (this.status == 0){
					statusContent = '<span class="badge badge-danger"> </span>';
					pendingMessages += 1;
				}
			}else{
				statusContent = (this.status == 0 ? '<i class="fa fa-share"></i>' : '<i class="fa fa-check"></i>');
			}
			
			status.innerHTML = statusContent;
			
			$("#all_conversations_container").append(element);
		});	
		
		_updateConvCounter("#pndConvCounter", pendingMessages);
	};
	
	/*
	 * funcion privada para actualizar el contador de conversaciones pendientes 
	 */
	var _updateConvCounter = function(id, value){
		if (value == 0 ){
			$(id).hide();
		}else{
			document.querySelector(id).innerHTML = value;
		}
	};
	
	/*
	 * funcion privada para generar instancia de conversacion 
	 */
	var _getConversationObject = function(c){
		var conversation = {};
	    conversation.userId = $(c).attr("data-userId");
	    conversation.userName = $(c).attr("data-userName");
	    conversation.id = $(c).attr("data-cId");
	    conversation.dir = $(c).attr('data-dir');
	    conversation.status = parseInt($(c).attr('data-status'));
	    return conversation;
	}
	
	var _processFrecuentUsers = function(r){

		if(r.length > 0){
			$('#NoFrecuentUsrsMsgs').hide();
			$('#frecuent__users__list').empty();
		}
		
		$.each(r, function(){
			var element = _activateTemplate(utils.Constants.Templates.FRECUENT_USERS_TEMPLATE);
			
			var item = element.querySelector("[data-frecuentUser]");
			
			var $item = $(item);
			$item.attr('data-userId', this.userId);
			$item.attr('data-userName', this.userName);
			$item.attr('data-cId', this.cId);
			
			element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
			element.querySelector("[data-name]").innerHTML = this.userName;
			element.querySelector("[data-employment]").innerHTML = this.employment;
			
			if(parseInt(this.online) == 0){
				element.querySelector("[data-lastConnection]").innerHTML = utils.DateUtils.getUXDate(utils.DateUtils.getDateFromDBString(this.lastConnection));
				$item.find('[data-online]').hide();
			}
			
			$("#frecuent__users__list").append(element);
		});	
	};
	
	/************************************************************************************************************************
	 	FUNCIONES PUBLICAS DEL SERVICIO CHAT
	 ************************************************************************************************************************/
	
	/*funcion para cargar el popup*/
	this.loadInitialData = function(){
		/*carga informacion de popup*/
		_getAjaxRequest("http://localhost:3000/v1/chat/getConversations/" + this.userId + "/0/6", _processInitialData);
		/*carga informacion de usuarios frecuentes*/
		this.loadFrecuentUsers();
	};
	
	/*funcion para actualizar el estado de los usuarios frecuentes*/
	this.loadFrecuentUsers = function(){
		_getAjaxRequest("http://localhost:3000/v1/chat/getFrecuentUsers/" + this.userId , _processFrecuentUsers);
	};
	
	
	/*funcion para mostrar conversacion*/
	this.loadConversation = function(c, source){
		
		if (!($('body').hasClass('page-quick-sidebar-open'))){
			$('body').toggleClass('page-quick-sidebar-open');
		}
	
		var conv = _getConversationObject(c);
		conv.source = source;
	    _updateSeen(conv, _processConversation);	    
	};
	
	/*funcion para mostrar todas las conversaciones*/
	this.loadAllConversations = function (){
		var userId = parseInt( $("#loggedUserId").val());
		_getAjaxRequest("http://localhost:3000/v1/chat/getConversations/" + userId + "/0/11", _processAllConversations);
	};
	
	/*funcion para cerrar conversacion*/
	this.closeConversation = function(){
		$('.page-quick-sidebar-wrapper .page-quick-sidebar-chat').removeClass("page-quick-sidebar-content-item-shown");
	    $('.sp__nav__basic').css("cssText", "display: table-cell !important;");
	    $('.sp__nav__comp').css("cssText", "display: none !important;");
	};
	
	this.closeLateralPanel = function(){
		$('body').removeClass('page-quick-sidebar-open'); 
	    //desbloqueo de pantalla
	    Metronic.unblockUI('.page-container');
	    Metronic.unblockUI('.page-header');
	};
	
	this.addChatSource = function(source){
		_addChatSource(source);
	};
	
	this.loadActiveUsers = function(r){
	};
	
	/************************************************************************************************************************
 		FUNCIONES DE SOCKET
	 ************************************************************************************************************************/
	
	this.initSocket = function(){
		var data = {"userId" : this.userId};
		this.socket = io.connect(utils.Constants.SOCKET_URL, {query :'data=' + JSON.stringify(data) });
		//_loadSocketEvents(this.socket);
		
		this.socket.on('load_active_users', function(r){
			if(r.length > 0){
				$('#not__online__users__msg').hide();
				$('#online__users__list').empty();
			}
			
			$.each(r, function(){
				var element = _activateTemplate(utils.Constants.Templates.ONLINE_USER_TEMPLATE);
				
				var item = element.querySelector("[data-onlineUser]");
				
				var $item = $(item);
				$item.attr('data-userId', this.userId);
				$item.attr('data-userName', this.userName);
				$item.attr('data-cId', this.cId);
				
				element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
				element.querySelector("[data-name]").innerHTML = this.userName;
				element.querySelector("[data-employment]").innerHTML = this.employment;
				
				$("#online__users__list").append(element);
			});	
		});
		
		this.socket.on('chat_new_user', function(data){
			console.log(data);
		});
		
		this.socket.on('chat_user_leave', function(data){
			console.log(data);
		});
	};

	/*
	var _loadSocketEvents = function(socket){
		
		socket.on('load_active_users', function(data){
			alert("usuarios en linea obtenidos");
			console.log(data);
		});
		
		socket.on('chat_new_user', function(data){
			alert("evento: chat_new_user");
			console.log(data);
		});
		socket.on('chat_user_leave', function(data){
			alert("evento: chat_user_leave");
			console.log(data);
		});
	};
	*/
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
		Chat.initSocket();
	}else{
		$("#header_inbox_bar").hide();
	}
});


/*------------------------------------------------------------------------------------------
	Eventos sobre popup de conversaciones
-------------------------------------------------------------------------------------------*/

/*evento al hacer click en los elementos del menu popup*/
$(document).on('click','.dropdown-menu-list.scroller li', function (e){
	Chat.loadConversation(this, 'popup');
});

/*evento para mostrar todas las conversaciones*/
$(document).on('click', '.sp__aux__view__all', function (e) {
    Chat.loadAllConversations();
});

/*------------------------------------------------------------------------------------------
	Eventos sobre panel lateral de conversaciones
-------------------------------------------------------------------------------------------*/

/*evento para controlar el click sobre el tab de conversaciones*/
$(document).on('click', '#conversationTab', function(){
	Chat.loadAllConversations();
});

/*evento para controlar el click sobre el tab de usuarios*/
$(document).on('click', '#userTab', function(){
	Chat.loadFrecuentUsers();
});

/*evento para mostrar la conversacion desde el panel lateral de conversaciones*/
$(document).on('click', '.page-quick-sidebar-chat-users .media-list > .media', function () {
	Chat.loadConversation(this, 'sidebar');
});


/*------------------------------------------------------------------------------------------
Eventos sobre chat abierto
-------------------------------------------------------------------------------------------*/

/*evento para cerrar chat y cerrar pantalla*/
$(document).on('click','.page-quick-sidebar-toggler',function (e) {
	Chat.closeConversation();
	Chat.closeLateralPanel();
});

/*evento para cerrar el chat*/
$('.page-quick-sidebar-chat-user .page-quick-sidebar-back-to-list').click(function () {
	Chat.closeConversation();
	var source = $(this).attr("data-source");
	if (source == "popup"){
		Chat.closeLateralPanel();
	}
});
