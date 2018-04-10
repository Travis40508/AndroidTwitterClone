package com.elkcreek.rodneytressler.twitterclone.ui.SplashView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.ui.MainView.MainActivity;
import com.elkcreek.rodneytressler.twitterclone.ui.PostsView.PostsActivity;

public class SplashActivity extends AppCompatActivity implements SplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenter();

        presenter.onCreate(this);
    }

    @Override
    public void launchPostsActivity() {
        startActivity(new Intent(this, PostsActivity.class));
    }

    @Override
    public void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
