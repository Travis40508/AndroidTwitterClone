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
    private String email;
    private String displayName;
    private List<String> likes;
    private String postKey;

    public Post(String postContent) {
        this.postContent = postContent;
        this.date = setDate();
        this.email = setEmail();
        this.displayName = setDisplayName();
    }

    public Post() {

    }

    public String getDate() {
        return date;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        return user.getEmail();
    }

    public String setDisplayName() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        return user.getDisplayName();
    }

    public String setDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate= formatter.format(date);
        return strDate;
    }



    public String getPostContent() {
        return postContent;
    }


    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getPostKey() {
        return postKey;
    }
}
