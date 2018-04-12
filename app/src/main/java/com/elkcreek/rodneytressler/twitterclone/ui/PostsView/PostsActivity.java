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
import com.elkcreek.rodneytressler.twitterclone.util.NotificationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsActivity extends AppCompatActivity implements PostsView {

    @BindView(R.id.posts_recycler_view)
    protected RecyclerView postsRecyclerView;

    @BindView(R.id.input_post_content)
    protected EditText postContent;

    @OnClick(R.id.button_leave_post)
    protected void onLeavePostClicked(View view) {
        presenter.leavePostClicked(new Post(postContent.getText().toString()));
    }

    private PostsAdapter adapter;
    private PostsPresenter presenter;
    private List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);
        presenter = new PostsPresenter();
        presenter.onCreate(this);

        postList = new ArrayList<>();
        adapter = new PostsAdapter(postList);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNewPost(Post post) {
        postList.add(post);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void clearPostText() {
        postContent.setText("");
    }

    @Override
    public void toastMustHavePostContent() {
        Toast.makeText(this, "Must Enter Some Text Before Posting", Toast.LENGTH_SHORT).show();
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
}
