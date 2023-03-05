package com.elbourn.android.whatsappweb.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.elbourn.android.whatsappweb.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static android.content.Context.MODE_PRIVATE;

public class IntroFragment extends Fragment {

    static String APP = "com.elbourn.android.whatsappweb";
    static String TAG = "IntroFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false);
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
        if (getIntroCheckBox(context)) {
            startWebviewFragment();
        } else {
            setupButtons(view);
        }
        Log.i(TAG, "end onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = this.getView();
        if (view != null) {
            setupButtons(view);
        }
    }

    void setupButtons(View view) {
        Log.i(TAG, "start setupButtons");
        ImageButton introImageButton = view.findViewById(R.id.introImageButton);
        introImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "introImageButton clicked");
                startWebviewFragment();
            }
        });
        CheckBox introCheckBox = view.findViewById(R.id.introCheckBox);
        introCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "introCheckBox clicked");
                CheckBox checkBox = (CheckBox) v;
                Context context = getContext();
                if (context == null) {
                    Log.i(TAG, "context is null");
                    return;
                }
                setIntroCheckBox(context, checkBox.isChecked());
            }
        });
        Log.i(TAG, "start setupButtons");
    }

    static public boolean getIntroCheckBox(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        Boolean introCheckBox = sharedPreferences.getBoolean("introCheckBox", false);
        Log.i(TAG, "introCheckBox: " + introCheckBox);
        return introCheckBox;
    }

    static public void setIntroCheckBox(Context context, Boolean checkBox) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP, MODE_PRIVATE);
        if (checkBox) {
            sharedPreferences.edit().putBoolean("introCheckBox", true).apply();
        } else {
            sharedPreferences.edit().putBoolean("introCheckBox", false).apply();
        }
    }

    void startWebviewFragment() {
        Log.i(TAG, "start startWebviewFragment");
        NavHostFragment.findNavController(this).navigate(R.id.action_introFragment_to_webviewFragment);
        Log.i(TAG, "end startWebviewFragment");
    }
}