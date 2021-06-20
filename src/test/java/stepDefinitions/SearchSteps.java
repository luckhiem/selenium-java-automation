package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObject.Header;
import pageObject.SearchPage;

import utils.ScenarioContext;
import org.assertj.core.api.SoftAssertions;

public class SearchSteps {

  public final static Logger LOGGER = LogManager.getLogger(SearchSteps.class);

  @When("I search weather with the keyword {string}")
  public void iSearchWeatherWithTheKeyword(String keyword) {
    ScenarioContext.currentContext().set("Search data").with(keyword);
    Header.inputSearchKeyword(keyword);
    Header.submitSearchForm();
  }

  @Then("I see the search result display correctly")
  public void theSearchResultWillDisplay() {
    SoftAssertions softly = ScenarioContext.currentContext().get("Assertion", SoftAssertions.class);
    softly.assertThat(SearchPage.getResultItems()).as("The number items").isGreaterThan(0);
    softly.assertThat(SearchPage.getSearchResult())
        .as("The search result")
        .isEqualTo(ScenarioContext.currentContext().get("Search data"));
    LOGGER.info("Verify the search result display correctly");
  }

  @Then("I see the search result not display data")
  public void iSeeTheSearchResultNotDisplayData() {
    SoftAssertions softly = ScenarioContext.currentContext().get("Assertion", SoftAssertions.class);
    softly.assertThat(SearchPage.getAlertMessage()).as("The alert message").containsIgnoringCase("Not Found");
    softly.assertThat(SearchPage.getSearchResult())
        .as("The search result")
        .isEqualTo(ScenarioContext.currentContext().get("Search data"));
    LOGGER.info("Verify the search result display not found data");
  }
}
