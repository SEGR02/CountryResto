package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.dao.entities.Address;
import ar.com.country.restaurant.security.SecurityUser;
import ar.com.country.restaurant.services.AddressService;
import ar.com.country.restaurant.web.dto.AddressDTO;
import ar.com.country.restaurant.web.hateoas.assemblers.AddressModelAssembler;
import ar.com.country.restaurant.web.mappers.AddressMapper;
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
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
@Tag(name = "Addresses", description = "API to manage addresses")
@ApiResponses({
        @ApiResponse(ref = BAD_REQUEST_RESPONSE_REF, responseCode = "400"),
        @ApiResponse(ref = UNAUTHORIZED_RESPONSE_REF, responseCode = "401"),
        @ApiResponse(ref = INTERNAL_SERVER_ERROR_RESPONSE_REF, responseCode = "500")
})
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;
    private final AddressModelAssembler addressModelAssembler;

    @Operation(summary = "Returns the address of the logged user")
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = AddressDTO.class)))
    })
    @GetMapping
    public CollectionModel<AddressDTO> getAddressOfLoggedUser(
            @AuthenticationPrincipal SecurityUser loggedUser
    ) {
        Long userId = loggedUser.getId();
        List<Address> result = addressService.getAddressesOfUser(userId);
        return addressModelAssembler.toCollectionModel(result);
    }

    @Operation(summary = "Returns the address associated with the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address found", content = {
                    @Content(schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404")
    })
    @GetMapping("/{addressId}")
    public AddressDTO getAddressById(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long addressId
    ) {
        Long userId = loggedUser.getId();
        Address result = addressService.getAddressById(userId, addressId);
        return addressModelAssembler.toModel(result);
    }

    @Operation(summary = "Adds an address to the logged user")
    @ApiResponse(responseCode = "201", description = "Address added", content = {
            @Content(schema = @Schema(implementation = AddressDTO.class))
    })
    @PostMapping
    public ResponseEntity<AddressDTO> addAddressToUser(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @RequestBody @Valid AddressDTO addressDto
    ) {
        Long userId = loggedUser.getId();
        Address providedAddress = addressMapper.toEntity(addressDto);
        Address result = addressService.addAddressToUser(userId, providedAddress);
        return ResponseEntity
                .created(URI.create("/addresses/" + result.getId()))
                .body(addressModelAssembler.toModel(result));
    }

    @Operation(summary = "Updates an address with the given payload. All fields must be provided.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated", content = {
                    @Content(schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404")
    })
    @PutMapping("/{addressId}")
    public AddressDTO updateAddress(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long addressId,
            @RequestBody @Valid AddressDTO payload
    ) {
        Long userId = loggedUser.getId();
        Address updatedAddress = addressMapper.toEntity(payload);
        Address result = addressService.updateAddress(userId, addressId, updatedAddress);
        return addressModelAssembler.toModel(result);
    }

    @Operation(summary = "Deletes the address associated with the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address deleted", content = {
                    @Content(schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(ref = NOT_FOUND_RESPONSE_REF, responseCode = "404")
    })
    @DeleteMapping("/{addressId}")
    public AddressDTO deleteAddress(
            @AuthenticationPrincipal SecurityUser loggedUser,
            @PathVariable Long addressId
    ) {
        Long userId = loggedUser.getId();
        Address result = addressService.deleteAddress(userId, addressId);
        return addressMapper.toDto(result);
    }

}
