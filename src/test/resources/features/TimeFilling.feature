@Filling

Feature: Filling cells

  Background:
    Given I open "time.epam.com"

  Scenario Outline: Check cells are filled with hours
    When I click Add activity button
    And I enter Activity name in the field
    And Fill the cells with the <hours> of hours per cell
    Then I verify the cells are filled
    Examples:
      | hours |

