package com.rallibau.twiter.tweet.domain;

import com.rallibau.shared.domain.AggregateRoot;

import java.util.List;

public final class Tweet extends AggregateRoot {
    private final TweetId id;
    private final TweetUser tweetUser;
    private final TweetText tweetText;
    private final TweetLocale tweetLocale;
    private TweetValidation tweetValidation;

    private final List<String> hasTags;

    public TweetId id() {
        return id;
    }

    public TweetUser tweetUser() {
        return tweetUser;
    }

    public TweetText tweetText() {
        return tweetText;
    }

    public TweetLocale tweetLocale() {
        return tweetLocale;
    }

    public TweetValidation tweetValidation() {
        return tweetValidation;
    }
    public void setTweetValidation(TweetValidation tweetValidation) {
        this.tweetValidation = tweetValidation;
    }

    public List<String> hasTags() {
        return hasTags;
    }

    public Tweet(TweetId id, TweetUser tweetUser, TweetText tweetText, TweetLocale tweetLocale, TweetValidation tweetValidation, List<String> hasTags) {
        this.id = id;
        this.tweetUser = tweetUser;
        this.tweetText = tweetText;
        this.tweetLocale = tweetLocale;
        this.tweetValidation = tweetValidation;
        this.hasTags = hasTags;
    }

    public Tweet() {
        this.id = null;
        this.tweetUser = null;
        this.tweetText = null;
        this.tweetLocale = null;
        this.tweetValidation = null;
        this.hasTags = null;
    }

}
