package com.rallibau.apps.tweets.controller.tweet;

import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.FilterOperator;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import com.rallibau.shared.infrastructure.spring.ApiController;
import com.rallibau.twiter.tweet.application.TweetDetail;
import com.rallibau.twiter.tweet.application.TweetSearch;
import com.rallibau.twiter.tweet.domain.Tweet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public final class TweetGetController extends ApiController {
    private final TweetSearch tweetSearch;
    private final TweetDetail tweetDetail;
    public TweetGetController(
        QueryBus queryBus,
        CommandBus commandBus,
        TweetSearch tweetSearch, TweetDetail tweetDetail) {
        super(queryBus, commandBus);
        this.tweetSearch = tweetSearch;
        this.tweetDetail = tweetDetail;
    }

    @GetMapping("/tweet/total")
    public HashMap<String, String> total(@RequestParam(required = false) String user) {
        Criteria criteria = new Criteria(
            Filters.none(),
            Order.none(),
            Optional.empty(),
            Optional.empty()
        );
        List<Tweet> tweetList = tweetSearch.search(criteria);

        HashMap<String, String> status = new HashMap<>();
        status.put("application", "tweets");
        status.put("total", String.valueOf(tweetList.size()));

        return status;
    }

    @GetMapping("/tweet")
    public List<HashMap<String, String>> index(@RequestParam(required = false) String user,
                                               @RequestParam(required = false) Boolean validado) {
        List<HashMap<String, String>> result = new ArrayList<>();
        List<HashMap<String, String>> filters = parseFilters(user,validado);

        Criteria criteria = new Criteria(
            Filters.fromValues(filters),
            Order.none(),
            Optional.empty(),
            Optional.empty()
        );
        List<Tweet> tweetList = tweetSearch.search(criteria);
        for(Tweet t : tweetList) {
            HashMap<String, String> tweets = new HashMap<>();
            tweets.put("id",t.id().value());
            tweets.put("text",t.tweetText().value());
            tweets.put("validado",t.tweetValidation().value().toString());
            result.add(tweets);
        }


        return result;
    }

    @GetMapping("/tweet/{tweeterId}")
    public HashMap<String, String> get(@PathVariable() String tweeterId) {
       Optional<Tweet> tweet = tweetDetail.getTweet(tweeterId);
        HashMap<String, String> result = new HashMap<>();
       if(tweet.isPresent()) {
           result.put("id",tweet.get().id().value());
           result.put("text",tweet.get().tweetText().value());
           result.put("locale",tweet.get().tweetLocale().value());
           result.put("validado",tweet.get().tweetValidation().value().toString());
       }

       return result;
    }

    private List<HashMap<String, String>> parseFilters(String user,Boolean validado) {
        List<HashMap<String, String>> filters = new ArrayList<>();
        if(user != null && !user.isEmpty()) {
            HashMap<String, String> filtroUsuario = new HashMap<>();
            filtroUsuario.put("field", "tweetUser");
            filtroUsuario.put("operator", FilterOperator.EQUAL.value());
            filtroUsuario.put("value", user);
            filters.add(filtroUsuario);
        }
        if(validado != null) {
            HashMap<String, String> filtroUsuario = new HashMap<>();
            filtroUsuario.put("field", "tweetValidation");
            filtroUsuario.put("type", "integer");
            filtroUsuario.put("operator", FilterOperator.EQUAL.value());
            if(validado) {
                filtroUsuario.put("value", "1");
            }else {
                filtroUsuario.put("value", "0");
            }
            filters.add(filtroUsuario);
        }
        return filters;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
