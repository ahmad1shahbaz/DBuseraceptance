@Deposit
Feature: Deposit money
  Scenario : Deposit money between accounts
    Given user is on signs up with following data on digital bank
      |title   |firstName      |lastName    |gender |dob                  |password             |      address     |locality  |region       |postalCode |country      |homePhone        |mobilePhone|
      |   Mr.  | Vader         | Darth      |   M   |      11/28/1990     |Vader.123            |    123 Frisco    |  Bay     |    Stars    |   94512   |   USA       |   6467960123    |64692395   |
    When user logs in with registered email and password "Vader.123"
    And the user creates new checking account for registered user with following data
      | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
      | Standard Checking   | Individual       |  Vader      | 10000.0              |
    And user created second checking account for user
      | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
      | Standard Checking   | Joint           |  Anakin        | 10000.0             |
    And user navigates Transfer Between Accounts
    And user select account1 and account2 enters valid amount and clicks on submit
    Then validate the amount was transferred
