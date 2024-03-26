Feature: Login Functionality
    		 As a user, I want to verify the login functionality page
    		     
    
  	User Registration
    	As a user, I want to register with my first name, last name, and email.
    
     User Authentication
    As a registered user
    I want to log in to the system
    So that I can access my account

  Scenario: User logs in with valid credentials
    Given the user is on the login page
    When the user enters "{string}" and "{string}"
    Then the user should be logged in successfully

  Scenario: Verify user details
    Given the following users exist:
      | Name  | Age | Email             |
      | Alice |  30 | alice@example.com |
      | Bob   |  35 | bob@example.com   |
      | Carol |  40 | carol@example.com |
    When the user searches for "<name>"
    Then the user details should be displayed

  Scenario: Verify user details
    Given the "<Name>" "<age>" and "<Email>" exist:
      | Name    | Age | Email               |
      | "Alice" |  30 | "alice@example.com" |
      | "Bob"   |  35 | "bob@example.com"   |
      | "Carol" |  40 | "carol@example.com" |
    When the user searches for "<name>"
    Then the user details should be displayed

  Scenario: Login with the credential
    Given User is on the login page
    When User enter "abc@gmail.com" as email
    And User enter "password" as password
    And User click on the login button
    Then User should be logged in succefully

  Scenario Outline: Login with credentials
    Given User is on the login page
    When User enters "<username>" and "<password>"
    And User clicks on the submit button
    Then User should be successfully logged in

    Examples: 
      | username         | password    |
      | "abc@gmail.com"  | "password"  |
      | "abc1@gmail.com" | "p1assword" |

  Scenario Outline: User login with valid different credentials
    Given User is on the login page
    When User enters "<username>" and "<password>"
    And User clicks on the login button
    Then User should be logged in successfully

    Examples: 
      | username | password |
      | user1    | pass123  |
      | user2    | pass456  |

  #   This scenario outline is useful when you want to test the login functionality with multiple sets of credentials,
  #  including both the username and password, and also includes a step for clicking on the login button.
  Scenario Outline: User logs in with different credentials
    Given the user is on the login page
    When the user enters "<username>" and "<password>"
    Then the user should be logged in successfully

    Examples: 
      | username            | password   |
      | "user1@example.com" | "password" |
      | "user2@example.com" | "p@ssw0rd" |

  #This scenario outline is similar to the first one but does not include a step for clicking on the login button.
  #It assumes that the login button action is implicit in the step "When the user enters the username and password".
  Scenario Outline: User enters first name, last name, and email
    Given User is on the registration page
    When User enters "<firstname>" and "<lastname>" and "<email>"
    Then User clicks on the register button
    And User should be successfully registered

    Examples: 
      | firstname | lastname | email                  |
      | John      | Doe      | john.doe@example.com   |
      | Jane      | Smith    | jane.smith@example.com |
