var Arrow = require('arrow');
var client;
module.exports.mqttInit = function() {
	var mqtt = require('mqtt');
	var mqtturl = 'mqtt://senfdtah:QFMngZSY4SL1@m15.cloudmqtt.com:18724';
	client = mqtt.connect(mqtturl);
	
	client.on('connect', function () {
		console.log('app.js - client connected');
		client.subscribe('light_inst/Street/#');
		
	});
	
	client.on('message', function (topic, message) {
		console.log('app.js - message received');
		handleMessage(topic, message);
	});
};	
var handleMessage = function(topic, message) {
	switch(topic) {
	case 'light_inst/Street':
	  		light_instStreetMessage(message);
	  		break;
	default: unhandledTopic(topic, message);
	}
	};
	var unhandledTopic = function(topic, message) {
		console.log('apibmqttstreet.js - unhandledTopic() - Unhandled Topic: '+topic);
	};
	var light_instStreetMessage = function(message, callback) {
		   message = JSON.parse(message);
		   var model = Arrow.getModel("light_instStreet");
		   var StreetObject = {
		       StreetId : message.StreetId,
		       south : message.south,
		       east : message.east,
		       north : message.north,
		       west : message.west,
		       date : message.date
		   };
		model.create(StreetObject,  function(err, instance){
		       if(err) {
		           console.log("Error creating InterstObject");
		       } else {
		           instance.set(StreetObject);
		       }
		   });
		
	
	};
