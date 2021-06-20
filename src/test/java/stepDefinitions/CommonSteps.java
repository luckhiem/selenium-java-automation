package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObject.BasePage;
import utils.factory.DriverFactory;

import java.net.MalformedURLException;

public class CommonSteps {

  @Given("I open the browser")
  public void iOpenTheBrowser() throws MalformedURLException {
    DriverFactory.createWebInstance();
    DriverFactory.initPages("pageObject", DriverFactory.getWebDriver());
  }

  @When("I go to the Open Weather Map")
  public void iGoToTheOpenWeatherMap() {
    new BasePage().gotoOpenWeatherMapPage();
  }
}
