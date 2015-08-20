var socketModule = require('socket.io');

var SecopreSocket = function(config){
	config = config || {};
	
	var io = socketModule.listen(config.server);

	io.sockets.on('connection', function(socket){
		console.log("nuevo usuario conectado: " + socket.id);
		console.log(socket);		
		socket.emit('chat_new_user', {data:'nuevo usuario conectado'});

		socket.on('disconnect', function(){
			console.log("usuario desconectado: " + socket.id);
		});
	});
}

module.exports = SecopreSocket;