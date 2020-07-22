Feature: Windows 10 Calculator operations

  @DesktopApp
  Scenario: Validate square root operation
    Given I am on windows calculator app
    When I enter number "65575"
    And I click on square root button
    Then the result should be "256.0761605460376"
