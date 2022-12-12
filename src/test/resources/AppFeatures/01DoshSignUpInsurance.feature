@dosh
Feature: Dosh Insurance Sign up



  @dosh1
  Scenario Outline: Verify the Dosh Insurance Sign up
    Given User Launches the Application
    And user clicks on "Sign up" button
    Then select the self registration radio button
    And user clicks on "Proceed" button
    And user Selects individual insurance as "DOSH 365 premium"
    And user clicks on "Continue" button
    And user select the pre health condition as "Yes"
    Then user select the payment method and fill the respective details "<Iteration>"
    And user clicks on "Proceed" button
    Then user should able to confirm the debit details
    And user should able to complete the ID verification from "<Iteration>"
    And user clicks on "Proceed" button
    And user should able to complete the "Insurance" personalization from "<Iteration>"
    And user clicks on Sign up button
    And user should able to enter OTP




    Examples:
      | Iteration |
      | 1         |

