var socketModule = require('socket.io');

var SecopreSocket = function(config){
	config = config || {};

	var DB = config.db;
	
	var io = socketModule.listen(config.server);

	io.sockets.on('connection', function(socket){
		console.log("nuevo usuario conectado: " + socket.id);
		var data = JSON.parse(socket.handshake.query.data);
		console.log(data);	

		/*definicion de eventos*/
		socket.on('disconnect', function(){
			console.log("usuario desconectado: " + socket.id);
		
			//se termina la conexion
			DB.processQuery("finishUserConnection",[socket.id], function(r){
        	    console.log("ok");
        	    console.log(r);
        	    socket.broadcast.emit('chat_user_leave', {user: data.userId});
        	});

		});

		//se inicia la conexion
		DB.processQuery("startUserConnection", [data.userId, socket.id], function(r){
            console.log("ok");
            console.log(r);
            socket.broadcast.emit('chat_new_user', {user: data.userId});
        });
	});
}

module.exports = SecopreSocket;