Feature: Test search functionality

  @test
  Scenario Outline: Check search result display correctly
    Given I open the browser
    Then I go to the Open Weather Map
    When I search weather with the keyword "<keyword>"
    And I see the search result display correctly
    Examples:
      | keyword     |
      | Hanoi, VN   |
      | Ho Chi Minh |
      | Paris       |

  @test
  Scenario: Check search result display with invalid value
    Given I open the browser
    Then I go to the Open Weather Map
    Then I search weather with the keyword "invalid"
    And I see the search result not display data
