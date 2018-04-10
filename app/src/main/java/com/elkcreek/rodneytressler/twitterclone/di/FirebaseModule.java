package com.elkcreek.rodneytressler.twitterclone.di;


import android.content.Context;

import com.elkcreek.rodneytressler.twitterclone.client.FirebaseService;
import com.elkcreek.rodneytressler.twitterclone.client.FirebaseServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rodneytressler on 4/10/18.
 */

@Module
public class FirebaseModule {

    @Provides
    @Singleton
    FirebaseAuth providesFirebaseAuth() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth;
    }

    @Provides
    @Singleton
    FirebaseUser providesFirebaseUser(FirebaseAuth auth) {
        FirebaseUser user = auth.getCurrentUser();
        return user;
    }

    @Provides
    @Singleton
    FirebaseService providesFirebaseService(FirebaseAuth auth, Context context) {
        return new FirebaseServiceImpl(auth, context);
    }


}
