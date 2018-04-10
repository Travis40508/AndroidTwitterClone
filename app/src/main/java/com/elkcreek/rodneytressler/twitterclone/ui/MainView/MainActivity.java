package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.client.FirebaseService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    FirebaseService firebaseService;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenter.onCreate(this);
    }
}
