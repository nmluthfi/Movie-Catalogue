package com.android.example.moviewcatalogue.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.android.example.moviewcatalogue.R;
import com.android.example.moviewcatalogue.receivers.DailyReceiver;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static String keyReleaseReminder, keyDailyReminder;

    private SwitchPreference spReleaseReminder, spDailyReminder;

    private DailyReceiver dailyReceiver = new DailyReceiver();

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preference_user);
        init();
        setSummaries();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equalsIgnoreCase(keyDailyReminder)) {
            boolean isDailryReminderChecked = sharedPreferences.getBoolean(keyDailyReminder, false);
            if (isDailryReminderChecked) {
                dailyReceiver.setRepeatingAlarm(getActivity());
            } else {
                dailyReceiver.cancelAlarm(getActivity());
            }
        }

        if (key.equalsIgnoreCase(keyReleaseReminder)) {
            spReleaseReminder.setChecked(sharedPreferences.getBoolean(keyReleaseReminder, false));
        }
    }

    private void setSummaries() {
        SharedPreferences sh = getPreferenceManager().getSharedPreferences();
        boolean isDailryReminderChecked = sh.getBoolean(keyDailyReminder, false);
        if (isDailryReminderChecked) {
            dailyReceiver.setRepeatingAlarm(getActivity());
        } else {
            dailyReceiver.cancelAlarm(getActivity());
        }

        spReleaseReminder.setChecked(sh.getBoolean(keyReleaseReminder, false));
    }

    private void init() {
        keyDailyReminder = getResources().getString(R.string.key_daily_reminder);
        keyReleaseReminder = getResources().getString(R.string.key_release_reminder);

        spReleaseReminder = (SwitchPreference) findPreference(keyReleaseReminder);
        spDailyReminder = (SwitchPreference) findPreference(keyDailyReminder);
    }
}