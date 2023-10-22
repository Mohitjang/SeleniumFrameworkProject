
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @Error
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerse Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username 						| password 		|
      | mjangid@gmail.com		|	Mj.34678	|
      | mohit2406@gmail.com |	Mj.12378	|
