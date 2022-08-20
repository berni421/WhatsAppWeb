package com.elbourn.android.whatsappweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    WebView webView;
    String Url = "https://web.whatsapp.com";
    String ua = "Mozilla/5.0 (Linux; Win64; x64; rv:46.0) Gecko/20100101 Firefox/68.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        runOnUiThread(new Runnable() {
            public void run() {
                String msg = "Loading .. please wait.";
                Log.i(TAG, "msg: " + msg);
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

            }
        });
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(Url);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUserAgentString(ua);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setNeedInitialFocus(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //webView.getSettings().setBlockNetworkLoads(true);
        webView.getSettings().setBlockNetworkImage(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(100);

        Log.i(TAG, "end onCreate");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        webView.loadUrl(Url);
        Log.i(TAG, "end onResume");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}