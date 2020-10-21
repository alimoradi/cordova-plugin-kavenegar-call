/**
 */
package com.android.plugins;

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
import android.util.Log;

import java.util.Date;

public class CordovaPluginKavenegarCall extends CordovaPlugin implements CallEventListener {
  private static final String TAG = "CordovaPluginKavenegarCall";
  KavenegarAudioManager audioManager;
  Call call;
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    KavenegarCall.initialize(cordova.getActivity(), Environment.PRODUCTION);
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing CordovaPluginKavenegarCall");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("initCall")) {
      this.initCall(callbackContext, args.getString(0), args.getString(1));
      return true;
    } else if(action.equals("getDate")) {
      // An example of returning data back to the web layer
      final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
      callbackContext.sendPluginResult(result);
    }
    return true;
  }
  public void initCall(final CallbackContext callbackContext, final String _callId, final String _accessToken )
  {
     try {
          KavenegarCall.getInstance().initCall(_callId, _accessToken, this, (status, call) -> {
            if (status == JoinStatus.SUCCESS) {
                  audioManager.initializeAudioForCall();
                  //callbackContext.success(status);
            } else {
                  //callbackContext.error("failed");
            }                
          });
        } catch (Exception ex) {
            
        }
   
  }
  @Override
    public void onMediaStateChanged(MediaStateChangedEvent event) {
        // Show caller & receptor media state
    }
    @Override
    public void onCallFinished(CallFinishedReason reason) {
        // When Call state changed to finished, this method called with reason of finish
    }

    @Override
    public void onCallStateChanged(CallStatus state,boolean isLocalChange) {
        // isLocalChange : Show's the change is made by you or your contact
        
    }


}
