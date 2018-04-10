package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import com.elkcreek.rodneytressler.twitterclone.client.FirebaseService;

import javax.inject.Inject;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class MainPresenter {


    private final FirebaseService firebaseService;
    private MainView view;

    @Inject
    public MainPresenter(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    public void onCreate(MainView view) {
        this.view = view;

        if(firebaseService.getCurrentUser() != null) {

        }
    }

    public void registerButtonClicked() {
        view.launchRegistrationFragment();
    }
}
