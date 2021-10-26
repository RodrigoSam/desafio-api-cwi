package br.com.desafioapi.tests.booking.requests;

import br.com.desafioapi.tests.booking.payloads.BookingPayLoads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayLoads bookingPayLoads = new BookingPayLoads();

    @Step("Atualiza uma Reserva específica utilizando um token válido")
    public Response updateBookingToken(int id,String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayLoads.payLoadValidBooking().toString())
                .put("booking/" + id);

    }

    @Step("Atualiza uma Reserva específica utilizando Basic Auth")
    public Response updateBookingWithBasicAuth(int id){
        return given()
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=" )
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayLoads.payLoadChangeBooking().toString())
                .put("booking/" +id);

    }

    }
