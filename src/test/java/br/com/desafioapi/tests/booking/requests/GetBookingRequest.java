package br.com.desafioapi.tests.booking.requests;


import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetBookingRequest  {

    @Step("Retorna os Ids da Listagem de Reservas")
    public Response bookingReturnIds(){
        return  given()
                .when()
                .get("booking");


    }

    @Step("Retorna uma reserva usando a url + id como parâmetro")
    public Response bookingReturnIdToUrl(int id){
        return given()
                .when()
                .get("booking/"+id);

    }

    @Step("Retorna Id de reservas utilizando parâmetros para criar um filtro")
    public Response getReturnIdWithFilter(String key, String value,String keyTwo, String valueTwo){
          return given().log().all()
                .queryParams(key,value,keyTwo,valueTwo)
                .when()
                .get("booking");

    }

    @Step("Retorna um Id de reserva utilizando parâmetros para criar um filtro")
    public Response getReturnIdFilterNameCheckInAndCheckOut(String key, String value,String keyTwo, String valueTwo, String checkIn, String dateOne, String checkOut, String dateTwo){
        return given()
                .queryParams(key,value,keyTwo,valueTwo,checkIn,dateOne,checkOut,dateTwo)
                .when()
                .get("booking");

    }

    @Step("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public Response getReturnIdWithBadFilter(String key, String value,String keyTwo, String valueTwo){
        return given()
                .queryParams(key,value)
                .queryParam(keyTwo,valueTwo)
                .when()
                .get("booking");

    }



    }


