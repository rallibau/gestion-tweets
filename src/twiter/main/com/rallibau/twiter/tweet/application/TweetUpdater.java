package com.rallibau.twiter.tweet.application;

import com.rallibau.shared.domain.Service;
import com.rallibau.twiter.hasTag.domain.HasTag;
import com.rallibau.twiter.hasTag.domain.HastagRepository;
import com.rallibau.twiter.hasTag.domain.Text;
import com.rallibau.twiter.tweet.domain.Tweet;
import com.rallibau.twiter.tweet.domain.TweetId;
import com.rallibau.twiter.tweet.domain.TweetRepository;
import com.rallibau.twiter.tweet.domain.TweetValidation;
import com.rallibau.twiter.tweet.domain.TwitterApi.TwiterApi;

import java.util.List;
import java.util.Optional;

@Service
public class TweetUpdater {
    private final TweetRepository tweetRepository;
    private final HastagRepository hastagRepository;
    private final TwiterApi twiterApi;


    public TweetUpdater(TweetRepository tweetRepository, HastagRepository hastagRepository, TwiterApi twiterApi) {
        this.tweetRepository = tweetRepository;
        this.hastagRepository = hastagRepository;
        this.twiterApi = twiterApi;
    }

    public void update(String tweeterId, Integer validado){
        Optional<Tweet> tweet = tweetRepository.get(new TweetId(tweeterId));;
        if(tweet.isPresent()) {
            if(validado > 0) {
                validado = 1;
            }
            tweet.get().setTweetValidation(new TweetValidation(validado));
            tweetRepository.save(tweet.get());
        }
    }
}
