package com.elbourn.android.whatsappweb.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.elbourn.android.whatsappweb.BuildConfig;
import com.elbourn.android.whatsappweb.R;
import com.elbourn.android.whatsappweb.Webview.Waw;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class WawFragment extends Fragment {
    String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();
    WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "start onViewCreated");
        wv = view.findViewById(R.id.webview);
        new Waw(getActivity(), getContext(), wv);
        Log.i(TAG, "end onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        wv.onResume();
        Log.i(TAG, "start onResume");
        Log.i(TAG, "end onResume");
    }
}