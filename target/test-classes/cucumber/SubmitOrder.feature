
@tag
Feature: Purchase the order from Ecommerse Website.
  I want to use this template for my feature file

Background:
	Given I landed on Ecommerse Page


  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." is displayed on confirmation page

    Examples: 
      | username 						| password 		| productName   |
      | mjangid@gmail.com		|	Mj.12345678	| ZARA COAT 3   |
      | mohit2406@gmail.com |	Mj.12345678	| iphone 13 pro |
