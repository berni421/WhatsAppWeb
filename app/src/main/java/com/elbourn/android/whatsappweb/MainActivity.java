package com.elbourn.android.whatsappweb;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.Toast;

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
            WebStorage.getInstance().deleteAllData();
            clearApplicationData();
            getApplicationContext()
                    .getSharedPreferences(APP, MODE_PRIVATE)
                    .edit()
                    .putBoolean("logout", false)
                    .apply();
            reloadApp();
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
        if (getForegroundFragment().getClass() == WebViewFragment.class &&
            IntroFragment.getIntroCheckBox(getApplicationContext())) {
            Log.i(TAG, "WebViewFragment exit");
            finishAffinity();
        }
        Log.i(TAG, "end onBackPressed");
    }

    void clearApplicationData() {
        Log.i(TAG, "start clearApplicationData");
        deleteHere(getFilesDir().getParent());
        Log.i(TAG, "end clearApplicationData");
    }

    void deleteHere(String folder) {
        Log.i(TAG, "start deleteHere");
        File here = new File(folder);
        if (here.exists()) {
            String[] fileNames = here.list();
            for (String fileName : fileNames) {
                String sp = "shared_prefs";
                if (fileName.equals(sp)) {
                    pruneSharedPrefs(folder + File.separator + sp);
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

    void pruneSharedPrefs(String spF) {
        Log.i(TAG, "start pruneSharedPrefs");
        File spHere = new File(spF);
        if (!spHere.exists()) {
            Log.i(TAG, "spHere is empty");
            return;
        }
        String[] spfNames = spHere.list();
        for (String spName : spfNames) {
            if (!spName.equals(APP + ".xml")) {
                deleteFile(new File(spHere, spName));
            }
        }
        Log.i(TAG, "end pruneSharedPrefs");
    }

    void deleteFile(File file) {
        Log.i(TAG, "deleteFile: " + file);
        if (file != null) {
            if (file.isDirectory()) {
                String[] filenames = file.list();
                for (String filename : filenames) {
                    deleteFile(new File(file, filename));
                }
            }
            if (!file.delete()) {
                String df = file.getName() + ".duff";
                Log.i(TAG, "renamed: " + df);
                file.renameTo(new File(df));
            }
        }
    }
}