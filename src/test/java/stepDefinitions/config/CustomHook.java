package stepDefinitions.config;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
  public void afterEachScenario(Scenario scenarioResult) {
    try {
      if (softly.errorsCollected().size() > 0 || scenarioResult.isFailed()) {
        final byte[] screenshot = ((TakesScreenshot) DriverFactory.getWebDriver())
            .getScreenshotAs(OutputType.BYTES);
        scenarioResult.embed(screenshot, "image/png");
      }
    } catch (Exception e) {
      throw e;
    } finally {
      DriverFactory.dismissInstances();
      softly.assertAll();
      System.out.println();
      System.out.println("============================================================");
      LOGGER.warn(String.format("** DONE TEST ***"));
      System.out.println("============================================================");
      System.out.println();
    }
  }
}
