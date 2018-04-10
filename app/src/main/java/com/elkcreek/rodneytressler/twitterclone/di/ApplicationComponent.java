package com.elkcreek.rodneytressler.twitterclone.di;

import com.elkcreek.rodneytressler.twitterclone.TwitterCloneApplication;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by rodneytressler on 4/10/18.
 */

@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, ActivitiesModule.class})
public interface ApplicationComponent {


    void inject(TwitterCloneApplication application);
}
