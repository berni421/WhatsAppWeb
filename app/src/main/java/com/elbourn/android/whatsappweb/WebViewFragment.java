package com.elbourn.android.whatsappweb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.fragment.app.Fragment;

public class WebViewFragment extends Fragment {

    String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();
    String URL = "https://web.whatsapp.com";
    String UA = "Mozilla/5.0 (Linux 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "start onViewCreated");
        setupWebView();
        Log.i(TAG, "end onViewCreated");
    }

    public WebView getWebView() {
        View view = this.getView();
        if (view == null) {
            Log.i(TAG, "view is null");
            return null;
        }
        WebView webview = (WebView) view.findViewById(R.id.webview);
        if (webview == null) {
            Log.i(TAG, "webview is null");
            return null;
        }
        return webview;
    }

    public void loadWebView() {
        Log.i(TAG, "start resumeWebView");
        WebView webview = getWebView();
        if (webview == null) {
            Log.i(TAG, "webview is null");
            return;
        }
        webview.loadUrl("about:blank");
        webview.loadUrl(URL);
        Log.i(TAG, "end resumeWebView");
    }

    public void setupWebView() {
        Log.i(TAG, "start startWebView");
        WebView webview = getWebView();
        if (webview == null ) {
            Log.i(TAG, "webview is null");
            return;
        }
        webview.setWebViewClient(new MyWebViewClient());
        webview.getSettings().setUserAgentString(UA);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setGeolocationEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setNeedInitialFocus(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setBlockNetworkImage(false);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().supportZoom();
        webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        loadWebView();
        Log.i(TAG, "end startWebView");
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.i(TAG, "start shouldOverrideUrlLoading");
            String requestUrl = request.getUrl().toString();
            Log.i(TAG, "requestUrl: " + requestUrl);
            if (requestUrl.equals("https://www.whatsapp.com/")) {
                // at this point Whatsapp connection has failed
                String msg = "Facebook seems to have disconnected this device. Use menu Reset to reconnect.";
                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                return false;
            }
            Intent i = new Intent(Intent.ACTION_VIEW, request.getUrl());
            startActivity(i);
            String msg = "Starting application for the link ...";
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}