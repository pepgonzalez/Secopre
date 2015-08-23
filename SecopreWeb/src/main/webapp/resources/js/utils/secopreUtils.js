monthMap = {
						"01":"Ene",
						"02":"Feb",
						"03":"Mar",
						"04":"Abr",
						"05":"May",
						"06":"Jun",
						"07":"Jul",
						"08":"Ago",
						"09":"Sep",
						"10":"Oct",
						"11":"Nov",
						"12":"Dic"
				};

SecopreUtils = function(){
	this.DateUtils = {
			getUXTimeFromDBDate :function(string){
				var str = string.substring(11,16);
				return str;
			},
			getDateFromDBString : function(string){
				var str = string.substring(0,19);
				return new Date(str);
			},
			getUXDate : function(date){
				var time = new Date().getTime() - date.getTime();
				var seconds = Math.floor(time / 1000);
				var minutes = Math.floor(seconds / 60);
				var hours = Math.floor(minutes / 60);
				var days = hours / 24;
				if (days > 1){
					return getDate(date);
				}else if(days == 1){
					return "Ayer a las " + getTime(date);
				}else{
					if(hours > 1){
						return "Hace " + hours + " horas";
					}else if (hours == 1){
						return "Hace " + hours + " hora";
					}else{
						if(minutes > 1){
							return "Hace " + minutes + " minutos";
						}else if(minutes == 1){
							return "Hace un minuto";
						}else{
							return "Justo ahora";
						}
					}
				}
				
				function getTime(temp){
					 var dateStr = padStr(temp.getHours()) + ":" +
	                  padStr(temp.getMinutes());
					 return dateStr;
				}
				
				function getDate(temp) {
				    var dateStr = padStr(temp.getFullYear()) +
				                  padStr(1 + temp.getMonth()) +
				                  padStr(temp.getDate()) +
				                  padStr(temp.getHours()) +
				                  padStr(temp.getMinutes()) +
				                  padStr(temp.getSeconds());
				    
				    var mes = padStr(1 + temp.getMonth());
				    var d = padStr(temp.getDate()) + " " + monthMap[mes] + " " + padStr(temp.getFullYear());  
				    return d;
				}

				function padStr(i) {
				    return (i < 10) ? "0" + i : "" + i;
				}
			}
	}
	
	this.Constants = {
			USER_BASE_PATH : '/Secopre/resources/img/users/',
			USER_DEFAULT_AVATAR : 'avatar.png',
			Templates: {
				INBOX_TEMPLATE : "#inbox__popup__message__template",
				CONVERSATION_TEMPLATE: "#conversation_template"
			},
			SOCKET_URL : 'http://localhost:3000/'
	}
	
}