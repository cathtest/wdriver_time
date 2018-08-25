Feature: Checks associated with Excel export

  Background:
    Given I open time.epam.com

  @Excel

  Scenario: Check export to Excel
    And Click on Export to Excel
    When Click on Export button
    Then Compare the project names