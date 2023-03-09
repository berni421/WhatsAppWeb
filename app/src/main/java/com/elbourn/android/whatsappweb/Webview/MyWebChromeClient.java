package com.elbourn.android.whatsappweb.Webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.elbourn.android.whatsappweb.BuildConfig;

import androidx.annotation.NonNull;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MyWebChromeClient extends WebChromeClient {
    String APP = BuildConfig.APPLICATION_ID;
    static String TAG = MyWebChromeClient.class.getSimpleName();
    Activity activity;

    public MyWebChromeClient(Activity activity) {
        Log.i(TAG, "start MyWebChromeClient");
        this.activity = activity;
    }

    static ValueCallback<Uri[]> filePathValueCallback;
    @Override
    public boolean onShowFileChooser(WebView webview, ValueCallback<Uri[]> filePathCallback, @NonNull FileChooserParams fileChooserParams) {
        Log.i(TAG, "start onShowFileChooser");
        // Start file chooser
        filePathValueCallback = filePathCallback;
        Intent chooserIntent = fileChooserParams.createIntent();
        startActivityForResult(activity, chooserIntent, 142, null);
        return true;
    }

    public static void handleFileChooserResult(Intent data) {
        Log.i(TAG, "start handleResult");
        if (data == null) return;
        Uri result = data.getData();
        if (result == null) return;
        Uri[] results = new Uri[1];
        results[0] = result;
        filePathValueCallback.onReceiveValue(results);
    }

    @Override
    public void onPermissionRequest(final PermissionRequest request) {
        Log.i(TAG, "start onPermissionRequest");
    }
}
