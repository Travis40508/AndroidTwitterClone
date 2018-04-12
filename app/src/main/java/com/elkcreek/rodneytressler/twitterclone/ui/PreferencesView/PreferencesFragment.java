package com.elkcreek.rodneytressler.twitterclone.ui.PreferencesView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.util.PrefUtil;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PreferencesFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREF_PUSH_NOTIFICATIONS = "push_notifications_enabled";
    public static final String KEY_LOGOUT = "log_out";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }



    public static PreferencesFragment newInstance() {

        Bundle args = new Bundle();

        PreferencesFragment fragment = new PreferencesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(KEY_PREF_PUSH_NOTIFICATIONS)) {
            android.support.v7.preference.Preference pushPreference = findPreference(key);
            PrefUtil.changePushSelection(getContext());
            pushPreference.setEnabled(PrefUtil.getPushNotificationPreference(getContext()));
        }
    }
}
