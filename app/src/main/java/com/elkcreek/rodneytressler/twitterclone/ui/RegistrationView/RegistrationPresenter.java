package com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class RegistrationPresenter {

    private RegistrationView view;

    @Inject
    public RegistrationPresenter() {

    }

    public void onStart(RegistrationView view) {
        this.view = view;
    }

    public void registrationButtonClicked(String email, String password, String confirmPassword) {
        if(!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
            if(password.equals(confirmPassword)) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                view.registerUser(email, password, firebaseAuth);
            } else {
                view.toastPasswordsMustMatch();
            }
        } else {
            view.toastFillEveryField();
        }
    }

    public void userRegistered(FirebaseUser firebaseUser) {
        if(firebaseUser != null) {
            view.userSuccessfullyRegistered();
        } else {
            view.errorRegisteringUserToast();
        }
    }
}
