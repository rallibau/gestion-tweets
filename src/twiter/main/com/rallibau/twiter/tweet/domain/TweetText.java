package com.rallibau.twiter.tweet.domain;

import com.rallibau.shared.domain.StringValueObject;

public class TweetText extends StringValueObject {
    public TweetText(String value) {
        super(value);
    }
    public TweetText() {
        super("");
    }
}
