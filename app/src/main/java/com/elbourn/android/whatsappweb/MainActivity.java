package com.elbourn.android.whatsappweb;

import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import kotlin.io.FilesKt;

public class MainActivity extends OptionsMenu {

    static String APP = "com.elbourn.android.whatsappweb";
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "start onCreate");
        // Check if logout needed
        boolean logout = getApplicationContext()
                .getSharedPreferences(APP, MODE_PRIVATE)
                .getBoolean("logout", false);
        if (logout) {
            Log.i(TAG, "Logout Requested");
            clearApplicationData();
            getApplicationContext()
                    .getSharedPreferences(APP, MODE_PRIVATE)
                    .edit()
                    .putBoolean("logout", false)
                    .apply();
        }
        setContentView(R.layout.activity_main);
        Log.i(TAG, "end onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "start onResume");
        Log.i(TAG, "end onResume");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "start onBackPressed");
        if (getForegroundFragment().getClass() == IntroFragment.class) {
            Log.i(TAG, "IntroFragment exit");
            finishAffinity();
        }
        Log.i(TAG, "end onBackPressed");
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