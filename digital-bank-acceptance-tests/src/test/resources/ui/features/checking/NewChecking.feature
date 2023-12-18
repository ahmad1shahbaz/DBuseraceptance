Feature: Creating new checking account
  Scenario: Create a standard individual checking account
    Given user logged in as "vader@sith.com" "Lovetwo.123"
    When the user creates new checking account with following data
    | checkingAccountType | accountOwnership | accountName | initialDepositAmount |
    | Standard Checking   | Individual       |  Vader      | 10000.0              |
    Then the user should see the green "Successfully created new Standard Checking account named Vader" message
    And user should see newly added account card
    |accountName | accountType      | ownership  | accountNumber | interestRate | balance |
    | Vader      | Standard Checking| Individual | 4861353535    | 0.0%         | 10000.0 |

