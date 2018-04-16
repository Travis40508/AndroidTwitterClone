package com.elkcreek.rodneytressler.twitterclone.ui.MainView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.ui.PostsView.PostsActivity;
import com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView.RegistrationFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements MainView, RegistrationFragment.Callback {


    @Inject
    MainPresenter presenter;

    private RegistrationFragment registrationFragment;

    @BindView(R.id.input_email)
    protected TextInputLayout emailText;

    @BindView(R.id.input_password)
    protected TextInputLayout passwordText;

    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;

    @OnClick(R.id.button_register)
    protected void onRegisterClicked(View view) {
        presenter.registerButtonClicked();
    }

    @OnClick(R.id.button_login)
    protected void onLoginClicked(View view) {
        String email = emailText.getEditText().getText().toString();
        String password = passwordText.getEditText().getText().toString();

        presenter.loginButtonClicked(email, password);
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
    public void logUserIn(String email, String password, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            presenter.loginSuccessful(firebaseAuth.getCurrentUser());
                        } else {
                            presenter.loginFailed();
                        }
                    }
                });
    }

    @Override
    public void toastLoginFailed() {
        Toast.makeText(this, "Login Failed - Please Try Again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchPostsActivity() {
        startActivity(new Intent(this, PostsActivity.class));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void userRegistered() {
        getSupportFragmentManager().beginTransaction().remove(registrationFragment).commit();
        Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
    }
}
