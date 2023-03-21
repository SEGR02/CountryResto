package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.UserService;
import ar.com.country.restaurant.web.dto.UserDTO;
import ar.com.country.restaurant.web.hateoas.assemblers.UserModelAssembler;
import ar.com.country.restaurant.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API to manage users")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = UNAUTHORIZED_RESPONSE_REF, responseCode = "401"),
        @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserModelAssembler userModelAssembler;

    @Operation(summary = "Returns user associated with the given ID")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = UserDTO.class))
    })
    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        User result = userService.getUserById(userId);
        return userModelAssembler.toModel(result);
    }

    @Operation(summary = "Updates user with the given payload")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated", content = {
                    @Content(schema = @Schema(implementation = UserDTO.class))
            }),
            @ApiResponse(ref = FORBIDDEN_RESPONSE_REF, responseCode = "403")
    })
    @PutMapping("/{userId}")
    @PreAuthorize("#loggedUser.id == #userId")
    public UserDTO updateUser(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long userId,
            @RequestBody @Valid UserDTO userDto
    ) {
        User updatedUser = userMapper.toEntity(userDto);
        User result = userService.updateUser(userId, updatedUser);
        return userModelAssembler.toModel(result);
    }

    @Operation(summary = "Deletes user with the given payload")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted", content = {
                    @Content(schema = @Schema(implementation = UserDTO.class))
            }),
            @ApiResponse(ref = FORBIDDEN_RESPONSE_REF, responseCode = "403")
    })
    @DeleteMapping("/{userId}")
    @PreAuthorize("#loggedUser.id == #userId")
    public UserDTO deleteUser(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long userId
    ) {
        User result = userService.deleteUser(userId);
        return userMapper.toDto(result);
    }

}
