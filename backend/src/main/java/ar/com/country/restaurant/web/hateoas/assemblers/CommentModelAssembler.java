package ar.com.country.restaurant.web.hateoas.assemblers;

import ar.com.country.restaurant.dao.entities.Comment;
import ar.com.country.restaurant.dao.entities.Dish;
import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.web.controllers.CommentController;
import ar.com.country.restaurant.web.controllers.DishController;
import ar.com.country.restaurant.web.controllers.UserController;
import ar.com.country.restaurant.web.dto.CommentDTO;
import ar.com.country.restaurant.web.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@RequiredArgsConstructor
public class CommentModelAssembler implements RepresentationModelAssembler<Comment, CommentDTO> {
    private final CommentMapper commentMapper;
    private Comment comment;

    @Override
    @NonNull
    public CommentDTO toModel(@NonNull Comment comment) {
        this.comment = comment;
        CommentDTO model = commentMapper.toDto(comment);
        model.add(selfLink(), dishLink(), userLink());
        return model;
    }

    public Link selfLink() {
        return linkTo(CommentController.class)
                .slash(comment.getId())
                .withSelfRel();
    }

    public Link dishLink() {
        Dish dish = comment.getDish();
        return linkTo(DishController.class)
                .slash(dish.getId())
                .withRel("dish");
    }

    public Link userLink() {
        User user = comment.getUser();
        return linkTo(UserController.class)
                .slash(user.getId())
                .withRel("user");
    }

}
