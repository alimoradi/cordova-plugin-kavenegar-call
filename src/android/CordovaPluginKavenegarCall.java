/**
 */
package com.example;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import io.kavenegar.sdk.call.enums.Environment;
import io.kavenegar.sdk.call.Call;
import io.kavenegar.sdk.call.CallEventListener;
import io.kavenegar.sdk.call.KavenegarCall;
import io.kavenegar.sdk.call.audio.KavenegarAudioManager;
import io.kavenegar.sdk.call.core.KavenegarException;
import io.kavenegar.sdk.call.enums.CallDirection;
import io.kavenegar.sdk.call.enums.CallFinishedReason;
import io.kavenegar.sdk.call.enums.CallStatus;
import io.kavenegar.sdk.call.enums.JoinStatus;
import io.kavenegar.sdk.call.enums.MediaState;
import io.kavenegar.sdk.call.enums.MessagingState;
import io.kavenegar.sdk.call.log.DefaultLogger;
import io.kavenegar.sdk.call.log.Logger;
import io.kavenegar.sdk.call.messaging.MediaStateChangedEvent;
import io.kavenegar.sdk.call.messaging.MessagingStateChangedEvent;
import io.kavenegar.sdk.call.webrtc.models.LocalMediaStateChangedEvent;
import android.util.Log;

import java.util.Date;

public class CordovaPluginKavenegarCall extends CordovaPlugin {
  private static final String TAG = "CordovaPluginKavenegarCall";
  KavenegarAudioManager audioManager;
  Call call;
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    KavenegarCall.initialize(this, Environment.PRODUCTION);
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing CordovaPluginKavenegarCall");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("echo")) {
      String phrase = args.getString(0);
      // Echo back the first argument
      Log.d(TAG, phrase);
    } else if(action.equals("getDate")) {
      // An example of returning data back to the web layer
      final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
      callbackContext.sendPluginResult(result);
    }
    return true;
  }
  public initCall(String _accessToken, String _callId)
  {
    KavenegarCall.getInstance().initCall(callId, accessToken, new DefaultLogger(), this, this::handleJoinResult);
  }
  private void handleJoinResult(JoinStatus status, Call call) {
  }


}
