var http = require('http');
var SecopreChat = require('./secopreExpressEngine');
var SecopreSocket = require('./secopreSocket');

var secopreChat = new SecopreChat();
var server = http.createServer(secopreChat.expressServer);
var Io = new SecopreSocket( {server:server} );

server.listen(3000, function(){
	console.log("escuchando en el puerto 3000");
});