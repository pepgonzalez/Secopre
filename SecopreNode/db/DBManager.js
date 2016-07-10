var mysql     =    require('mysql');

var DBPool =  mysql.createPool({
        connectionLimit : 100,
        host     : process.env.SECOPRE_DB_HOST,
        user     : process.env.SECOPRE_DB_USER,
        password : process.env.SECOPRE_DB_PASS,
        database : process.env.SECOPRE_DB_DB,
        debug    :  false,
        multipleStatements: true
    });

var Q = require('./SQLManager');
var QueryManager = new Q();
QueryManager.loadQueries(function(r){
    QueryManager.q = r;
});

var DBManager = function(config){
	config = config || {};

	console.log(process.env);
	console.log("DBPOOL");
	console.log(DBPool);
	
	this.processQuery = function(key, params, callback){

		console.log("ejecutando consulta: " + key + ", con parametros: " + params);
		
        //se obtiene una conneccion del pool de conexiones
        DBPool.getConnection(function(error, connection){
            //si error, logueo y trueno
            if(error){
                console.log("Error de al obtener la conexion de base de datos: " + error);
                connection.release();
                return;
            }

            var q = QueryManager.getQuery(key);
            connection.query(q, params, function(error, resultado){
                connection.release();
                if(error){
                    console.log("Error al realizar la consulta: " + q +": " + error);
                    return;
                }
                //ejecutamos el callback con el resultado
                console.log("ejecucion correcta, ejecutando callback con resultado");
                callback(resultado);
            });
        });
	};

    this.processMultipleQuery = function (key, params, aditionalParams, callback){
        console.log("ejecutando consulta multiple: " + key + ", con parametros: " + params + " y parametros adicionales: " + aditionalParams);
    	
        console.log("obteniendo conexion de base de datos");
    	
        DBPool.getConnection(function (error, connection){
            
        	//se valida se pudo obtener la conexion a la base de datos
    		if (error){
                connection.release();
                console.log("Error al obtener la connexion de base de datos");
                return;
            }
            
            var q = QueryManager.getQuery(key);
            var data = {};
            var d2 = [];
            
            var clients = params.length;
            console.log("total de sockets conectados: " + clients);

            for (var i = 0; i < params.length; i ++){

                var param = params[i];

                function getParams(a, b){
                    var array = [];
                    array.push(b[0]);
                    array.push(a);
                    array.push(b[1]);
                    return array;
                }
                
                function _cb(r){
                    if(r.length > 0){
                        d2.push(r[0]);
                    }
                    if ( i == params.length){
                        callback(d2);
                    }
                }

                var p = getParams(param, aditionalParams);
                console.log("parametros de consulta de query : "  + key);
                console.log(p);
                connection.query(q, p, function(error, resultado){
                    if (error){
                        console.log("error de consulta en query: " + q + ": " + error);
                        //callback({});
                    }
                    _cb(resultado);
                });
            }
        });
    };
}

module.exports = DBManager;