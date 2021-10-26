package br.com.desafioapi.tests.runner;

import br.com.desafioapi.tests.auth.tests.PostAuthTest;
import br.com.desafioapi.tests.booking.tests.GetBookingTest;
import br.com.desafioapi.tests.booking.tests.PostBookingTest;
import br.com.desafioapi.tests.booking.tests.PutBookingTest;
import br.com.desafioapi.tests.ping.tests.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.desafioapi.suites.AllTests.class)
@Suite.SuiteClasses({

     /*   GetPingTest.class,
        GetBookingTest.class,
        PostAuthTest.class,
        PutBookingTest.class,
        PostBookingTest.class,*/
        AcceptanceTests.class,
        ContractTests.class,
        SecurityTests.class,
        SmokeTests.class



})
public class AllTests {
}
