package br.com.desafioapi.runner;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({

        AcceptanceTests.class,
        ContractTests.class,
        SecurityTests.class,
        SmokeTests.class



})
public class AllTests {
}
