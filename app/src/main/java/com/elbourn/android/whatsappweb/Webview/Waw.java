package com.elbourn.android.whatsappweb.Webview;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.elbourn.android.whatsappweb.BuildConfig;

public class Waw {
    String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();
    public static String URL = "https://web.whatsapp.com/";
    String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";

    public Waw(Activity activity,
               Context context,
               WebView webview) {
        Log.i(TAG, "start setupWebView");
        if (activity == null ) {
            Log.i(TAG, "activity is null");
            return;
        }
        if (context == null ) {
            Log.i(TAG, "context is null");
            return;
        }
        if (webview == null ) {
            Log.i(TAG, "webview is null");
            return;
        }

        // Intercept web requests
        webview.setWebChromeClient(new MyWebChromeClient(activity));
        webview.setWebViewClient(new MyWebViewClient(context));

        // For the web site
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);

        // for camera
        webview.getSettings().setAllowContentAccess(true);

        // for audio
        webview.getSettings().setMediaPlaybackRequiresUserGesture(false);

        //for html5 app
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        // for layout
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);

        // Zoom
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        // Scroll

        webview.setHorizontalScrollBarEnabled(true);

        // Javascript
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setSaveFormData(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setBlockNetworkImage(false);
        webview.getSettings().setBlockNetworkLoads(false);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setNeedInitialFocus(false);
        webview.getSettings().setGeolocationEnabled(true);

        // Scrollbar
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(true);

        webview.loadUrl(URL);
        webview.getSettings().setUserAgentString(UA);
        Log.i(TAG, "end setupWebView");
    }
}
