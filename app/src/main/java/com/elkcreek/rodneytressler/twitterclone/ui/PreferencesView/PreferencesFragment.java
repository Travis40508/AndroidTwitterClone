package com.elkcreek.rodneytressler.twitterclone.ui.PreferencesView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.CheckBoxPreference;

import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.ui.MainView.MainActivity;
import com.elkcreek.rodneytressler.twitterclone.util.PrefUtil;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PreferencesFragment extends PreferenceFragmentCompat implements PreferencesView {

    public static final String KEY_PREF_PUSH_NOTIFICATIONS = "push_notifications_enabled";
    public static final String KEY_LOGOUT = "log_out";
    private CheckBoxPreference checkBoxPreference;
    private Preference logout;
    private PreferencesPresenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        checkBoxPreference = (CheckBoxPreference) getPreferenceManager().findPreference(KEY_PREF_PUSH_NOTIFICATIONS);
        logout = (Preference) getPreferenceManager().findPreference(KEY_LOGOUT);
        setupCheckBox();
        setupLogoutClick();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setupLogoutClick() {
        logout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.onLogoutClicked();
                return false;
            }
        });

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

    @Override
    public void showLogoutDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Log Out")
                .setMessage("Are you sure you want to Log Out?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.logoutConfirmed();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void showLoginScreen() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
