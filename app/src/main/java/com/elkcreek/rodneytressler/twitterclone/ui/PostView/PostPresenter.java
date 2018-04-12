package com.elkcreek.rodneytressler.twitterclone.ui.PostView;

/**
 * Created by rodneytressler on 4/12/18.
 */

public class PostPresenter {
    private PostView view;

    public void onSubmitClicked(String tweetContent) {
        if(!tweetContent.isEmpty()) {
            view.leaveTweet(tweetContent);
        } else {
            view.toastMustHaveContent();
        }
    }

    public void attachView(PostView view) {
        this.view = view;
    }
}
