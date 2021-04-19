package com.rallibau.apps.tweets.controller.tweet;

import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infrastructure.spring.ApiController;
import com.rallibau.twiter.tweet.application.TweetUpdater;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class TweetPostController extends ApiController {
    private final TweetUpdater tweetUpdater;

    public TweetPostController(
        QueryBus queryBus,
        CommandBus commandBus,
        TweetUpdater tweetUpdater) {
        super(queryBus, commandBus);
        this.tweetUpdater = tweetUpdater;
    }

    @PostMapping("/tweet/{tweeterId}")
    public void index(@PathVariable String tweeterId, @RequestBody(required = true) TweetRequest tweet) {
        tweetUpdater.update(tweeterId, tweet.validate());
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
