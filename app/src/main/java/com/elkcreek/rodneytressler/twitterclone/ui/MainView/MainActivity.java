package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elkcreek.rodneytressler.twitterclone.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
