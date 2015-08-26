
var QuickSidebar = function () {

    // Handles quick sidebar toggler
	
	/*
    var handleQuickSidebarToggler = function () {
        // quick sidebar toggler
        $('.sp__aux__view__all').click(function (e) {
            $('body').toggleClass('page-quick-sidebar-open'); 
            
            //bloqueo de pantalla
            Metronic.blockUI({ target: '.page-container', textOnly: true,  message: '' });
            Metronic.blockUI({ target: '.page-header', textOnly: true, message: '' });
            
        });
        $('.page-quick-sidebar-toggler').click(function (e) {
            $('body').removeClass('page-quick-sidebar-open'); 
            
            //bloqueo de pantalla
            Metronic.unblockUI('.page-container');
            Metronic.unblockUI('.page-header');
            
        });
        
    };
    */
    
    /*
	 * funcion para actualizar el visto del msg
	 * */
    /*
	function updateSeen(cId, userId, userName, callback){
		$.ajax({
			url: "http://localhost:3000/v1/chat/updateSeen/" + cId,
			dataType: 'json'
		}).done(function(r) {
			callback(cId, userId, username, r);
		});
	}
	*/
	/*
	 * funcion para obtener la conversacion
	 * */
	/*
	function getConversation(cId, userId,  callback){
		$.ajax({
			url: "http://localhost:3000/v1/chat/getConversation/" + cId + "/" + userId,
			dataType: 'json'
		}).done(function(r) {
			callback(r);
		});
	}
	*/
	
	/*
	function activateTemplate(id) {
	    var t = document.querySelector(id);
	    return document.importNode(t.content, true);
	  };
	 */
	
	/*
	function handleConversation(cId, userId, userName, res){
		
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
		getConversation(cId, userId, function(data){
			
			$.each(data, function(){
				var element = activateTemplate("#chat_message");
				
				element.querySelector("[msg]").className = (this.direction == 'IN' ? 'post out' : 'post in');
				element.querySelector("[data-avatar]").src = utils.Constants.USER_BASE_PATH + this.avatar;
				element.querySelector("[data-username]").innerHTML = this.userName;
				element.querySelector("[data-msg-body]").innerHTML = this.msg;
				element.querySelector("[data-time]").innerHTML= utils.DateUtils.getUXTimeFromDBDate(this.creationDate);
				
				$("#chat_container").append(element);
				
			});
		});
		
	}
	*/
    
	/*
    var handleChatPopup = function(){
    	$(document).on('click','.dropdown-menu-list.scroller li', function (e){
    		 $('body').toggleClass('page-quick-sidebar-open'); 
    		 
    		 var userId = $(this).attr("data-userId");
             var userName = $(this).attr("data-userName");
             var conversationId = $(this).attr("data-cId");
             
             var clases = $(this).attr("class");
             
             if(clases == "sp__message__pending"){
            	 updateSeen(conversationId,userId, userName, handleConversation);
             }else{
            	 handleConversation(conversationId, userId, userName, {});
             }
    	});
    }
    */
    
    // Handles quick sidebar chats
    var handleQuickSidebarChat = function () {
        var wrapper = $('.page-quick-sidebar-wrapper');
        var wrapperChat = wrapper.find('.page-quick-sidebar-chat');

        var initChatSlimScroll = function () {
            var chatUsers = wrapper.find('.page-quick-sidebar-chat-users');
            var chatUsersHeight;

            chatUsersHeight = wrapper.height() - wrapper.find('.nav-justified > .nav-tabs').outerHeight();

            // chat user list 
            Metronic.destroySlimScroll(chatUsers);
            chatUsers.attr("data-height", chatUsersHeight);
            Metronic.initSlimScroll(chatUsers);

            //var chatMessages = wrapperChat.find('.page-quick-sidebar-chat-user-messages');
            var chatMessages = wrapperChat.siblings('.page-quick-sidebar-item').find('.page-quick-sidebar-chat-user-messages');
            
            var $form = wrapperChat.siblings('.page-quick-sidebar-item').find('.page-quick-sidebar-chat-user-form');
            var $nav = wrapperChat.siblings('.page-quick-sidebar-item').find('.page-quick-sidebar-nav');
            
            //var chatMessagesHeight = chatUsersHeight - wrapperChat.find('.page-quick-sidebar-chat-user-form').outerHeight() - wrapperChat.find('.page-quick-sidebar-nav').outerHeight();
            var chatMessagesHeight = chatUsersHeight - $form.outerHeight() - $nav.outerHeight();
                        
            // user chat messages 
            Metronic.destroySlimScroll(chatMessages);
            chatMessages.attr("data-height", chatMessagesHeight);
            Metronic.initSlimScroll(chatMessages);
        };

        initChatSlimScroll();
        Metronic.addResizeHandler(initChatSlimScroll); // reinitialize on window resize

        //Levanta la pestaña de chat al dar click en el cualquier media
        
        /*
        wrapper.find('.page-quick-sidebar-chat-users .media-list > .media').click(function () {
        	console.log("click en media");
            wrapperChat.addClass("page-quick-sidebar-content-item-shown");
            
            var userId = $(this).attr("data-userId");
            var userName = $(this).attr("data-userName");
            
            $('.sp__nav__basic').css("cssText", "display: none !important;");
            var complexNav = $('.sp__nav__comp');
            complexNav.css("cssText", "display: table-cell !important;");
            complexNav.find('a').html(userName);
            
            $('.post.in .message .name').empty().html(userName);           
        });
        */

        /*
        wrapper.find('.page-quick-sidebar-chat-user .page-quick-sidebar-back-to-list').click(function () {
            wrapperChat.removeClass("page-quick-sidebar-content-item-shown");
            $('.sp__nav__basic').css("cssText", "display: table-cell !important;");
            $('.sp__nav__comp').css("cssText", "display: none !important;");
        });
        */

        var handleChatMessagePost = function (e) {
            e.preventDefault();

            //var chatContainer = wrapperChat.find(".page-quick-sidebar-chat-user-messages");
            var chatContainer = $('.page-quick-sidebar-chat-user-messages');
            //var input = wrapperChat.find('.page-quick-sidebar-chat-user-form .form-control');
            var input = $('.page-quick-sidebar-chat-user-form .form-control');
            
            var currentUserToSend = $('.sp__nav__comp a').html();
            
            var text = input.val();
            if (text.length === 0) {
                return;
            }
            
            var logedUserName = $('.name').html();

            var preparePost = function(dir, time, name,  message) {
                var tpl = '';
                tpl += '<div class="post '+ dir +'">';
                tpl += '<img class="avatar" alt="" src="/Secopre/resources/img/avatar.png"/>';
                tpl += '<div class="message">';
                tpl += '<span class="arrow"></span>';
                tpl += '<a href="#" class="name">' + name + '</a>&nbsp;';
                tpl += '<span class="datetime">' + time + '</span>';
                tpl += '<span class="body">';
                tpl += message;
                tpl += '</span>';
                tpl += '</div>';
                tpl += '</div>';

                return tpl;
            };

            // handle post
            var time = new Date();
            var message = preparePost('out', (time.getHours() + ':' + time.getMinutes()), logedUserName, text);
            message = $(message);
            chatContainer.append(message);

            var getLastPostPos = function() {
                var height = 0;
                chatContainer.find(".post").each(function() {
                    height = height + $(this).outerHeight();
                });

                return height;
            };           

            chatContainer.slimScroll({
                scrollTo: getLastPostPos()
            });

            input.val("");

            // simulate reply
            setTimeout(function(){
                var time = new Date();
                var message = preparePost('in', (time.getHours() + ':' + time.getMinutes()), currentUserToSend, 'AutoRespuesta para simular comportamiento de chat...');
                message = $(message);
                chatContainer.append(message);

                chatContainer.slimScroll({
                    scrollTo: getLastPostPos()
                });
            }, 1500);
        };

        
        //wrapperChat.find('.page-quick-sidebar-chat-user-form .btn').click(handleChatMessagePost);
        //wrapperChat.find('.page-quick-sidebar-chat-user-form .form-control').keypress(function (e) {
        
        /*
        $('.page-quick-sidebar-chat-user-form .btn').click(handleChatMessagePost);
        $('.page-quick-sidebar-chat-user-form .form-control').keypress(function (e) {
        	if (e.which == 13) {
                handleChatMessagePost(e);
                return false;
            }
        });
        */
    };

    // Handles quick sidebar tasks
    var handleQuickSidebarAlerts = function () {
        var wrapper = $('.page-quick-sidebar-wrapper');
        var wrapperAlerts = wrapper.find('.page-quick-sidebar-alerts');

        var initAlertsSlimScroll = function () {
            var alertList = wrapper.find('.page-quick-sidebar-alerts-list');
            var alertListHeight;

            alertListHeight = wrapper.height() - wrapper.find('.nav-justified > .nav-tabs').outerHeight();

            // alerts list 
            Metronic.destroySlimScroll(alertList);
            alertList.attr("data-height", alertListHeight);
            Metronic.initSlimScroll(alertList);
        };

        initAlertsSlimScroll();
        Metronic.addResizeHandler(initAlertsSlimScroll); // reinitialize on window resize
    };

    // Handles quick sidebar settings
    var handleQuickSidebarSettings = function () {
        var wrapper = $('.page-quick-sidebar-wrapper');
        var wrapperAlerts = wrapper.find('.page-quick-sidebar-settings');

        var initSettingsSlimScroll = function () {
            var settingsList = wrapper.find('.page-quick-sidebar-settings-list');
            var settingsListHeight;

            settingsListHeight = wrapper.height() - wrapper.find('.nav-justified > .nav-tabs').outerHeight();

            // alerts list 
            Metronic.destroySlimScroll(settingsList);
            settingsList.attr("data-height", settingsListHeight);
            Metronic.initSlimScroll(settingsList);
        };

        initSettingsSlimScroll();
        Metronic.addResizeHandler(initSettingsSlimScroll); // reinitialize on window resize
    };

    return {

        init: function () {
            //layout handlers
            //handleQuickSidebarToggler(); // handles quick sidebar's toggler
            handleQuickSidebarChat(); // handles quick sidebar's chats
            //handleQuickSidebarAlerts(); // handles quick sidebar's alerts
            //handleQuickSidebarSettings(); // handles quick sidebar's setting
            //handleChatPopup();
        }
    };

}();