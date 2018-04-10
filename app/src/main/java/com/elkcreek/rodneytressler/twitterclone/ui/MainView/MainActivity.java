package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.client.FirebaseService;
import com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView.RegistrationFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Binds;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainView, RegistrationFragment.Callback {

    @Inject
    FirebaseService firebaseService;

    @Inject
    MainPresenter presenter;

    private RegistrationFragment registrationFragment;

    @OnClick(R.id.button_register)
    protected void onRegisterClicked(View view) {
        presenter.registerButtonClicked();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        presenter.onCreate(this);
    }

    @Override
    public void launchRegistrationFragment() {
        registrationFragment = RegistrationFragment.newInstance();
        registrationFragment.attachParent(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, registrationFragment).commit();
    }

    @Override
    public void userRegistered() {
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
    }
}
