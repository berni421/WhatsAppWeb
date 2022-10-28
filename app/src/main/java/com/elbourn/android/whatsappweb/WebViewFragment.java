package com.elbourn.android.whatsappweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class WebViewFragment extends Fragment {

    static String APP = BuildConfig.APPLICATION_ID;
    static String TAG = "WebViewFragment";
    View view = null;
    WebView webView = null;
    String url = "https://web.whatsapp.com/";
    String ua = "Mozilla/5.0 (ECS Creators 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_webview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "start onViewCreated");
        startWebView(view);
        Log.i(TAG, "end onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        if (webView != null) {
            webView.loadUrl(url);
        }
        Log.i(TAG, "end onResume");
    }

    public void startWebView(View view) {
        Log.i(TAG, "start startWebView");
        WebView webView = (WebView) view.findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setUserAgentString(ua);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().supportZoom();
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.loadUrl(url);
        Log.i(TAG, "end startWebView");
    }

    public final class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.i(TAG, "start shouldOverrideUrlLoading");
            Context context = getContext();
            String requestUrl = request.getUrl().toString();
            Log.i(TAG, "requestUrl: " + requestUrl);
            Intent i = new Intent(Intent.ACTION_VIEW, request.getUrl());
            startActivity(i);
            String msg = "Starting application for the link ...";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return true;
        }

    }
}