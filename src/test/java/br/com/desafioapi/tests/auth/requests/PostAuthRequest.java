package br.com.desafioapi.tests.auth.requests;

import br.com.desafioapi.tests.auth.requests.payloads.AuthPayLoads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    @Step("Retorna o token")
    public Response tokenReturn() {

        AuthPayLoads authPayLoads = new AuthPayLoads();

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayLoads.jSonAuthLogin().toString())
                .post("auth");
    }

    @Step("Busca o token")
    public String getToken(){
        return "token="+this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

}
