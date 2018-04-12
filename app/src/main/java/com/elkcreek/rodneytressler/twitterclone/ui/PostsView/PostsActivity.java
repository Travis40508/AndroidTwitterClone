package com.elkcreek.rodneytressler.twitterclone.ui.PostsView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.models.Post;
import com.elkcreek.rodneytressler.twitterclone.ui.Adapters.PostsAdapter;
import com.elkcreek.rodneytressler.twitterclone.ui.PostView.PostFragment;
import com.elkcreek.rodneytressler.twitterclone.util.NotificationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsActivity extends AppCompatActivity implements PostsView, PostFragment.Callback {

    @BindView(R.id.posts_recycler_view)
    protected RecyclerView postsRecyclerView;

    @OnClick(R.id.fab_leave_tweet)
    protected void onLeaveTweetClicked(View view) {
        presenter.leaveTweetClicked();
    }

    private PostsAdapter adapter;
    private PostsPresenter presenter;
    private List<Post> postList;
    private PostFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);
        presenter = new PostsPresenter();
        presenter.onCreate(this);

        postList = new ArrayList<>();
        adapter = new PostsAdapter(postList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        postsRecyclerView.setLayoutManager(mLayoutManager);
        postsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showNewPost(Post post) {
        postList.add(post);
        adapter.notifyDataSetChanged();
        postsRecyclerView.scrollToPosition(postList.size() - 1);
    }

    @Override
    public void clearPostText() {
//        postContent.setText("");
    }


    @Override
    public void showNotification() {
        NotificationUtil.notifyTweetReceived(this);
    }

    @Override
    public void sendAppToBackground() {
        moveTaskToBack(true);
    }

    @Override
    public void attachLeaveTweetFragment() {
        fragment = PostFragment.newInstance();
        fragment.attachView(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment).commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onBackPressed() {
        presenter.backPressed();
    }

    @Override
    public void tweetSubmitted(Post post) {
        presenter.leavePostClicked(post);
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
