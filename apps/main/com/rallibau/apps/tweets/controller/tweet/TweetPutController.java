package com.rallibau.apps.tweets.controller.tweet;

import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infrastructure.spring.ApiController;
import com.rallibau.twiter.tweet.application.TweetCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class TweetPutController extends ApiController {
    private final TweetCreator tweetCreator;
    public TweetPutController(
        QueryBus queryBus,
        CommandBus commandBus,
        TweetCreator tweetCreator) {
        super(queryBus, commandBus);
        this.tweetCreator = tweetCreator;
    }

    @PutMapping("/tweet/{user}")
    public void index(@PathVariable String user) {
        tweetCreator.create(user);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
