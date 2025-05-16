Feature: Search functionality

  Background:
    Given the user is on the homepage

  @SearchData
  Scenario Outline: Search using different product names
    When the user searches for "<ProductName>"
    Then relevant results including "<ProductName>" should be displayed

    Examples:
      | ProductName |
      | Laptop      |
      | Mobile      |
      | Tablet      |
