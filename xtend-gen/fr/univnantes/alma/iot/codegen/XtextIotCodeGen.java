package fr.univnantes.alma.iot.codegen;

import iotModel.Device;
import iotModel.Features;
import iotModel.Instrument;
import iotModel.Interest;
import iotModel.Network;
import java.math.BigInteger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class XtextIotCodeGen {
  public static String generateCode(final Network net) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("var Arrow = require(\'arrow\');");
    _builder.newLine();
    _builder.append("var client;");
    _builder.newLine();
    _builder.append("module.exports.mqttInit = function() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var mqtt = require(\'mqtt\');");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var mqtturl = \'mqtt://");
    String _user = net.getBrokerconf().getUser();
    _builder.append(_user, "\t");
    _builder.append(":");
    String _password = net.getBrokerconf().getPassword();
    _builder.append(_password, "\t");
    _builder.append("@");
    String _server = net.getBrokerconf().getServer();
    _builder.append(_server, "\t");
    _builder.append(":");
    BigInteger _port = net.getBrokerconf().getPort();
    _builder.append(_port, "\t");
    _builder.append("\';");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("client = mqtt.connect(mqtturl);");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("client.on(\'connect\', function () {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("console.log(\'app.js - client connected\');");
    _builder.newLine();
    {
      EList<Device> _device = net.getDevice();
      for(final Device device : _device) {
        {
          EList<Instrument> _instrument = device.getInstrument();
          for(final Instrument instrument : _instrument) {
            {
              EList<Interest> _interet = instrument.getInteret();
              for(final Interest interest : _interet) {
                _builder.append("\t\t");
                _builder.append("client.subscribe(\'");
                String _name = interest.getName();
                _builder.append(_name, "\t\t");
                _builder.append("/server/#\');");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("client.on(\'message\', function (topic, message) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("console.log(\'app.js - message received\');");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("handleMessage(topic, message);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("});");
    _builder.newLine();
    _builder.append("};\t");
    _builder.newLine();
    _builder.append("var handleMessage = function(topic, message) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("switch(topic) {");
    _builder.newLine();
    {
      EList<Device> _device_1 = net.getDevice();
      for(final Device device_1 : _device_1) {
        {
          EList<Instrument> _instrument_1 = device_1.getInstrument();
          for(final Instrument instrument_1 : _instrument_1) {
            {
              EList<Interest> _interet_1 = instrument_1.getInteret();
              for(final Interest interest_1 : _interet_1) {
                _builder.append("\t");
                _builder.append("case \'");
                String _name_1 = interest_1.getName();
                _builder.append(_name_1, "\t");
                _builder.append("/server\':");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("  \t\t");
                _builder.append("process");
                String _firstUpper = StringExtensions.toFirstUpper(interest_1.getName());
                _builder.append(_firstUpper, "\t  \t\t");
                _builder.append("Message(message);");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("  \t\t");
                _builder.append("break;");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    _builder.append("\t");
    _builder.append("default: unhandledTopic(topic, message);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var unhandledTopic = function(topic, message) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("console.log(\'apibmqttstreet.js - unhandledTopic() - Unhandled Topic: \'+topic);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("};");
    _builder.newLine();
    {
      EList<Device> _device_2 = net.getDevice();
      for(final Device device_2 : _device_2) {
        {
          EList<Instrument> _instrument_2 = device_2.getInstrument();
          for(final Instrument instrument_2 : _instrument_2) {
            {
              EList<Interest> _interet_2 = instrument_2.getInteret();
              for(final Interest interest_2 : _interet_2) {
                _builder.append("\t");
                _builder.append("var process");
                String _firstUpper_1 = StringExtensions.toFirstUpper(interest_2.getName());
                _builder.append(_firstUpper_1, "\t");
                _builder.append("Message = function(message, callback) {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t   ");
                _builder.append("message = JSON.parse(message);");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t   ");
                _builder.append("var model = Arrow.getModel(\"");
                String _name_2 = interest_2.getName();
                _builder.append(_name_2, "\t\t   ");
                _builder.append("\");");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t   ");
                _builder.append("var ");
                String _name_3 = interest_2.getName();
                _builder.append(_name_3, "\t\t   ");
                _builder.append("Object = {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t       ");
                String _name_4 = interest_2.getName();
                _builder.append(_name_4, "\t\t       ");
                _builder.append("Id : message.");
                String _name_5 = interest_2.getName();
                _builder.append(_name_5, "\t\t       ");
                _builder.append("Id,");
                _builder.newLineIfNotEmpty();
                {
                  EList<Features> _features = interest_2.getFeatures();
                  for(final Features feature : _features) {
                    _builder.append("\t");
                    _builder.append("\t       ");
                    String _name_6 = feature.getName();
                    _builder.append(_name_6, "\t\t       ");
                    _builder.append(" : message.");
                    String _name_7 = feature.getName();
                    _builder.append(_name_7, "\t\t       ");
                    _builder.append(",");
                    _builder.newLineIfNotEmpty();
                  }
                }
                _builder.append("\t");
                _builder.append("\t       ");
                _builder.append("date : message.date");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t   ");
                _builder.append("};");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.append("model.create(");
                String _name_8 = interest_2.getName();
                _builder.append(_name_8, "\t\t");
                _builder.append("Object,  function(err, instance){");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t       ");
                _builder.append("if(err) {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t           ");
                _builder.append("console.log(\"Error creating InterstObject\");");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t       ");
                _builder.append("} else {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t           ");
                _builder.append("instance.set(");
                String _name_9 = interest_2.getName();
                _builder.append(_name_9, "\t\t           ");
                _builder.append("Object);");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("\t       ");
                _builder.append("}");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t   ");
                _builder.append("});");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("\t");
                _builder.newLine();
                _builder.append("\t");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("};");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    final String code = _builder.toString();
    System.out.println("do dod ");
    return code;
  }
  
  public static String generateModels(final Interest interest) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("var Arrow = require(\'arrow\');");
    _builder.newLine();
    _builder.append("var Model = Arrow.createModel(\'");
    String _name = interest.getName();
    _builder.append(_name);
    _builder.append("\', {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("\"fields\": {");
    _builder.newLine();
    _builder.append("\t \t ");
    _builder.append("\"");
    String _name_1 = interest.getName();
    _builder.append(_name_1, "\t \t ");
    _builder.append("Id\": {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t \t            ");
    _builder.append("\"type\": \"string\"  ");
    _builder.newLine();
    _builder.append("\t \t        ");
    _builder.append("},");
    _builder.newLine();
    {
      EList<Features> _features = interest.getFeatures();
      for(final Features feature : _features) {
        _builder.append("\t \t   ");
        _builder.append("\"");
        String _name_2 = feature.getName();
        _builder.append(_name_2, "\t \t   ");
        _builder.append("\" :{");
        _builder.newLineIfNotEmpty();
        _builder.append("\t \t   ");
        _builder.append("            ");
        _builder.append("\"type\": \"");
        String _name_3 = feature.getMeasure().getType().getName();
        _builder.append(_name_3, "\t \t               ");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
        _builder.append("\t \t   ");
        _builder.append("},");
        _builder.newLine();
      }
    }
    _builder.append("\t \t   ");
    _builder.append("\"date\": {");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"type\": \"string\",");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"description\": \"trafic light date\"");
    _builder.newLine();
    _builder.append("\t \t           ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t \t       ");
    _builder.append("},");
    _builder.newLine();
    _builder.append("\t \t        ");
    _builder.append("\"connector\": \"appc.arrowdb\",");
    _builder.newLine();
    _builder.append("\t \t        ");
    _builder.append("\"actions\": [");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"create\",");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"read\",");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"update\",");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"delete\",");
    _builder.newLine();
    _builder.append("\t \t               ");
    _builder.append("\"deleteAll\"");
    _builder.newLine();
    _builder.append("\t \t           ");
    _builder.append("],");
    _builder.newLine();
    _builder.append("\t \t           ");
    _builder.append("\"description\": \"bla bla bla\"");
    _builder.newLine();
    _builder.append("});");
    _builder.newLine();
    _builder.append("module.exports = Model;");
    _builder.newLine();
    final String code = _builder.toString();
    return code;
  }
}
