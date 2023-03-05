package com.elbourn.android.whatsappweb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.elbourn.android.whatsappweb.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static android.content.Context.MODE_PRIVATE;

public class DisclaimerFragment extends Fragment {

    static String APP = "com.elbourn.android.whatsappweb";
    static String TAG = "DisclaimerFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disclaimer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "start onViewCreated");
        Context context = getContext();
        if (context == null) {
            Log.i(TAG, "context is null");
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        boolean disclaimerCheckBox = sharedPreferences.getBoolean("disclaimerCheckBox", false);
        Log.i(TAG, "disclaimerCheckBox: " + disclaimerCheckBox);
        if (disclaimerCheckBox) {
            startIntroFragment();
        } else {
            setupButtons(view);
        }
        Log.i(TAG, "end onViewCreated");
    }

    void setupButtons(View view) {
        CheckBox disclaimerAgreedBox = view.findViewById(R.id.disclaimerCheckBox);
        disclaimerAgreedBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "disclaimerAgreedBox clicked");
                CheckBox checkBox = (CheckBox) v;
                Context context = getContext();
                if (context == null) {
                    Log.i(TAG, "context is null");
                    return;
                }
                SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
                if (checkBox.isChecked()) {
                    sharedPreferences.edit().putBoolean("disclaimerCheckBox", true).apply();
                    startIntroFragment();
                }
                Log.i(TAG, "disclaimerAgreedBox: " + disclaimerAgreedBox);
            }
        });
    }

    void startIntroFragment() {
        Log.i(TAG, "start startIntroFragment");
        NavHostFragment.findNavController(this).navigate(R.id.action_disclaimerFragment_to_introFragment);
        Log.i(TAG, "end startIntroFragment");
    }
}