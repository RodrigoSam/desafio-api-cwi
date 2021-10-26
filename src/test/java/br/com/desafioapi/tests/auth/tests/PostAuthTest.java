package br.com.desafioapi.tests.auth.tests;

import br.com.desafioapi.base.BaseTest;
import br.com.desafioapi.suites.AllTests;
import br.com.desafioapi.suites.SmokeTests;
import br.com.desafioapi.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.hamcrest.CoreMatchers.notNullValue;

@Feature("Feature - Autenticação de Usuário")
public class PostAuthTest extends BaseTest {
    PostAuthRequest postAuthRequest =new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retorna token para um usuário")
    public void testValidateTheReturnOfATokenToTheUser(){
        postAuthRequest.tokenReturn()
                .then()
                .statusCode(200)
                .body("token",notNullValue());


    }

}
