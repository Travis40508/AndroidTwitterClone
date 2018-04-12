package com.elkcreek.rodneytressler.twitterclone.ui.PostsView;

import com.elkcreek.rodneytressler.twitterclone.models.Post;

/**
 * Created by rodneytressler on 4/10/18.
 */

public interface PostsView {
    void showNewPost(Post post);

    void clearPostText();

    void showNotification();

    void sendAppToBackground();

    void attachLeaveTweetFragment();

    void detachFragment();

    void launchPreferenceFragment();
}
