package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import pageObject.Header;
import pageObject.SearchPage;

import utils.ScenarioContext;
import org.assertj.core.api.SoftAssertions;
import utils.common.TextUtils;

public class SearchSteps {

  @When("I search weather with the keyword {string}")
  public void iSearchWeatherWithTheKeyword(String keyword) {
    ScenarioContext.currentContext().set("Search data").with(keyword);
    Header.inputSearchKeyword(keyword);
    Header.submitSearchForm();
  }

  @And("I see the search result display correctly")
  public void theSearchResultWillDisplay() {
    SoftAssertions softly = ScenarioContext.currentContext().get("Assertion", SoftAssertions.class);
    softly.assertThat(SearchPage.getResultItems()).as("The number items").isGreaterThan(0);
    softly.assertThat(SearchPage.getSearchResult())
        .as("The search result")
        .isEqualTo(ScenarioContext.currentContext().get("Search data"));
  }

  @And("I see the search result not display data")
  public void iSeeTheSearchResultNotDisplayData() {
    String alertMessage = TextUtils.removeSpecialCharacters(SearchPage.getAlertMessage());
    SoftAssertions softly = ScenarioContext.currentContext().get("Assertion", SoftAssertions.class);
    softly.assertThat(alertMessage).as("The alert message").containsIgnoringCase("Not Found");
    softly.assertThat(SearchPage.getSearchResult())
        .as("The search result")
        .isEqualTo(ScenarioContext.currentContext().get("Search data"));
  }
}
