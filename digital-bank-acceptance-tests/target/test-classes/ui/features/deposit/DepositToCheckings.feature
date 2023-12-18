@Deposit
Feature: Deposit To Checking
  Scenario: Deposit checking positive case
    Given user is on signs up with following data on digital bank
      |title   |firstName      |lastName    |gender |dob                  |password             |      address     |locality  |region       |postalCode |country      |homePhone        |mobilePhone|
      |   Mr.  | Vader         | Darth      |   M   |      11/28/1990     |Vader.123            |    123 Frisco    |  Bay     |    Stars    |   94512   |   USA       |   6467960123    |64692395   |
    When user logs in with registered email and password "Vader.123"
    And the user creates new checking account for registered user with following data
      | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
      | Standard Checking   | Individual       |  Vader      | 10000.0              |
    And user should go deposit page and choose account and deposit income
    |accountNameAndType   |           amount |
    |Vader (Standard Checking)|   100 |
    |                         |     100  |
    Then user should validate view checking with following data
      |operationType      |     category |description            |
      | Online Deposit           | Income       | (DPT) - Online Deposit  |
Scenario Outline: Deposit to checking negative case
  Given user is on signs up with following data on digital bank
    |title   |firstName      |lastName    |gender |dob                  |password             |      address     |locality  |region       |postalCode |country      |homePhone        |mobilePhone|
    |   Mr.  | Vader         | Darth      |   M   |      11/28/1990     |Vader.123            |    123 Frisco    |  Bay     |    Stars    |   94512   |   USA       |   6467960123    |64692395   |
  When user logs in with registered email and password "Vader.123"
  And the user creates new checking account for registered user with following data
    | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
    | Standard Checking   | Individual       |  Vader      | 10000.0              |
  And user should go deposit page and choose <accountNameAndType> and deposit <amount>
    |accountNameAndType   |       amount |
    |<accountNameAndType> |      <amount>|
  Examples:
    |accountNameAndType         |       amount |
    |                           |  100         |
    |  Vader (Standard Checking)|            |
    |  Vader (Standard Checking)|      -1     |
    |  Vader (Standard Checking)|      ab    |
    |  Vader (Standard Checking)|      1!2    |







