package com.elkcreek.rodneytressler.twitterclone.ui.PreferencesView;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.elkcreek.rodneytressler.twitterclone.R;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PreferencesFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    public static PreferencesFragment newInstance() {

        Bundle args = new Bundle();

        PreferencesFragment fragment = new PreferencesFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
