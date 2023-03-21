package ar.com.country.restaurant.services.impl;

import ar.com.country.restaurant.dao.entities.Comment;
import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.exceptions.CommentNotFoundException;
import ar.com.country.restaurant.exceptions.UserNotOwnerOfCommentException;
import ar.com.country.restaurant.repositories.CommentRepository;
import ar.com.country.restaurant.services.CommentService;
import ar.com.country.restaurant.services.DishService;
import ar.com.country.restaurant.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final DishService dishService;
    private final UserService userService;
    private final CommentRepository commentRepository;

    @Override
    public Page<Comment> getCommentsOfDish(Long dishId, Pageable pageable) {
        return commentRepository.findAllByDishId(dishId, pageable);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
    }

    @Override
    public Comment addCommentToDish(Long userId, Long dishId, Comment comment) {
        Dish dish = dishService.getDishById(dishId);
        User user = userService.getUserById(userId);
        dish.addComment(comment);
        comment.setUser(user);
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment updateComment(Long userId, Long commentId, Comment comment) {
        Comment commentToUpdate = getCommentById(commentId);
        ensureUserIsOwnerOfComment(userId, commentToUpdate);
        commentToUpdate.setContent(comment.getContent());
        return commentRepository.save(commentToUpdate);
    }

    @Override
    public Comment deleteComment(Long userId, Long commentId) {
        Comment commentToDelete = getCommentById(commentId);
        ensureUserIsOwnerOfComment(userId, commentToDelete);
        commentRepository.deleteById(commentId);
        return commentToDelete;
    }

    private void ensureUserIsOwnerOfComment(Long userId, Comment comment) {
        User user = comment.getUser();
        if (!user.getId().equals(userId)) {
            throw new UserNotOwnerOfCommentException(userId, comment.getId());
        }
    }

}
