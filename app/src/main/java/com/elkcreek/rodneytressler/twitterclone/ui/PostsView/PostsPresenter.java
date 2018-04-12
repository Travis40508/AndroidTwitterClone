package com.elkcreek.rodneytressler.twitterclone.ui.PostsView;

import android.os.AsyncTask;
import android.util.Log;

import com.elkcreek.rodneytressler.twitterclone.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by rodneytressler on 4/10/18.
 */

public class PostsPresenter {
    private PostsView view;
    private DatabaseReference mDatabase;
    private boolean appIsActive = false;

    public void onCreate(PostsView view) {
        this.view = view;
        appIsActive = true;

        if (view != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        listenForPostUpdates();
    }

    private void listenForPostUpdates() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        Post post = dataSnapshot.getValue(Post.class);
                        view.showNewPost(post);
                        if(!post.getPostEmail().equals(user.getEmail()) && !appIsActive) {
                            Log.d("@@@@", "IT'S NOTIFICATION TIME!");
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    public void leavePostClicked(Post post) {
        if (!post.getPostContent().isEmpty()) {
            mDatabase.push().setValue(post);
            view.clearPostText();
        } else {
            view.toastMustHavePostContent();
        }
    }

    public void onResume() {
        appIsActive = true;
    }

    public void onPause() {
        appIsActive = false;
    }
}
