package ar.com.country.restaurant.web.mappers;

import ar.com.country.restaurant.dao.entities.Comment;
import ar.com.country.restaurant.web.dto.CommentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentDTO commentDto);

    CommentDTO toDto(Comment comment);

}
