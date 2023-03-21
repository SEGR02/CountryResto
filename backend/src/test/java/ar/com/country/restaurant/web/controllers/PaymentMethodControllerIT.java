package ar.com.country.restaurant.web.controllers;

import ar.com.country.restaurant.AbstractIntegrationTest;
import ar.com.country.restaurant.dao.entities.CardType;
import ar.com.country.restaurant.dao.entities.PaymentMethod;
import ar.com.country.restaurant.repositories.PaymentMethodRepository;
import ar.com.country.restaurant.util.DateUtils;
import ar.com.country.restaurant.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/payment_method/data.sql")
@Sql(value = "/payment_method/clear-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class PaymentMethodControllerIT extends AbstractIntegrationTest {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @MockBean(name = "clock")
    private Clock clock;

    /**
     * From the test perspective, "today" is always February 14th, 2023
     */
    @BeforeEach
    public void init() {
        LocalDate february14th2023 = LocalDate.of(2023, Month.FEBRUARY, 14);
        ZoneId zoneId = ZoneId.systemDefault();
        given(clock.instant()).willReturn(february14th2023.atStartOfDay(zoneId).toInstant());
        given(clock.getZone()).willReturn(zoneId);
    }

    @Nested
    class GetPaymentMethods {

        @Test
        public void shouldReturnPaymentMethodsOfLoggedUser() throws Exception {
            doLogin();

            mockMvc.perform(
                            get("/api/payment_methods")
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$._embedded.payment_methods.size()", is(2)));
        }

        @Test
        public void shouldReturnPaymentMethodByIdOfLoggedUser() throws Exception {
            doLogin();
            PaymentMethod firstPaymentMethodOfLoggedUser = getFirstPaymentMethodOfLoggedUser();

            mockMvc.perform(
                            get("/api/payment_methods/" + firstPaymentMethodOfLoggedUser.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").value(firstPaymentMethodOfLoggedUser.getId()))
                    .andExpect(jsonPath("$._links.self.href").value("http://localhost/api/payment_methods/" + firstPaymentMethodOfLoggedUser.getId()))
                    .andExpect(jsonPath("$._links.user.href").value("http://localhost/api/users/1"));
        }

        @Test
        public void shouldReturn404_whenPaymentMethodNotFound() throws Exception {
            doLogin();
            long noSuchPaymentMethodId = -50L;

            mockMvc.perform(
                            get("/api/payment_methods/" + noSuchPaymentMethodId)
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    class CreatePaymentMethod {

        @Test
        public void shouldAddPaymentMethodToLoggedUser_whenValidFields() throws Exception {
            doLogin();
            PaymentMethod providedPaymentMethod = PaymentMethod.builder()
                    .type(CardType.CREDIT)
                    .number("1111 1111 1111 1111")
                    .holder("Julion Alvarez")
                    .expirationDate(DateUtils.today())
                    .cvv("1234")
                    .build();

            mockMvc.perform(
                            post("/api/payment_methods")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(providedPaymentMethod))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(header().exists("Location"))
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.type").value("CREDIT"))
                    .andExpect(jsonPath("$.number").value("1111 1111 1111 1111"))
                    .andExpect(jsonPath("$.holder").value("Julion Alvarez"))
                    .andExpect(jsonPath("$.expirationDate").value("2023-02-14"))
                    .andExpect(jsonPath("$.cvv").value("1234"))
                    .andExpect(jsonPath("$._links.self.href").exists())
                    .andExpect(jsonPath("$._links.user.href").value("http://localhost/api/users/1"));
        }

        @Test
        public void shouldReturn409Conflict_whenACardWithTheSameNumberAlreadyExists() throws Exception {
            doLogin();
            PaymentMethod paymentMethodWithAlreadyTakenCardNumber = PaymentMethod.builder()
                    .type(CardType.CREDIT)
                    .number("1234 5678 1234 5678")
                    .holder("Julion Alvarez")
                    .expirationDate(DateUtils.today())
                    .cvv("1234")
                    .build();

            mockMvc.perform(
                            post("/api/payment_methods")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(paymentMethodWithAlreadyTakenCardNumber))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isConflict());
        }

        @Test
        public void shouldReturn400_whenMissingFields() throws Exception {
            doLogin();
            PaymentMethod paymentMethodWithMissingFields = PaymentMethod.builder()
                    .type(CardType.DEBIT)
                    .holder("Nicolás C. Ibarra")
                    .cvv("589")
                    .build();

            mockMvc.perform(
                            post("/api/payment_methods")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(paymentMethodWithMissingFields))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isBadRequest());
        }

    }

    @Nested
    class UpdatePaymentMethod {

        @Test
        public void shouldUpdatePaymentMethod_whenValidFields() throws Exception {
            doLogin();
            PaymentMethod firstPaymentMethodOfLoggedUser = getFirstPaymentMethodOfLoggedUser();
            PaymentMethod updatedPaymentMethod = PaymentMethod.builder()
                    .type(CardType.DEBIT)
                    .number("1234 5678 1234 5678")
                    .holder("Julion Alvarez")
                    .expirationDate(DateUtils.tomorrow())
                    .cvv("123")
                    .build();

            mockMvc.perform(
                            put("/api/payment_methods/" + firstPaymentMethodOfLoggedUser.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedPaymentMethod))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.type").value("DEBIT"))
                    .andExpect(jsonPath("$.number").value("1234 5678 1234 5678"))
                    .andExpect(jsonPath("$.holder").value("Julion Alvarez"))
                    .andExpect(jsonPath("$.expirationDate").value("2023-02-15"))
                    .andExpect(jsonPath("$.cvv").value("123"))
                    .andExpect(jsonPath("$._links.self.href").value("http://localhost/api/payment_methods/" + firstPaymentMethodOfLoggedUser.getId()))
                    .andExpect(jsonPath("$._links.user.href").value("http://localhost/api/users/1"));
        }

        @Test
        public void shouldReturn409Conflict_whenUpdatedCardNumberAlreadyExists() throws Exception {
            doLogin();
            PaymentMethod firstPaymentMethodOfLoggedUser = getFirstPaymentMethodOfLoggedUser();
            PaymentMethod paymentMethodWithTakenCardNumber = PaymentMethod.builder()
                    .type(CardType.DEBIT)
                    .number("4321 5678 4321 5678")
                    .holder("Julion Alvarez")
                    .expirationDate(DateUtils.tomorrow())
                    .cvv("123")
                    .build();

            mockMvc.perform(
                            put("/api/payment_methods/" + firstPaymentMethodOfLoggedUser.getId())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(paymentMethodWithTakenCardNumber))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isConflict());
        }

        @Test
        public void shouldReturn404_whenPaymentMethodToUpdateNotFound() throws Exception {
            doLogin();
            long noSuchPaymentMethodId = -50L;
            PaymentMethod updatedPaymentMethod = PaymentMethod.builder()
                    .type(CardType.DEBIT)
                    .number("1234 5678 1234 5678")
                    .holder("Julion Alvarez")
                    .expirationDate(DateUtils.tomorrow())
                    .cvv("123")
                    .build();

            mockMvc.perform(
                            put("/api/payment_methods/" + noSuchPaymentMethodId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(JsonUtils.asJsonString(updatedPaymentMethod))
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    class DeletePaymentMethod {

        @Test
        public void shouldDeletePaymentMethodOfLoggedUser() throws Exception {
            doLogin();
            PaymentMethod firstPaymentMethodOfLoggedUser = getFirstPaymentMethodOfLoggedUser();

            mockMvc.perform(
                            delete("/api/payment_methods/" + firstPaymentMethodOfLoggedUser.getId())
                                    .headers(authHeader())
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(jsonPath("$.id").exists());
        }

        @Test
        public void shouldReturn404_whenPaymentMethodNotFound() throws Exception {
            doLogin();
            long noSuchPaymentMethodId = -50L;

            mockMvc.perform(
                            delete("/api/payment_methods/" + noSuchPaymentMethodId)
                                    .headers(authHeader())
                    )
                    .andExpect(status().isNotFound());
        }

    }

    private PaymentMethod getFirstPaymentMethodOfLoggedUser() {
        Long loggedUserId = 1L;
        return paymentMethodRepository.findAllByUserId(loggedUserId).get(0);
    }

}
