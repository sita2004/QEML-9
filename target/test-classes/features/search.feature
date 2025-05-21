Feature: Search Functionality

  Background: Given I am on the product search page

  Scenario Outline: Test search functionality with product names

    When I search for "<ProductName>"
    Then I should see search results containing "<ProductNameResults>"

    Examples:
      | ProductName | ProductNameResults |
      | Laptop      | for “Laptop”       |
      | Mobile      | for “Mobile”       |
      | Tablet      | for “Tablet54”     |