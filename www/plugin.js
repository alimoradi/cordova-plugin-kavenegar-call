
var exec = require('cordova/exec');

var PLUGIN_NAME = 'CordovaPluginKavenegarCall';

var CordovaPluginKavenegarCall = {
  initCall: function(callId, accessToken, cb)
  {
    exec(cb, null, PLUGIN_NAME, 'initCall', [callId, accessToken]);
  },
  onStateChanged: function(cb)
  {
    exec(cb, null, PLUGIN_NAME, 'onStateChanged', []);
  }
};

module.exports = CordovaPluginKavenegarCall;
