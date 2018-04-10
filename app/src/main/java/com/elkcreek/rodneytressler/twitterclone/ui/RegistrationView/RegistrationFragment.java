package com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class RegistrationFragment extends Fragment implements RegistrationView {

    @Inject
    RegistrationPresenter presenter;


    @BindView(R.id.input_email)
    protected TextInputLayout emailText;

    @BindView(R.id.input_password)
    protected TextInputLayout passwordText;

    @BindView(R.id.input_confirm_password)
    protected TextInputLayout confirmPasswordText;

    private Callback callback;

    @OnClick(R.id.button_register)
    protected void onRegistrationButtonClicked(View view) {
        String email = emailText.getEditText().getText().toString();
        String password = passwordText.getEditText().getText().toString();
        String confirmPassword = confirmPasswordText.getEditText().getText().toString();

        presenter.registrationButtonClicked(email, password, confirmPassword);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart(this);
    }

    public static RegistrationFragment newInstance() {

        Bundle args = new Bundle();

        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void toastPasswordsMustMatch() {
        Toast.makeText(getContext(), "Passwords Must Match", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastFillEveryField() {
        Toast.makeText(getContext(), "Please Fill in Every Field First!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerUser(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            presenter.userRegistered(firebaseAuth.getCurrentUser());
                        } else {
                            Log.e("@@@@@@@@", task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void errorRegisteringUserToast() {
        Toast.makeText(getContext(), "Error Registering User - Please Try Again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userSuccessfullyRegistered() {
        callback.userRegistered();
    }

    public void attachParent(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void userRegistered();
    }
}
