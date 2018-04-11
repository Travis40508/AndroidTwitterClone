package com.elkcreek.rodneytressler.twitterclone.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rodneytressler on 4/11/18.
 */

public class Post {
    private String postContent;
    private String date;
    private List<String> likes;

    public Post(String postContent) {
        this.postContent = postContent;
        this.likes = new ArrayList<>();
        this.date = getDate();
    }

    public Post() {

    }

    public String getEmail() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        return user.getEmail();
    }

    public String getDisplayName() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        return user.getDisplayName();
    }

    public String getDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate= formatter.format(date);
        return strDate;
    }



    public String getPostContent() {
        return postContent;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLike(String like) {
        this.likes.add(like);
    }
}
