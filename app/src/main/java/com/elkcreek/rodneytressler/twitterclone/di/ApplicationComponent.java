package com.elkcreek.rodneytressler.twitterclone.di;

import com.elkcreek.rodneytressler.twitterclone.TwitterCloneApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by rodneytressler on 4/10/18.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, ActivitiesModule.class, FirebaseModule.class})
public interface ApplicationComponent {


    void inject(TwitterCloneApplication application);
}
