package com.elbourn.android.whatsappweb.Webview;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.elbourn.android.whatsappweb.BuildConfig;

import static androidx.core.content.ContextCompat.startActivity;

public class MyWebViewClient extends WebViewClient {
    String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();
    Context activity;

    public MyWebViewClient(Activity activity) {
        Log.i(TAG, "start MyWebViewClient");
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.i(TAG, "start shouldOverrideUrlLoading");
        String requestUrl = request.getUrl().toString();
        Log.i(TAG, "requestUrl: " + requestUrl);
        if (requestUrl.startsWith("https://www.whatsapp.com/")) {
            // at this point Whatsapp connection has failed
            String msg = "Facebook seems to have disconnected this device. Use menu Reset or Logout and reconnect.";
            Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            return false;
        } else if (requestUrl.startsWith(Waw.URL)) {
            return super.shouldOverrideUrlLoading(view, request);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
            startActivity(activity.getApplicationContext(), intent, null);
            String msg = "Starting application for the link ...";
            Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
