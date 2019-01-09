package fr.univnantes.alma.iot.codegen

import iotModel.Network

class XtendIotCodeGen {
			String code;

	def void generateCode(Network net) {
		code = ''' 
			var Arrow = require('arrow');
			var client;
			module.exports.mqttInit = function() {
				var mqtt = require('mqtt');
				var mqtturl = 'mqtt://«net.brokerconf.user»:«net.brokerconf.password»@«net.brokerconf.server»:«net.brokerconf.port»';
				client = mqtt.connect(mqtturl);
				
				client.on('connect', function () {
					console.log('app.js - client connected');
					«FOR device : net.device»
						«FOR instrument:device.instrument »
							«FOR interest: instrument.interet»
								client.subscribe('«instrument.name»/«interest.name»/#');
							«ENDFOR»
						«ENDFOR»
					«ENDFOR»
					
				});
				
				client.on('message', function (topic, message) {
					console.log('app.js - message received');
					handleMessage(topic, message);
				});
			};	
			var handleMessage = function(topic, message) {
				switch(topic) {
				«FOR device : net.device»
					«FOR instrument:device.instrument »
						«FOR interest: instrument.interet»
							case '«instrument.name»/«interest.name»':
							  		«instrument.name»«interest.name.toFirstUpper»Message(message);
							  		break;
						«ENDFOR»
					«ENDFOR»
				«ENDFOR»
				default: unhandledTopic(topic, message);
				}
				};
				var unhandledTopic = function(topic, message) {
					console.log('apibmqttstreet.js - unhandledTopic() - Unhandled Topic: '+topic);
				};
				«FOR device : net.device»
					«FOR instrument:device.instrument »
						«FOR interest: instrument.interet»
							var «instrument.name»«interest.name.toFirstUpper»Message = function(message, callback) {
								   message = JSON.parse(message);
								   var model = Arrow.getModel("«instrument.name»«interest.name.toFirstUpper»");
								   var «interest.name»Object = {
								       «interest.name»Id : message.«interest.name»Id,
								       «FOR feature: interest.features»
								       	«feature.name» : message.«feature.name»,
								       «ENDFOR»
								       date : message.date
								   };
								model.create(«interest.name»Object,  function(err, instance){
								       if(err) {
								           console.log("Error creating InterstObject");
								       } else {
								           instance.set(«interest.name»Object);
								       }
								   });
								
							
							};
						«ENDFOR»
					«ENDFOR»
				«ENDFOR»
			'''
			System.out.println("do dod ")
		Util.writeJavaClassToFile("iot", "apiMQTT", code);
		generateModels(net);
	}

	def generateModels(Network net) {
		for (device : net.device) {
			for (instument : device.instrument) {
				for (interest : instument.interet) {
					code = '''
						var Arrow = require('arrow');
						var Model = Arrow.createModel('«instument.name»«interest.name.toFirstUpper»', {
							 "fields": {
							 	 "«interest.name»Id": {
							 	            "type": "string"  
							 	        },
							 	   «FOR feature : interest.features»
							 	   	"«feature.name»" :{
							 	   	            "type": "«feature.measure.type.getName()»"
							 	   	},
							 	   «ENDFOR»
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
					'''
				Util.writeJavaClassToFile("models", instument.name+interest.name.toFirstUpper, code);	
				}
			}
		}

	}
	
}