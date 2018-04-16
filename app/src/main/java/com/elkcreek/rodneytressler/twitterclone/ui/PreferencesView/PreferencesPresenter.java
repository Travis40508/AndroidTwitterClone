package com.elkcreek.rodneytressler.twitterclone.ui.PreferencesView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PreferencesPresenter {
    private PreferencesView view;

    public void onStart(PreferencesView view) {
        this.view = view;
    }

    public void preferenceChanged() {
        view.changePushPreference();
    }

    public void onLogoutClicked() {
        view.showLogoutDialog();
    }

    public void logoutConfirmed() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        view.showLoginScreen();
    }
}
