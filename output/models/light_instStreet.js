var Arrow = require('arrow');
var Model = Arrow.createModel('light_instStreet', {
	 "fields": {
	 	 "StreetId": {
	 	            "type": "string"  
	 	        },
	 	   "south" :{
	 	               "type": "string"
	 	   },
	 	   "east" :{
	 	               "type": "string"
	 	   },
	 	   "north" :{
	 	               "type": "string"
	 	   },
	 	   "west" :{
	 	               "type": "string"
	 	   },
	 	   "date": {
	 	               "type": "string",
	 	               "description": "trafic light date"
	 	           }
	 	       },
	 	        "connector": "appc.arrowdb",
	 	        "actions": [
	 	               "create",
	 	               "read",
	 	               "update",
	 	               "delete",
	 	               "deleteAll"
	 	           ],
	 	           "description": "bla bla bla"
});
module.exports = Model;
