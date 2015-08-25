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
				
				DB.processMultipleQuery("getActiveUsers", connectedSockets, [data.userId, data.userId], function(r){
	            	socket.emit('load_active_users', r);
				});
	        });
        }
	});
}

module.exports = SecopreSocket;