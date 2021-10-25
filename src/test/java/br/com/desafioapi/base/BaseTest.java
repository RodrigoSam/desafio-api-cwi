package br.com.desafioapi.base;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {
    @Before
    public void setup(){
        RestAssured.baseURI= "https://treinamento-api.herokuapp.com/";
    }


}
