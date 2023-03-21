package ar.com.country.restaurant.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long commentId) {
        super(String.format("Comment with id %d not found", commentId));
    }

}
