package br.com.desafioapi.tests.booking.tests;

import br.com.desafioapi.base.BaseTest;
import br.com.desafioapi.suites.AllTests;
import br.com.desafioapi.tests.auth.requests.PostAuthRequest;
import br.com.desafioapi.tests.booking.requests.GetBookingRequest;
import br.com.desafioapi.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature - Atualização de Reservas")
public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva usando o token")
    public void testValidateChangingABookingUsingToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId,postAuthRequest.getToken())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva usando o Basic Auth")
    public void testeValidateChangingABookingUsingBasicAuth(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingWithBasicAuth(primeiroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva sem usar o Token, espera-se um retorno 403(Forbidden)")
    public void testChangeAReservationWithoutToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        putBookingRequest.requestChangeAReservationWithoutToken(primeiroId,"")
                .then()
                .statusCode(403)
                .time(lessThan(4L),TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva utilizando um Token inválido, espera-se um retorno 403(Forbidden)")
    public void testChangeABookingWithInvalidToken(){
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        putBookingRequest.requestChangeAReservationInvalidToken(primeiroId,"hdlfasjfjagajasj")
                .then()
                .statusCode(403)
                .time(lessThan(4L),TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(AllTests.class)
    @DisplayName("Alterar uma reserva que não existe, espera-se um retorno 405(Method Not Allowed)")
    public void testChangeANonExistentBooking(){
        putBookingRequest.requestTryingToChangeABookingThatDoesntExist(postAuthRequest.getToken())
                .then()
                .statusCode(405)
                .time(lessThan(4L), TimeUnit.SECONDS);

    }

}
