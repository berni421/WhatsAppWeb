package com.elbourn.android.whatsappweb.Webview;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebChromeClient;

import com.elbourn.android.whatsappweb.BuildConfig;

public class MyWebChromeClient extends WebChromeClient {
   String APP = BuildConfig.APPLICATION_ID;
   String TAG = getClass().getSimpleName();
   Activity activity;

   public MyWebChromeClient(Activity activity) {
      Log.i(TAG, "start MyWebChromeClient");
      this.activity = activity;
   }
}
