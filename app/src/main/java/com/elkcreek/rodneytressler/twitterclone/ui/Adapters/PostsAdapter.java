package com.elkcreek.rodneytressler.twitterclone.ui.Adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodneytressler on 4/11/18.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Post> postList;

    public PostsAdapter(List<Post> postList) {
        this.postList = postList;
    }


    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_twitter_post, parent, false);
        return new PostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        holder.bindPosts(postList.get(position));

        holder.isFavorited.setOnClickListener(holder.onLikeClicked());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_user_email)
        protected TextView userEmail;

        @BindView(R.id.text_post_content)
        protected TextView postContent;

        @BindView(R.id.text_post_time_stamp)
        protected TextView postTimeStamp;

        @BindView(R.id.image_favorite)
        protected ImageView isFavorited;

        @BindView(R.id.text_user_name)
        protected TextView userName;

        public PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPosts(Post post) {
            userName.setText(post.getDisplayName());
            userEmail.setText(post.getEmail());
            postContent.setText(post.getPostContent());
            postTimeStamp.setText(post.getDate());
            isFavorited.setImageDrawable(getFavorited(post));
        }

        private Drawable getFavorited(Post post) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (post.getLikes() != null) {
                if (post.getLikes().contains(firebaseUser.getEmail())) {
                    return itemView.getResources().getDrawable(R.drawable.ic_favorite);
                } else {
                    return itemView.getResources().getDrawable(R.drawable.ic_unfavorite);
                }
            } else {
                return itemView.getResources().getDrawable(R.drawable.ic_unfavorite);
            }
        }

        public View.OnClickListener onLikeClicked() {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
        }
    }
}
