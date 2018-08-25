Feature: Tests associated with the time range switching

  Background:
    Given I open time.epam.com

  @Calendar

    Scenario Outline: Check there is no project available for invalid time period
      And I click on Calendar
      And I click on Calendar month view
      And I click on Calendar year view
      And I open the <year> chosen for check
      And I choose the first month on the list"
      And I choose the time range that is not current
      When I click on User's list
      Then I verify 'You have no project team members to be reviewed' text is displayed
    Examples:
      | year |
      | 1998 |
      | 1971 |


  @MonthView

    Scenario: Check month view is preserved
      And I click on the list of users
      And I click on any user from the list
      And I choose Month view on Control panel
      When I click to return to current User's page on the Warning message
      Then I verify the Month view is preserved
