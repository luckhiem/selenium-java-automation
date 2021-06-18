package stepDefinitions.config;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.factory.DriverFactory;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/jsonReports/cucumber.json"},
    features = {"target/test-classes/features/"},
    glue = {"stepDefinitions"},
    tags = "@test",
    monochrome = true)
public class TestRunner {

  @BeforeClass
  public static void setUp() {
    DriverFactory.prepareChromeDriver();
  }
}
