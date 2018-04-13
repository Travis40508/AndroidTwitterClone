package com.elkcreek.rodneytressler.twitterclone.ui.PreferencesView;

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
}
