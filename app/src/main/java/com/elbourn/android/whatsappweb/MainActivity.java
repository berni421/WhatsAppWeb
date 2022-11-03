package com.elbourn.android.whatsappweb;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebStorage;

import java.io.File;

import kotlin.io.FilesKt;

public class MainActivity extends OptionsMenu {

    static String APP = "com.elbourn.android.whatsappweb";
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        // Check if logout needed
        boolean logout = getApplicationContext()
                .getSharedPreferences(APP, MODE_PRIVATE)
                .getBoolean("logout", false);
        if (!logout) {
            Log.i(TAG, "logout not requested");
            return;
        }
        Log.i(TAG, "Logout Requested");
        clearApplicationData();
        getApplicationContext()
                .getSharedPreferences(APP, MODE_PRIVATE)
                .edit()
                .putBoolean("logout", false)
                .apply();
        Log.i(TAG, "end onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        // start main screen
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    void clearApplicationData() {
        deleteHere(getFilesDir().getParent());
    }

    void deleteHere(String folder) {
        Log.i(TAG, "start deleteHere");
        File here = new File(folder);
        if (here.exists()) {
            String[] fileNames = here.list();
            for (String fileName : fileNames) {
                if (fileName.equals("shared_prefs")) {
                    deleteFile(new File(here, "shared_prefs/WebViewChromiumPrefs.xml"));
                } else {
                    deleteFile(new File(here, fileName));
                }
            }
        }
        String[] verifyNames = here.list();
        for (String fileName : verifyNames) {
            Log.i(TAG, "Verify file: " + fileName);
        }
        Log.i(TAG, "end deleteHere");
    }

    void deleteFile(File file) {
        Log.i(TAG, "deleteFile: " + file);
        if (file != null) {
            if (file.isDirectory()) {
                FilesKt.deleteRecursively(file);
            } else {
                file.delete();
            }
        }
    }
}