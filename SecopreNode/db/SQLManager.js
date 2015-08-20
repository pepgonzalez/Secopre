var xml2js = require('xml2js');
var S = require('string');
var fs = require('fs');

var Q = function(){

	this.loadQueries = function(callback){

		var parser = new xml2js.Parser({explicitArray:false});
		var dirname = __dirname;
		console.log(dirname);
		fs.readFile(__dirname + '/sql.xml', function(err, data) {
			if(err){
				console.log("error al cargar xml" + err);
			}
    		parser.parseString(data, function (err, r) {
    			if(err){
    				console.log("error al parsear el archivo:" + err);
    			}
        		callback(r);
    		});
		});
	}

	this.q = {};

	this.getQuery = function(key){
		return S(this.q.queries[key]).collapseWhitespace().s;
	}
}

module.exports = Q;