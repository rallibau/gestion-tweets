package com.rallibau.twiter.tweet.infrastructure.persistence.hibernate;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infrastructure.hibernate.HibernateRepository;
import com.rallibau.twiter.tweet.domain.Tweet;
import com.rallibau.twiter.tweet.domain.TweetId;
import com.rallibau.twiter.tweet.domain.TweetRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("products-transaction_manager")
public class HibernateTweetRepository extends HibernateRepository<Tweet> implements TweetRepository {

    public HibernateTweetRepository(@Qualifier("products-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory, Tweet.class);
    }

    @Override
    public void save(Tweet tweet) {
        persist(tweet);
    }

    @Override
    public List<Tweet> matching(Criteria criteria) {
        return byCriteria(criteria);
    }

    @Override
    public Optional<Tweet> get(TweetId id) {
        return byId(id);
    }

    @Override
    public List<Tweet> getAll() {
        return all();
    }
}
