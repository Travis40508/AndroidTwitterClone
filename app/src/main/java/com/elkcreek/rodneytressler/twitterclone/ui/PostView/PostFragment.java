package com.elkcreek.rodneytressler.twitterclone.ui.PostView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.models.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PostFragment extends Fragment implements PostView {

    @BindView(R.id.input_leave_tweet)
    protected EditText leaveTweetInput;
    private Callback callback;
    private PostPresenter presenter;

    @OnClick(R.id.button_submit_tweet)
    protected void onSubmitTweetClicked(View view) {
        String tweetContent = leaveTweetInput.getText().toString();
        presenter.onSubmitClicked(tweetContent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave_post, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new PostPresenter();
        presenter.attachView(this);
    }

    public static PostFragment newInstance() {

        Bundle args = new Bundle();

        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void attachView(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void leaveTweet(String tweetContent) {
        Post post = new Post(tweetContent);
        callback.tweetSubmitted(post);
    }

    @Override
    public void toastMustHaveContent() {
        Toast.makeText(getContext(), "Please enter a Tweet First!", Toast.LENGTH_SHORT).show();
    }

    public interface Callback {
        void tweetSubmitted(Post post);
    }
}
