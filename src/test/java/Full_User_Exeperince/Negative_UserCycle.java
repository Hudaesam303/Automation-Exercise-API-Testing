package Full_User_Exeperince;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Automation Exercise API")
@Feature("Negative User Cycle")
public class Negative_UserCycle {
    String email= "Nadine_" + System.currentTimeMillis() + "@test.com";
    String baseUrl=ConfigReader.get("base.url");

    @Test(priority = 1)
    @Description("Create a new user account with dynamic email - expect 201 User created")
    public void createAccount() {
        Response response =
                given()
                        .baseUri(baseUrl)
                        .formParam("name", ConfigReader.get("user.name"))
                        .formParam("email", email)
                        .formParam("password",ConfigReader.get("user.password"))
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

    @Test(priority = 2)
    @Description("Delete the created account and verify 200 Account deleted")
    public void deleteAccount_TC02(){
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
        System.out.println("TC02_Response"+response.asString());

    }

    @Test(priority = 3)
    @Description("Try to update deleted account - expect 404 User not found")
    public void updateDeletedData_TC03()
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
                        .formParam("firstname",     "salma")
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
        System.out.println("TC03_Response: " + response.asString());
        assert response.asString().contains("404") : "Expected 404 but got: " + response.asString();


    }

    @Test(priority = 4)
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
        System.out.println("TC04_Response: " + response.asString());
        assert response.asString().contains("404") : "Expected 404 but got: " + response.asString();
    }

    @Test(priority = 5)
    @Description("Try to login into deleted account - expect 404 User not found")
    public void loginIntoDeletedAccount_05(){
        Response response=
                given()
                        .baseUri(baseUrl)
                        .queryParam("email",email)
                        .when()
                        .post("/api/getUserDetailByEmail")
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response();


        System.out.println("TC05_Response"+response.asString());
    }


    @Test(priority = 6)
    @Description("Try to delete already deleted account - expect 404 not found")
    public void deleteAccountAlreadyDeleted(){
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
        System.out.println("__________________________________________________________________________________________");

    }


}
