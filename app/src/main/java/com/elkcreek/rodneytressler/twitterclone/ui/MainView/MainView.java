package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rodneytressler on 4/10/18.
 */

public interface MainView {
    void launchRegistrationFragment();

    void logUserIn(String email, String password, FirebaseAuth firebaseAuth);

    void toastLoginFailed();

    void launchPostsActivity();

    void showProgressBar();

    void hideProgressBar();
}
