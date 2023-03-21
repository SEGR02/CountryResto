package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.PaymentMethodService;
import ar.com.country.restaurant.web.dto.PaymentMethodDTO;
import ar.com.country.restaurant.web.hateoas.assemblers.PaymentMethodModelAssembler;
import ar.com.country.restaurant.web.mappers.PaymentMethodMapper;
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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@RestController
@RequestMapping("/api/payment_methods")
@RequiredArgsConstructor
@Tag(name = "Payment methods", description = "API to manage payment methods")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = UNAUTHORIZED_RESPONSE_REF, responseCode = "401"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;
    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodModelAssembler paymentMethodModelAssembler;

    @Operation(summary = "Returns the payment methods of the logged user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = PaymentMethodDTO.class)))
    })
    @GetMapping
    public CollectionModel<PaymentMethodDTO> getPaymentMethodsOfLoggedUser(
            @AuthenticationPrincipal SecurityUser loggedUser
    ) {
        Long userId = loggedUser.getId();
        List<PaymentMethod> result = paymentMethodService.getPaymentMethodsOfUser(userId);
        return paymentMethodModelAssembler.toCollectionModel(result);
    }

    @Operation(summary = "Returns the payment method associated with the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment method found", content = {
                    @Content(schema = @Schema(implementation = PaymentMethodDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404")
    })
    @GetMapping("/{paymentMethodId}")
    public PaymentMethodDTO getPaymentMethodByIdOfLoggedUser(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long paymentMethodId
    ) {
        Long userId = loggedUser.getId();
        PaymentMethod result = paymentMethodService.getPaymentMethodById(userId, paymentMethodId);
        return paymentMethodModelAssembler.toModel(result);
    }

    @Operation(summary = "Adds a payment method to the logged user")
    @ApiResponse(responseCode = "201", description = "Payment method added", content = {
            @Content(schema = @Schema(implementation = PaymentMethodDTO.class))
    })
    @PostMapping
    public ResponseEntity<PaymentMethodDTO> addPaymentMethodToLoggedUser(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @RequestBody @Valid PaymentMethodDTO paymentMethodDto
    ) {
        Long userId = loggedUser.getId();
        PaymentMethod providedPaymentMethod = paymentMethodMapper.toEntity(paymentMethodDto);
        PaymentMethod result = paymentMethodService.addPaymentMethodToUser(userId, providedPaymentMethod);
        return ResponseEntity
                .created(URI.create("/payment_methods/" + result.getId()))
                .body(paymentMethodModelAssembler.toModel(result));

    }

    @Operation(summary = "Updates a payment method with the given payload. All fields must be provided.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment method updated", content = {
                    @Content(schema = @Schema(implementation = PaymentMethodDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404")
    })
    @PutMapping("/{paymentMethodId}")
    public PaymentMethodDTO updatePaymentMethodOfLoggedUser(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long paymentMethodId,
            @RequestBody @Valid PaymentMethodDTO paymentMethodDto
    ) {
        Long userId = loggedUser.getId();
        PaymentMethod updatedPaymentMethod = paymentMethodMapper.toEntity(paymentMethodDto);
        PaymentMethod result = paymentMethodService.updatePaymentMethod(userId, paymentMethodId, updatedPaymentMethod);
        return paymentMethodModelAssembler.toModel(result);
    }

    @Operation(summary = "Deletes a payment method associated with the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment method deleted", content = {
                    @Content(schema = @Schema(implementation = PaymentMethodDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404")
    })
    @DeleteMapping("/{paymentMethodId}")
    public PaymentMethodDTO deletePaymentMethod(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long paymentMethodId
    ) {
        Long userId = loggedUser.getId();
        PaymentMethod result = paymentMethodService.deletePaymentMethod(userId, paymentMethodId);
        return paymentMethodModelAssembler.toModel(result);
    }

}
