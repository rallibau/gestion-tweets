package com.rallibau.twiter.tweet.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface TweetRepository {
    void save(Tweet tweet);
    List<Tweet> matching(Criteria criteria);

    Optional<Tweet> get(TweetId id);

    List<Tweet> getAll();
}
