package br.com.desafioapi.tests.runner;

import br.com.desafioapi.tests.booking.tests.DeleteBookingTest;
import br.com.desafioapi.tests.booking.tests.GetBookingTest;
import br.com.desafioapi.tests.booking.tests.PostBookingTest;
import br.com.desafioapi.tests.booking.tests.PutBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.desafioapi.suites.AcceptanceTests.class)
@Suite.SuiteClasses({
    AcceptanceTests.class,
    DeleteBookingTest.class,
        GetBookingTest.class,
        PostBookingTest.class,
        PutBookingTest.class


})
public class AcceptanceTests{
}
