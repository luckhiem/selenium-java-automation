package stepDefinitions.config;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import utils.ScenarioContext;
import utils.factory.DriverFactory;

public class CustomHook {

  private final static Logger LOGGER = LogManager.getLogger("Cucumber");
  SoftAssertions softly = null;

  @Before
  public void beforeEachScenario(Scenario scenario) {
    System.out.println();
    System.out.println("============================================================");
    LOGGER.warn(String.format("** AUTOMATION TEST ***"));
    System.out.println("============================================================");
    System.out.println();
    String scenarioName = scenario.getName();
    LOGGER.info(String.format("Starting test: %s", scenarioName));
    softly = new SoftAssertions();
    ScenarioContext.currentContext().set("Assertion").with(softly);
  }

  @After()
  public void afterEachScenario() {
    DriverFactory.dismissInstances();
    softly.assertAll();
    System.out.println();
    System.out.println("============================================================");
    LOGGER.warn(String.format("** DONE TEST ***"));
    System.out.println("============================================================");
    System.out.println();
  }
}
