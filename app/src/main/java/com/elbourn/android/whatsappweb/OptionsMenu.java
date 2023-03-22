package com.elbourn.android.whatsappweb;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.elbourn.android.whatsappweb.Webview.MyWebChromeClient;
import com.elbourn.android.whatsappweb.fragments.IntroFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class OptionsMenu extends AppCompatActivity {

    private String TAG = "OptionsMenu";
    static String APP = "com.elbourn.android.whatsappweb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "start onCreateOptionsMenu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        Context context = getApplicationContext();
        MenuItem introCheckBox = menu.findItem(R.id.menuIntroOff);
        introCheckBox.setChecked(IntroFragment.getIntroCheckBox(context));
        Log.i(TAG, "end onCreateOptionsMenu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuKeyboardOff:
                keyboardOff();
                return true;
            case R.id.menuKeyboardOn:
                keyboardOn();
                return true;
            case R.id.menuIntroOff:
                setIntroductionOff(item);
                return true;
            case R.id.menuDonate:
                startDonationWebsite();
                return true;
            case R.id.menuReload:
                reloadApp();
                return true;
            case R.id.menuLogout:
                logout();
                return true;
            case R.id.menuReset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void keyboardOff() {
        // turn off keyboard
        ViewGroup container = findViewById(R.id.layout);
        if (container == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        container.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        WebView webview = container.findViewById(R.id.webview);
        if (webview == null) return;
        webview.getRootView().requestFocus();
        if (getCurrentFocus() == null) return;
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        Log.i(TAG, "keyboard off");
    }

    public void keyboardOn() {
        // turn on keyboard
        ViewGroup container = findViewById(R.id.layout);
        if (container == null) return;
        container.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        Log.i(TAG, "keyboard on");
    }


    public Fragment getForegroundFragment() {
        FragmentManager childFM = getFM();
        if (childFM == null) {
            Log.i(TAG, "childFM is null");
            return null;
        }
        Fragment current = childFM
                .getFragments()
                .get(0);
        if (current == null) {
            Log.i(TAG, "current is null");
        }
        return current;
    }

    FragmentManager getFM() {
        Fragment navHostFragment = getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment == null) {
            Log.i(TAG, "navHostFragment is null");
            return null;
        }
        return navHostFragment.getChildFragmentManager();
    }

    void setIntroductionOff(MenuItem item) {
        Context context = getApplicationContext();
        boolean subscriptionsIntroOff = !item.isChecked();
        item.setChecked(subscriptionsIntroOff);
        IntroFragment.setIntroCheckBox(context, subscriptionsIntroOff);
//        reloadApp();
        Log.i(TAG, "subscriptionsIntroOff: " + subscriptionsIntroOff);
    }

    void startDonationWebsite() {
        Log.i(TAG, "start startDonationWebsite");
        Context context = getApplicationContext();
        runOnUiThread(new Runnable() {
            public void run() {
                String msg = "Starting browser to feed the cat ...";
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                String url = "https://www.elbourn.com/feed-the-cat/";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }});
        Log.i(TAG, "end startDonationWebsite");
    }

    void logout() {
        Log.i(TAG, "start logout");
        getApplicationContext()
                .getSharedPreferences(APP, MODE_PRIVATE)
                .edit()
                .putBoolean("logout", true)
                .apply();
        String msg = "Logout requested...";
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                reloadApp();
            }});
        Log.i(TAG, "end logout");
    }

    void stopApp() {
        Log.i(TAG, "start stopApp");
        finishAffinity();
        Log.i(TAG, "end stopApp");
    }

    void reloadApp() {
        Log.i(TAG, "start reloadApp");
        runOnUiThread(new Runnable() {
            public void run() {
                String msg = "Restarting...";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
            }});
        Log.i(TAG, "end reloadApp");
    }

    void reset() {
        Log.i(TAG, "start reset");
        ((ActivityManager) getApplicationContext()
                .getSystemService(ACTIVITY_SERVICE))
                .clearApplicationUserData();
        // never reached
        Log.i(TAG, "end reset");
    }
}
