package br.com.desafioapi.runner;

import br.com.desafioapi.tests.booking.tests.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.desafioapi.suites.ContractTests.class)
@Suite.SuiteClasses({
        GetBookingTest.class

})
public class ContractTests {

}
