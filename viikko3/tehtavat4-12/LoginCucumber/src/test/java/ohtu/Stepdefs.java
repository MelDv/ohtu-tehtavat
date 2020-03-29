package ohtu;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.domain.User;
import ohtu.services.*;

public class Stepdefs {

    App app;
    StubIO io;
    UserDao userDao;
    AuthenticationService auth;
    List<String> inputLines;

    @Before
    public void setup() {
        userDao = new InMemoryUserDao();
        auth = new AuthenticationService(userDao);
        inputLines = new ArrayList<>();
    }

    @Given("^command login is selected$")
    public void commandLoginSelected() throws Throwable {
        inputLines.add("login");
    }

    @When("username {string} and password {string} are entered")
    public void usernameAndPasswordAreEntered(String username, String password) {
        inputLines.add(username);
        inputLines.add(password);

        io = new StubIO(inputLines);
        app = new App(io, auth);
        app.run();
    }

    @When("password {string} doesn't match username {string}")
    public void passwordDoesnTMatchUsername(String string, String string2) {
        inputLines.add(string);
        inputLines.add(string2);

        io = new StubIO(inputLines);
        app = new App(io, auth);
        app.run();
    }

    @When("username {string} doesn't exist")
    public void usernameDoesnTExist(String string) {
        inputLines.add(string);

        io = new StubIO(inputLines);
        app = new App(io, auth);
        app.run();
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        assertTrue(io.getPrints().contains(expectedOutput));
    }

    @Given("^command new is selected$")
    public void commandNewSelected() throws Throwable {
        inputLines.add("new");
    }

    @When("creation is successful with valid username and password")
    public void validUsernameAndPassword(String username, String password) {
        auth.createUser(username, password);
    }

    @When("creation fails with already taken username and valid password")
    public void usernameIsTaken(String username, String password) {
        auth.createUser(username, password);
    }

    @When("creation fails with too short username and valid password")
    public void usernameIsTooShort(String username, String password) {
        auth.createUser(username, password);
    }

    @When("creation fails with valid username and too short password")
    public void passwordIsTooShort(String username, String password) {
        auth.createUser(username, password);
    }

    @When("creation fails with valid username and password long enough but consisting of only letters")
    public void passwordConsistsOfOnlyLetters(String username, String password) {
        auth.createUser(username, password);
    }

    @Given("user {string} with password {string} is created")
    public void userWithPasswordIsCreated(String string, String string2) throws Throwable {
        inputLines.add("new");
    }

    @When("eero can successfully generate an account")
    public void userEeroIsCreated(String string, String string2) {
        User u = new User(string, string2);
        userDao.add(u);
        inputLines.add(string);
        inputLines.add(string2);

        io = new StubIO(inputLines);
        app = new App(io, auth);
        app.run();
    }

//
//    @Given("user {string} with password {string} logs in")
//    public void userWithPasswordLogsIn(String string, String string2) throws Throwable {
//        inputLines.add("login");
//    }
//
//    @When("eero can login")
//    public void eeroCanLogIn(String username, String password) {
//        auth.createUser(username, password);
//    }
//
//    @Then("system will create user and say {string}")
//    public void systemWillCreateUserAndSay(String string) {
//        assertTrue(io.getPrints().contains(string));
//    }
}
