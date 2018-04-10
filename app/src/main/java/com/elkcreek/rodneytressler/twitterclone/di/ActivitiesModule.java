package com.elkcreek.rodneytressler.twitterclone.di;

import com.elkcreek.rodneytressler.twitterclone.ui.MainView.MainActivity;
import com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView.RegistrationFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rodneytressler on 4/10/18.
 */

@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector
    abstract RegistrationFragment contributesRegistrationFragmentInjector();
}
