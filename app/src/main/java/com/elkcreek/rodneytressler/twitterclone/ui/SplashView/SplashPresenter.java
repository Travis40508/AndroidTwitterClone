package com.elkcreek.rodneytressler.twitterclone.ui.SplashView;

import android.os.CountDownTimer;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class SplashPresenter {
    private SplashView view;

    public void onCreate(SplashView view) {
        this.view = view;

        CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                checkUser();
            }
        }.start();
    }

    private void checkUser() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            view.launchPostsActivity();
        } else {
            view.launchMainActivity();
        }
    }
}
