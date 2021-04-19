package com.rallibau.twiter.tweet.application;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.twiter.tweet.domain.Tweet;
import com.rallibau.twiter.tweet.domain.TweetRepository;

import java.util.List;

@Service
public class TweetSearch {
    private final TweetRepository tweetRepository;

    public TweetSearch(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> search(Criteria criteria){
        return tweetRepository.matching(criteria);
    }
}
