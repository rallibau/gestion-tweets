package com.rallibau.twiter.hasTag.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface HastagRepository {
    void save(HasTag hasTag);
    List<HasTag> matching(Criteria criteria);

    List<HasTag> getAll();

    Optional<HasTag> getById(Text id);
}
