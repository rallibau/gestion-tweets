package com.rallibau.apps.products.controller.tweet;

import org.junit.jupiter.api.Test;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import java.util.List;

public class TweetShould {


    @Test
    public void test_libreria_tweeter_spring(){
        String consumerKey = "BxW1Tds0JTYRNUvhcxNWfRlIO";
        String consumerSecret = "OSfYl9D3JRC4R6q5aVzIAzoVoWcL3E8swgNFYnrcsjFOIMgufJ";
        String accessToken = "324332251-Dnkekm8hlUWPS0akpM52agWIx5heFObUeydAGi1o";
        String accessTokenSecret = "HzBQU1FySlfSaSoVzZnH1lqBIGHcq6A3ik25psnRIKiDw";

        TwitterTemplate twitterTemplate =
            new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        List<Tweet> tweets = twitterTemplate.timelineOperations().getUserTimeline("@ezcritor");
        for(Tweet t:tweets){

            System.out.println(t.getText());
        }
        System.out.println("Followers !!!!!!!!!!!!!!!!!"+twitterTemplate.friendOperations().getFollowerIds("@ezcritor").size());
        System.out.println("language !!!!!!!!!!!!!!!!!!"+twitterTemplate.userOperations().getUserProfile("@levante_emv").getLanguage());
        System.out.println("language !!!!!!!!!!!!!!!!!!"+twitterTemplate.userOperations().getUserProfile("@levante_emv").getLocation());
    }
}
