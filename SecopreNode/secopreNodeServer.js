var http = require('http');
var SecopreChat = require('./secopreExpressEngine');
var SecopreSocket = require('./secopreSocket');

DBManager = require('./db/DBManager');
var DB = new DBManager();

var secopreChat = new SecopreChat({db: DB});
var server = http.createServer(secopreChat.expressServer);
var Io = new SecopreSocket( {server:server, db: DB} );

server.listen(3000, function(){
	console.log("escuchando en el puerto 3000");
});