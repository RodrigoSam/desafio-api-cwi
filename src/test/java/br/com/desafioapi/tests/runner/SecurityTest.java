package br.com.desafioapi.tests.runner;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.desafioapi.suites.SecurityTest.class)
@Suite.SuiteClasses({


})
public class SecurityTest {
}
