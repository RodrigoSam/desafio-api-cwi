package br.com.desafioapi.tests.runner;


import br.com.desafioapi.tests.auth.tests.PostAuthTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.desafioapi.suites.SmokeTests.class)
@Suite.SuiteClasses({
        PostAuthTest.class

})
public class SmokeTests {
}
