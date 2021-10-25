package br.com.desafioapi.tests.booking.tests;

import br.com.desafioapi.base.BaseTest;
import br.com.desafioapi.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
@Feature("Feature - Criação de novas Reservas na Api")
public class PostBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({})
    @DisplayName("Criar  uma nova Reserva")
    public void testCreateANewBooking() {
        Response response = postBookingRequest.requestNewBooking();
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0));


       }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({})
    @DisplayName("Validar um retorno 500 quando o payload da Reserva estiver inválido")
      public void testCreateBookingWithError() {
      Response response = postBookingRequest.requestNewBookingInvalidPayload();
           response.then()
                 .statusCode(500)
                 .time(lessThan(5L),TimeUnit.SECONDS);
        System.out.println("Esta retornando sucess 200, invalid data, deveria retornar um erro");

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({})
    @DisplayName("Validar a criação de mais de uma Reserva em sequência")
    public void testCreateMoreThanOneReservationInARow() {

        int i = 0;
        while (i < 3) {
            testCreateANewBooking();
            given()
                    .when()
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThan(0));
                             i++;
        }
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({})
    @DisplayName("Fazer uma reserva passando parâmetros a mais no payload")
    public void testMakeAReservationWithMoreParametersOnPayload(){
        Response response = postBookingRequest.requesMakeAReservationWithMoreParametersOnPayload();
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .log().all();
        System.out.println("teste está passando mesmo com parâmetros nao previstos na documentacao, retornando 200, quando deveria retornar um 400 badrequest");

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({})
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void testBookWithoutAccept(){
        postBookingRequest.requestBookWithoutAccept("hhg")
                .then()
                .log().all()
                .statusCode(418)
                .time(lessThan(4L),TimeUnit.SECONDS);

    }

    }


