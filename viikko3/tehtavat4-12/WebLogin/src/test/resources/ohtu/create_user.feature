Feature: A new user account can be created if a proper unused username and a proper password are given

    Scenario: user can login with successfully generated account
        Given user with username "lea" with password "salainen1" is successfully created
        And   login is selected
        When  username "lea" and password "salainen1" are given
        Then  a new user is created  

    Scenario: user can not login with account that is not successfully created
        Given user with username "aa" and password "bad" is tried to be created
        And   login is selected
        When  username "aa" and password "bad" are given
        Then  user is not created and error message is given