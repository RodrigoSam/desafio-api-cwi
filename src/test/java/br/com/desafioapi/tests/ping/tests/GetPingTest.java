package br.com.desafioapi.tests.ping.tests;

import br.com.desafioapi.base.BaseTest;
import br.com.desafioapi.tests.ping.requests.GetPingRequest;
import br.com.desafioapi.suites.AllTests;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature - Verificação status da Api")
public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Verificar se a Api está online")
    public void validaApiOnLine(){

        getPingRequest.pingReturnApi()
                .then()
                .statusCode(201)
                .time(lessThan(8L), TimeUnit.SECONDS);
        System.out.println("Status Code ok");
        System.out.println("API respondendo dentro do time esperado ok");

    }

}
