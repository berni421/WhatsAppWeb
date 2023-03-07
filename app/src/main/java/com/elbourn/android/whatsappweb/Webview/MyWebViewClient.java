package com.elbourn.android.whatsappweb.Webview;


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
    Context context;

    public MyWebViewClient(Context context) {
        Log.i(TAG, "start MyWebViewClient");
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        Log.i(TAG, "start shouldOverrideUrlLoading");
        String requestUrl = request.getUrl().toString();
        Log.i(TAG, "requestUrl: " + requestUrl);
        if (requestUrl.startsWith("https://www.whatsapp.com/")) {
            // at this point Whatsapp connection has failed
            String msg = "Facebook may have disconnected this device. Try back button. Use menu Reset or Logout and reconnect.";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return false;
        } else if (requestUrl.startsWith(Waw.URL)) {
            return super.shouldOverrideUrlLoading(view, request);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
            startActivity(context, intent, null);
            String msg = "Starting application for the link ...";
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
