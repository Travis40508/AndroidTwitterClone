package com.elkcreek.rodneytressler.twitterclone;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.elkcreek.rodneytressler.twitterclone.di.ApplicationComponent;
import com.elkcreek.rodneytressler.twitterclone.di.ApplicationModule;
import com.elkcreek.rodneytressler.twitterclone.di.DaggerApplicationComponent;
import com.elkcreek.rodneytressler.twitterclone.di.FirebaseModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class TwitterCloneApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .firebaseModule(new FirebaseModule())
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }
}
