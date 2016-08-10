var socketModule = require('socket.io');

var SecopreSocket = function(config){
	
	config = config || {};
	var DB = config.db;
	
	var io = socketModule.listen(config.server);

	io.sockets.on('connection', function(socket){
		console.log("nuevo usuario conectado: " + socket.id);
		var data = JSON.parse(socket.handshake.query.data);

		/*-----------------------------------
			definicion de eventos
		-------------------------------------*/

		/*se valida si el usuario esta activo en otro lado, si es asi, se plancha el socket id con el activo actualmente*/
		
		//evento que gestiona la desconexion del usuario
		socket.on('disconnect', function(){
			console.log("usuario desconectado: " + data.userId);
			//se termina la conexion
			console.log("elimando conexion de usuario: " + data.userId);
			DB.processQuery("finishUserConnection",[socket.id], processDesconection);

        	function processDesconection(r){
        		console.log("notificando que se ha desconectado el user id: " + data.userId);
        		DB.processQuery("getUser", [data.userId], function(r){
					socket.broadcast.emit('chat_user_leave', r);
				});
        	}
		});

		/*-----------------------------------------------------------------------------------------------------------------------------------*/
		/*evento y funcion para notificar cuando se creó una conversacion*/
		function newConversationNotification(r){
			var cId = r.cId;
			var me = r.me;
			var userId = r.userId;
			console.log("nueva conversacion creada: conversationId: " +cId+ ", usuario que crea:" + me+ ", usuario 2: "+ userId);
			//preguntamos si el usuario esta activo
			//si esta activo, le notificamos para actualice el valor del elemento en el panel de usuarios en linea
			console.log("preguntando si el usuario esta en linea: ");
			DB.processQuery('isUserOnline', [userId], function(r){
				if (r[0].active == 1){
					console.log("uusario activo, mandando notificacion");
					io.to(r[0].socketId).emit('complement_cId', {"cId":cId, "userId": me});
				}
			});
		};

		socket.on('new_cId', newConversationNotification);
		
		/*------------------------------------------------------------------------------------------------------------------------------------*/

		/*funcion generica para empujar un msj al socket si esta en linea y ejecutar el callback recibido*/
		function pushEvent(event, userId, data, callback){
			DB.processQuery('isUserOnline', [userId], function(res){
				if(res != undefined){
					if (res[0].active == 1){
						console.log("enviando evento " + event + " a socketid: " + res[0].data);
						io.to(res[0].socketId).emit(event, data);
					}
				}
				if (typeof callback === "function") {
					callback();
				}
			});
		}

		/*------------------------------------------------------------------------------------------------------------------------------------*/
		/*evento y funcion para procesar un nuevo mensaje*/
		function processNewMessage(data){
			DB.processQuery("insertMessage", [data.cId, data.me, data.userId, data.msg], function(r){
				//emito al usuario que recibio un msj
				var dataToReceiver = {"cId":data.cId, "userId": data.me, "msg":data.msg};			
				pushEvent('new_message_received', data.userId, dataToReceiver, function(){
					pushEvent('update', data.me, {}, function(){});
				});
			});
		}

		socket.on('new_message', processNewMessage);
		
		/*--------------------------------------------------------------------------------------------------------------------------------------*/
		/*funcion y evento para busqueda de usuarios*/
		function processUserSearch(data){
			console.log(data.me);
			console.log(data.userName);
			DB.processQuery("getUserInfo", [data.me, data.userName, data.me], function(r){
				pushEvent("search_user_result", data.me, r)
			});
		}

		socket.on('search_user', processUserSearch);
		
		/*--------------------------------------------------------------------------------------------------------------------------------------*/

		console.log("registrando conexion de usuario en base de datos...");
		//se inicia la conexion
		DB.processQuery("startUserConnection", [data.userId, socket.id], pushConnectedUsers);

		function pushNewConnectedUser(userId, callback){
			DB.processQuery("getUser", [userId], function(r){
				console.log("enviando evento de nuevo usuario conectado: " + userId);
				socket.broadcast.emit('chat_new_user', r);
				callback();
			});
		}

        function pushConnectedUsers(r){

        	pushNewConnectedUser(data.userId, function(){
        		var connectedSockets = [];
				for ( var socketId in io.nsps["/"].adapter.rooms){
					connectedSockets.push(socketId);
				};
				
				DB.processMultipleQuery("getActiveUsersV2", connectedSockets, [data.userId, data.userId], function(r){
					console.log("usuarios conectados:");
					console.log(r);
	            	socket.emit('load_active_users', r);
				});
	        });
        }
	});


	/*-----------------------------------------------------------------------------------------------------------------------------------*/
	/*
	//funcion para hacer pooling a la base de datos en busqueda de nueva informacion
	setInterval(searchForNotifications, 3000);

	function searchForNotifications(){
		DB.processQuery("getNewNotifications", [], function(r){
			console.log("resultados: ");
			console.log(r);
		});
	}
	*/
}

module.exports = SecopreSocket;