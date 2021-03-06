package br.com.desafioapi.tests.booking.tests;

import br.com.desafioapi.base.BaseTest;
import br.com.desafioapi.suites.*;
import br.com.desafioapi.tests.booking.requests.GetBookingRequest;
import br.com.desafioapi.tests.booking.requests.PostBookingRequest;
import br.com.desafioapi.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature - Busca de Reservas")
public class GetBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SmokeTests.class})
    @DisplayName("Listar Ids de reservas")
    public void testValidatesBookingIdList() {

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size", greaterThan(0))
                .body("firstname",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({ContractTests.class})
    @DisplayName("Garantir o Schema de retorno da listagem de reservas")
    public void testValidatesBookingListingSchema() {

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id específico")
    public void testSearchForASpecificId() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        getBookingRequest.bookingReturnIdToUrl(primeiroId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("firstname", notNullValue());


    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({ContractTests.class})
    @DisplayName("Garantir o Schema de retorno de uma reserva específica")
    public void testValidateSchemaOfASpecificId() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        getBookingRequest.bookingReturnIdToUrl(primeiroId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "validaid"))));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id utilizando o filtro firstname")
    public void testSearchForIdWithFilterFirstName() {
    Response response = postBookingRequest.requestNewBooking();
                 given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String firstName = extractor.get("booking.firstname");

        getBookingRequest.getReturnIdWithFilter("firstname", firstName,"","")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("firstname",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id utilizando o filtro lastname")
    public void testSearchForIdWithFilterLastName() {
        Response response = postBookingRequest.requestNewBooking();
                given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String lastname = extractor.get("booking.lastname");

        getBookingRequest.getReturnIdWithFilter("lastname", lastname, "","")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("firstname",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id utilizando o filtro checkin")
    public void testSearchForIdWithFilterCheckIn() {
        Response response = postBookingRequest.requestNewBooking();
                given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();
        String checkin = extractor.get("booking.bookingdates.checkin");

        getBookingRequest.getReturnIdWithFilter("checkin",checkin,"","")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("firstname",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id utilizando o filtro checkout")
    public void testSearchForIdWithFilterCheckOut() {
        Response response = postBookingRequest.requestNewBooking();
                 given()
                .contentType("application/json")
                .get("booking");

        JsonPath extractor = response.jsonPath();

        String checkout = extractor.get("booking.bookingdates.checkout");

        getBookingRequest.getReturnIdWithFilter("checkout",checkout,"","")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id utilizando duas datas checkout, esperasse um retorno 500(Internal Server Error) ")
    public void testSearchForAnIdWithFilterCheckOutAndCheckOut() {

        getBookingRequest.getReturnIdWithBadFilter("checkout","2019-01-01","checkout","2021-08-18")
                .then()
                .statusCode(500)
                .time(lessThan(4L),TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({SmokeTests.class})
    @DisplayName("Listar um Id utilizando o filtro name, checkin e checkout")

    // Utilizei uma data menor a que eu queria buscar no filtro checkin pois percebi conflito quanto a documentação,
    // conforme documentei na File "Analise_sobre_comportamentos_inesperados_e_sugestoes_de_aprimoramento_da_API, no end-point booking."
    // No filtro checkout também encontrei comportamento incompatível,
    // documentado na File "Analise_sobre_comportamentos_inesperados_e_sugestoes_de_aprimoramento_da_API, no end-point booking no end-point booking".

    public void testSearchForAnIdWithParameterNameCheckInAndCheckout() {
        getBookingRequest.getReturnIdFilterNameCheckInAndCheckOut("firstname","Jim","lastname","Brown",
                        "checkin","2017-12-31","checkout","2019-01-01")
                .then()
                .statusCode(200)
                .body("bookingid",notNullValue())
                .body("firstname",notNullValue());

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AcceptanceTests.class})
    @DisplayName("Passar um filtro checkin mal formatado e verificar se a Api retorna o erro 500(Internal Server Error) conforme o esperado")
    public void testReturnErrorWithBadFilter(){
        getBookingRequest.getReturnIdWithBadFilter("checkin","2017-31-12","","")
                .then()
                .statusCode(500)
                .time(lessThan(8L), TimeUnit.SECONDS);

            }

    }

