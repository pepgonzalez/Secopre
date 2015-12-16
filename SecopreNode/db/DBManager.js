var mysql     =    require('mysql');

var DBPool =  mysql.createPool({
        connectionLimit : 100,
        host     : '189.210.196.197',
        user     : 'secopre',
        password : 'secopre',
        database : 'secopre',
        debug    :  true,
        multipleStatements: true
    });

var Q = require('./SQLManager');
var QueryManager = new Q();
QueryManager.loadQueries(function(r){
    QueryManager.q = r;
});

var DBManager = function(config){
	config = config || {};

	this.processQuery = function(key, params, callback){

        //se obtiene una conneccion del pool de conexiones
        DBPool.getConnection(function(error, connection){
            //si error, logueo y trueno
            if(error){
                console.log("Error de al obtener la conexion: " + error);
                connection.release();
                return;
            }

            var q = QueryManager.getQuery(key);
            //si todo ok, tiro mi query
            connection.query(q, params, function(error, resultado){
                connection.release();
                if(error){
                    console.log("Error al consultar el query: " + q );
                    console.log("ERROR: " + error);
                    return;
                }
                //ejecutamos el callback con el
                callback(resultado);
            });
        });
	};

    this.processMultipleQuery = function (key, params, aditionalParams, callback){
        DBPool.getConnection(function (error, connection){
            if (error){
                connection.release();
                console.log("Error al obtener la connexion");
                callback({});
            }
            var q = QueryManager.getQuery(key);

            
            var data = {};
            var d2 = [];
            

            var clients = params.length;

            console.log("usuarios conectados: " + clients);

            for (var i = 0; i < params.length; i ++){

                var param = params[i];

                function getParams(a, b){
                    var array = [];
                    
                    /*
                    array.push(a);
                    for (var m=0; m < b.length; m++){
                        array.push(b[i]);
                    }*/
                    array.push(b[0]);
                    array.push(a);
                    array.push(b[1]);
                    return array;
                }

                var p = getParams(param, aditionalParams);
                console.log("parametros de consulta");
                console.log(p);
                connection.query(q, p, function(error, resultado){
                    if (error){
                        console.log("error de consulta en query: " + error);
                        callback({});
                    }
                    _cb(resultado);
                });

                function _cb(r){
                    if(r.length > 0){
                        d2.push(r[0]);
                    }
                    if ( i == params.length){
                        callback(d2);
                    }
                }
            }
        });
    };
}

module.exports = DBManager;