package br.com.desafioapi.tests.booking.tests;

import br.com.desafioapi.base.BaseTest;
import br.com.desafioapi.suites.AcceptanceTests;
import br.com.desafioapi.suites.AllTests;
import br.com.desafioapi.suites.SmokeTests;
import br.com.desafioapi.tests.auth.requests.PostAuthRequest;
import br.com.desafioapi.tests.booking.requests.DeleteBookingRequest;
import br.com.desafioapi.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Exclusão de Reserva")
public class DeleteBookingTest extends BaseTest {
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SmokeTests.class})
    @DisplayName("Excluir uma Reserva utilizando um token válido")
    public void testeDeleteBooking() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(201);


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceTests.class})
    @DisplayName("Excluir uma Reserva não existente, retorno esperado 405(Method Not Allowed)")
    public void testDeleteANonExistentBooking() {
        int primeiroId = 9999;
        deleteBookingRequest.deleteBooking(primeiroId, postAuthRequest.getToken())
                .then()
                .statusCode(405);


    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AcceptanceTests.class})
    @DisplayName("Excluir uma Reserva sem autorização, retorno esperado 403(Forbidden)")

    public void testDeleteReservationWithoutAuthorization() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBooking(primeiroId,"")
                .then()
                .statusCode(403);

    }
}
