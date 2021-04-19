package com.rallibau.twiter.tweet.infrastructure.TwiterApi;

import com.rallibau.shared.domain.Service;
import com.rallibau.twiter.hasTag.domain.HasTag;
import com.rallibau.twiter.hasTag.domain.HastagRepository;
import com.rallibau.twiter.hasTag.domain.Text;
import com.rallibau.twiter.hasTag.domain.Used;
import com.rallibau.twiter.tweet.domain.*;
import com.rallibau.twiter.tweet.domain.TwitterApi.TwiterApi;
import org.springframework.social.twitter.api.Entities;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SpringTwitter implements TwiterApi {

    public static final String CONSUMER_KEY = "BxW1Tds0JTYRNUvhcxNWfRlIO";
    public static final String CONSUMER_SECRET = "OSfYl9D3JRC4R6q5aVzIAzoVoWcL3E8swgNFYnrcsjFOIMgufJ";
    public static final String ACCESS_TOKEN = "324332251-Dnkekm8hlUWPS0akpM52agWIx5heFObUeydAGi1o";
    public static final String ACCESS_TOKEN_SECRET = "HzBQU1FySlfSaSoVzZnH1lqBIGHcq6A3ik25psnRIKiDw";
    public static final List<String> ACCEPT_lANGUAGE = Arrays.asList("es", "fr", "it");

    private final TweetRepository tweetRepository;
    private final HastagRepository hastagRepository;

    public SpringTwitter(TweetRepository tweetRepository, HastagRepository hastagRepository) {
        this.tweetRepository = tweetRepository;
        this.hastagRepository = hastagRepository;
    }

    @Override
    public void getUserTimeline(String userName) {
        TwitterTemplate twitterTemplate =
            new TwitterTemplate(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
        if (!testMinFollowers(userName, twitterTemplate)) {
            getUserTimeline(userName,0L,twitterTemplate);
        }

    }

    private void getUserTimeline(String userName,Long oldId,TwitterTemplate twitterTemplate) {

        List<org.springframework.social.twitter.api.Tweet> tweets = twitterTemplate.timelineOperations().getUserTimeline(userName,200,0,oldId);
        Tweet oldTweet = null;
        for (org.springframework.social.twitter.api.Tweet t : tweets) {
            if (ACCEPT_lANGUAGE.contains(t.getLanguageCode())) {
                Tweet tweet = new Tweet(new TweetId(t.getIdStr()),
                    new TweetUser(userName),
                    new TweetText(t.getText()),
                    new TweetLocale(t.getLanguageCode()),
                    new TweetValidation(0),
                    null);

                tweetRepository.save(tweet);
                for (HashTagEntity h :t.getEntities().getHashTags()) {
                    Optional<HasTag> hasTag = hastagRepository.getById(new Text(h.getText()));
                    if(hasTag.isPresent()) {
                        hasTag.get().addUsed();
                        hastagRepository.save(hasTag.get());
                    }else {
                        HasTag hastag = new HasTag(new Text(h.getText()),new Used(0));
                        hastagRepository.save(hastag);
                    }

                }
                oldTweet = tweet;
            }

        }
        if(oldTweet != null && !Long.valueOf(oldTweet.id().value()).equals(oldId)) {
            getUserTimeline(userName,Long.valueOf(oldTweet.id().value()),twitterTemplate);
        }

    }

    private boolean testMinFollowers(String userName, TwitterTemplate twitterTemplate) {
        int followers = twitterTemplate.friendOperations().getFollowerIds(userName).size();
        return followers <= 1500;
    }
}
