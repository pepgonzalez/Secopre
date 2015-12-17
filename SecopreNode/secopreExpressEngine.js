express = require('express');

var cors = require('cors');
//var corsOptions = { origin : 'http://smartsecopre.ddns.net:8080'}

var whitelist = ['http://smartsecopre.ddns.net:8080', 'http://192.168.228.1:8888','http://189.183.32.111:8888','http://localhost:8888'];
var corsOptions = {
  origin: function(origin, callback){
    var originIsWhitelisted = whitelist.indexOf(origin) !== -1;
    callback(null, originIsWhitelisted);
  }
};

var SecopreChat = function(config){
    config = config || {};

    var DB = config.db;
    
    this.expressServer = express();

    this.expressServer.use(cors(corsOptions));

    this.expressServer.get('/v1/chat/getConversations/:usrId/:from/:to', function(req, res){
        
        var usrId = req.params.usrId;
        var fromP = req.params.from;
        var toP = req.params.to;
        
        DB.processQuery("getConversations", [usrId, usrId, parseInt(fromP), parseInt(toP)], function(r){
            res.json(r);
        });
    });

    this.expressServer.get('/v1/chat/updateSeen/:cId', function(req, res){
        var cId = req.params.cId;
        DB.processQuery("updateSeen", [cId], function(r){
            res.json(r);
        });
    });

    this.expressServer.get('/v1/chat/getConversation/:cId/:userId', function(req, res){
        var cId = req.params.cId;
        var userId = req.params.userId;
        DB.processQuery("getConversation", [cId, userId, cId, userId, 0, 21], function(r){
            res.json(r);
        });
    });

    this.expressServer.get('/v1/chat/getMoreMsgs/:cId/:userId/:from', function(req, res){
        var cId = req.params.cId;
        var userId = req.params.userId;
        var from = req.params.from;
        DB.processQuery("getConversation", [cId, userId, cId, userId, parseInt(from), 21], function(r){
            res.json(r);
        });
    });

    this.expressServer.get('/v1/chat/getFrecuentUsers/:userId', function(req, res){
        var userId = req.params.userId;
        DB.processQuery("getFrecuentUsers", [userId], function(r){
            res.json(r);
        });
    });  


    /*valida si existe una conversacion*/
    this.expressServer.get('/v1/chat/getConversationId/:userId/:contactId', function(req, res){
        var userId = req.params.userId;
        var contactId = req.params.contactId;
        DB.processQuery("existConversation", [userId, contactId], function(r){
            res.json(r);
        });
    });  

    /*proceso para crear un registro en la tabla de conversaciones*/
    this.expressServer.get('/v1/chat/createConversation/:u1/:u2', function(req, res){
        var u1 = req.params.u1;
        var u2 = req.params.u2;

        DB.processQuery('startConversation', [u1, u2], function(r){
            res.json(r[1][0]);
        });
    });

    /*ping*/
    this.expressServer.get('/v1/', function(req, res){
        res.json({"status":200});
    });
};

module.exports = SecopreChat;