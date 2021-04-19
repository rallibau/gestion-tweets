package com.rallibau.twiter.tweet.application;

import com.rallibau.shared.domain.Service;
import com.rallibau.twiter.tweet.domain.Tweet;
import com.rallibau.twiter.tweet.domain.TweetId;
import com.rallibau.twiter.tweet.domain.TweetRepository;

import java.util.Optional;

@Service
public class TweetDetail {
    private final TweetRepository tweetRepository;

    public TweetDetail(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
    public Optional<Tweet> getTweet(String tweeterId){
        return tweetRepository.get(new TweetId(tweeterId));
    }
}
