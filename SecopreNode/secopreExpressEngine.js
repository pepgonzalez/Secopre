express = require('express');

var cors = require('cors');
var corsOptions = { origin : 'http://localhost:9999'}

var SecopreChat = function(config){
    config = config || {};

    var DB = config.db;
    
    this.expressServer = express();

    this.expressServer.use(cors(corsOptions));

    this.expressServer.get('/v1/chat/getConversations/:usrId/:from/:to', function(req, res){
        
        var usrId = req.params.usrId;
        var fromP = req.params.from;
        var toP = req.params.to;
        
        DB.processQuery("getConversations", [usrId, parseInt(fromP), parseInt(toP)], function(r){
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
        DB.processQuery("getConversation", [cId, userId, cId, userId], function(r){
            res.json(r);
        });
    });
    
};

module.exports = SecopreChat;