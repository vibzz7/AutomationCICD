
@tag
Feature: Validating if error are found
  I want to use this template for my feature file
  

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on the eCommerce website
    When Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed on login page

    Examples: 
      | username 				 | password 		  |
      | vibhor@gmail.com | Vibhor@12			|
