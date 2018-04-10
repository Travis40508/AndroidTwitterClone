package com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView;

/**
 * Created by rodneytressler on 4/10/18.
 */

public interface RegistrationView {
    void toastPasswordsMustMatch();

    void toastFillEveryField();

    void registerUser(String email, String password);

    void errorRegisteringUserToast();

    void userSuccessfullyRegistered();
}
