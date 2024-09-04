
@tag
Feature: Purchase the Order from the eCommerce Website
  I want to use this template for my feature file


 Background:
 Given I landed on the eCommerce website


  @Regression
  Scenario Outline: End to End Flow
    Given Logged in with <username> and <password>
    When I add the <productName> to cart
    And Checkout <productName> and submit the order selecting China
    Then "Thankyou for the order." message is displayed on the confirmation page

    Examples: 
      | username 						 | password 		  | productName  			|
      | vibhor@gmail.com | Vibhor@123			| ZARA COAT 3				|
