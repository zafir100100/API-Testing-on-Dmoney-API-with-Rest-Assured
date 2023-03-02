package controller;

import Setup.Setup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class User extends Setup {
    public User() throws IOException {
        initConfig();
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void callingLoginAPI(String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(email, password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();
        String token = response.get("token");
        String message = response.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);
    }

    public String callingUserListAPI() throws IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .when()
                        .get("/user/list")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        String id = response.get("users[0].id").toString();
        return id;
    }

    public void createCustomerAPI(String name, String email,
                                  String password, String phone_number,
                                  String nid, String role) throws IOException, ConfigurationException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(name, email, password, phone_number, nid, role);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                        .body(userModel)
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();
        String id = response.get("user.id").toString();
        Utils.setEnvVariable("customerPhoneNumber", phone_number);
        Utils.setEnvVariable("customerEmail", email);
        Utils.setEnvVariable("customerPassword", password);
        Utils.setEnvVariable("customerId", id);

        String message = response.get("message");
        setMessage(message);

        //  System.out.println(response.);
//        ResponseBody body = res.getBody();
//        // By using the ResponseBody.asString() method, we can convert the  body
//        // into the string representation.
//        System.out.println("Response Body is: " + body.asString());

    }

    public void createAgentAPI(String name, String email, String password, String phone_number, String nid, String role) throws IOException, ConfigurationException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(name, email, password, phone_number, nid, role);
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                        .body(userModel)
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();
        String id = response.get("user.id").toString();

        Utils.setEnvVariable("agentPhoneNumber", phone_number);
        Utils.setEnvVariable("agentEmail", email);
        Utils.setEnvVariable("agentPassword", password);
        Utils.setEnvVariable("agentId", id);

        String message = response.get("message");
        setMessage(message);

    }

    public void callingCustomerLoginAPI() throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        String email=prop.getProperty("customerEmail");
        String password=prop.getProperty("customerPassword");
        UserModel userModel = new UserModel(email, password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();
        String token = response.get("token");
        String message = response.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);
    }


    public void callingUpdateCustomerDataAPI(String phone_number) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(phone_number);


        String customerId=prop.getProperty("customerId");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                        .body("{\n" +
                                "    \"phone_number\": \""+phone_number+"\"\n" +
                                "}")
                        .when()
                        .patch("/user/update/"+customerId)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();

        String message = response.get("message");
        setMessage(message);
        Utils.setEnvVariable("customerPhoneNumber", phone_number);
    }

    public void callingDepositAgentAPI(int amount) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        String agentPhoneNumber=prop.getProperty("agentPhoneNumber");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                        .body("{\n" +
                                "    \"from_account\":\"SYSTEM\",\n" +
                                "    \"to_account\":\""+agentPhoneNumber+"\",\n" +
                                "    \"amount\":"+amount+"\n" +
                                "}")
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();

        String message = response.get("message");
        setMessage(message);
    }

    public void callingDepositCustomerAPI(int amount) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        String agentPhoneNumber=prop.getProperty("agentPhoneNumber");
        String customerPhoneNumber=prop.getProperty("customerPhoneNumber");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                        .body("{\n" +
                                "    \"from_account\":\""+agentPhoneNumber+"\",\n" +
                                "    \"to_account\":\""+customerPhoneNumber+"\",\n" +
                                "    \"amount\":"+amount+"\n" +
                                "}")
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(201).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();

        String message = response.get("message");
        setMessage(message);
    }

    public void callingAgentBalanceAPI() throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        String agentPhoneNumber=prop.getProperty("agentPhoneNumber");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))

                        .when()
                        .get("/transaction/balance/"+agentPhoneNumber)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();

        String message = response.get("message");
        setMessage(message);
    }

    public void callingCustomerBalanceAPI() throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        String customerPhoneNumber=prop.getProperty("customerPhoneNumber");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))

                        .when()
                        .get("/transaction/balance/"+customerPhoneNumber)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();

        String message = response.get("message");
        setMessage(message);
    }

    public void callingCashOutAPI(int amount) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        String agentPhoneNumber=prop.getProperty("agentPhoneNumber");
        String customerPhoneNumber=prop.getProperty("customerPhoneNumber");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                        .body("{\n" +
                                "    \"from_account\":\""+customerPhoneNumber+"\",\n" +
                                "    \"to_account\":\""+agentPhoneNumber+"\",\n" +
                                "    \"amount\":"+amount+"\n" +
                                "}")
                        .when()
                        .post("/transaction/withdraw")
                        .then()
                        .extract().response();

        JsonPath response = res.jsonPath();
        response.prettyPrint();

        String message = response.get("message");
        setMessage(message);
    }


}
