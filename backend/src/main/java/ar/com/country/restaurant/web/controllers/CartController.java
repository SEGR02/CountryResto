package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.Cart;
import ar.com.country.restaurant.dao.entities.CartItem;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.CartService;
import ar.com.country.restaurant.web.dto.CartDTO;
import ar.com.country.restaurant.web.dto.CartItemDTO;
import ar.com.country.restaurant.web.hateoas.assemblers.CartModelAssembler;
import ar.com.country.restaurant.web.mappers.CartItemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@Tag(name = "Cart items", description = "API to manage cart items")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = UNAUTHORIZED_RESPONSE_REF, responseCode = "401"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class CartController {
    private final CartService cartService;
    private final CartModelAssembler cartModelAssembler;
    private final CartItemMapper cartItemMapper;

    @Operation(summary = "Returns the cart of the logged user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = CartDTO.class))
    })
    @GetMapping
    public CartDTO getCartOfLoggedUser(@AuthenticationPrincipal SecurityUser loggedUser) {
        Long userId = loggedUser.getId();
        Cart result = cartService.getCartOfUser(userId);
        return cartModelAssembler.toModel(result);
    }

    @Operation(summary = "Adds an item to the cart of the logged user")
    @ApiResponse(responseCode = "201", description = "Cart item created", content = {
            @Content(schema = @Schema(implementation = CartItemDTO.class))
    })
    @PostMapping("/items")
    public ResponseEntity<CartItemDTO> addItemToCart(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @RequestParam Long dishId,
            @RequestBody @Valid CartItemDTO cartItemDto
    ) {
        CartItem item = cartItemMapper.toEntity(cartItemDto);
        Long userId = loggedUser.getId();
        CartItem result = cartService.addCartItem(userId, dishId, item);
        return ResponseEntity
                .created(URI.create("/api/carts/items/" + result.getId()))
                .body(cartItemMapper.toDto(result));
    }

    @Operation(summary = "Removes an item from the cart of the logged user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = CartItemDTO.class))
    })
    @DeleteMapping("/items/{cartItemId}")
    public CartItemDTO removeItemFromCart(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long cartItemId
    ) {
        Long userId = loggedUser.getId();
        CartItem result = cartService.deleteCartItem(userId, cartItemId);
        return cartItemMapper.toDto(result);
    }

}

