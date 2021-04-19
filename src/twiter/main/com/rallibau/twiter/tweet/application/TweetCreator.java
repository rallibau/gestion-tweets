package com.rallibau.twiter.tweet.application;

import com.rallibau.shared.domain.Service;
import com.rallibau.twiter.tweet.domain.Tweet;
import com.rallibau.twiter.tweet.domain.TweetRepository;
import com.rallibau.twiter.tweet.domain.TwitterApi.TwiterApi;
import com.rallibau.twiter.hasTag.domain.HasTag;
import com.rallibau.twiter.hasTag.domain.HastagRepository;
import com.rallibau.twiter.hasTag.domain.Text;

import java.util.List;

@Service
public class TweetCreator {
    private final TweetRepository tweetRepository;
    private final HastagRepository hastagRepository;
    private final TwiterApi twiterApi;


    public TweetCreator(TweetRepository tweetRepository, HastagRepository hastagRepository, TwiterApi twiterApi) {
        this.tweetRepository = tweetRepository;
        this.hastagRepository = hastagRepository;
        this.twiterApi = twiterApi;
    }

    public void create(String userName){

        Thread thread = new Thread(){
            public void run(){
                twiterApi.getUserTimeline(userName);

            }
        };

        thread.start();
    }
}
