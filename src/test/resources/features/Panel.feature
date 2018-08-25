
Feature: Check User Search Is Correct

  Background:
    Given I open time.epam.com

  @Search

  Scenario Outline:Searching users via Time search
    And I click on current User button
    And I click search button
    When I send String <someName> to the Search field
    Then I check that String <someName> value sent is equal to the String value shown
    Examples:
      | someName          |
      |Alexey Alexandrov  |
      |Ivan Ivanov        |
      |Alexandra Zaytseva |


  @ProjectsList

  Scenario: Projects on project list are shown on the Dashboard
    When I click on projects drop-down list
    Then I get text from the first project on Control Panel and in the Table and compare their values



