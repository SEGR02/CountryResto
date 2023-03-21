package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.AbstractIntegrationTest;
import ar.com.country.restaurant.dao.entities.Address;
import ar.com.country.restaurant.repositories.AddressRepository;
import ar.com.country.restaurant.utils.JsonUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/address/data.sql")
@Sql(value = "/address/clear-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AddressControllerIT extends AbstractIntegrationTest {
    @Autowired
    private AddressRepository addressRepository;

    @Nested
    class GetAddresses {

        @Test
        public void shouldReturn401_whenAuthTokenNotPresent() throws Exception {
            mockMvc.perform(
                            get("/api/addresses")
                    )
                    .andExpect(status().isUnauthorized());
        }

        @Test
        public void shouldReturnAddressesOfLoggedUser() throws Exception {
            doLogin();

            mockMvc.perform(
                            get("/api/addresses")
                                    .headers(authHeader())
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$._embedded.addresses.size()", is(1)));
        }

        @Test
        public void shouldReturnAddressById() throws Exception {
            doLogin();
            Address firstAddress = getFirstAddressOfLoggedUser();

            mockMvc.perform(
                            get("/api/addresses/" + firstAddress.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").value(firstAddress.getId()))
                    .andExpect(jsonPath("$.street").value(firstAddress.getStreet()))
                    .andExpect(jsonPath("$.city").value(firstAddress.getCity()))
                    .andExpect(jsonPath("$.state").value(firstAddress.getState()))
                    .andExpect(jsonPath("$.country").value(firstAddress.getCountry()))
                    .andExpect(jsonPath("$.clarifications").value(firstAddress.getClarifications()))
                    .andExpect(jsonPath("$._links.self.href").value("http://localhost/api/addresses/" + firstAddress.getId()))
                    .andExpect(jsonPath("$._links.user.href").value("http://localhost/api/users/1"));
        }

        @Test
        public void shouldReturn404_whenAddressNotFound() throws Exception {
            doLogin();
            long noSuchAddressId = -50L;

            mockMvc.perform(
                            get("/api/addresses/" + noSuchAddressId)
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    class CreateAddress {

        @Test
        public void shouldAddAddressToLoggedUser_whenValidFields() throws Exception {
            doLogin();
            Address providedAddress = Address.builder()
                    .street("Street 50")
                    .number("300")
                    .city("Mérida")
                    .state("Yucatán")
                    .country("México")
                    .zipCode("97500")
                    .clarifications("White house with red door")
                    .build();

            mockMvc.perform(
                            post("/api/addresses")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(providedAddress))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(header().exists("Location"))
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.city").value("Mérida"))
                    .andExpect(jsonPath("$.state").value("Yucatán"))
                    .andExpect(jsonPath("$.country").value("México"))
                    .andExpect(jsonPath("$.clarifications").value("White house with red door"))
                    .andExpect(jsonPath("$._links.self").exists())
                    .andExpect(jsonPath("$._links.user.href").value("http://localhost/api/users/1"));
        }

        @Test
        public void shouldReturn400_whenNoBodyInRequest() throws Exception {
            doLogin();

            mockMvc.perform(
                            post("/api/addresses")
                                    .headers(authHeader())
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void shouldReturn400_whenMissingFields() throws Exception {
            doLogin();
            Address invalidProvidedAddress = Address.builder()
                    .street("Street 5")
                    .city("Mérida")
                    .zipCode("97600")
                    .build();

            mockMvc.perform(
                            post("/api/addresses")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(invalidProvidedAddress))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class UpdateAddress {

        @Test
        public void shouldUpdateAddress_whenValidFields() throws Exception {
            doLogin();
            Address firstAddress = getFirstAddressOfLoggedUser();
            Address updatedAddress = Address.builder()
                    .street("Street 50")
                    .number("540")
                    .city("Kanasín")
                    .state("Yucatán")
                    .country("México")
                    .zipCode("97150")
                    .build();

            mockMvc.perform(
                            put("/api/addresses/" + firstAddress.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedAddress))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.street").value("Street 50"))
                    .andExpect(jsonPath("$.number").value("540"))
                    .andExpect(jsonPath("$.city").value("Kanasín"))
                    .andExpect(jsonPath("$.state").value("Yucatán"))
                    .andExpect(jsonPath("$.country").value("México"))
                    .andExpect(jsonPath("$.zipCode").value("97150"))
                    .andExpect(jsonPath("$._links.self").exists())
                    .andExpect(jsonPath("$._links.user.href").value("http://localhost/api/users/1"));
        }

        @Test
        public void shouldReturn404_whenAddressNotFound() throws Exception {
            doLogin();
            long noSuchAddressId = -50L;
            Address updatedAddress = Address.builder()
                    .street("Street 50")
                    .number("540")
                    .city("Kanasín")
                    .state("Yucatán")
                    .country("México")
                    .zipCode("97150")
                    .build();

            mockMvc.perform(
                            put("/api/addresses/" + noSuchAddressId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedAddress))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

        @Test
        public void shouldReturn400_whenMissingFields() throws Exception {
            doLogin();
            Address firstAddress = getFirstAddressOfLoggedUser();
            Address updatedAddressWithMissingFields = Address.builder()
                    .street("Street 50")
                    .number("540")
                    .city("Kanasín")
                    .build();

            mockMvc.perform(
                            put("/api/addresses/" + firstAddress.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedAddressWithMissingFields))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class DeleteAddress {

        @Test
        public void shouldDeleteAddressOfLoggedUser() throws Exception {
            doLogin();
            Address firstAddressOfLoggedUser = getFirstAddressOfLoggedUser();

            mockMvc.perform(
                            delete("/api/addresses/" + firstAddressOfLoggedUser.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").exists());
        }

        @Test
        public void shouldReturn404_whenAddressNotFound() throws Exception {
            doLogin();
            long noSuchAddressId = -50L;

            mockMvc.perform(
                            delete("/api/addresses/" + noSuchAddressId)
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    private Address getFirstAddressOfLoggedUser() {
        Long loggedUserId = 1L;
        return addressRepository.findAddressesByUserId(loggedUserId).get(0);
    }

}
