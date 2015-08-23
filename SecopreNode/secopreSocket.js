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
			DB.processQuery("finishUserConnection",[socket.id], function(r){
        	    socket.broadcast.emit('chat_user_leave', {user: data.userId});
        	});

		});

		//se inicia la conexion
		DB.processQuery("startUserConnection", [data.userId, socket.id], function(r){

            socket.broadcast.emit('chat_new_user', {user: data.userId});

	        /*test*/
	        var connectedSockets = [];
			for ( var socketId in io.nsps["/"].adapter.rooms){
				connectedSockets.push(socketId);
			};

			console.log("sockets conectados");
			console.log(connectedSockets);
			console.log("-------------------------------");
			
			DB.processMultipleQuery("getActiveUsers", connectedSockets, [data.userId, data.userId], function(r){
				//pasamos la lista de usuarios
				console.log("cargando usuarios activos");
            	socket.emit('load_active_users', r);
			});
        });
	});
}

module.exports = SecopreSocket;