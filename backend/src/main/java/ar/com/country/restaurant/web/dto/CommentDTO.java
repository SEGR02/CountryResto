package ar.com.country.restaurant.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "comments", itemRelation = "comment")
public class CommentDTO extends RepresentationModel<CommentDTO> {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;

    @NotBlank
    private final String content;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Calendar createdAt;
}
