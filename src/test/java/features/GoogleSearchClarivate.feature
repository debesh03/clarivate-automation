Feature: Google search for Clarivate Analytics

  @WebApp
  Scenario: Validate clarivate.com as the first google search result
    Given I am on chrome browser
    When I navigate to "https://www.google.com/"
    And I search for "Clarivate Analytics"
    Then first search result should be "clarivate.com"