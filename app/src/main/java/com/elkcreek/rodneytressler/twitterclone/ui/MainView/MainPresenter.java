package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class MainPresenter {


    private MainView view;
    private FirebaseAuth firebaseAuth;

    @Inject
    public MainPresenter() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void onCreate(MainView view) {
        this.view = view;

    }


    public void registerButtonClicked() {
        view.launchRegistrationFragment();
    }

    public void loginButtonClicked(String email, String password) {
        view.logUserIn(email, password, firebaseAuth);
    }

    public void loginSuccessful(FirebaseUser currentUser) {
        if(currentUser != null) {
            view.launchPostsActivity();
        }
    }

    public void loginFailed() {
        view.toastLoginFailed();
    }
}
