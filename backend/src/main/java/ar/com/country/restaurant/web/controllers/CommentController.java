package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.Comment;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.CommentService;
import ar.com.country.restaurant.web.dto.CommentDTO;
import ar.com.country.restaurant.web.hateoas.assemblers.CommentModelAssembler;
import ar.com.country.restaurant.web.mappers.CommentMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "Comments", description = "API to manage comments")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class CommentController {
    private final CommentService commentService;
    private final CommentModelAssembler commentModelAssembler;
    private final PagedResourcesAssembler<Comment> commentPagedResourcesAssembler;
    private final CommentMapper commentMapper;

    @Operation(summary = "Returns all comments of a dish")
    @ApiResponse(responseCode = "200", description = "Comments of a dish", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = CommentDTO.class)))
    })
    @GetMapping
    public PagedModel<CommentDTO> getCommentsOfDish(
            @RequestParam Long dishId,
            @PageableDefault Pageable pageable
    ) {
        Page<Comment> result = commentService.getCommentsOfDish(dishId, pageable);
        return commentPagedResourcesAssembler.toModel(result, commentModelAssembler);
    }

    @Operation(summary = "Returns a comment by id")
    @ApiResponse(responseCode = "200", description = "Comment", content = {
            @Content(schema = @Schema(implementation = CommentDTO.class))
    })
    @GetMapping("/{commentId}")
    public CommentDTO getCommentById(@PathVariable Long commentId) {
        Comment result = commentService.getCommentById(commentId);
        return commentModelAssembler.toModel(result);
    }

    @Operation(summary = "Adds a comment to a dish")
    @ApiResponse(responseCode = "201", description = "Comment", content = {
            @Content(schema = @Schema(implementation = CommentDTO.class))
    })
    @PostMapping
    public ResponseEntity<CommentDTO> addCommentToDish(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @RequestParam Long dishId,
            @RequestBody @Valid CommentDTO commentDto
    ) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment result = commentService.addCommentToDish(loggedUser.getId(), dishId, comment);
        return ResponseEntity
                .created(URI.create("/api/comments/" + result.getId()))
                .body(commentModelAssembler.toModel(result));
    }

    @Operation(summary = "Updates a comment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment", content = {
                    @Content(schema = @Schema(implementation = CommentDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, description = "Comment not found", responseCode = "404"),
            @ApiResponse(ref = FORBIDDEN_RESPONSE_REF, description = "User is not the owner of the comment", responseCode = "403")
    })
    @PutMapping("/{commentId}")
    public CommentDTO updateComment(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long commentId,
            @RequestBody @Valid CommentDTO commentDto
    ) {
        Comment comment = commentMapper.toEntity(commentDto);
        Comment result = commentService.updateComment(loggedUser.getId(), commentId, comment);
        return commentModelAssembler.toModel(result);
    }

    @Operation(summary = "Deletes a comment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment deleted", content = {
                    @Content(schema = @Schema(implementation = CommentDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, description = "Comment not found", responseCode = "404"),
            @ApiResponse(ref = FORBIDDEN_RESPONSE_REF, description = "User is not the owner of the comment", responseCode = "403")
    })
    @DeleteMapping("/{commentId}")
    public CommentDTO deleteComment(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long commentId
    ) {
        Comment result = commentService.deleteComment(loggedUser.getId(), commentId);
        return commentMapper.toDto(result);
    }

}
