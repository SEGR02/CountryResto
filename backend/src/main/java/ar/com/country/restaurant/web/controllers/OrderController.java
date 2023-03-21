package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.order.Order;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.OrderService;
import ar.com.country.restaurant.web.dto.order.OrderDTO;
import ar.com.country.restaurant.web.hateoas.assemblers.OrderModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "API to manage orders")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = UNAUTHORIZED_RESPONSE_REF, responseCode = "401"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class OrderController {
    private final OrderService orderService;
    private final OrderModelAssembler orderModelAssembler;

    @Operation(summary = "Returns the orders of the user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
    })
    @GetMapping
    public CollectionModel<OrderDTO> getOrdersOfLoggedUser(@AuthenticationPrincipal SecurityUser loggedUser) {
        Long userId = loggedUser.getId();
        List<Order> result = orderService.getOrdersOfUser(userId);
        return orderModelAssembler.toCollectionModel(result);
    }

    @Operation(summary = "Returns the order with the given id of the logged user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = OrderDTO.class))
    })
    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long orderId
    ) {
        Long userId = loggedUser.getId();
        Order result = orderService.getOrderById(userId, orderId);
        return orderModelAssembler.toModel(result);
    }

    @Operation(summary = "Creates an order for the logged user")
    @ApiResponse(responseCode = "201", description = "OK", content = {
            @Content(schema = @Schema(implementation = OrderDTO.class))
    })
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @RequestParam Long paymentMethodId
    ) {
        Long userId = loggedUser.getId();
        Order result = orderService.createOrder(userId, paymentMethodId);
        return ResponseEntity
                .created(URI.create("/api/orders/" + result.getId()))
                .body(orderModelAssembler.toModel(result));
    }

    @Operation(summary = "Cancels the order of the logged user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(schema = @Schema(implementation = OrderDTO.class))
    })
    @PostMapping("/{orderId}/cancel")
    public OrderDTO cancelOrder(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long orderId
    ) {
        Long userId = loggedUser.getId();
        Order order = orderService.cancelOrder(userId, orderId);
        return orderModelAssembler.toModel(order);
    }

}
