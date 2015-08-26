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

		//al desconectar
		socket.on('disconnect', function(){
			console.log("usuario desconectado: " + socket.id);
			//se termina la conexion
			DB.processQuery("finishUserConnection",[socket.id], processDesconection);

        	function processDesconection(r){

        		DB.processQuery("getUser", [data.userId], function(r){
					socket.broadcast.emit('chat_user_leave', r);
				});
        	}

		});

		/*evento y funcion para notificar cuando se creó una conversacion*/
		function newConversationNotification(r){
			var cId = r.cId;
			var me = r.me;
			var userId = r.userId;
			console.log("nueva conversacion creada");
			console.log("conversationid: " +cId);
			console.log("usuario que crea:" + me);
			console.log("usuario 2 "+ userId);
			//preguntamos si el usuario esta activo
			//si esta activo, le notificamos para actualice el valor del elemento en el panel de usuarios en linea
			DB.processQuery('isUserOnline', [userId], function(r){
				console.log(r);
				if (r[0].active == 1){
					console.log("uusario activo, mandando notificacion");
					io.to(r[0].socketId).emit('complement_cId', {"cId":cId, "userId": me});
				}
			});
		};

		socket.on('new_cId', newConversationNotification);


		/*evento y funcion para procesar un nuevo mensaje*/
		function processNewMessage(data){
			DB.processQuery("insertMessage", [data.cId, data.me, data.userId, data.msg], function(r){
				console.log("mensaje insertado");
				console.log(r);

				DB.processQuery('isUserOnline', [data.userId], function(res){
				console.log(res);
				if (res[0].active == 1){
					console.log("mandando notif nuevo msg");
					io.to(res[0].socketId).emit('new_message_received', 
						{"cId":data.cId, "userId": data.me, "msg":data.msg});
				}
			});

			});
		}

		socket.on('new_message', processNewMessage);

		/*----------------------------------
			fin de definicion de eventos
		------------------------------------*/



		//se inicia la conexion
		DB.processQuery("startUserConnection", [data.userId, socket.id], pushConnectedUsers);

		function pushNewConnectedUser(userId, callback){
			DB.processQuery("getUser", [userId], function(r){
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
}

module.exports = SecopreSocket;