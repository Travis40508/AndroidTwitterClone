package com.elkcreek.rodneytressler.twitterclone.client;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseAuth mAuth;
    private final Context mContext;
    private FirebaseUser firebaseUser;

    public FirebaseServiceImpl(FirebaseAuth firebaseAuth, Context context) {
        this.mAuth = firebaseAuth;
        this.mContext = context;
    }

    @Override
    public FirebaseUser signUpUserWithEmailAndPassword(String email, String password, Activity activity) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            firebaseUser = mAuth.getCurrentUser();
                        }
                    }
                });

        return firebaseUser;
    }

    @Override
    public FirebaseUser signInUserwithEmailAndPassword(String email, String password, Activity activity) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        firebaseUser = mAuth.getCurrentUser();
                    }
                });
        return firebaseUser;
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }


}
