package br.com.desafioapi.tests.booking.requests;

import br.com.desafioapi.tests.booking.payloads.BookingPayLoads;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayLoads bookingPayLoads = new BookingPayLoads();

    @Step("Cria uma nova Reserva")
    public Response requestNewBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(BookingPayLoads.payLoadCreateANewBooking().toString())
                .post("booking");

    }

    @Step("Tenta criar uma nova Reserva passando um payload inválido")
    public Response requestNewBookingInvalidPayload() {
                    return given()
                    .header("Content-Type", "application/json")
                    .when()
                    .body(BookingPayLoads.payLoadInvalidReturn().toString())
                    .post("booking");

        }

    @Step("Tenta criar uma nova reserva passando parâmetros a mais no payload")
        public Response requesMakeAReservationWithMoreParametersOnPayload(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayLoads.createReservationWithMoreParametersThanAllowed().toString())
                .post("booking");

        }

        @Step("Tenta Criar uma nova Reserva sem passar o Accept Header")
        public Response requestBookWithoutAccept(String acceptHeader){
        return given()
                .header("Accept",acceptHeader)
                .header("Content-Type","application/json")
                .when()
                .body(bookingPayLoads.payLoadValidBooking().toString())
                .post("booking");

        }

    }

