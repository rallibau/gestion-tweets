package com.rallibau.twiter.tweet.domain;

import com.rallibau.shared.domain.IntValueObject;

public class TweetValidation extends IntValueObject {
    public TweetValidation(Integer value) {
        super(value);
    }
    public TweetValidation() {
        super(0);
    }
}
