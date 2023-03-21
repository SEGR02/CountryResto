package ar.com.country.restaurant.bootstrap;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Configuration
@Profile("re-index")
public class DatabaseEntitySearchIndexer implements CommandLineRunner {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        SearchSession searchSession = Search.session(em);
        searchSession.massIndexer().startAndWait();
    }

}
