package br.com.desafioapi.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {
    @Step("Exclui uma reserva usando a url + id como parâmetro")
    public Response deleteBooking(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .delete("booking/" + id);

    }

}
