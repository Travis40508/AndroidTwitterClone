package com.elkcreek.rodneytressler.twitterclone.ui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elkcreek.rodneytressler.twitterclone.R;
import com.elkcreek.rodneytressler.twitterclone.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rodneytressler on 4/11/18.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Post> postList;
    private Callback callback;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;


    public PostsAdapter(List<Post> postList, Callback callback) {
        this.postList = postList;
        this.callback = callback;
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }


    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_twitter_post, parent, false);
        return new PostsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        holder.bindPosts(postList.get(position));

        holder.isFavorited.setOnClickListener(holder.onLikeClicked(postList.get(position)));
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

        private String itemKey;

        public PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindPosts(Post post) {
            userName.setText(post.getDisplayName());
            userEmail.setText(post.getEmail());
            postContent.setText(post.getPostContent());
            postTimeStamp.setText(post.getDate());

            isFavorited.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_unfavorite));

            firebaseDatabase.getReference().child(post.getPostKey()).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String email = snapshot.getValue(String.class);
                        if(email.equals(firebaseUser.getEmail())) {
                            isFavorited.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_favorite));
                            itemKey = snapshot.getKey();
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    boolean isFavorite = false;
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String email = snapshot.getValue(String.class);
                        String currentUserEmail = firebaseUser.getEmail();
                        if(email.equals(currentUserEmail)) {
                            isFavorite = true;
                            itemKey = snapshot.getKey();
                        }
                    }
                    if(isFavorite) {
                        isFavorited.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_favorite));
                    } else {
                        isFavorited.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_unfavorite));
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    isFavorited.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_unfavorite));
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        public View.OnClickListener onLikeClicked(Post post) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFavorited.getDrawable().getConstantState() == itemView.getResources().getDrawable(R.drawable.ic_favorite).getConstantState()) {
                        callback.unFavoriteClicked(post, itemKey);
                    } else {
                        callback.favoriteClicked(post, firebaseUser.getEmail());
                    }
                }
            };
        }
    }

    public interface Callback {
        void favoriteClicked(Post post, String userEmail);
        void unFavoriteClicked(Post post, String itemKey);
    }
}
