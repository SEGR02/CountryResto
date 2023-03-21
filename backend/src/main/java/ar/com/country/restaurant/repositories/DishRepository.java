package ar.com.country.restaurant.repositories;

import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.repositories.search.SearchDishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long>, SearchDishRepository {

    @Query("select d from Dish d where d.category.id = ?1")
    Page<Dish> findDishesByDishCategoryId(Long dishCategoryId, Pageable pageable);

}
