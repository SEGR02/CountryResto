package ar.com.country.restaurant.exceptions;

public class UserNotOwnerOfCommentException extends RuntimeException {

    public UserNotOwnerOfCommentException(Long userId, Long commentId) {
        super(String.format("User with id %d is not the owner of comment with id %d", userId, commentId));
    }

}
