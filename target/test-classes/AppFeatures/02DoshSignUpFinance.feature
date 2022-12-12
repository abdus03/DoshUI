@dosh
Feature: Dosh Financial Sign up



  @dosh
  Scenario Outline: Verify the Dosh Financial Sign up
    Given User Launches the Application
    And user clicks on "Sign up" button
    Then select the self registration radio button
    And user clicks on "Proceed" button
    And user clicks on "DOSH Financial" Menu
    And user Selects individual financial account as "Personal"
    And user clicks on "Continue" button
    Then user select the payment method and fill the respective details "<Iteration>"
    And user clicks on "Proceed" button
    Then user should able to confirm the debit details
    And user should able to complete the ID verification from "<Iteration>"
    And user clicks on Sign up button
    And user should able to complete the "financial" personalization from "<Iteration>"
    And user should able to fill the addresses from "<Iteration>"
    And user clicks on Sign up button
    And user should able to enter OTP




    Examples:
      | Iteration |
      | 1         |

