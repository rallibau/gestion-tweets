package com.rallibau.twiter.hasTag.infrastructure.persistence.hibernate;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infrastructure.hibernate.HibernateRepository;
import com.rallibau.twiter.hasTag.domain.HasTag;
import com.rallibau.twiter.hasTag.domain.HastagRepository;
import com.rallibau.twiter.hasTag.domain.Text;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("products-transaction_manager")
public class HibernateHastagRepository extends HibernateRepository<HasTag> implements HastagRepository {
    public HibernateHastagRepository(@Qualifier("products-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, HasTag.class);
    }

    @Override
    public void save(HasTag hasTag) {
        persist(hasTag);
    }

    @Override
    public List<HasTag> matching(Criteria criteria) {
        return byCriteria(criteria);
    }

    @Override
    public List<HasTag> getAll() {
        return all();
    }

    @Override
    public Optional<HasTag> getById(Text id) {
        return byId(id);
    }
}
