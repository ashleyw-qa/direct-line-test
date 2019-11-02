# This should really be unit tested

Feature: Vehicle Check Validation
  As a Direct Line Employee
  I want to be confident that the system validates my data entry
  So that I minimise my mistakes

  Scenario: An error is displayed when a search is conducted without text
    Given I am on the cover check web page
    When I do not enter any text in the search field
    Then I receive an error message

  Scenario: White space is ignored when searching for a vehicle
    Given I am on the cover check web page
    When I enter a known vehicle registration with whitespace characters
    Then I receive insurance cover details