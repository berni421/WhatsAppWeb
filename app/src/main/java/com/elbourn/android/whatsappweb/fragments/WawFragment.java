package com.elbourn.android.whatsappweb.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elbourn.android.whatsappweb.BuildConfig;
import com.elbourn.android.whatsappweb.R;
import com.elbourn.android.whatsappweb.Webview.Waw;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class WawFragment extends Fragment {
    String APP = BuildConfig.APPLICATION_ID;
    String TAG = getClass().getSimpleName();

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
        new Waw(getActivity(), getContext(), view.findViewById(R.id.webview));
        Log.i(TAG, "end onViewCreated");
    }
}