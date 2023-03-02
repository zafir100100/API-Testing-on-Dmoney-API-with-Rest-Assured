package testrunner;

import Setup.Setup;
import com.github.javafaker.Faker;
import controller.User;
import io.qameta.allure.Allure;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner {
    @Test(priority = 1, description = "login with invalid creds")
    public void doLoginWithInvalid() throws ConfigurationException, IOException {
        User user = new User();
        user.callingLoginAPI("salman@grr.la", "123");
        String messageExpected = "Password incorrect";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("login with invalid creds");
    }

    @Test(priority = 2, description = "login with valid creds")
    public void doLogin() throws ConfigurationException, IOException {
        User user = new User();
        user.callingLoginAPI("salman@grr.la", "1234");
        String messageExpected = "Login successfully";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("login with valid creds");
    }

    @Test(priority = 3, description = "Get User List")
    public void getUserList() throws IOException {
        User user = new User();
        String id = user.callingUserListAPI();
        System.out.println(id);
        Assert.assertEquals(id, String.valueOf(81));
        Allure.description("Get User List");
    }

    @Test(priority = 4 , description = "Create Customer")
    public void createCustomer() throws IOException, ConfigurationException {
        User user = new User();
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = "cus" + Utils.generateRandomNumber(10000, 99999) + "@test.com";
        String password = "1234";
        String phone_number = "0150" + Utils.generateRandomNumber(1000000, 9999999);
        String nid = "" + Utils.generateRandomNumber(10000000, 99999999);
        String role = "Customer";

        user.createCustomerAPI(name, email, password, phone_number, nid, role);

        String messageExpected = "User created successfully";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Create Customer");



    }

    @Test(priority = 5, description = "Create Agent")
    public void createAgent() throws IOException, ConfigurationException {
        User user = new User();
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String email = "agent" + Utils.generateRandomNumber(10000, 99999) + "@test.com";
        String password = "1234";
        String phone_number = "0150" + Utils.generateRandomNumber(1000000, 9999999);
        String nid = "" + Utils.generateRandomNumber(10000000, 99999999);
        String role = "Agent";

        user.createAgentAPI(name, email, password, phone_number, nid, role);

        String messageExpected = "User created successfully";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Create Agent");

    }

    @Test(priority = 6 , description = "login with Customer Id")
    public void customerLogin() throws IOException, ConfigurationException {

        User user = new User();
        user.callingCustomerLoginAPI();

        String messageExpected = "Login successfully";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("login with Customer Id");
    }

    @Test(priority = 7 , description = "Update Customer Data")
    public void updateCustomerData() throws IOException, ConfigurationException {

        User user = new User();
        String phone_number = "0181" + Utils.generateRandomNumber(1000000, 9999999);
        user.callingUpdateCustomerDataAPI(phone_number);
        String messageExpected = "User updated successfully";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Update Customer Data");
    }

    @Test(priority = 8, description = "Deposit to Agent")
    public void depositToAgent() throws IOException, ConfigurationException {

        User user = new User();
        int amount=2000;
        user.callingDepositAgentAPI(amount);
        String messageExpected = "Deposit successful";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Deposit to Agent");
    }

    @Test(priority = 9,  description = "Deposit to Customer/ Cashin")
    public void depositToCustomer() throws IOException, ConfigurationException {

        User user = new User();
        int amount=1000;
        user.callingDepositCustomerAPI(amount);
        String messageExpected = "Deposit successful";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Deposit to Customer/ Cashin");
    }

    @Test(priority = 10, description = "Check Agent Balance")
    public void checkAgentBalance() throws IOException, ConfigurationException {

        User user = new User();
        user.callingAgentBalanceAPI();
        String messageExpected = "User balance";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Check Agent Balance");
    }

    @Test(priority = 11, description = "Check Customer Balance")
    public void checkCustomerBalance() throws IOException, ConfigurationException {

        User user = new User();
        user.callingCustomerBalanceAPI();
        String messageExpected = "User balance";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Check Customer Balance");
    }

    @Test(priority = 12, description = "Cashout by Customer")
    public void cashOut() throws IOException, ConfigurationException {

        User user = new User();
        int amount = 500;
        user.callingCashOutAPI(amount);
        String messageExpected = "Withdraw successful";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Cashout by Customer");
    }

    @Test(priority = 13, description = "Check Balance of Customer")
    public void checkCustomerBalanceAgain() throws IOException, ConfigurationException {

        User user = new User();
        user.callingCustomerBalanceAPI();
        String messageExpected = "User balance";
        Assert.assertEquals(user.getMessage(), messageExpected);
        Allure.description("Check Balance of Customer");
    }



}
