Feature: Vehicle Check
  As a Direct Line Employee
  I want to check if a vehicle has active insurance cover
  So that I can advise the customer correctly

  Scenario: Results are returned when a vehicle exists
    Given I am on the cover check web page
    When I enter a known vehicle registration
    Then I receive insurance cover details

  Scenario: Not results are returned when a vehicle does not exist
    Given I am on the cover check web page
    When I enter an unknown vehicle registration
    Then I do not receive insurance cover details
