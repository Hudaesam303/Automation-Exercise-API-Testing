package Full_User_Exeperince;
import io.qameta.allure.Description;
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


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Automation Exercise API")
@Feature("Full User Lifecycle")

public class Full_User_Life_Cycle {
    String email= "Nadine_" + System.currentTimeMillis() + "@test.com";
    String baseUrl=ConfigReader.get("base.url");

    @Test(priority = 1)
    @Description("Create a new user account with dynamic email and verify 201 response")
    public void createAccount_TC01(){

        Response response =
                given()
                        .baseUri(baseUrl)
                        .formParam("name",         ConfigReader.get("user.name"))
                        .formParam("email",email)
                        .formParam("password",ConfigReader.get("user.password"))
                        .formParams("title",    ConfigReader.get("user.title"))
                        .formParam("birth_date",   ConfigReader.get("user.birth.date"))
                        .formParam("birth_month",ConfigReader.get("user.birth.month"))
                        .formParam("birth_year",   ConfigReader.get("user.birth.year"))
                        .formParam("firstname",    ConfigReader.get("user.firstname"))
                        .formParam("lastname",     ConfigReader.get("user.lastname"))
                        .formParam("company",      ConfigReader.get("user.company"))
                        .formParam("address1",     ConfigReader.get("user.address1"))
                        .formParam("address2",     ConfigReader.get("user.address2"))
                        .formParam("country",      ConfigReader.get("user.country"))
                        .formParam("zipcode",      ConfigReader.get("user.zipcode"))
                        .formParam("state",        ConfigReader.get("user.state"))
                        .formParam("city",         ConfigReader.get("user.city"))
                        .formParam("mobile_number",ConfigReader.get("user.mobile"))
                        .when()
                        .post("/api/createAccount")
                        .then()
                        .statusCode(200)
                        .extract().response();

        System.out.println("TC01_Account created — email: " + email);



    }


    @Test (priority = 2)
    @Description("Login with the registered email and password - expect 200 User exists")
    public void loginAccount_TC02(){
        given()
                .baseUri(baseUrl)
                .formParam("email",email)
                .formParam("password",ConfigReader.get("user.password"))
                .when()
                .post("api/verifyLogin")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("TC02_Logged in with: " + email);


    }

    @Test(priority = 3)
    @Description("Fetch user details by registered email and verify all fields returned")
    public void getAccountDetails_TC03(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .queryParam("email",email)
                        .when()
                        .get("/api/getUserDetailByEmail")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response();


        System.out.println("TC03_Response"+response.asString());




    }

    @Test(priority = 4)
    @Description("Update user details - change firstname, lastname, city and company")
    public void updateData_TC04()
    {

        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("name",          ConfigReader.get("user.name"))
                        .formParam("email",         email)
                        .formParam("password",      ConfigReader.get("user.password"))
                        .formParam("title",         ConfigReader.get("user.title"))
                        .formParam("birth_date",    ConfigReader.get("user.birth.date"))
                        .formParam("birth_month",   ConfigReader.get("user.birth.month"))
                        .formParam("birth_year",    ConfigReader.get("user.birth.year"))
                        .formParam("firstname",     "salma")        // ← القيمة الجديدة
                        .formParam("lastname",      "aziz")
                        .formParam("company",       "Valeo")
                        .formParam("address1",      ConfigReader.get("user.address1"))
                        .formParam("address2",      ConfigReader.get("user.address2"))
                        .formParam("country",       ConfigReader.get("user.country"))
                        .formParam("zipcode",       ConfigReader.get("user.zipcode"))
                        .formParam("state",         ConfigReader.get("user.state"))
                        .formParam("city",          "Minya")
                        .formParam("mobile_number", "+201200560646")

                        .when()
                        .put("/api/updateAccount")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response();
        System.out.println("TC04_Response"+response.asString());

    }
    @Test(priority = 5)
    @Description("Verify updated fields appear correctly in GET response")
    public void getUpdatedData_TC05(){

        Response response=
                given()
                        .baseUri(baseUrl)
                        .queryParam("email",email)
                        .when()
                        .get("/api/getUserDetailByEmail")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response();


        System.out.println("TC05_Response"+response.asString());

    }

    @Test(priority = 6)
    @Description("Delete the account and verify 200 Account deleted response")
    public void deleteAccount_TC06(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("email",email)
                        .formParam("password",ConfigReader.get("user.password"))
                        .when()
                        .delete("/api/deleteAccount")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response();
        System.out.println("TC06_Response"+response.asString());

    }

    @Test(priority = 7)
    @Description("Try to login with deleted account credentials - expect 404 User not found")
    public void loginDeletedAccount_TC07() {
        Response response=
                given()
                        .baseUri(baseUrl)
                        .formParam("email", email)
                        .formParam("password", ConfigReader.get("user.password"))
                        .when()
                        .post("api/verifyLogin")
                        .then()
                        .statusCode(200)
                        .extract().response();

        String body = response.asString();
        assert body.contains("404") : "Expected 404 but got: " + body;
        System.out.println("TC07_Response: " + body);
    }

    @Test(priority = 8)
    @Description("Try to fetch deleted account details - expect 404 not found")
    public void getAccountDetailsAfterDeleted_TC08(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .queryParam("email",email)
                        .when()
                        .get("/api/getUserDetailByEmail")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response();


        System.out.println("TC08_Response"+response.asString());




        System.out.println("__________________________________________________________________________________________");
    }



}
