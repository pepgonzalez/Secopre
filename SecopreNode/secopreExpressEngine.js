express = require('express');
DBManager = require('./db/DBManager');

var cors = require('cors');
var corsOptions = { origin : 'http://localhost:9999'}

DB = new DBManager();

var SecopreChat = function(config){
    config = config || {};

    this.expressServer = express();

    this.expressServer.get('/v1/chat/getConversations/:usrId/:from/:to',  cors(corsOptions), function(req, res){
        
        var usrId = req.params.usrId;
        var fromP = req.params.from;
        var toP = req.params.to;
        
        DB.processQuery("getConversations", [usrId, parseInt(fromP), parseInt(toP)], function(r){
            res.json(r);
        });
    });

    this.expressServer.get('/v1/chat/updateSeen/:cId', cors(corsOptions), function(req, res){
        var cId = req.params.cId;
        DB.processQuery("updateSeen", [cId], function(r){
            res.json(r);
        });
    });

    this.expressServer.get('/v1/chat/getConversation/:cId/:userId', cors(corsOptions), function(req, res){
        var cId = req.params.cId;
        var userId = req.params.userId;
        DB.processQuery("getConversation", [cId, userId, cId, userId], function(r){
            res.json(r);
        });
    });
    
};

module.exports = SecopreChat;