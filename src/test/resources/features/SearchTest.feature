Feature: As an users, I want to search the weather by city, so that I can see the weather

  @test
  Scenario Outline: Check search result display correctly
    Given I open the browser
    When I go to the Open Weather Map
    When I search weather with the keyword "<keyword>"
    Then I see the search result display correctly
    Examples:
      | keyword     |
      | Hanoi, VN   |
      | Ho Chi Minh |
      | Paris       |

  @test
  Scenario: Check search result display with invalid value
    Given I open the browser
    When I go to the Open Weather Map
    When I search weather with the keyword "invalid"
    Then I see the search result not display data
