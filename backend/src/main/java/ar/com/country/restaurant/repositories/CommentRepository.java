package ar.com.country.restaurant.repositories;

import ar.com.country.restaurant.dao.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.dish.id = ?1")
    Page<Comment> findAllByDishId(Long dishId, Pageable pageable);

}
