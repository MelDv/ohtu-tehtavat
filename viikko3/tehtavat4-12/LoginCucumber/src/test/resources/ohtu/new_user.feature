Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "Ilkka" and password "sjfdslk990!?" are entered
        Then  system will create user and say "new user registered"

    Scenario: creation fails with already taken username and valid password
        Given command new is selected
        When  username "pekka" and password "sjfdslk990!?" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "er" and password "sjfdslk990!?" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new is selected
        When  username "Alfonse" and password "s3?" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password long enough but consisting of only letters
        Given command new is selected
        When  username "Alfonse" and password "jflkdsjfdidk" are entered
        Then  system will respond with "new user not registered"

    Scenario: eero can successfully generate an account
        Given user "eero" with password "salainen1" is created
        When  username "eero" and password "salainen1" are entered
        Then  system will respond with "new user registered"
 
    Scenario: eero can login
        Given user "eero" with password "salainen1" logs in
        When  username "eero" and password "salainen1" are entered
        Then  system will respond with "logged in" 
