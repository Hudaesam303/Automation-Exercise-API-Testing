package Full_User_Exeperince;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import Full_User_Exeperince.ConfigReader;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Automation Exercise API")
@Feature("Negative Login")
public class Negative_Login {
   // String email="hodaesam00@gmail.com";
   // String password="01027252016";
    String baseUrl=ConfigReader.get("base.url");

    @Test(priority = 1)
    @Description("Login with valid registered email and password - expect 200 User exists")    public void validLogin_TC01(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("email",ConfigReader.get("user.valid.email"))
                        .formParam("password",ConfigReader.get("user.valid.password"))
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("TC01_Response : "+ response.asString());
    }

    @Test(priority = 2)
    @Description("Login with missing email field - expect 400 Bad request")
    public void invalidLoginMissingEmail_TC02(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("missingEmail",ConfigReader.get("user.missingEmail"))
                        .formParam("password",ConfigReader.get("user.valid.password"))
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("TC02_Response : " +response.asString());
        assert response.asString().contains("400") : "Expected 400 but got: " + response.asString();
    }

    @Test(priority = 3)
    @Description("Login with invalid email - expect 400 Bad request")
    public void invalidLoginWithInvalidEmail_TC03(){
        Response response =
                given()
                        .baseUri(baseUrl)
                        .formParam("InvalidEmail",ConfigReader.get("user.InvalidEmail"))
                        .formParam("password",ConfigReader.get("user.password"))
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("TC03_Response : " +response.asString());
        assert response.asString().contains("400") : "Expected 400 but got: " + response.asString();


    }

    @Test(priority = 4)
    @Description("Login with wrong password - expect 400 Bad request")
    public void wrongPasswordLogin_TC04(){

        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("validEmail",ConfigReader.get("user.valid.email"))
                        .formParam("InvalidPassword",ConfigReader.get("user.InvalidPassword"))
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("TC04_Response : "+ response.asString());
        assert response.asString().contains("400") : "Expected 400 but got : " + response.asString();
    }

    @Test(priority = 5)
    @Description("Login with missing password field - expect 400 Bad request")
    public void missingPasswordLogin_TC05(){

        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("validEmail",ConfigReader.get("user.valid.email"))
                        .formParam("InvalidPassword",ConfigReader.get("user.MissingPassword"))
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("TC05_Response : "+ response.asString());
        assert response.asString().contains("400") : "Expected 400 but got : " + response.asString();
    }

    @Test(priority = 6)
    @Description("Login with valid email containing space - expect 400 Bad request")
    public void spaceInEmailLogin_TC06(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("InvalidEmail",ConfigReader.get("user.spaces.email"))
                        .formParam("Password",ConfigReader.get("user.password"))
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();
        System.out.println("TC06_Response : "+ response.asString());
        assert response.asString().contains("400") : "Expected 400 but got : " + response.asString();

        System.out.println("__________________________________________________________________________________________");


    }
}
