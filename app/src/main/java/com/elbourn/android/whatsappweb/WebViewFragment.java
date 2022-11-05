package com.elbourn.android.whatsappweb;

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

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class WebViewFragment extends Fragment {

    String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();
    String URL = "https://web.whatsapp.com";
    String UA = "Mozilla/5.0 (Linux) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";

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
        startWebView();
        Log.i(TAG, "end onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "start fragment onResume");
        startWebView();
        Log.i(TAG, "end fragment onResume");
    }

    public void startWebView() {
        Log.i(TAG, "start startWebView");
        View v = this.getView();
        if (v == null) {
            Log.i(TAG, "v is null");
            return;
        }
        WebView webView = (WebView) v.findViewById(R.id.webview);
        if (webView == null) {
            Log.i(TAG, "webview is null");
            return;
        }
        webView.loadUrl("about:blank");
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setUserAgentString(UA);
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
        Map<String, String> m = new HashMap<String, String>()
        {
            {
                put("https://www.whatsapp.com/", URL);
                put("https://web.whatsapp.com/?post_logout=1", URL);
                put("https://web.whatsapp.com/?post_logout=1&logout_reason=0", URL);
            }
        };
        webView.loadUrl(URL, m);
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