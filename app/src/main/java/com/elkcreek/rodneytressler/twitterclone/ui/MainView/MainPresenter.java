package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class MainPresenter {


    private MainView view;

    @Inject
    public MainPresenter() {

    }

    public void onCreate(MainView view) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        this.view = view;

        if(firebaseAuth.getCurrentUser() != null) {

        }
    }

    public void registerButtonClicked() {
        view.launchRegistrationFragment();
    }
}
