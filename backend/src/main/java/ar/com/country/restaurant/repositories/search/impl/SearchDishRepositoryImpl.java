package ar.com.country.restaurant.repositories.search.impl;

import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.repositories.search.SearchDishRepository;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class SearchDishRepositoryImpl implements SearchDishRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Dish> searchDishByNameAndDescription(String query, Pageable pageable) {
        SearchSession searchSession = Search.session(em);
        SearchResult<Dish> result = searchSession.search(Dish.class)
                .where(f -> f.match()
                        .field("name").boost(2F)
                        .field("description")
                        .matching(String.format("*%s*", query))
                        .fuzzy()
                )
                .fetch((int) pageable.getOffset(), pageable.getPageSize());
        long totalHitCount = result.total().hitCount();
        List<Dish> matchingDishes = result.hits();
        return new PageImpl<>(matchingDishes, pageable, totalHitCount);
    }

}
