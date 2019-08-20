package com.android.example.moviewcatalogue.preferences;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.android.example.moviewcatalogue.R;

public class MyPreferenceActivity extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preference_user);
    }
}
