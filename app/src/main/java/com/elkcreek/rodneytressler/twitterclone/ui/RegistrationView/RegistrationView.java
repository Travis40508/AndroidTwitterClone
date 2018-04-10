package com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rodneytressler on 4/10/18.
 */

public interface RegistrationView {
    void toastPasswordsMustMatch();

    void toastFillEveryField();

    void registerUser(String email, String password, FirebaseAuth firebaseAuth);

    void errorRegisteringUserToast();

    void userSuccessfullyRegistered();
}
