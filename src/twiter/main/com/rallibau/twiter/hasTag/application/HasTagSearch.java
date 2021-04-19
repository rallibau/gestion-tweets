package com.rallibau.twiter.hasTag.application;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.twiter.hasTag.domain.HasTag;
import com.rallibau.twiter.hasTag.domain.HastagRepository;

import java.util.List;

@Service
public class HasTagSearch {
    private final HastagRepository hastagRepository;

    public HasTagSearch(HastagRepository hastagRepository) {
        this.hastagRepository = hastagRepository;
    }

    public List<HasTag> search(Criteria criteria) {
        return hastagRepository.matching(criteria);
    }
}
