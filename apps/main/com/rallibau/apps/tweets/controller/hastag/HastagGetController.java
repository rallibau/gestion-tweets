package com.rallibau.apps.tweets.controller.hastag;

import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import com.rallibau.shared.domain.criteria.OrderType;
import com.rallibau.shared.infrastructure.spring.ApiController;
import com.rallibau.twiter.hasTag.application.HasTagSearch;
import com.rallibau.twiter.hasTag.domain.HasTag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class HastagGetController extends ApiController {
    private final HasTagSearch hasTagSearch;

    public HastagGetController(QueryBus queryBus, CommandBus commandBus, HasTagSearch hasTagSearch) {
        super(queryBus, commandBus);
        this.hasTagSearch = hasTagSearch;
    }

    @GetMapping("/hastag")
    public List<HashMap<String, String>> index(@RequestParam(required = false) Integer limit) {
        List<HashMap<String, String>> result = new ArrayList<>();
        Optional<Integer> limitOpt = Optional.empty();
        if (limit != null) {
            limitOpt = Optional.of(limit);
        }


        Criteria criteria = new Criteria(
            Filters.none(),
            Order.desc("used"),
            limitOpt,
            Optional.empty()
        );

        List<HasTag> hastags = hasTagSearch.search(criteria);
        Integer i =1;
        for (HasTag hastag : hastags) {
            HashMap<String, String> tags = new HashMap<>();
            tags.put("text", hastag.id().value());
            tags.put("usos", String.valueOf(hastag.used().value()));
            result.add(tags);
            if(limitOpt.isPresent() && limitOpt.get().equals(i)) {
                break;
            }
            i++;
        }


        return result;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
