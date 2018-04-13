package com.elkcreek.rodneytressler.twitterclone.ui.PreferencesView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.util.PrefUtil;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PreferencesFragment extends PreferenceFragmentCompat implements PreferencesView {

    public static final String KEY_PREF_PUSH_NOTIFICATIONS = "push_notifications_enabled";
    public static final String KEY_LOGOUT = "log_out";
    private CheckBoxPreference checkBoxPreference;
    private PreferencesPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        checkBoxPreference = (CheckBoxPreference) getPreferenceManager().findPreference(KEY_PREF_PUSH_NOTIFICATIONS);
        setupCheckBox();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new PreferencesPresenter();
        presenter.onStart(this);
    }

    private void setupCheckBox() {
        checkBoxPreference.setChecked(PrefUtil.getPushNotificationPreference(getContext()));
        checkBoxPreference.setOnPreferenceChangeListener(new android.support.v7.preference.Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(android.support.v7.preference.Preference preference, Object newValue) {
                presenter.preferenceChanged();
                return false;
            }
        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }



    @Override
    public void changePushPreference() {
        PrefUtil.changePushSelection(getContext());
        checkBoxPreference.setChecked(PrefUtil.getPushNotificationPreference(getContext()));
    }
}
