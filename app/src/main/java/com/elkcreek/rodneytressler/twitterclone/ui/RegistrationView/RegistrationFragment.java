package com.elkcreek.rodneytressler.twitterclone.ui.RegistrationView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkcreek.rodneytressler.twitterclone.R;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class RegistrationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static RegistrationFragment newInstance() {

        Bundle args = new Bundle();

        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
