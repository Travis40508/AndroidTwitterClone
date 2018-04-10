package com.elkcreek.rodneytressler.twitterclone.client;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by rodneytressler on 4/10/18.
 */

public interface FirebaseService {

    FirebaseUser signUpUserWithEmailAndPassword(String email, String password, Activity activity);

    FirebaseUser signInUserwithEmailAndPassword(String email, String password, Activity activity);

    FirebaseUser getCurrentUser();
}
