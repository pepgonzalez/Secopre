var mysql     =    require('mysql');

var DBPool =  mysql.createPool({
        connectionLimit : 100,
        host     : 'localhost',
        user     : 'root',
        password : 'root',
        database : 'secopre',
        debug    :  false
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
                connection.release();
                console.log("Error de al obtener la conexion: " + error);
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
	}
}

module.exports = DBManager;