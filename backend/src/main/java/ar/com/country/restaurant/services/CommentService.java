package ar.com.country.restaurant.services;

import ar.com.country.restaurant.dao.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<Comment> getCommentsOfDish(Long dishId, Pageable pageable);

    Comment getCommentById(Long commentId);

    Comment addCommentToDish(Long userId, Long dishId, Comment comment);

    Comment updateComment(Long userId, Long commentId, Comment comment);

    Comment deleteComment(Long userId, Long commentId);

}
