package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.User;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.security.TokenGenerator;
import ar.com.country.restaurant.services.UserService;
import ar.com.country.restaurant.web.dto.UserDTO;
import ar.com.country.restaurant.web.dto.auth.LoginDTO;
import ar.com.country.restaurant.web.dto.auth.TokenDTO;
import ar.com.country.restaurant.web.dto.validation.OnCreate;
import ar.com.country.restaurant.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Login and registration", description = "Login and registration for users")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class LoginAndRegistrationController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenGenerator tokenGenerator;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider refreshTokenAuthProvider;

    public LoginAndRegistrationController(
            UserService userService,
            UserMapper userMapper,
            TokenGenerator tokenGenerator,
            DaoAuthenticationProvider daoAuthenticationProvider,
            @Qualifier("jwtRefreshTokenAuthProvider") JwtAuthenticationProvider refreshTokenAuthProvider
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenGenerator = tokenGenerator;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.refreshTokenAuthProvider = refreshTokenAuthProvider;
    }

    @Operation(summary = "Login for registered user", description = "Login a user and return a JWT token")
    @ApiResponse(responseCode = "200", description = "User Logged In", content = {
            @Content(schema = @Schema(implementation = TokenDTO.class))
    })
    @PostMapping("/login")
    public TokenDTO login(@RequestBody @Valid LoginDTO payload) {
        var token = UsernamePasswordAuthenticationToken.unauthenticated(payload.email(), payload.password());
        Authentication authentication = daoAuthenticationProvider.authenticate(token);
        return tokenGenerator.issueToken(authentication);
    }

    @Operation(summary = "Register a user", description = "Register a new user and return a JWT token")
    @ApiResponse(responseCode = "201", description = "User Registered", content = {
            @Content(schema = @Schema(implementation = TokenDTO.class))
    })
    @PostMapping("/register")
    @Validated(OnCreate.class)
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDTO register(@RequestBody @Valid UserDTO userDto) {
        User newUser = userMapper.toEntity(userDto);
        User result = userService.createUser(newUser);
        SecurityUser securityUser = new SecurityUser(result);
        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(securityUser, userDto.getPassword(), securityUser.getAuthorities());
        return tokenGenerator.issueToken(authentication);
    }

    @Operation(summary = "Issues a new access token with the provided refresh token")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = TokenDTO.class))
    })
    @PostMapping("/refresh-token")
    public TokenDTO refreshToken(@RequestBody TokenDTO payload) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(payload.refreshToken()));
        Jwt jwt = (Jwt) authentication.getCredentials();
        String email = jwt.getSubject();
        userService.getUserByEmail(email);
        return tokenGenerator.issueToken(authentication);
    }

}
